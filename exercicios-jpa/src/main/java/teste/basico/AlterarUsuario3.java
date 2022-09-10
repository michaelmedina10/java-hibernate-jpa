package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Usuario;

/* 
 * Quando por exemplo o front-end envia dados para API em JAVA
 * os dados vem em um contexto não gerênciavel
 * PORÉM
 * quando tu faz uma consulta ele entra nesse contexto transacional
 * CUIDADO
 * */

public class AlterarUsuario3 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, 1L);
		// eu disse explicitamente para o JPA parar de monitorar esse objeto
		em.detach(usuario);
		usuario.setNome("Mic Alterado 3");
		usuario.setEmail("mic@outlook.com");
		// se eu fiz o detach preciso chamar o merge para persistir a alteração
		em.merge(usuario);
		System.out.println(usuario.getEmail());
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
