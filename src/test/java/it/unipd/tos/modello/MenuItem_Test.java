////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.modello;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuItem_Test {

    @Test
    public void MenuItem_constructorWorks_Test() {
        MenuItem item = new MenuItem("Biancaneve", 8.0D, MenuItem.itemType.BUDINO);
        assertEquals("Biancaneve", item.getName());
        assertEquals(8.0D, item.getPrice(), 0.0);
        assertEquals(MenuItem.itemType.BUDINO, item.getItemType());
    }
}