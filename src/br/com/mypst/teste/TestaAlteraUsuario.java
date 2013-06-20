package br.com.mypst.teste;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.UsuarioUtil;
import br.com.mypst.modelo.Usuario;

public class TestaAlteraUsuario {

	public static void main(String[] args) throws IOException {
		EntityManager em = new JPAUtil().getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);

		// Usuario usuario = dao.busca(2L);
		List<Usuario> usuarios = dao.lista();

		for (Usuario u : usuarios) {
			em.getTransaction().begin();

			Usuario usuarioXml = new UsuarioUtil().getUsuario(u.getNome());

			usuarioXml.setId(u.getId());
			usuarioXml.setJogos(u.getJogos());

			System.out.println(u.getNome());
			dao.altera(usuarioXml);
			em.getTransaction().commit();
		}

		em.close();

	}

}
