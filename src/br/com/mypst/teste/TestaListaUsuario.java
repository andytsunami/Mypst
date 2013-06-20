package br.com.mypst.teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Usuario;

public class TestaListaUsuario {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		DAO<Usuario> dao = new DAO<>(em, Usuario.class);

		List<Usuario> usuarios = new ArrayList<>();

		usuarios = dao.lista();

		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNome() + " - " + usuario.getLevel());
		}

	}

}
