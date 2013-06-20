package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.JogoDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Trofeu;

public class TestaJogoFactory {
	
	public static void main(String[] args) {
		Jogo jogo = new JogoUtil().getJogo("NPWR01478_00");
		
		System.out.println(jogo.getNome());
		
		for (Trofeu t : jogo.getTrofeus()) {
			System.out.println(t.getNome() + " - -" + t.getDescricao());
		}
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		JogoDAO dao = new JogoDAO(em);
		em.getTransaction().begin();
		
		dao.altera(jogo);
		
		em.getTransaction().commit();
		
		em.close();
		
		
		
	}

}
