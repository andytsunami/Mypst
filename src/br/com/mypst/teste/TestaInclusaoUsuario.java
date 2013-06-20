package br.com.mypst.teste;

import javax.persistence.EntityManager;

import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Usuario;

public class TestaInclusaoUsuario {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		Usuario usuario = new Usuario();
		usuario.setNome("Sou unico?");
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);

		em.getTransaction().begin();
		usuarioDAO.adiciona(usuario);
		System.out.println("Algo de diferente?");
		em.getTransaction().commit();
		em.close();

	}

}
