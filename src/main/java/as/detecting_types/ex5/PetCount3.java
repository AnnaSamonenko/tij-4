package as.detecting_types.ex5;

import java.util.*;

/**
 * Add a new type of Pet to PetCount3.java. Verify that it is created and counted correctly in main( ).
 */

class Pet {
}

class Dog extends Pet {
}

class Pug extends Dog {
}

class Cat extends Pet {
}

class Rodent extends Pet {
}

class Gerbil extends Rodent {
}

class Hamster extends Rodent {
}

class Unicorn extends Pet {
}

class Counter {
    int i;
}

public class PetCount3 {
    public static void main(String[] args)
            throws Exception {
        ArrayList pets = new ArrayList();
        Class[] petTypes = {
                Pet.class,
                Dog.class,
                Pug.class,
                Cat.class,
                Rodent.class,
                Gerbil.class,
                Hamster.class,
                Unicorn.class
        };
        try {
            for (int i = 0; i < 15; i++) {
                // Offset by one to eliminate Pet.class:
                int rnd = 1 + (int) (
                        Math.random() * (petTypes.length - 1));
                pets.add(
                        petTypes[rnd].newInstance());
            }
        } catch (InstantiationException e) {
            System.err.println("Cannot instantiate");
            throw e;
        } catch (IllegalAccessException e) {
            System.err.println("Cannot access");
            throw e;
        }
        HashMap h = new HashMap();
        for (int i = 0; i < petTypes.length; i++)
            h.put(petTypes[i].toString(),
                    new Counter());
        for (int i = 0; i < pets.size(); i++) {
            Object o = pets.get(i);
            // Using isInstance to eliminate individual
            // instanceof expressions:
            for (int j = 0; j < petTypes.length; ++j)
                if (petTypes[j].isInstance(o)) {
                    String key = petTypes[j].toString();
                    ((Counter) h.get(key)).i++;
                }
        }
        for (int i = 0; i < pets.size(); i++)
            System.out.println(pets.get(i).getClass());
        Iterator keys = h.keySet().iterator();
        while (keys.hasNext()) {
            String nm = (String) keys.next();
            Counter cnt = (Counter) h.get(nm);
            System.out.println(
                    nm.substring(nm.lastIndexOf('.') + 1) +
                            " quantity: " + cnt.i);
        }
    }
}
