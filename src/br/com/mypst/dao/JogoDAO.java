package br.com.mypst.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Trofeu;

public class JogoDAO {

	private EntityManager em;

	private final DAO<Jogo> dao;

	public JogoDAO(EntityManager em) {
		dao = new DAO<Jogo>(em, Jogo.class);
		this.em = em;
	}

	public void adiciona(Jogo t) {
		List<Jogo> jogos = listaPorIdSony(t.getIdSony());

		if (jogos.isEmpty()) {

			for (Trofeu trofeu : t.getTrofeus()) {
				new TrofeuDAO(em).adiciona(trofeu);
			}

			dao.adiciona(t);
		}
	}

	public Jogo busca(Long id) {
		return dao.busca(id);
	}

	public List<Jogo> lista() {
		return dao.lista();
	}

	public void altera(Jogo jogo) {
		Jogo j = buscaPorIdSony(jogo.getIdSony());

		if (j.getId() != null) {
			// System.out.println(j.getId());

			jogo.setId(j.getId());

			dao.altera(jogo);

			// System.out.println(jogo.getTrofeus().size());

		} else {
			adiciona(jogo);
		}

	}

	public List<Jogo> listaPorIdSony(String idSony) {
		CriteriaBuilder busca = em.getCriteriaBuilder();
		CriteriaQuery<Jogo> criteria = busca.createQuery(Jogo.class);
		Root<Jogo> alias = criteria.from(Jogo.class);
		criteria.where(busca.equal(alias.<Jogo> get("idSony"), idSony));

		return em.createQuery(criteria).getResultList();

	}

	public Jogo buscaPorIdSony(String idSony) {

		CriteriaBuilder busca = em.getCriteriaBuilder();
		CriteriaQuery<Jogo> criteria = busca.createQuery(Jogo.class);
		Root<Jogo> alias = criteria.from(Jogo.class);
		criteria.where(busca.equal(alias.<Jogo> get("idSony"), idSony));

		return em.createQuery(criteria).getSingleResult();

	}

	public void alteraOuAdiciona(Jogo jogo) {
		if (jogo.getId() == null) {
			adiciona(jogo);
		} else {
			altera(jogo);
		}
	}

	public boolean existe(String idSony) {
		Query query = em.createQuery("from jogo where idSony = :idSony")
				.setParameter("idSony", idSony);
		boolean encontrado = !query.getResultList().isEmpty();
		return encontrado;
	}

}
