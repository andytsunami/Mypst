package br.com.mypst.modelo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="jogo")
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String idSony;
	private String nome;
	private String imagem;
	private String imagemGrande;

	@OneToMany(mappedBy = "jogo",cascade = {CascadeType.ALL})
	private List<Trofeu> trofeus;
	
	
	@ManyToMany(mappedBy = "jogos")
	private List<Usuario> usuarios;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDeCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdSony() {
		return idSony;
	}

	public void setIdSony(String idSony) {
		this.idSony = idSony;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagemGrande() {
		return imagemGrande;
	}

	public void setImagemGrande(String imagemGrande) {
		this.imagemGrande = imagemGrande;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public List<Trofeu> getTrofeus() {
		return trofeus;
	}

	public void setTrofeus(List<Trofeu> trofeus) {
		for (Trofeu trofeu : trofeus) {
			trofeu.setJogo(this);
		}
		this.trofeus = trofeus;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	

}
