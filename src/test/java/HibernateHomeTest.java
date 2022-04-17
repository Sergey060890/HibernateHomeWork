import entity.Address;
import org.junit.*;
import service.ServiceHibernate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class HibernateHomeTest {
    public static EntityManager em;
    public static Address address;
    public static Address address1;
    public static Address address2;
    /**
     * @BeforeClass.
     * creating a Test Entity Address
     */
    @BeforeClass
    public static void setAddress() throws Exception {
        em = ServiceHibernate.getEntityManager();
        address = Address.builder()
                .city("Madrid")
                .street("Concha Espina")
                .house(1)
                .build();
        address1 = Address.builder()
                .city("Paris")
                .street("Sent-Anore")
                .house(14)
                .build();
        address2 = Address.builder()
                .city("London")
                .street("Carnaby")
                .house(32)
                .build();
    }
    /**
     * checking connection to EntinyManager.
     */
    @Test
    public void getEntinyManagerTest() throws Exception {
        Assert.assertTrue(ServiceHibernate.getEntityManager().isOpen());
    }
    /**
     * insert test.
     */
    @Test
    public void insertTest() throws Exception {
        em.persist(address);
        em.persist(address1);
        em.persist(address2);
        ServiceHibernate.insert(address);
        ServiceHibernate.insert(address1);
        ServiceHibernate.insert(address2);
        Assert.assertEquals(em.find(Address.class, 1), ServiceHibernate.get(Address.class, 1));
        Assert.assertEquals(em.find(Address.class, 2), ServiceHibernate.get(Address.class, 2));
        Assert.assertEquals(em.find(Address.class, 3), ServiceHibernate.get(Address.class, 3));
    }
    /**
     * read test.
     */
    @Test
    public void readTest() throws Exception {
        em.persist(address);
        ServiceHibernate.insert(address);
        String str = em.find(Address.class, 1).toString();
        String str1 = ServiceHibernate.read(Address.class, 1).toString();
        Assert.assertEquals(str, str1);
    }
    /**
     * update test.
     */
    @Test
    public void updateTest() throws Exception {
        em.persist(address);
        ServiceHibernate.insert(address);
        address.setCity("Monaco");
        ServiceHibernate.update(address);
        String str = em.find(Address.class, 3).toString();
        String str1 = ServiceHibernate.get(Address.class, 3).toString();
        Assert.assertEquals(str, str1);
    }
    /**
     * delete test.
     */
    @Test
    public void deleteTest() throws Exception {
        ServiceHibernate.delete(Address.class, 2);
        Assert.assertTrue(ServiceHibernate.get(Address.class, 2) == null);
    }
    /**
     * getEntity test.
     */
    @Test
    public void getTest() throws Exception {
        String str = em.find(Address.class,2).toString();
        String str1 = ServiceHibernate.get(Address.class, 2).toString();
        Assert.assertEquals(str, str1);
    }
    /**
     * getAllEntity test.
     */
    @Test
    public void getAllTest() throws Exception {
        int count = ServiceHibernate.getAll(Address.class).size();
        TypedQuery<Address> namedQuery = em.createNamedQuery("Address", Address.class);
        List<Address> list = namedQuery.getResultList();
        Assert.assertEquals(count, list.size());
    }
    /**
     * equals test.
     */
    @Test
    public void EqualsTest() {
        Address actualAddress = Address.builder()
                .city("Madrid")
                .street("Concha Espina")
                .house(1)
                .build();
        Address expectedAdress = Address.builder()
                .city("Madrid")
                .street("Concha Espina")
                .house(1)
                .build();
        Address expectedAddress2 = Address.builder()
                .city("Madrid")
                .street("Concha Espina")
                .house(2)
                .build();

        assertThat("Mistake! Objects are not equal!", actualAddress, is(expectedAdress));
        assertThat("Mistake! Objects are not equal!", actualAddress, is(expectedAdress));
    }
    /**
     * toString test.
     */
    @Test
    public void testToStringTest() {
        Address actualAddress = Address.builder()
                .city("Paris")
                .street("Sent-Anore")
                .house(14)
                .build();
        String expectedAddress = "Address(id=null, city=Paris, street=Sent-Anore, house=14)";
        Assert.assertEquals(expectedAddress, actualAddress.toString());
    }
    /**
     * @AfterClass
     * clean test.
     */
    @AfterClass
    public static void clean() throws Exception {
        ServiceHibernate.close();
    }
}
