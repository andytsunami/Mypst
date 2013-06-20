package br.com.mypst.teste;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.mypst.dao.UsuarioDAO;
import br.com.mypst.infra.JPAUtil;
import br.com.mypst.infra.UsuarioUtil;
import br.com.mypst.modelo.Usuario;

public class TestaInclusaoUsuarioMypst {

	public static void main(String[] args) throws FileNotFoundException {
/*
 * Não usar pois está apagando os jogos
 * 
 * */

		Scanner scanner = new Scanner(new FileReader("MassaUsuario.txt"))
				.useDelimiter("\\n");

		while (scanner.hasNext()) {
			EntityManager em = new JPAUtil().getEntityManager();
			String us = scanner.next();
			System.out.println("Adicionando " + us);

			Usuario usuario = new Usuario();
			try {
				usuario = new UsuarioUtil().getUsuario(us);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UsuarioDAO usuarioDAO = new UsuarioDAO(em);
			
			
			em.getTransaction().begin();
			usuarioDAO.adiciona(usuario);
			em.getTransaction().commit();
			System.out.println("Sucesso ao adicionar " + us);
			em.close();
		}
		System.out.println("Adicionados com sucesso :D");

	}

}
