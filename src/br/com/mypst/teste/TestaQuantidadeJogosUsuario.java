package br.com.mypst.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Usuario;

public class TestaQuantidadeJogosUsuario {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		
		List<Usuario> usuarios = dao.lista();
		
		for (Usuario usuario : usuarios) {
			System.out.println("Nome: " + usuario.getNome() + " | " + usuario.getJogos().size());
		}
	}

}
