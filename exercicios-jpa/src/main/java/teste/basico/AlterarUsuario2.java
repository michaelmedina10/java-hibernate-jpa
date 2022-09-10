package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

public class AlterarUsuario2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		/*
		 * Mesmo sem o merge o meu nome foi alterado no BD
		 * por quê?
		 * porque dentro de um contexto transacional o objeto está sendo
		 * gerenciado, e qualquer mudança haverá um sincronismo com o BD
		 * Mesmo sem chamar o método que em tese deveria fazer isso
		 * 
		 * Cuidado, pois qualquer mudança feita nesse contexto será levado para
		 * o BD
		 * 
		 * mesmo se eu alterar o objeto fora do em.getTransaction().begin();
		 * ainda sim ele estará sendo gerenciado, existe uma forma de fazer um detach
		 * veremos no updateusuario3
		 * */
		Usuario usuario = em.find(Usuario.class, 1L);
		usuario.setNome("Mic Alterado 2");
		usuario.setEmail("mic@outlook.com");
		
//		em.merge(usuario);
		System.out.println(usuario.getEmail());
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
