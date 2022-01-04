package consultas;

import java.util.Iterator;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utilidades.HibernateUtil;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class Consulta2 {
    
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        int size = 0;
        
        SQLQuery sql = session.createSQLQuery("select * from actor where first_name = \"JULIA\"");
        
        try {
            
            List actores = sql.list();
            Iterator it = actores.iterator();
            
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%5s %-15s %-15s %-15s\n", "ID", "FIRST NAME", "LAST NAME", "MODIFIED");
            System.out.println("---------------------------------------------------------------------------------");
            
            while (it.hasNext()) {
                Object object[] = (Object[]) it.next();
                System.out.format("%5s %-15s %-15s %-15s\n", object[0], object[1], object[2], object[3]);
                size++;
            }
            
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Actores registrados en la tabla: " + size);
            
        } catch (Exception e) {
            System.err.println("Se ha producido un error en la consulta: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
                sf.close();
            }
        }
    }
    
}
