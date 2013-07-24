package br.com.mypst.crawler;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("trophy")
public class Trophy {

	private Long id;

	// Redundante para o XStream conseguir serializar

	private String name;
	private String desc;
	private String type;
	private String pic;
	private String pic_big;
	private String dlc;
	private String pdm;
	private String date;
	private String game_id;
	private String game_name;
	private String game_pic;
	private String game_pic_big;
	private String trophy_pic;
	private String trophy_pic_big;

	public String getGame_pic() {
		return game_pic;
	}

	public void setGame_pic(String game_pic) {
		this.game_pic = game_pic;
	}

	public String getGame_pic_big() {
		return game_pic_big;
	}

	public void setGame_pic_big(String game_pic_big) {
		this.game_pic_big = game_pic_big;
	}

	@Override
	public String toString() {
		return "-------------------------------\n" + "Trofeu: " + this.id
				+ "\nNome: " + this.name.trim() + "\nDescrição: "
				+ this.desc.trim() + "\nTipo: " + this.type.trim()
				+ "\nImagem: " + this.pic.trim() + "\nImagem Grande: "
				+ this.pic_big + "\nDLC: " + this.dlc
				+ "\nPontos de dificuldade:" + this.pdm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic_big() {
		return pic_big;
	}

	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}

	public String getDlc() {
		if (this.dlc.equals("None")) {
			return null;
		} else {
			return dlc;
		}
	}

	public void setDlc(String dlc) {
		this.dlc = dlc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPdm() {
		return pdm;
	}

	public void setPdm(String pdm) {
		this.pdm = pdm;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

}
