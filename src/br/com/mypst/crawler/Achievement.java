package br.com.mypst.crawler;

import java.util.List;

public class Achievement {

	List<Trophy> have;
	List<Trophy> not_have;

	public List<Trophy> getHave() {
		return have;
	}

	public void setHave(List<Trophy> have) {
		this.have = have;
	}

	public List<Trophy> getNot_have() {
		return not_have;
	}

	public void setNot_have(List<Trophy> not_have) {
		this.not_have = not_have;
	}

	@Override
	public String toString() {
		String retorno = "";
		for (Trophy trophy : getHave()) {
			retorno += trophy.getId() + " - " + trophy.getName() + " ------ " + trophy.getDate() + " \n";
		}
		
		return retorno += "Total conquistado " + getHave().size();
	}

}
