package br.com.mypst.teste;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.Trophy;
import br.com.mypst.dao.DAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Trofeu;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaInclusaoTrofeu {
	public static void main(String[] args) throws IOException {
		
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Trofeu> dao = new DAO<>(em, Trofeu.class);
		DAO<Jogo> daoJogo = new DAO<>(em, Jogo.class);
		
		List<Jogo> jogos = daoJogo.lista();
		
		for (Jogo jogo : jogos) {
			
		
		URL url = new URL("http://mypst.com.br/jogos/"+ jogo.getIdSony() +"/xml/");

		XStream stream = new XStream(new DomDriver());
		stream.alias("game", Game.class);
		stream.alias("trophy", Trophy.class);

		// Pega o atributo id na tag trophy
		stream.useAttributeFor(Trophy.class, "id");

		Game game = (Game) stream.fromXML(url.openStream());

		em.getTransaction().begin();
		
		for (Trophy trofeu : game.getTrophys()) {
			Trofeu t = new Trofeu();
			t.setId(trofeu.getId());
			t.setNome(trofeu.getName());
			
			t.setTipo(trofeu.getType());
			t.setDescricao(trofeu.getDesc());
			t.setImagem(trofeu.getPic());
			t.setImagemGrande(trofeu.getPic_big());
			t.setDlc(trofeu.getDlc());

			dao.adiciona(t);
		

		}
		System.out.println("Adicionado o jogo " + jogo.getNome() + "\n");
		em.getTransaction().commit();
		}
		em.close();
	}
	
	

}
