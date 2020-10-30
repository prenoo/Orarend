package usermanagement;

import all.HibernateUtil;
import org.hibernate.Session;


public class UserDatabaseManager {

    protected void create(UsersDB user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
