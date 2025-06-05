package org.abhishek.structural;

/*

A proxy object provide a surrogate or placeholder for another object to control access to it.
A proxy is basically a substitute for an intended object which we create due to many reasons
e.g. security reasons or cost associated with creating fully initialized original object.

Design participants

1) Subject – is an interface which expose the functionality available to be used by the clients.
2) Real Subject – is a class implementing Subject, it is concrete implementation which needs to
be hidden behind a proxy.
3) Proxy – hides the real object by extending it and clients communicate to real object via this proxy
object. Usually frameworks create this proxy object when client request for real object.


Usage:
1) Proxy is heavily used to implement lazy loading related use cases where we do not want to
create full object until it is actually needed.
2) A proxy can be used to add a security layer around the original object as well.

Example:
1) Hibernate proxy on domain class
2) Internet in corporation

 */

interface RealObject {
    void doSomething();
}

class RealObjectImpl implements RealObject {

    @Override
    public void doSomething() {
        System.out.println("Performing work in real object");
    }

}

class RealObjectProxy extends RealObjectImpl {
    @Override
    public void doSomething() {
        //Perform additional logic and security
        //Even we can block the operation execution
        System.out.println("Delegating work on real object");
        super.doSomething();
    }
}

public class Proxy {

    public static void main(String[] args) {
        RealObject proxy = new RealObjectProxy();
        proxy.doSomething();
    }
}
