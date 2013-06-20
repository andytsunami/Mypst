package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Jogo;

public class TestaListaJogos {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Jogo> dao = new DAO<>(em, Jogo.class);
		
		for (Jogo jogo : dao.lista()) {
			System.out.println(jogo.getIdSony() + " | " + jogo.getNome());
		}
		
	}

}
