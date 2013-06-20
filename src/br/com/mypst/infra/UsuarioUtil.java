package br.com.mypst.infra;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.mypst.crawler.Ranking;
import br.com.mypst.crawler.Trophy;
import br.com.mypst.crawler.User;
import br.com.mypst.modelo.Usuario;

public class UsuarioUtil {

	private Usuario usuario = new Usuario();
	private User user = new User();

	public Usuario getUsuario(String psnid) throws IOException {

		try {
			URL url = new URL("http://mypst.com.br/rank/" + psnid.trim()
					+ "/xml/");
			XStream stream = new XStream(new DomDriver());

			stream.alias("user", User.class);
			stream.alias("trophy", Trophy.class);
			stream.alias("ranking", Ranking.class);
			stream.alias("error", User.class);

			stream.omitField(User.class, "ranking");

			user = (User) stream.fromXML(url.openStream());

			usuario.setNome(user.getPsn_id());
			usuario.setAvatar(user.getAvatar());
			usuario.setFrase(user.getCard_phrase());
			// usuario.setPlus(user.getp)
			usuario.setLevel(user.getLevel());
			usuario.setAvatarGrande(user.getAvatar_big());
			usuario.setPorcentagem(user.getPerc_done());
			usuario.setDataRegistro(new DataUtil().getCalendar(user
					.getRegister_date()));

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return usuario;

	}

}
