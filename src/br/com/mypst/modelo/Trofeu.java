package br.com.mypst.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "trofeu")
public class Trofeu {

	@Id
	private Long id;

	private int tipo; // 15- Bronze 30- Silver 90- Gold 180- Platinum

	private String nome;

	@Column(length = 1000)
	private String descricao;

	private String imagem;
	private String imagemGrande;
	private String dlc;

	@ManyToOne()
	private Jogo jogo;
	
	@OneToMany(mappedBy = "jogo")
	List<Conquista> conquistas;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public void setTipo(String tipo) {

		switch (tipo.toLowerCase().trim()) {
		case "bronze":
			this.tipo = 15;
			break;
		case "silver":
			this.tipo = 30;
			break;
		case "gold":
			this.tipo = 90;
			break;
		case "platinum":
			this.tipo = 180;
			break;
		default:
			this.tipo = 0;
			break;

		}

	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public String getDlc() {
		return dlc;
	}

	public void setDlc(String dlc) {
		this.dlc = dlc;
	}

	public List<Conquista> getConquistas() {
		return conquistas;
	}

	public void setConquistas(List<Conquista> conquistas) {
		this.conquistas = conquistas;
	}
	
	

}
