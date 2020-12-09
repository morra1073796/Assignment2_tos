////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import it.unipd.tos.modello.MenuItem;
import it.unipd.tos.modello.User;
import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayBillImpl_Test {

    private TakeAwayBillImpl orderCalculator = new TakeAwayBillImpl();

    /* Test for simple order */

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

        double total = orderCalculator.getOrderPrice(items, new User("Pinko", "Pallino", 19));
    }
}