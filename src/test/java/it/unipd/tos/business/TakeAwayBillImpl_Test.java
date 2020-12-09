////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import it.unipd.tos.modello.MenuItem;
import it.unipd.tos.modello.User;
import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayBillImpl_Test {

    private TakeAwayBillImpl orderCalculator = new TakeAwayBillImpl();

    /* Test for simple order */

    @Test
    public void TakeAwayBillImpl_constructorWorks_Test() {
        orderCalculator.setTime(LocalTime.of(0, 0));
    	assertEquals(LocalTime.of(0, 0), orderCalculator.getTime());
        assertEquals(0, orderCalculator.getCountFree());
    }
    
    @Test
    public void TakeAwayBillImpl_simpleOrderCalculationWorks_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.5D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 7.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Coca Cola", 1.5D, MenuItem.itemType.BEVANDA));

        double total = orderCalculator.getOrderPrice(itemsOrdered, new User("Pinko", "Pallino", 19));
        assertEquals(15.0D, total, 0.0D);
    }
    
    @Test
    public void TakeAwayBillImpl_fiveIcecreamsPlusOrderCalculationWorks_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 4.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coca Cola", 1.5D, MenuItem.itemType.BEVANDA));

        double total = orderCalculator.getOrderPrice(itemsOrdered, new User("Pinko", "Pallino", 19));
        assertEquals(33.5D, total, 0.0D);
    }
    
    @Test
    public void TakeAwayBillImpl_fiftyPlusSimpleOrderCalculationWorks_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coca Cola", 1.5D, MenuItem.itemType.BEVANDA));

        double total = orderCalculator.getOrderPrice(itemsOrdered, new User("Pinko", "Pallino", 19));
        assertEquals(55.35D, total, 0.0D);
    }
    
    @Test
    public void TakeAwayBillImpl_fiftyPlusAndFiveIcecreamsOrderCalculationWorks_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 4.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coca Cola", 1.5D, MenuItem.itemType.BEVANDA));

        double total = orderCalculator.getOrderPrice(itemsOrdered, new User("Pinko", "Pallino", 19));
        assertEquals(51.75D, total, 0.0D);
    }
    
    @Test
    public void TakeAwayBillImpl_commisionOrderCalculationWorks_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.BUDINO));
        itemsOrdered.add(new MenuItem("Coca Cola", 1.5D, MenuItem.itemType.BEVANDA));

        double total = orderCalculator.getOrderPrice(itemsOrdered, new User("Pinko", "Pallino", 19));
        assertEquals(8.0D, total, 0.0D);
    }
    
    @Test(expected = TakeAwayBillException.class)
    public void TakeAwayBillImpl_overSizedOrder_Test() throws TakeAwayBillException {
        MenuItem item = new MenuItem("Banana Split", 10.0D, MenuItem.itemType.BUDINO);
        Stream<MenuItem> gelati = Stream.generate(() -> item);
        List<MenuItem> items = gelati.limit(31).collect(Collectors.toList());

        Double total = orderCalculator.getOrderPrice(items, new User("Pinko", "Pallino", 19));
    }
    
    @Test
    public void TakeAwayBillImpl_freeOrderRightTime_Test() throws TakeAwayBillException {
    	List<MenuItem> itemsOrdered = new ArrayList<>();
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.BUDINO));
        List<User> utenti = Arrays.asList(
            new User("Paolo", "1", 17),
            new User("Paolo", "2", 17),
            new User("Paolo", "3", 17),
            new User("Paolo", "4", 17),
            new User("Paolo", "5", 17),
            new User("Paolo", "6", 17),
            new User("Paolo", "7", 17),
            new User("Paolo", "8", 17),
            new User("Paolo", "9", 17),
            new User("Paolo", "10", 17),
            new User("Paolo", "11", 17),
            new User("Paolo", "12", 17),
            new User("Paolo", "13", 17),
            new User("Paolo", "14", 17),
            new User("Paolo", "15", 17),
            new User("Paolo", "16", 17),
            new User("Paolo", "17", 17),
            new User("Paolo", "18", 17),
            new User("Paolo", "19", 20),
            new User("Luca", "1", 21),
            new User("Luca", "2", 49),
            new User("Luca", "3", 13),
            new User("Luca", "4", 15),
            new User("Luca", "5", 7),
            new User("Luca", "6", 19));

        for (User user : utenti) {
        	orderCalculator.setTime(LocalTime.of(18, 23));
            double total = orderCalculator.getOrderPrice(itemsOrdered, user);
            assertTrue(total == 6.5D || total == 0);
        }
        assertTrue(orderCalculator.getCountFree() <= 10);
    }
    
    @Test
    public void TakeAwayBillImpl_freeOrderReset_Test() throws TakeAwayBillException {
    	List<MenuItem> itemsOrdered = new ArrayList<>();
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.BUDINO));
        List<User> utenti = Arrays.asList(
                new User("Paolo", "1", 17),
                new User("Paolo", "2", 17),
                new User("Paolo", "3", 17),
                new User("Paolo", "4", 17),
                new User("Paolo", "5", 17),
                new User("Paolo", "6", 17),
                new User("Paolo", "7", 17),
                new User("Paolo", "8", 17),
                new User("Paolo", "9", 17),
                new User("Paolo", "10", 17),
                new User("Paolo", "11", 17),
                new User("Paolo", "12", 17),
                new User("Paolo", "13", 17),
                new User("Paolo", "14", 17),
                new User("Paolo", "15", 17),
                new User("Paolo", "16", 17),
                new User("Paolo", "17", 17),
                new User("Paolo", "18", 17),
                new User("Paolo", "19", 20),
                new User("Luca", "1", 21),
                new User("Luca", "2", 49),
                new User("Luca", "3", 13),
                new User("Luca", "4", 15),
                new User("Luca", "5", 7),
                new User("Luca", "6", 19));

        int countFree = 0;
        for (User user : utenti) {
        	orderCalculator.setTime(LocalTime.of(18, 23));
            double total = orderCalculator.getOrderPrice(itemsOrdered, user);
            assertTrue(total == 6.5D || total == 0);
            if(total == 0) {
            	countFree++;
                assertTrue(countFree == orderCalculator.getCountFree());
            }
        }
        assertTrue(orderCalculator.getCountFree() >= 0);
        //esegui nuovo ordine NON in happyHour (prima di)
        orderCalculator.setTime(LocalTime.of(14, 10));
        double total = orderCalculator.getOrderPrice(itemsOrdered, utenti.get(2));
        assertTrue(orderCalculator.getCountFree() == 0);
        //esegui nuovo ordine NON in happyHour (dopo di)
        orderCalculator.setTime(LocalTime.of(20, 0));
        total = orderCalculator.getOrderPrice(itemsOrdered, utenti.get(2));
        assertTrue(orderCalculator.getCountFree() == 0);
    }
}