package br.com.mypst.teste;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.mypst.crawler.Trophy;
import br.com.mypst.crawler.User;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaUser {
	public static void main(String[] args) throws IOException {

		URL url = new URL("http://mypst.com.br/rank/Pockidrive/xml/");
		XStream stream2 = new XStream(new DomDriver());

		stream2.alias("trophy", Trophy.class);
		stream2.alias("user", User.class);
		stream2.omitField(User.class, "ranking");

		System.out.println("Usuario-------------");
		User user = (User) stream2.fromXML(url.openStream());
		System.out.println(user.toString());

		for (Trophy t : user.getLast_trophy()) {
			System.out.println(t.toString());
		}

	}

}
