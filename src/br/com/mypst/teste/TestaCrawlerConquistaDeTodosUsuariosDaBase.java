package br.com.mypst.teste;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.dao.DAO;
import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;
import br.com.mypst.modelo.Conquista;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Usuario;

public class TestaCrawlerConquistaDeTodosUsuariosDaBase {
	
	public static void main(String[] args) throws IOException {
		
		EntityManager em = new JPAUtil().getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
		DAO<Conquista> conquistaDao = new DAO<>(em, Conquista.class);
		em.getTransaction().begin();
		
		for (Usuario usuario : usuarioDAO.lista()) {
			System.out.println("==========\n"+usuario.getNome()+"\n===========");
			List<Jogo> jogos = usuario.getJogos();
			
			for (Jogo jogo : jogos) {
				System.out.println(jogo.getNome());
				List<Conquista> conquistas = new JogoUtil().getConquistas(usuario, jogo, em);
				System.out.println(conquistas.size());
				for (Conquista conquista : conquistas) {
					System.out.println(conquista.getTrofeu().getId());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					System.out.println(conquista.getTrofeu().getNome() + " - " + sdf.format(conquista.getDataConquista().getTime()));
					conquistaDao.adiciona(conquista);
					
				}
			}
			
		}
		
		em.getTransaction().commit();
	}

}
