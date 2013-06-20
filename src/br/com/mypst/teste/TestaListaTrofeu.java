package br.com.mypst.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Trofeu;

public class TestaListaTrofeu {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Trofeu> dao = new DAO<>(em, Trofeu.class);
		
		List<Trofeu> trofeus = dao.lista();
		
		for (Trofeu trofeu : trofeus) {
			System.out.println("Nome: " + trofeu.getNome() + " | Descricão: " + trofeu.getDescricao());
			
		}
	}

}
