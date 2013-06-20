package br.com.mypst.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.mypst.modelo.Usuario;

public class UsuarioDAO {
	private EntityManager em;
	private final DAO<Usuario> dao;

	public UsuarioDAO(EntityManager em) {
		dao = new DAO<Usuario>(em, Usuario.class);
		this.em = em;
	}

	public void adiciona(Usuario t) {
		UsuarioDAO dao2 = new UsuarioDAO(em);
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = dao2.buscaPorNome(t.getNome());

		if (usuarios.size() > 0) {

			for (Usuario usuario : usuarios) {
				t.setId(usuario.getId());
				// t.setJogos(usuario.getJogos());
				dao.altera(t);
			}

		} else {

			dao.adiciona(t);
		}
	}

	/*
	 * Metodo alterado para evitar o paremetro unique na anotation Collunm de
	 * entidade public void adiciona(Usuario t) { try { dao.adiciona(t);
	 * 
	 * } catch (PersistenceException e) { e.printStackTrace();
	 * 
	 * } }
	 */

	public List<Usuario> buscaPorNome(String nome) {
		CriteriaBuilder busca = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = busca.createQuery(Usuario.class);
		Root<Usuario> alias = criteria.from(Usuario.class);
		criteria.where(busca.equal(alias.<Usuario> get("nome"), nome));

		return em.createQuery(criteria).getResultList();

	}

	public void altera(Usuario t) {

		dao.altera(t);

	}

	public Usuario busca(Long id) {
		return dao.busca(id);
	}

	public List<Usuario> lista() {
		return dao.lista();
	}

	public boolean existe(Usuario usuario) {
		Query query = em.createQuery("from usuario where id = :id")
				.setParameter("id", usuario.getId());
		boolean encontrado = !query.getResultList().isEmpty();
		return encontrado;
	}

}