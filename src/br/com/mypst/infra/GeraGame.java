package br.com.mypst.infra;

import java.io.IOException;
import java.net.URL;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.Trophy;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeraGame {
	
	private String id;
	

	public GeraGame(String id) {
		super();
		this.id = id;
	}


	public Game getGame() throws IOException{
		URL url = new URL("http://mypst.com.br/jogos/"+this.id+"/xml/");
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("game", Game.class);
		stream.alias("trophy", Trophy.class);
		
		//Pega o atributo id na tag trophy
		stream.useAttributeFor(Trophy.class,"id");

		Game game = (Game) stream.fromXML(url.openStream());
		
		return game;	
		
	}
	
	 

}
