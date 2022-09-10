package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO<E> {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	/* 
	 * Bloco statico, executa uma unica vez quando a classe é carregada
	 * pode ser util para iniciar os objetos
	 * */
	static {
		// lembrando que passamos a persist unit no metodo abaixo
		try {
			emf = Persistence.createEntityManagerFactory("exercicios-jpa");			
		} catch (Exception e) {
			// logar -> Log4j
		}
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	// quando metodo retorna a propria classe permite encadear chamadas
	// que retorna this
	public DAO<E> abrirT(){
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}

	public DAO<E> incluir(E entidade){
		em.persist(entidade);
		return this;
	}

	public DAO<E> incluirAtomico(E entidade){
		return this.abrirT().incluir(entidade).fecharT();
	}

	public E obterPorId(Object id) {
		return em.find(classe, id);
	}
	
	public List<E> obterTodos(int qtde, int deslocamento){
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		return query.getResultList();
	}
	
	public List<E> obterTodos(){
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(10);
		query.setFirstResult(0);
		return query.getResultList();
	}
	
	public void fechar() {
		em.clear();
	}
	
}
