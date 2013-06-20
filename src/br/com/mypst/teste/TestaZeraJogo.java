package br.com.mypst.teste;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Usuario;

public class TestaZeraJogo {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
		
		Usuario usuario = usuarioDAO.busca(2L);
		
		usuario.setJogos(new ArrayList<Jogo>());
		
		em.getTransaction().commit();
		em.close();
		
	}

}
