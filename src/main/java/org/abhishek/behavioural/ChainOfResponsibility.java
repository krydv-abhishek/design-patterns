package org.abhishek.behavioural;


/*
The main objective of this pattern is that it avoids coupling the sender of the request to the receiver,
giving more than one object the opportunity to handle the request.
Participants in the solution

1) Handler : This can be an interface which will primarily receive the request and dispatches the request
to chain of handlers. It has reference of only first handler in the chain and does not know anything about
rest of the handlers.
2) Concrete handlers : These are actual handlers of the request chained in some sequential order.

3) Client : Originator of request and this will access the handler to handle it.

E.g.javax.servlet.Filter#doFilter()

Usage:
This pattern is recommended when multiple objects can handle a request and the handler
does not have to be a specific object. Also, handler is determined at runtime.
Please note that a request not handled at all by any handler is a valid use case.


 */

enum ServiceLevel {
    LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, INVALID_REQUEST
}

class ServiceRequest {

    private ServiceLevel type;
    private String conclusion = null;

    public ServiceLevel getType() {
        return type;
    }

    public void setType(ServiceLevel type) {
        this.type = type;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}

interface SupportServiceItf {
    void handleRequest(ServiceRequest request);
}

//Handler
class SupportService implements SupportServiceItf {
    private SupportServiceItf handler = null;

    public SupportServiceItf getHandler() {
        return handler;
    }

    public void setHandler(SupportServiceItf handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(ServiceRequest request) {
        handler.handleRequest(request);
    }
}

//Concrete handlers
class FrontDeskSupport implements SupportServiceItf {
    private SupportServiceItf next = null;

    public SupportServiceItf getNext() {
        return next;
    }

    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest service) {
        if (service.getType() == ServiceLevel.LEVEL_ONE) {
            service.setConclusion("Front desk solved level one request !!");
        } else {
            if (next != null) {
                next.handleRequest(service);
            } else {
                throw new IllegalArgumentException("No handler found for ::" + service.getType());
            }
        }
    }
}

class SuperVisorSupport implements SupportServiceItf {
    private SupportServiceItf next = null;

    public SupportServiceItf getNext() {
        return next;
    }

    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest service) {
        if (service.getType() == ServiceLevel.LEVEL_TWO) {
            service.setConclusion("Supervisor solved level one request !!");
        } else {
            if (next != null) {
                next.handleRequest(service);
            } else {
                throw new IllegalArgumentException("No handler found for ::" + service.getType());
            }
        }
    }
}

class ManagerSupport implements SupportServiceItf {
    private SupportServiceItf next = null;

    public SupportServiceItf getNext() {
        return next;
    }

    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest service) {
        if (service.getType() == ServiceLevel.LEVEL_THREE) {
            service.setConclusion("Manger solved level one request !!");
        } else {
            if (next != null) {
                next.handleRequest(service);
            } else {
                throw new IllegalArgumentException("No handler found for ::" + service.getType());
            }
        }
    }
}

class DirectorSupport implements SupportServiceItf {
    private SupportServiceItf next = null;

    public SupportServiceItf getNext() {
        return next;
    }

    public void setNext(SupportServiceItf next) {
        this.next = next;
    }

    @Override
    public void handleRequest(ServiceRequest service) {
        if (service.getType() == ServiceLevel.LEVEL_FOUR) {
            service.setConclusion("Director solved level one request !!");
        } else {
            if (next != null) {
                next.handleRequest(service);
            } else {
                service.setConclusion("Problem is none of our business");
            }
        }
    }
}


public class ChainOfResponsibility {

    public static void main(String[] args) {

        SupportService supportService = new SupportService();
        FrontDeskSupport frontDeskSupport = new FrontDeskSupport();
        SuperVisorSupport superVisorSupport = new SuperVisorSupport();
        ManagerSupport managerSupport = new ManagerSupport();
        DirectorSupport directorSupport = new DirectorSupport();

        supportService.setHandler(frontDeskSupport);
        frontDeskSupport.setNext(superVisorSupport);
        superVisorSupport.setNext(managerSupport);
        managerSupport.setNext(directorSupport);

        ServiceRequest request = new ServiceRequest();
        request.setType(ServiceLevel.LEVEL_ONE);
        supportService.handleRequest(request);
        System.out.println(request.getConclusion());

        request = new ServiceRequest();
        request.setType(ServiceLevel.LEVEL_THREE);
        supportService.handleRequest(request);
        System.out.println(request.getConclusion());

        request = new ServiceRequest();
        request.setType(ServiceLevel.INVALID_REQUEST);
        supportService.handleRequest(request);
        System.out.println(request.getConclusion());

    }
}
