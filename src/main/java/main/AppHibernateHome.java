package main;

import entity.Address;
import entity.People;
import service.ServiceHibernate;

public class AppHibernateHome {
    /**
     * template for house number 1.
     */
    private static final int HOUSE_NUMBER_1 = 1;
    /**
     * template for house number 14.
     */
    private static final int HOUSE_NUMBER_14 = 14;
    /**
     * template for house number 32.
     */
    private static final int HOUSE_NUMBER_32 = 32;
    /**
     * house number replacement template 16.
     */
    private static final int HOUSE_SET_16 = 16;
    /**
     * template for address ID 1.
     */
    private static final int ADDRESS_ID_1 = 1;
    /**
     * template for address ID 3.
     */
    private static final int ADDRESS_ID_3 = 3;
    /**
     * Main class runner.
     *
     * @param args some args
     * @throws org.hibernate.HibernateException
     */
    public static void main(String[] args) {
        /**
         * creating an address entity.
         */
        Address address = Address.builder()
                .city("Madrid")
                .street("Concha Espina")
                .house(HOUSE_NUMBER_1)
                .build();

        Address address1 = Address.builder()
                .city("Paris")
                .street("Sent-Anore")
                .house(HOUSE_NUMBER_14)
                .build();

        Address address2 = Address.builder()
                .city("London")
                .street("Carnaby")
                .house(HOUSE_NUMBER_32)
                .build();
        /**
         * creating an people entity.
         */
        People people = People.builder()
                .name("Viktor")
                .surname("Comarov")
                .patronymic("Sergeevich")
                .build();

        People people1 = People.builder()
                .name("Roman")
                .surname("Sobolev")
                .patronymic("Andreevich")
                .build();

        People people2 = People.builder()
                .name("Anna")
                .surname("Ivanova")
                .patronymic("Vladimirovna")
                .build();
        /**
         * adding entities to the database.
         */
        address = (Address) ServiceHibernate.insert(address);
        address1 = (Address) ServiceHibernate.insert(address1);
        address2 = (Address) ServiceHibernate.insert(address2);
        people = (People) ServiceHibernate.insert(people);
        people1 = (People) ServiceHibernate.insert(people1);
        people2 = (People) ServiceHibernate.insert(people2);
        /**
         * reading entity from database.
         */
        ServiceHibernate.read(Address.class, ADDRESS_ID_1);
        /**
         * change the data in essence.
         */
        address1.setHouse(HOUSE_SET_16);
        people2.setName("Viktoria");
        ServiceHibernate.update(address1);
        ServiceHibernate.update(people2);
        /**
         * remove an entity from the database.
         */
        ServiceHibernate.delete(Address.class, ADDRESS_ID_1);
        /**
         * getting an entity.
         */
        ServiceHibernate.get(Address.class, ADDRESS_ID_3);
        /**
         * getting all entities from a table.
         */
        ServiceHibernate.getAll(Address.class);
        ServiceHibernate.getAll(People.class);
        /**
         * closing ENTITY_MANAGER_FACTORY.
         */
        ServiceHibernate.close();
    }
}
