package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceHibernate {
    /**
     * template for ENTITY_MANAGER_FACTORY.
     */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("HibernateHome");
    /**
     * method getEntityManager.
     * @return new ENTITY_MANAGER_FACTORY
     */
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
    /**
     * create EntityManager.
     */
    public static EntityManager em = ServiceHibernate.getEntityManager();

    /**
     * adding an entity.
     * @return entity.
     */
    public static Object insert(Object ob) {
        em.getTransaction().begin();
        Object obFromDB = em.merge(ob);
        em.getTransaction().commit();
        return obFromDB;
    }
    /**
     * reading a line.
     * return entity.
     */
    public static Object read(Class cl, Integer id) {
        System.out.println(em.find(cl, id));
        return em.find(cl, id);
    }
    /**
     * line change.
     */
    public static void update(Object ob) {
        em.getTransaction().begin();
        em.merge(ob);
        em.getTransaction().commit();
    }
    /**
     * deleting an entity.
     */
    public static void delete(Class cl, Integer id) {
        em.getTransaction().begin();
        em.remove(get(cl, id));
        em.getTransaction().commit();
    }
    /**
     * getting an entity.
     * @return entity.
     */
    public static Object get(Class cl, Integer id) {
        return em.find(cl, id);
    }
    /**
     * getting all table entities.
     * @return list</Object>
     */
    public static List<Object> getAll(Class cl) {
        TypedQuery<Object> namedQuery =
                em.createNamedQuery(cl.getSimpleName(), cl);
        List<Object> list = namedQuery.getResultList();
        System.out.println();
        System.out.println("Data from table: " + cl.getSimpleName());
        for (Object o : list
        ) {
            System.out.println(o);
        }
        return list;
    }
    /**
     * closing ENTITY_MANAGER_FACTORY.
     */
    public static void close() {
        ENTITY_MANAGER_FACTORY.close();
    }

}
