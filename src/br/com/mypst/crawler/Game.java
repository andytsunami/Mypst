package br.com.mypst.crawler;

import java.util.List;


public class Game {
	private String id;
	private String pic;
	private String p;
	private String pic_big;
	private String perc_done;
	private String name;
	private String last_trophy;
	
	private Quantidade trophy_count;
	private List<Trophy> trophys;	

	@Override
	public String toString() {
		return "Jogo: " + this.name + "\nID: " + this.id + " \nimagem: "
				+ this.pic.replace(";", ":") + " \nimagem Grande: "
				+ this.pic_big + "\nTotal de trofeus: " + trophy_count.total();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic() {
		return pic.replace(";", ":");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Quantidade getTrophy_count() {
		return trophy_count;
	}

	public void setTrophy_count(Quantidade trophy_count) {
		this.trophy_count = trophy_count;
	}

	
	
	public void listaTroufeus(){
		for (Trophy t : this.getTrophys()) {
			t.toString();
		}
		
	}

	public List<Trophy> getTrophys() {
		return trophys;
	}

	public void setTrophys(List<Trophy> trophys) {
		this.trophys = trophys;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}




}
