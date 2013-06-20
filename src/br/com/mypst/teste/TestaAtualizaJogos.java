package br.com.mypst.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.JogoDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;
import br.com.mypst.modelo.Jogo;

public class TestaAtualizaJogos {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		JogoDAO jogoDAO = new JogoDAO(em);
		List<Jogo> jogos = jogoDAO.lista();
		em.getTransaction().begin();

		
		
		for (Jogo j : jogos) {
			System.out.println("Crawler do " + j.getNome() + " | " + j.getTrofeus().size());
			jogoDAO.altera(new JogoUtil().getJogo(j.getIdSony()));
			System.out.println(j.getNome() + " | " + j.getTrofeus().size());
		}
				
		em.getTransaction().commit();
		em.close();
	}

}
