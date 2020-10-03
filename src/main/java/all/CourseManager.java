package all;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CourseManager {

    protected SessionFactory sessionFactory;

    protected void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected void exit() {
        sessionFactory.close();
    }

    protected void create(Course c) {
            CourseDB course = new CourseDB();
            course.setCsoport(c.getCsoport());
            course.setEloado(c.getEloado());
            course.setFelev(c.getFelev());
            course.setFo(c.getFo());
            course.setHossz(c.getHossz());
            course.setKar(c.getKar());
            course.setKezdes(c.getKezdes());
            course.setNap(c.getNap());
            course.setSzki(c.getSzki());
            course.setTanszek(c.getTanszek());
            course.setTantargy(c.getTantargy());
            course.setTerem(c.getTerem());
            course.setTi(c.getTi());
            course.setTipus(c.getTipus());

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(course);

            session.getTransaction().commit();
            session.close();
    }

    protected void read(long id) {
        Session session = sessionFactory.openSession();

        CourseDB course = session.get(CourseDB.class, id);

        System.out.println("10. id: \n" + course.toString());
    }

    protected void delete(long id) {
        CourseDB course = new CourseDB();
        course.setId(id);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(course);

        session.getTransaction().commit();
        session.close();
    }




}

