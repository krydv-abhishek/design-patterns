package org.abhishek.behavioural;

/*
Visitor design pattern makes it possible to add new operations to preexisting classes without changing them.
It improves the modularity and maintainability of code.

Components of Visitor Design Pattern
The Visitor design pattern consists of several key components that work together to enable its functionality. Here’s a breakdown of these components:

Visitor Interface: This interface declares a visit method for each type of element in the object structure.
Each method is designed to handle a specific element type.

Concrete Visitor: This class implements the Visitor interface and provides the specific behavior for each
visit method. It contains the logic for the operations that need to be performed on the elements.

Element Interface: This interface defines an accept method that takes a visitor as an argument.
This method allows the visitor to visit the concrete elements.

Concrete Elements: These classes implement the Element interface and represent the various types of
objects in the structure. Each concrete element defines how it accepts a visitor by calling the corresponding
method on the visitor.

Object Structure: This is the collection of elements (the concrete elements) that the visitor will operate on.
It often includes methods to add, remove, and retrieve elements.

Example:
1) A simple example of the Visitor design pattern is in an online shopping cart.
Imagine you have different items like books, electronics, and clothing. Each item can accept a
visitor that performs actions like calculating the total price or applying discounts.

 */
import java.util.ArrayList;
import java.util.List;

// Visitor interface
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Square square);
    void visit(Triangle triangle);
}

// Element interface
interface Shape {
    void accept(ShapeVisitor visitor);
}

// Concrete Elements
class Circle implements Shape {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }

    public int getRadius() {
        return radius;
    }
}

class Square implements Shape {
    private final int side;

    public Square(int side) {
        this.side = side;
    }
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }

    public int getSide() {
        return side;
    }
}

class Triangle implements Shape {

    private final int base;
    private final int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }
}

// Concrete Visitors
class AreaCalculator implements ShapeVisitor {
    private double totalArea = 0;

    @Override
    public void visit(Circle circle) {
        // Calculate area of circle and update totalArea
        totalArea += Math.PI * Math.pow(circle.getRadius(), 2);
    }

    @Override
    public void visit(Square square) {
        // Calculate area of square and update totalArea
        totalArea += Math.pow(square.getSide(), 2);
    }

    @Override
    public void visit(Triangle triangle) {
        // Calculate area of triangle and update totalArea
        totalArea += (double) (triangle.getBase() * triangle.getHeight()) / 2;
    }

    public double getTotalArea() {
        return totalArea;
    }
}

// Main class
public class Visitor {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(2));
        shapes.add(new Square(2));
        shapes.add(new Triangle(2, 3));

        AreaCalculator areaCalculator = new AreaCalculator();
        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }

        System.out.println("Total area: " + areaCalculator.getTotalArea());
    }
}
