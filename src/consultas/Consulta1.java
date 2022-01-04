package consultas;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.Actor;
import utilidades.HibernateUtil;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Consulta1 {
    
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        int size = 0;
        
        SQLQuery sql = session.createSQLQuery("select * from actor");
        sql.addEntity(Actor.class);
        
        try {
            List<Actor> actores = sql.list();
            
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%5s %-15s %-15s %-15s\n", "ID", "FIRST NAME", "LAST NAME", "MODIFIED");
            System.out.println("---------------------------------------------------------------------------------");
            
            for (Actor actor: actores) {
                System.out.format("%5s %-15s %-15s %-15s\n", actor.getActorId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate());
                System.out.println();
                size++;
            }
            
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Actores registrados en la tabla: " + size);
            
        } catch (Exception e) {
            System.err.println("Error de ejecución: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
                sf.close();
            }
        }
    }
}
