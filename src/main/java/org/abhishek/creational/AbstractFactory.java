package org.abhishek.creational;

/*
The Abstract Factory Pattern is a way of organizing how you create groups of things that are related
to each other. It provides a set of rules or instructions that let you create different types of
things without knowing exactly what those things are. This helps you keep everything organized and
lets you switch between different types easily.

Abstract Factory pattern is almost same as Factory Pattern and is considered as another layer of
abstraction over factory pattern.
Abstract Factory patterns work around a super-factory which creates other factories.
At runtime, the abstract factory is coupled with any desired concrete factory which can create
objects of the desired type.

Components of Abstract Factory Pattern:
Abstract Factory:
Concrete Factories:
Abstract Products:
Concrete Products:
Client:

 */

//Product interface for Car
interface Car {
    void assemble();
}

//Concrete product for Car
class Sedan implements Car {
    public void assemble() {
        System.out.println("Sedan car");
    }
}
//Concrete product for Car
class Hatchback implements Car {
    public void assemble() {
        System.out.println("Hatchback car");
    }
}
//Product interface for Car Specs
interface CarSpecification {
    void display();
}
//Concrete product for Car Specs
class IndianCarSpecs implements CarSpecification {
    public void display() {
        System.out.println("Indian display");
    }
}
//Concrete product for Car Specs
class EuropeanCarSpecs implements CarSpecification {
    public void display() {
        System.out.println("European display");
    }
}
//Factory interface
interface CarFactory {
    Car createCar();
    CarSpecification createSpecs();
}

//Concrete Factory
class IndianCarFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Sedan();
    }

    @Override
    public CarSpecification createSpecs() {
        return new IndianCarSpecs();
    }
}

//Concrete Factory
class EuropeanCarFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Hatchback();
    }

    @Override
    public CarSpecification createSpecs() {
        return new EuropeanCarSpecs();
    }
}

public class AbstractFactory {

    public static void main(String[] args) {
        IndianCarFactory indianCarFactory = new IndianCarFactory();
        Car indianCar = indianCarFactory.createCar();
        CarSpecification indianCarSpecs = indianCarFactory.createSpecs();

        indianCar.assemble();
        indianCarSpecs.display();

        EuropeanCarFactory europeanCarFactory = new EuropeanCarFactory();
        Car europeanCar = europeanCarFactory.createCar();
        CarSpecification europeanCarSpecs = europeanCarFactory.createSpecs();

        europeanCar.assemble();
        europeanCarSpecs.display();

    }

}
