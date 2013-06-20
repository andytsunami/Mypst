package br.com.mypst.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Usuario;
import br.com.mypst.dao.UsuarioDAO;

public class TestaBuscaNome {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();

		UsuarioDAO dao = new UsuarioDAO(em);

		List<Usuario> usuarios = dao.buscaPorNome("pia");

		for (Usuario usuario : usuarios) {
			System.out.println("Nome: " + usuario.getNome());
		}

		System.out.println("Quantidade: " + usuarios.size());

	}
}
