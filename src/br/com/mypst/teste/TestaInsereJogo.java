package br.com.mypst.teste;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.User;
import br.com.mypst.dao.JogoDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Jogo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaInsereJogo {
	
	public static void main(String[] args) throws IOException {
		EntityManager em = new JPAUtil().getEntityManager();
		URL url = new URL("http://mypst.com.br/rank/destruidor_85/jogos/xml/");
		XStream stream2 = new XStream(new DomDriver());

		stream2.alias("user", User.class);
		stream2.alias("game", Game.class);

		//File file = new File("TesteDoido.xml");

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
		em.getTransaction().begin();


		for (Game game : user.getGames()) {
			JogoDAO dao = new JogoDAO(em);
			
			Jogo jogo = new Jogo();
			jogo.setIdSony(game.getId());
			jogo.setNome(game.getName());
			jogo.setImagem(game.getP());
			jogo.setImagemGrande(game.getPic_big());
			jogo.setDataDeCadastro(Calendar.getInstance());
			
			dao.adiciona(jogo);
			
			
			
			System.out.println(game.getId() + " - " + game.getName());
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Total de jogos: " + user.getGames().size());

	}

	}

