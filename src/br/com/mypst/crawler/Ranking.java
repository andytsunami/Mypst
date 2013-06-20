package br.com.mypst.crawler;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Ranking {

	@XStreamImplicit(itemFieldName = "type")
	private List<String> type = new ArrayList<>();
	private List<Double> rank;
	private List<Double> best;

	public List<Double> getRank() {
		return rank;
	}

	public void setRank(List<Double> rank) {
		this.rank = rank;
	}

	public List<Double> getBest() {
		return best;
	}

	public void setBest(List<Double> best) {
		this.best = best;
	}

	private List<String> best_date;

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getBest_date() {
		return best_date;
	}

	public void setBest_date(List<String> best_date) {
		this.best_date = best_date;
	}

}
