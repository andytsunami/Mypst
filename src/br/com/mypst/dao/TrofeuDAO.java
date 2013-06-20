package br.com.mypst.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.mypst.modelo.Trofeu;

public class TrofeuDAO {

	private EntityManager em;

	private final DAO<Trofeu> dao;

	public TrofeuDAO(EntityManager em) {
		dao = new DAO<Trofeu>(em, Trofeu.class);
		this.em = em;
	}

	public void altera(Trofeu trofeu) {
		dao.altera(trofeu);
	}

	public List<Trofeu> lista() {
		return dao.lista();
	}

	public void adiciona(Trofeu trofeu) {
		dao.adiciona(trofeu);
	}

	public Trofeu busca(Long id) {
		return dao.busca(id);
	}

}
