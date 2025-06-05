package org.abhishek.behavioural;


/*
Template method design pattern is widely accepted behavioral design pattern to enforce some sort of
algorithm (fixed set of steps) in the context of programming.

It defines the sequential steps to execute a multi-step algorithm and optionally can provide a default
implementation as well (based on requirements).

Design participants:
1) Abstract class - contains common method field
2) Concrete class - contains unique implementations apart from common ones


Usage:
1) For pre-requisite of all concrete implementation
2) Removes code duplication
 */

abstract class House {
    /**
     * This is the template method we are discussing. This method should be
     * final so that other class can't re-implement and change the order of the
     * steps.
     */
    public final void buildhouse() {
        constructBase();
        constructRoof();
        constructWalls();
        constructWindows();
        constructDoors();
        paintHouse();
        decorateHouse();
    }

    public abstract void decorateHouse();

    public abstract void paintHouse();

    public abstract void constructDoors();

    public abstract void constructWindows();

    public abstract void constructWalls();

    /**
     * final implementation of constructing roof - final as all type of house
     * Should build roof in same manner.
     */
    private final void constructRoof() {
        System.out.println("Roof has been constructed.");
    }

    /**
     * final implementation of constructing base - final as all type of house
     * Should build base/foundation in same manner.
     */
    private final void constructBase() {
        System.out.println("Base has been constructed.");
    }
}

class ConcreteWallHouse extends House {
    @Override
    public void decorateHouse() {
        System.out.println("Decorating Concrete Wall House");
    }

    @Override
    public void paintHouse() {
        System.out.println("Painting Concrete Wall House");
    }

    @Override
    public void constructDoors() {
        System.out.println("Constructing Doors for Concrete Wall House");
    }

    @Override
    public void constructWindows() {
        System.out.println("Constructing Windows for Concrete Wall House");
    }

    @Override
    public void constructWalls() {
        System.out.println("Constructing Concrete Wall for my House");
    }
}

class GlassWallHouse extends House {
    @Override
    public void decorateHouse() {
        System.out.println("Decorating Glass Wall House");
    }

    @Override
    public void paintHouse() {
        System.out.println("Painting Glass Wall House");
    }

    @Override
    public void constructDoors() {
        System.out.println("Constructing Doors for Glass Wall House");
    }

    @Override
    public void constructWindows() {
        System.out.println("Constructing Windows for Glass Wall House");
    }

    @Override
    public void constructWalls() {
        System.out.println("Constructing Glass Wall for my House");
    }
}

public class Template {

    public static void main(String[] args) {
        House concreteWallHouse = new ConcreteWallHouse();
        concreteWallHouse.buildhouse();

        House glassWallHouse = new GlassWallHouse();
        glassWallHouse.buildhouse();
    }

}
