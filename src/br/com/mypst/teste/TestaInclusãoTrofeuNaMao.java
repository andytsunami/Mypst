package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Trofeu;

public class TestaInclusãoTrofeuNaMao {
	
	public static void main(String[] args) {
		String lala = "キャラのレベルをMAXにした";
		
		Trofeu trofeu = new Trofeu();
		
		trofeu.setId(666L);
		trofeu.setNome("最強の男たち ");
		trofeu.setDescricao("キャラのレベルをMAXにした");
		
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Trofeu> dao = new DAO<>(em, Trofeu.class);
		
		em.getTransaction().begin();
		
		dao.altera(trofeu);
		
		em.getTransaction().commit();
		em.close();
		
		System.out.println(lala.toUpperCase());
		System.out.println(lala.toLowerCase());
		
	}

}
