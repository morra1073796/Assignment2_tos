////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.modello;

public class User {

    private final String name, surname;
    private final int age;

    //costruttore
    public User(String name, String surname, int age) {

        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    //getters
    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    //fine getters
}
