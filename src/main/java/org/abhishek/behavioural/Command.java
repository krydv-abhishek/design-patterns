package org.abhishek.behavioural;

/*
 the command pattern is a behavioral design pattern in which an object is used to encapsulate all
 information needed to perform an action, a business operation or trigger an event e.g. method name,
 receiver object reference and method parameter values, if any. This object is called command.

Participants for command design pattern are:

Command interface – for declaring an operation.
Concrete command classes – which extends the Command interface, and has execute method for invoking business operation methods on receiver. It internally has reference of the receiver of command.
Invoker – which is given the command object to carry out the operation.
Receiver – which execute the operation.

Usage:
Handling actions for Java menu items and buttons.
Providing support for macros (recording and playback of macros).
Providing “undo” support.
Progress bars implementations.
Creating multi-step wizards.
 */

//Command interface
interface ICommand {
    void execute();
}

//Receiver
class Light {
    public  void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}
//Receiver
class Fan {
    void start() {
        System.out.println("Fan Started..");

    }

    void stop() {
        System.out.println("Fan stopped..");

    }
}

//Concrete command classes
class TurnOffLightCommand implements ICommand {

    Light light;

    public TurnOffLightCommand(Light light) {
        super();
        this.light = light;
    }

    public void execute() {
        System.out.println("Turning off light.");
        light.turnOff();
    }
}

//Concrete command classes
class TurnOnLightCommand implements ICommand {

    Light light;

    public TurnOnLightCommand(Light light) {
        super();
        this.light = light;
    }

    public void execute() {
        System.out.println("Turning on light.");
        light.turnOn();
    }
}
//Concrete command classes
class StopFanCommand implements ICommand {

    Fan fan;

    public StopFanCommand(Fan fan) {
        super();
        this.fan = fan;
    }

    public void execute() {
        System.out.println("Fan is stopping");
        fan.stop();
    }
}
//Concrete command classes
class StartFanCommand implements ICommand {

    Fan fan;

    public StartFanCommand(Fan fan) {
        super();
        this.fan = fan;
    }

    public void execute() {
        System.out.println("Fan is starting");
        fan.start();
    }
}

//Invoker
class HomeAutomationRemote {

    //Command Holder
    ICommand command;

    //Set the command in runtime to trigger.
    public void setCommand(ICommand command) {
        this.command = command;
    }

    //Will call the command interface method so that particular command can be invoked.
    public void buttonPressed() {
        command.execute();
    }
}

public class Command {
    public static void main(String[] args)
    {
        Light livingRoomLight = new Light();  //receiver 1

        Fan livingRoomFan = new Fan();  //receiver 2

        Light bedRoomLight = new Light(); //receiver 3

        Fan bedRoomFan = new Fan();   //receiver 4

        HomeAutomationRemote remote = new HomeAutomationRemote(); //Invoker

        remote.setCommand(new TurnOnLightCommand( livingRoomLight ));
        remote.buttonPressed();

        remote.setCommand(new TurnOnLightCommand( bedRoomLight ));
        remote.buttonPressed();

        remote.setCommand(new StartFanCommand( livingRoomFan ));
        remote.buttonPressed();

        remote.setCommand(new StopFanCommand( livingRoomFan ));
        remote.buttonPressed();

        remote.setCommand(new StartFanCommand( bedRoomFan ));
        remote.buttonPressed();

        remote.setCommand(new StopFanCommand( bedRoomFan ));
        remote.buttonPressed();
    }
}
