////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.modello;

import static org.junit.Assert.*;

import org.junit.Test;

public class User_Test {

    @Test
    public void User_constructorWorks_Test() {
        User user = new User("Pinko", "Pallino", 19);
        assertEquals("Pinko", user.getName());
        assertEquals("Pallino", user.getSurname());
        assertEquals(19, user.getAge());
    }
}