package org.abhishek.creational;

/*
The prototype design pattern is used in scenarios where an application needs to create
a number of instances of a class that have almost the same state or differ very little.

Prototype: This is the prototype of the actual object, as discussed above.
Prototype registry: This is used as a registry service to have all prototypes accessible using simple string parameters.
Client: The client will be responsible for using the registry service to access prototype instances.

When to use:
When an object is required that is similar to an existing object or when the creation
would be expensive compared to cloning.
 */


import java.util.HashMap;
import java.util.Map;

interface PrototypeCapable extends Cloneable {
    PrototypeCapable clone() throws CloneNotSupportedException;

    String toString();
}

class Movie implements PrototypeCapable {
    public PrototypeCapable clone() throws CloneNotSupportedException {
        System.out.println("Cloning Movie object..");
        return (Movie) super.clone();
    }

    public String toString() {
        return "Movie";
    }
}

class TV implements PrototypeCapable {
    public PrototypeCapable clone() throws CloneNotSupportedException {
        System.out.println("Cloning TV object..");
        return (TV) super.clone();
    }

    public String toString() {
        return "TV";
    }
}

class PrototypeFactory {

    public static class ModelType {

        public static final String MOVIE = "movie";
        public static final String TV = "tv";
    }

    private static Map<String, PrototypeCapable> prototypes = new HashMap<>();

    static {
        prototypes.put(ModelType.MOVIE, new Movie());
        prototypes.put(ModelType.TV, new TV());
    }

    public static PrototypeCapable getInstance(final String s) throws CloneNotSupportedException {
        return prototypes.get(s).clone();
    }
}

public class Prototype {

    public static void main(String[] args) {
        try {
            String movie = PrototypeFactory.getInstance(PrototypeFactory.ModelType.MOVIE).toString();
            System.out.println(movie);

            String tv = PrototypeFactory.getInstance(PrototypeFactory.ModelType.TV).toString();
            System.out.println(tv);

        } catch (CloneNotSupportedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
