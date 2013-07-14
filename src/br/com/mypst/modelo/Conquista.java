package br.com.mypst.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.mypst.infra.ConquistaPK;

@Entity
@IdClass(ConquistaPK.class)
public class Conquista {

	
	@ManyToOne
	@Id
	private Usuario usuario;
	
	@ManyToOne
	@Id
	private Trofeu trofeu;
	
	@Column(name="dataConquista")
	private Calendar dataConquista;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Trofeu getTrofeu() {
		return trofeu;
	}

	public void setTrofeu(Trofeu trofeu) {
		this.trofeu = trofeu;
	}

	public Calendar getDataConquista() {
		return dataConquista;
	}

	public void setDataConquista(Calendar dataConquista) {
		this.dataConquista = dataConquista;
	}

}
