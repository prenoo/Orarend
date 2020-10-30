/**
 * Osztály a Course osztály adatbázisműveleteinek kezeléséhez
 */

package all;

import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("ALL")
public class CourseDatabaseManager {

    protected void create(Course c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(c);

        session.getTransaction().commit();
        session.close();
    }


    /**
     * Adatbázisból egy rekord beolvasása az id alapján
     * @param id a tárgy elsődleges kulcsa
     * @return ha létezik az id visszaadja a CourseDB objektumot
     */
    protected Course read(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Course course = session.get(Course.class, id);
        session.close();
        return course;
    }

    /**
     * Tárgy törlése
     * @param id a törlendő tárgy elsődleges kulcsa
     */
    protected void delete(long id) {
        Course course = new Course();
        course.setId(id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(course);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Az összes adatbázisba lévő rekord beolvasása
     * @return Course objektumokból álló lista
     */
    protected List<Course> loadAllData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = new Course();

        return session.createCriteria(Course.class).list();
    }
}

