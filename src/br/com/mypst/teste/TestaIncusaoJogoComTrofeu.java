package br.com.mypst.teste;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.User;
import br.com.mypst.dao.DAO;
import br.com.mypst.dao.JogoDAO;
import br.com.mypst.dao.TrofeuDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Trofeu;
import br.com.mypst.modelo.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaIncusaoJogoComTrofeu {

	public static void main(String[] args) throws MalformedURLException {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Jogo> dao = new DAO<>(em, Jogo.class);
		JogoDAO jogoDao = new JogoDAO(em);
		TrofeuDAO trofeuDao = new TrofeuDAO(em);

		URL url = new URL("http://mypst.com.br/rank/ユーリ/jogos/xml/");
		XStream stream2 = new XStream(new DomDriver());

		stream2.alias("user", User.class);
		stream2.alias("game", Game.class);

		// File file = new File("TesteDoido.xml");

		String xml = "";

		try {
			Scanner arq = new Scanner(url.openStream());
			while (arq.hasNextLine()) {
				String linha = arq.nextLine() + "\n";
				xml += linha;
			}
			arq.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		xml = xml.replace("<games>", "<user>\n<games>");
		xml = xml.replace("</games>", "</games></user>");

		// User user = (User) stream2.fromXML(new File("meu.xml"));
		User user = (User) stream2.fromXML(xml);

		for (Game game : user.getGames()) {

			Jogo jogo = new JogoUtil().getJogo(game.getId());
			
			
			if (jogoDao.listaPorIdSony(jogo.getIdSony()).size() > 0) {
				System.out.println("Jogo " + jogo.getNome() + " já está na base!");
				em.getTransaction().begin();
				
				Jogo jogoBase = jogoDao.buscaPorIdSony(jogo.getIdSony());
				
				jogo.setId(jogoBase.getId());
				
				for (Trofeu tt : jogo.getTrofeus()) {
					tt.setJogo(jogo);
					trofeuDao.altera(tt);
				}
							
				jogoDao.altera(jogo);
				
				em.getTransaction().commit();
				
							
			}else{
				em.getTransaction().begin();
			
			

			jogoDao.adiciona(jogo);
			System.out.println("Adicionado " + jogo.getNome());
			

			for (Trofeu trofeu : jogo.getTrofeus()) {
				trofeu.setJogo(jogo);
				trofeuDao.adiciona(trofeu);
			}

			em.getTransaction().commit();
			
			}
		

		}
		em.close();

	}

}
