package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Usuario;

public class TestaAlteraUser {

	public static void main(String[] args) {
		
		

		Usuario usuario = new Usuario();
		Usuario coxinha = new Usuario();
		
		
		coxinha.setNome("Coxinha");
		coxinha.setId(1L);
		coxinha.setAvatar("FDP");
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Usuario> dao = new DAO<>(em, Usuario.class);
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);

		em.getTransaction().begin();

		usuario = usuarioDAO.buscaPorNome("mangaloyds").get(0);
		
		usuario = coxinha;
		
		dao.altera(usuario);
				

		em.getTransaction().commit();
		em.close();

	}

}
