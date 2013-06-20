package br.com.mypst.teste;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.Trophy;
import br.com.mypst.crawler.User;
import br.com.mypst.dao.DAO;
import br.com.mypst.dao.JogoDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Trofeu;
import br.com.mypst.modelo.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persiste {

	public void gravaJogosDeUmUsuario(Usuario usuario) throws IOException {
		System.out.println("Iniciando a gravacao dos jogos do usuario : "
				+ usuario.getNome() + " | " + new Date().toString());
		EntityManager em = new JPAUtil().getEntityManager();
		URL url = new URL("http://mypst.com.br/rank/" + usuario.getNome()
				+ "/jogos/xml/");
		XStream stream2 = new XStream(new DomDriver());

		stream2.alias("user", User.class);
		stream2.alias("game", Game.class);

		// File file = new File("TesteDoido.xml");

		String xml = "";

		try {
			Scanner arq = new Scanner(url.openStream());
			while (arq.hasNextLine()) {
				String linha = arq.nextLine() + "\n";
				xml += linha;
			}
			arq.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		xml = xml.replace("<games>", "<user>\n<games>");
		xml = xml.replace("</games>", "</games></user>");

		// User user = (User) stream2.fromXML(new File("meu.xml"));
		User user = (User) stream2.fromXML(xml);
		em.getTransaction().begin();

		for (Game game : user.getGames()) {
			JogoDAO dao = new JogoDAO(em);

			Jogo jogo = new Jogo();
			jogo.setIdSony(game.getId());
			jogo.setNome(game.getName());
			jogo.setImagem(game.getP());
			jogo.setImagemGrande(game.getPic_big());
			jogo.setDataDeCadastro(Calendar.getInstance());

			dao.adiciona(jogo);
			gravaTrofeusDeUmjogo(jogo);


			System.out.println(game.getId() + " - " + game.getName());
		}
		em.getTransaction().commit();
		em.close();
		System.out.println("Fim da gravacao dos jogos do usuario : "
				+ usuario.getNome() + " | " + new Date().toString());

	}

	public void gravaTrofeusDeUmjogo(Jogo jogo) throws IOException {
		System.out.println("Iniciando a gravacao dos trofeus do jogo : "
				+ jogo.getNome() + " | " + new Date().toString());

		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Trofeu> dao = new DAO<>(em, Trofeu.class);

		URL url = new URL("http://mypst.com.br/jogos/" + jogo.getIdSony()
				+ "/xml/");

		XStream stream = new XStream(new DomDriver());
		stream.alias("game", Game.class);
		stream.alias("trophy", Trophy.class);

		// Pega o atributo id na tag trophy
		stream.useAttributeFor(Trophy.class, "id");

		Game game = (Game) stream.fromXML(url.openStream());

		em.getTransaction().begin();

		for (Trophy trofeu : game.getTrophys()) {
			Trofeu t = new Trofeu();
			t.setId(trofeu.getId());
			t.setNome(trofeu.getName());

			t.setTipo(trofeu.getType());
			t.setDescricao(trofeu.getDesc());
			t.setImagem(trofeu.getPic());
			t.setImagemGrande(trofeu.getPic_big());
			t.setDlc(trofeu.getDlc());
			
			t.setJogo(jogo);

			System.out.println("Adicionando o trofeu: " + trofeu.getName()
					+ " | " + new Date().toString());

			Long id = trofeu.getId();

			try {
				dao.altera(t);
			} catch (Exception e) {
				// TODO: handle exception
				System.out
						.println("Trofeu já existe!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}

		}
		System.out.println("Fim da gravacao dos trofeus do jogo : "
				+ jogo.getNome() + " | " + new Date().toString());
		em.getTransaction().commit();
		em.close();

	}

}
