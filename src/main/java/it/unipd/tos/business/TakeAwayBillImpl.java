////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.modello.MenuItem;
import it.unipd.tos.modello.MenuItem.itemType;
import it.unipd.tos.modello.User;

public class TakeAwayBillImpl implements TakeAwayBill {

    private LocalTime timeOfOrder; 

    TakeAwayBillImpl(){
        this.timeOfOrder= LocalTime.now();
    }

    //funzione per effettuare i test
    void setTime(LocalTime timeOfOrder){
        this.timeOfOrder= timeOfOrder;
    }
    
    //getter
    LocalTime getTime() {
        return timeOfOrder;
    }
    //fine getter 
    
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {
        //lancio eccezione
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException();
        }
        double total = 0.0;
        int countIcecreams = 0;
        double cheapestIcecream = 0.0;
        int countFree = 0;
        for(MenuItem item : itemsOrdered) {
            total += item.getPrice();
            if(item.getItemType() == itemType.GELATO) {
                countIcecreams++;
                if(countIcecreams == 1  || item.getPrice() < cheapestIcecream) {
                    cheapestIcecream = item.getPrice(); 
                }
            }
        }
        //commissione
        if(total < 10.0) {
            total += 0.5;
        }
        //sconto sul gelato meno costoso
        if(countIcecreams > 5) {
            total -= cheapestIcecream / 2;
        }
        //sconto sul totale
        if(total > 50.0) {
            total = total * 90.0 / 100.0;
        }
        //lotteria serale per minorenni
        if (user.getAge() < 18 && ChronoUnit.HOURS.between(LocalTime.of(19, 0), timeOfOrder) <= 1 
        && ChronoUnit.HOURS.between(LocalTime.of(19, 0), timeOfOrder) >= 0) {
            if(Math.random() < 0.5D && countFree<10) {
                total = 0;
                countFree++;
            }
        }
        return total;
    }
}