package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import modelo.basico.Usuario;

public class ObterUsuarios {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		// o nome Usuario se refere a classe e não tabela do BD
		String jpql = "select u from Usuario u";
		// .class são os 'metadados da classe', tem a definição, construtor etc.
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setMaxResults(5);
		
		// armazenando resulta da consulta
		List<Usuario> usuarios = query.getResultList();
		
		usuarios.stream().forEach(u -> System.out.println(
				"ID: " + u.getId() + "\n" +
				"Nome: " + u.getNome() + "\n"));
		
		em.close();
		emf.close();
	}
}
