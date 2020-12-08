////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TakeAwayBillException_Test {

    @Test
    public void TakeAwayBillException_constructorWorks_Test() {
        TakeAwayBillException exc = new TakeAwayBillException();
        assertEquals("Troppi item nell'ordine", exc.getMessage());
    }
}