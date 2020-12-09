////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

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
        itemsOrdered.add(new MenuItem("Coppa Nafta", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Biancaneve", 6.0D, MenuItem.itemType.GELATO));
        itemsOrdered.add(new MenuItem("Coca Cola", 1.5D, MenuItem.itemType.BEVANDA));

        double total = orderCalculator.getOrderPrice(itemsOrdered, new User("Pinko", "Pallino", 19));
        assertEquals(34.5D, total, 0.0D);
    }
}