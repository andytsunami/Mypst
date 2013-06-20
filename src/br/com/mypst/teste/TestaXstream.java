package br.com.mypst.teste;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import br.com.mypst.crawler.Achievement;
import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.Trophy;
import br.com.mypst.crawler.User;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaXstream {

	public static void main(String[] args) throws IOException {
		// Craula Jogo e trofeus
		URL url = new URL(
				"http://mypst.com.br/jogos/NPWR02652_00/xml/");

		XStream stream = new XStream(new DomDriver());
		stream.alias("game", Game.class);
		stream.alias("trophy", Trophy.class);
		
		
		//Pega o atributo id na tag trophy
		stream.useAttributeFor(Trophy.class,"id");

		Game game = (Game) stream.fromXML(url.openStream());
		System.out.println(game.toString());
		System.out.println(game.getTrophy_count().toString());
		System.out.println("Lista de trofeus: ");

		for (Trophy trofeu : game.getTrophys()) {
			System.out.println(trofeu.toString());
		}
		
		
		// Craula CADASTRO do usuario
		url = new URL("http://mypst.com.br/rank/mangaloyds/xml/");
		XStream stream2 =  new XStream(new DomDriver());
		
		stream2.alias("trophy", Trophy.class);
		stream2.alias("user", User.class);
		stream2.omitField(User.class, "ranking");
		
		
		System.out.println("Usuario-------------");
		User user = (User) stream2.fromXML(url.openStream());
		System.out.println(user.toString());
		
		for (Trophy t : user.getLast_trophy()) {
			System.out.println(t.toString());
		}
		
		
		//Craula trofeus de um jogo do usuario
		
		url = new URL("http://mypst.com.br/rank/mangaloyds/jogo/NPWR02972_00/xml/");
		
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
		
		//xml = xml.replace("<trophys>", "<achievement>\n<trophys>");
		//xml = xml.replace("</trophys>", "</trophys></achievement>");
		
		
		
		XStream stream3 = new XStream(new DomDriver());
		stream3.alias("achievement", Achievement.class);
		stream3.alias("trophy", Trophy.class);
				
		Achievement achievement = (Achievement) stream3.fromXML(xml);
		
		System.out.println(achievement.toString());
		
		
		
		

	}

}
