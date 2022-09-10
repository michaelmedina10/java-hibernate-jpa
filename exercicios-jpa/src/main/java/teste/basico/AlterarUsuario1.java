package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario1 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, 1L);
		usuario.setNome("Mic");
		usuario.setEmail("mic@outlook.com");
		
		// merge pega um registro já no banco e atualiza
		em.merge(usuario);
		System.out.println(usuario.getEmail());
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
