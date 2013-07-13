package br.com.mypst.teste;

import br.com.mypst.modelo.Trofeu;

public class TestaTrofeu {
	
	public static void main(String[] args) {
		Trofeu trofeu = new Trofeu();
		
		trofeu.setTipo("BRONZE");
		trofeu.setTipo(10);
		
		System.out.println(trofeu.getTipo());
		
	}

}
