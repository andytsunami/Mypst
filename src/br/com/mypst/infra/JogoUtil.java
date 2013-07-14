package br.com.mypst.infra;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.mypst.crawler.Achievement;
import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.Trophy;
import br.com.mypst.dao.TrofeuDAO;
import br.com.mypst.modelo.Conquista;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Trofeu;
import br.com.mypst.modelo.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class JogoUtil {
	
	
	public Jogo getJogo(String idSony){
		
		Game game = new Game();
		List<Trofeu> trofeus = new ArrayList<>();
		URL url = null;
		Jogo jogo = new Jogo();
		
		try {
			url = new URL(
					"http://mypst.com.br/jogos/"+idSony+"/xml/");
		} catch (MalformedURLException e) {
			System.out.println("Jogo não encontrado");
		}

		XStream stream = new XStream(new DomDriver());
		stream.alias("game", Game.class);
		stream.alias("trophy", Trophy.class);
		
		//Pega o atributo id na tag trophy
		stream.useAttributeFor(Trophy.class,"id");

		try {
			game = (Game) stream.fromXML(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jogo.setIdSony(game.getId());
		jogo.setNome(game.getName());
		jogo.setImagem(game.getPic());
		jogo.setImagemGrande(game.getPic_big());
		jogo.setDataDeCadastro(Calendar.getInstance());
		
		//Jogo e trofeu montados juntos pois o mesmo XML traz os dois
		//TODO Arrancar esse trecho de codigo pois ele esta repetindo
		
		for (Trophy trofeu : game.getTrophys()) {
			Trofeu t = new Trofeu();
			t.setId(trofeu.getId());
			t.setNome(trofeu.getName());
			
			t.setTipo(trofeu.getType());
			t.setDescricao(trofeu.getDesc());
			t.setImagem(trofeu.getPic());
			t.setImagemGrande(trofeu.getPic_big());
			t.setDlc(trofeu.getDlc());
			
			trofeus.add(t);

		}
		
		jogo.setTrofeus(trofeus);
		
		
	return jogo;	
		
	}
	
	public List<Conquista> getConquistas(Usuario usuario, Jogo jogo, EntityManager em) throws IOException{
		
				
URL url = new URL("http://mypst.com.br/rank/" + usuario.getNome() + "/jogo/" + jogo.getIdSony() + "/xml/");
		
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
		xml = xml.replace("<trophys>", "<achievement>\n");
		xml = xml.replace("</trophys>", "</achievement>");
				
		
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("achievement", Achievement.class);
		stream.alias("trophy", Trophy.class);
		stream.useAttributeFor(Trophy.class,"id");
				
		Achievement achievement = (Achievement) stream.fromXML(xml);
		List<Conquista> conquistas = new ArrayList<>();
		System.out.println(achievement.toString());
		for (Trophy trofeu : achievement.getHave()) {
			Conquista conquista = new Conquista();
			
			TrofeuDAO dao = new TrofeuDAO(em);
			
			Trofeu t = dao.busca(trofeu.getId());
			/*t.setId(trofeu.getId());
			t.setNome(trofeu.getName());
			
			t.setTipo(trofeu.getType());
			t.setDescricao(trofeu.getDesc());
			t.setImagem(trofeu.getPic());
			t.setImagemGrande(trofeu.getPic_big());
			//t.setDlc(trofeu.getDlc());
			*/
			conquista.setUsuario(usuario);
			conquista.setTrofeu(t);
			conquista.setDataConquista(new DataUtil().getCalendar(trofeu.getDate()));
			
			conquistas.add(conquista);
						
		
		}
			
		return conquistas;
		
		
		
	}
	
	

}
