package org.abhishek.behavioural;

/*
a state allows an object to alter its behavior when its internal state changes.
The object will appear to change its class.

It can be drawn from above definition that there shall be a separate concrete class per possible
state of an object. Each concrete state object will have logic to accept or reject a state transition
request based on it’s present state and context information passed to it as method arguments.

Design participants

State – The interface define operations which each state must handle.
Concrete States – The classes which contain the state specific behavior.
Context – Defines an interface to client to interact. It maintains references to concrete state
object which may be used to define current state of object. It delegates state-specific behavior
to different State objects.

Usage:
1) State machines

Examples:
1) TV with remote, if tv is on then only can change the channel
2) Java thread states. start, stop, run, wait

*/

class DeliveryContext {

    private PackageState currentState;
    private String packageId;

    public DeliveryContext(PackageState currentState, String packageId) {
        super();
        this.currentState = currentState;
        this.packageId = packageId;

        if (currentState == null) {
            this.currentState = Acknowledged.instance();
        }
    }

    public PackageState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(PackageState currentState) {
        this.currentState = currentState;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void update() {
        currentState.updateState(this);
    }
}

interface PackageState {
    void updateState(DeliveryContext ctx);
}

class Acknowledged implements PackageState {
    //Singleton
    private static Acknowledged instance = new Acknowledged();

    private Acknowledged() {
    }

    public static Acknowledged instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx) {
        System.out.println("Package is acknowledged !!");
        ctx.setCurrentState(Shipped.instance());
    }
}

class Shipped implements PackageState {
    //Singleton
    private static Shipped instance = new Shipped();

    private Shipped() {
    }

    public static Shipped instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx) {
        System.out.println("Package is shipped !!");
        ctx.setCurrentState(InTransition.instance());
    }
}

class InTransition implements PackageState
{
    //Singleton
    private static InTransition instance = new InTransition();

    private InTransition() {}

    public static InTransition instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is in transition !!");
        ctx.setCurrentState(OutForDelivery.instance());
    }
}

class OutForDelivery implements PackageState
{
    //Singleton
    private static OutForDelivery instance = new OutForDelivery();

    private OutForDelivery() {}

    public static OutForDelivery instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is out of delivery !!");
        ctx.setCurrentState(Delivered.instance());
    }
}

class Delivered implements PackageState
{
    //Singleton
    private static Delivered instance = new Delivered();

    private Delivered() {}

    public static Delivered instance() {
        return instance;
    }

    //Business logic
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is delivered!!");
    }
}

public class State {
    public static void main(String[] args) {
        DeliveryContext deliveryContext = new DeliveryContext(null, "Test Package");

        deliveryContext.update();
        deliveryContext.update();
        deliveryContext.update();
        deliveryContext.update();
        deliveryContext.update();

    }
}
