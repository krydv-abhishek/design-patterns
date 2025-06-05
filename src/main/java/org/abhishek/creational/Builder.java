package org.abhishek.creational;

/*
The Builder Design Pattern is a creational pattern used in software design to construct a
complex object step by step. It allows the construction of a product in a step-by-step manner,
where the construction process can change based on the type of product being built.
This pattern separates the construction of a complex object from its representation,
allowing the same construction process to create different representations.

Components of the Builder Design Pattern:

1. Product
2. Builder
3. ConcreteBuilder
4. Director
5. Client


 */


//Product
class Computer {
    private String cpu;
    private String ram;
    private String storage;

    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public void setRAM(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void displayInfo() {
        System.out.println("Computer Configuration:");
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
    }
}

interface Builderr {
    void buildCPU();

    void buildRAM();

    void buildStorage();

    Computer build();
}

class GamingComputerBuilder implements Builderr {
    private Computer computer;

    public GamingComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void buildCPU() {
        computer.setCPU("Gaming CPU");
    }

    @Override
    public void buildRAM() {
        computer.setRAM("16GB DDR4");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("1TB SSD");
    }

    @Override
    public Computer build() {
        return computer;
    }
}


public class Builder {
    public static void main(String[] args) {
        GamingComputerBuilder gamingBuilder = new GamingComputerBuilder();
        gamingBuilder.buildCPU();

        gamingBuilder.buildRAM();
        gamingBuilder.buildStorage();

        Computer gamingComputer = gamingBuilder.build();
        gamingComputer.displayInfo();
    }
}
