package br.com.mypst.crawler;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("trophy_count")
public class Quantidade {
	private int platinum;
	private int gold;
	private int silver;
	private int bronze;
	
	
	
	@Override
	public String toString() {
		return "Quantidade [platinum=" + platinum + ", gold=" + gold
				+ ", silver=" + silver + ", bronze=" + bronze + "]";
	}
	public int getPlatinum() {
		return platinum;
	}
	public void setPlatinum(int platinum) {
		this.platinum = platinum;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getBronze() {
		return bronze;
	}
	public void setBronze(int bronze) {
		this.bronze = bronze;
	}
	
	public int total(){
		return this.platinum + this.gold + this.silver + this.bronze;
	}
	
	
	

}
