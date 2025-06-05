package org.abhishek.behavioural;

/*
The observer pattern defines a one-to-many dependency between objects so that when one object changes state,
all its dependents are notified and updated automatically. It is also referred to as the publish-subscribe pattern.
In the observer pattern, many observers (subscriber objects) observe a particular subject (publisher object).
Observers register with a subject to be notified when a change is made inside that subject.


Usage/Example:
1) Notification systems
2) Event handlers(Mouse click or Touch)

Design participants

The observer pattern has four participants.

Subject – interface or abstract class defining the operations for attaching and de-attaching observers to the subject.
ConcreteSubject – is a concrete Subject class. It maintains the state of the object and notifies the
attached observers when a change in the state occurs.
Observer – interface or abstract class defining the operations to notify this object.
ConcreteObserver – concrete Observer implementations.


*/

import java.util.ArrayList;

class Message{
    private final String message;

    public Message(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}

interface Subject {

    void attach(Observer observer);
    void detach(Observer observer);
    void notify(Message message);
}

class MessagePublisher implements Subject {

    private final ArrayList<Observer> observerList;

    public MessagePublisher() {
        observerList = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notify(Message message) {
        for(Observer observer: observerList) {
            observer.update(message);
        }

    }
}


interface Observer {
    void update(Message message);
}

class MessageSubscriberOne implements Observer {

    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriberOne :: " + m.getMessage());
    }
}

class MessageSubscriberTwo implements Observer {

    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriberTwo :: " + m.getMessage());
    }
}

public class ObserverDP {
    public static void main(String[] args) {
        Observer subscriberOne = new MessageSubscriberOne();
        Observer subscriberTwo = new MessageSubscriberTwo();


        Subject messagePublisher = new MessagePublisher();
        messagePublisher.attach(subscriberOne);
        messagePublisher.notify(new Message("Message 1"));

        messagePublisher.attach(subscriberTwo);
        messagePublisher.notify(new Message("Message 2"));

        messagePublisher.detach(subscriberOne);
        messagePublisher.notify(new Message("Message 3"));

    }

}
