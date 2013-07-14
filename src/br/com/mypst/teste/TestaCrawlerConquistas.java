package br.com.mypst.teste;

import java.io.IOException;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.dao.JogoDAO;
import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;
import br.com.mypst.modelo.Conquista;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Usuario;

public class TestaCrawlerConquistas {
	
	public static void main(String[] args) throws IOException {
		EntityManager em = new JPAUtil().getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
		JogoDAO jogoDAO = new JogoDAO(em);
		DAO<Conquista> conquistaDAO = new DAO<>(em, Conquista.class);
		em.getTransaction().begin();
		
		Usuario usuario = usuarioDAO.busca(2L);
		
		Jogo jogo = jogoDAO.buscaPorIdSony("NPWR03484_00");
		System.out.println(jogo.getNome());
		
		for (Conquista conquista : new JogoUtil().getConquistas(usuario, jogo,em)) {
			System.out.println(conquista.getTrofeu().getId());
			System.out.println(conquista.getTrofeu().getNome() + " - " + conquista.getDataConquista().toString());
			conquistaDAO.adiciona(conquista);
			
		}
		em.getTransaction().commit();
		em.close();
		
		
		
		
		
	}

}
