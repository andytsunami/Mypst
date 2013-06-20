package br.com.mypst.crawler;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
public class User {

	private Quantidade trophy_total;
	private String psn_id;
	private String avatar;
	private String avatar_big;
	private String register_date;
	private String card_phrase;
	private String last_update;
	private String error;

	private int level;
	private int perc_done;
	private List<Trophy> last_trophy;
	private List<Game> games = new ArrayList<>();


	@Override
	public String toString() {
		return "PSN: " + this.psn_id + "\nAvatar: " + this.avatar
				+ "\nData de registro: " + this.register_date
				+ "\nFrase do cartão: " + this.card_phrase
				+ "\nUltima alteração: " + this.last_update + "\nLevel: "
				+ this.level + "\nPorcentagem: " + this.perc_done;
	}

	public Quantidade getTrophy_total() {
		return trophy_total;
	}

	public void setTrophy_total(Quantidade trophy_total) {
		this.trophy_total = trophy_total;
	}

	public String getPsn_id() {
		return psn_id;
	}

	public void setPsn_id(String psn_id) {
		this.psn_id = psn_id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatar_big() {
		return avatar_big;
	}

	public void setAvatar_big(String avatar_big) {
		this.avatar_big = avatar_big;
	}

	public String getRegister_date() {
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	public String getCard_phrase() {
		return card_phrase;
	}

	public void setCard_phrase(String card_phrase) {
		this.card_phrase = card_phrase;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPerc_done() {
		return perc_done;
	}

	public void setPerc_done(int perc_done) {
		this.perc_done = perc_done;
	}

	public List<Trophy> getLast_trophy() {
		return last_trophy;
	}

	public void setLast_trophy(List<Trophy> last_trophy) {
		this.last_trophy = last_trophy;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
