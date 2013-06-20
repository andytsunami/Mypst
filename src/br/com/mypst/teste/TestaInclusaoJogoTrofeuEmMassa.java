package br.com.mypst.teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Usuario;

public class TestaInclusaoJogoTrofeuEmMassa {

	public static void main(String[] args) throws IOException {

		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Usuario> usuarioDao = new DAO<>(em, Usuario.class);

		List<Usuario> usuarios = new ArrayList<>();
		usuarios = usuarioDao.lista();

		System.out.println("Inicio da brincadeira: " + new Date().toString());

		for (Usuario usuario : usuarios) {
			new Persiste().gravaJogosDeUmUsuario(usuario);
		}

		System.out.println("Será que funcionou? " + new Date().toString());

	}

}
