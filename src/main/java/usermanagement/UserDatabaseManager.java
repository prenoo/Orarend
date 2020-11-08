package usermanagement;

import all.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("ALL")
public class UserDatabaseManager {

    protected void create(Users user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Az összes adatbázisba lévő rekord beolvasása
     * @return Course objektumokból álló lista
     */
    public List<Users> loadAllUserData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Users user = new Users();

        return session.createCriteria(Users.class).list();
    }
}
