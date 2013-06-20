package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.JogoDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Jogo;

public class TestaDonoDeJogo {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		JogoDAO dao = new JogoDAO(em);

		em.getTransaction().begin();
		for (Jogo jogo : dao.lista()) {

			System.out.println(jogo.getNome() + " | "
					+ jogo.getUsuarios().size());

		}

		em.close();
	}

}
