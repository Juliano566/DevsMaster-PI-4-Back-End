
package br.com.devsmasterpi4.devsmasterpi4;

/**
 *
 * @author nails
 */
import br.com.devsmasterpi4.devsmasterpi4.dominio.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class testeJpa {
    
    public static void main(String[] args) {
        Produto p1 = new Produto("computador", "teste", 1, true, 1, 500);
        Produto p2 = new Produto("computador1");
        Produto p3 = new Produto("computador2");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexao-jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        
        em.getTransaction().commit();
       
        System.out.println("Pronto");
        

        
    }
    
}
