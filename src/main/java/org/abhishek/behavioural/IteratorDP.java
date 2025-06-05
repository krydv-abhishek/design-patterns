package org.abhishek.behavioural;

/*

An iterator pattern provides a way to access the elements of an aggregate object sequentially
without exposing its underlying representation.

Design participants

The participants of iterator pattern are as follows:

Iterator: An interface to access or traverse the elements collection.
          Provide methods which concrete iterators must implement.
ConcreteIterator: Implements the Iterator interface methods. It can also keep track of the current
                  position in the traversal of the aggregate collection.
Aggregate: It is typically a collection interface which defines a method that can create an Iterator object.
ConcreteAggregate: It implements the Aggregate interface and its specific method returns an instance of ConcreteIterator.

Usage:
If we have collection of objects and clients need a way to iterate over each collection elements
in some proper sequence, we must use iterator pattern to design the solution.

 */
class Topic {
    private String name;

    public Topic(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//Iterator
interface Iterator<E> {
    void reset(); // reset to the first element

    E next(); // To get the next element

    E currentItem();  // To retrieve the current element

    boolean hasNext();  // To check whether there is any next element or not.
}
//ConcreteIterator
class TopicIterator implements Iterator<Topic> {

    private Topic[] topics;
    private int position;

    public TopicIterator(Topic[] topics) {
        this.topics = topics;
        position = 0;
    }


    @Override
    public void reset() {
        position = 0;
    }

    @Override
    public Topic next() {
        return topics[position++];
    }

    @Override
    public Topic currentItem() {
        return topics[position];
    }

    @Override
    public boolean hasNext() {
        return position < topics.length;
    }
}

//Aggregate
interface List<E> {
    Iterator<E> iterator();
}

//ConcreteAggregate
class TopicList implements List<Topic> {
    private Topic[] topics;

    public TopicList(Topic[] topics) {
        this.topics = topics;
    }

    @Override
    public Iterator<Topic> iterator() {
        return new TopicIterator(topics);
    }
}

public class IteratorDP {
    public static void main(String[] args) {
        Topic[] topics = new Topic[5];
        topics[0] = new Topic("topic1");
        topics[1] = new Topic("topic2");
        topics[2] = new Topic("topic3");
        topics[3] = new Topic("topic4");
        topics[4] = new Topic("topic5");

        List<Topic> list = new TopicList(topics);

        Iterator<Topic> iterator = list.iterator();

        while (iterator.hasNext()) {
            Topic currentTopic = iterator.next();
            System.out.println(currentTopic.getName());
        }
    }
}
