package br.com.mypst.teste;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.mypst.crawler.Game;
import br.com.mypst.crawler.User;
import br.com.mypst.dao.JogoDAO;
import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.JogoUtil;
import br.com.mypst.modelo.Jogo;
import br.com.mypst.modelo.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestaJogoUsuario {


	public static void main(String[] args) throws IOException {
		System.out.println("Inicio!"
				+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
		EntityManager em = new JPAUtil().getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
		JogoDAO jogoDAO = new JogoDAO(em);

		List<Usuario> usuarios = usuarioDAO.lista();

		for (Usuario usuario : usuarios) {
			em.getTransaction().begin();
			List<Jogo> jogos = new ArrayList<>();

			// Lista de jogos do user
			URL url = new URL("http://mypst.com.br/rank/" + usuario.getNome()
					+ "/jogos/xml/");
			XStream JogosUser = new XStream(new DomDriver());

			JogosUser.alias("user", User.class);
			JogosUser.alias("game", Game.class);

			// File file = new File("TesteDoido.xml");
			/* Inicio do acerto do XML */
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

			/* Fim do acerto do XML */

			// User user = (User) stream2.fromXML(new File("meu.xml"));
			User user = (User) JogosUser.fromXML(xml);

			for (Game game : user.getGames()) {
				if (!jogoDAO.existe(game.getId())) {
					
				Jogo jj = new JogoUtil().getJogo(game.getId());
				jogoDAO.alteraOuAdiciona(jj);
				System.out.println("=============    Adicionado o Jogo " + game.getName() + " =======================");
				}
				for (Jogo j : jogoDAO.listaPorIdSony(game.getId())) {
					jogoDAO.alteraOuAdiciona(j);
					jogos.add(j);
				}
				
				

			}
			// usuario.setJogos(new ArrayList<Jogo>());
			usuario.setJogos(jogos);
			System.out.println(usuario.getNome() + " | "
					+ usuario.getJogos().size() + " jogos."
					+ " Jogos no mypst " + user.getGames().size());
			// usuarioDAO.adiciona(usuario);

			em.getTransaction().commit();

			// usuario = usuarioDAO.busca(usuario.getId());
			/*
			 * for (Jogo jogo : usuario.getJogos()) {
			 * System.out.println(usuario.getNome() + " | " +jogo.getNome() +
			 * " - " + jogo.getIdSony()); }
			 */

		}
		em.close();
		System.out.println("FIM!"
				+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

}
