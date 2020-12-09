////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.modello.MenuItem;
import it.unipd.tos.modello.MenuItem.itemType;
import it.unipd.tos.modello.User;

public class TakeAwayBillImpl implements TakeAwayBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        double total = 0.0;
        int countIcecreams = 0;
        double cheapestIcecream = 0.0;
        for(MenuItem item : itemsOrdered) {
            total += item.getPrice();
            if(item.getItemType() == itemType.GELATO) {
                countIcecreams++;
                if(countIcecreams == 1  || item.getPrice() < cheapestIcecream) {
                    cheapestIcecream = item.getPrice(); 
                }
            }
        }
        if(countIcecreams >= 5) {
            total -= cheapestIcecream / 2;
        }
        if(total >= 50.0) {
            total = total * 90.0 / 100.0;
        }
        return total;
    }
}