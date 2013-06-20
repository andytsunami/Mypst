package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.JogoDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;

public class TestaAdicionaJogo {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		JogoDAO dao = new JogoDAO(em);
		
		em.getTransaction().begin();
		
		dao.adiciona(new JogoUtil().getJogo("NPWR02480_00"));
		
		em.getTransaction().commit();
		em.close();
		
	}

}
