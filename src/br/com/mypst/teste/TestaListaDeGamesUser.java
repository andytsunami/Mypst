package br.com.mypst.teste;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.User;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaListaDeGamesUser {

	public static void main(String[] args) throws IOException {

		URL url = new URL("http://mypst.com.br/rank/mangaloyds/jogos/xml/");
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


		for (Game game : user.getGames()) {
			System.out.println(game.getId() + " - " + game.getName());
		}
		System.out.println("Total de jogos: " + user.getGames().size());

	}

}
