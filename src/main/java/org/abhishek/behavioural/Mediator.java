package org.abhishek.behavioural;

/*
Mediator pattern defines an object that encapsulates how a set of objects interact.
Mediator promotes loose coupling by keeping objects from referring to each other explicitly,
and it lets us vary their interaction independently.

Design participants

Mediator – defines the interface for communication between Colleague objects
ConcreteMediator – implements the Mediator interface and coordinates communication between Colleague objects.
It is aware of all the Colleagues and their purposes in regard to inter-communication.
Colleague – defines the interface for communication with other Colleagues through its Mediator
ConcreteColleague – implements the Colleague interface and communicates with other Colleagues through its Mediator


Mediator helps in replacing “many-to-many” relationship with “one-to-many” relationships,
so it is much easier to read and understand. Also, the maintenance becomes easy due to
centralized control of communication.

Example:
1) Air traffic control
2) Chat application
3) java.util.concurrent.Executor
 */

import java.util.HashMap;
import java.util.Map;

interface IChatRoom {
    void sendMessage(String msg, String userId);

    void addUser(User user);
}

class ChatRoom implements IChatRoom {

    private Map<String, User> usersMap = new HashMap<>();

    @Override
    public void sendMessage(String msg, String userId) {
        User u = usersMap.get(userId);
        u.receive(msg);
    }

    @Override
    public void addUser(User user) {
        this.usersMap.put(user.getId(), user);
    }
}

abstract class User {
    private IChatRoom mediator;

    private String id;
    private String name;

    public User(IChatRoom room, String id, String name) {
        this.mediator = room;
        this.name = name;
        this.id = id;
    }

    public abstract void send(String msg, String userId);

    public abstract void receive(String msg);

    public IChatRoom getMediator() {
        return mediator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class ChatUser extends User {

    public ChatUser(IChatRoom room, String id, String name) {
        super(room, id, name);
    }

    @Override
    public void send(String msg, String userId) {
        System.out.println(this.getName() + " :: Sending Message : " + msg);
        getMediator().sendMessage(msg, userId);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " :: Received Message : " + msg);
    }

}

public class Mediator {

    public static void main(String[] args)
    {
        IChatRoom chatroom = new ChatRoom();

        User user1 = new ChatUser(chatroom,"1", "Alex");
        User user2 = new ChatUser(chatroom,"2", "Brian");
        User user3 = new ChatUser(chatroom,"3", "Charles");
        User user4 = new ChatUser(chatroom,"4", "David");

        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);
        chatroom.addUser(user4);

        user1.send("Hello brian", "2");
        user2.send("Hey buddy", "1");
    }
}
