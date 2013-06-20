package br.com.mypst.infra;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.mypst.dao.JogoDAO;
import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.modelo.Conquista;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Usuario;

public class TestaCrawlerConquistas {
	
	public static void main(String[] args) throws IOException {
		EntityManager em = new JPAUtil().getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
		JogoDAO jogoDAO = new JogoDAO(em);
		
		Usuario usuario = usuarioDAO.busca(2L);
		
		Jogo jogo = jogoDAO.buscaPorIdSony("NPWR02972_00");
		
		for (Conquista conquista : new JogoUtil().getConquistas(usuario, jogo)) {
			System.out.println(conquista.getTrofeu().getNome() + " - " + conquista.getDataConquista().toString());
			
		}
		
		em.close();
		
		
		
		
		
	}

}
