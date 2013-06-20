package br.com.mypst.teste;

import java.io.IOException;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.Trophy;
import br.com.mypst.infra.GeraGame;

public class TestaGeraGame {
	
	public static void main(String[] args) {
		GeraGame geraGame = new GeraGame("NPWR03297_00");
		Game game = new Game();
		
		
		try {
			game = geraGame.getGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(game.toString());
		
		for (Trophy t : game.getTrophys()) {
			System.out.println(t.toString());
		}
		
		
	}
	
	

}
