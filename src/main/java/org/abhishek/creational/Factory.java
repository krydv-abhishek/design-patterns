package org.abhishek.creational;

/*
The Factory Method Design Pattern is a creational design pattern used in software development.
It provides an interface for creating objects in a superclass while allowing subclasses to specify
the types of objects they create.

Use Cases of the Factory Method
Below are the main use cases of factory method design pattern:

Used in JDBC for creating connections and in frameworks like Spring for managing beans.
Libraries like Swing and JavaFX use factories to create flexible UI components.
Tools like Log4j rely on factories to create configurable loggers.
Factories help create objects from serialized data, supporting various formats.
Advantages of the Factory Method
Below are the main advantages of factory method design pattern:

Separates creation logic from client code, improving flexibility.
New product types can be added easily.
Simplifies unit testing by allowing mock product creation.
Centralizes object creation logic across the application.
Hides specific product classes from clients, reducing dependency.
*/

//Product
abstract class Vehicle {
    public abstract void printVehicle();
}
//Concrete Product
class TwoWheeler extends Vehicle {
    @Override
    public void printVehicle() {
        System.out.println("I am a two wheeler");
    }
}
//Concrete Product
class FourWheeler extends Vehicle {
    @Override
    public void printVehicle() {
        System.out.println("I am a four wheeler");
    }
}
//Concrete Product
class ThreeWheeler extends Vehicle {
    @Override
    public void printVehicle() {
        System.out.println("I am a three wheeler");
    }
}

//Creator
interface VehicleFactory {
    public Vehicle createVehicle();
}

//Concrete Creator
class TwoWheelerFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new TwoWheeler();
    }
}
//Concrete Creator
class FourWheelerFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new FourWheeler();
    }
}
//Concrete Creator
class ThreeWheelerFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new ThreeWheeler();
    }
}

//User concrete creator to create the product
class Client {
    private final Vehicle vehicle;

    public Client(VehicleFactory factory) {
        this.vehicle = factory.createVehicle();
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}


public class Factory {
    public static void main(String[] args) {
        VehicleFactory twoWheelerFactory = new TwoWheelerFactory();
        Client twoWheelerClient = new Client(twoWheelerFactory);
        Vehicle twoWheeler = twoWheelerClient.getVehicle();
        twoWheeler.printVehicle();

        VehicleFactory fourWheelerFactory = new FourWheelerFactory();
        Client fourWheelerClient = new Client(fourWheelerFactory);
        Vehicle fourWheeler = fourWheelerClient.getVehicle();
        fourWheeler.printVehicle();

        //Adding new product don't require changes in existing class hence maintaining solid principles
        VehicleFactory threeWheelerFactory = new ThreeWheelerFactory();
        Client threeWheelerClient = new Client(threeWheelerFactory);
        Vehicle threeWheeler = threeWheelerClient.getVehicle();
        threeWheeler.printVehicle();
    }
}
