package br.com.mypst.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Conquista {

	@Id
	private Usuario usuario;
	@Id
	private Trofeu trofeu;
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
