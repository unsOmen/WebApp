import bean.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by OmeN on 04.05.2016.
 */
public class Main {

    private static final SessionFactory outSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            outSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException {
        return outSessionFactory.openSession();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.listUsers();
    }

    public void listUsers() {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            List users = session.createQuery("FROM bean.Users").list();
            for(Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                Users user = (Users) iterator.next();
                System.out.print("ID: " + user.getId() + "\t");
                System.out.print("Login: " + user.getUsername() + "\t");
                System.out.println("Pass: " + user.getPass());
            }
            System.out.println("==========================");
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
