package org.abhishek.structural;

/*
Facade Method Design Pattern provides a unified interface to a set of interfaces in a subsystem.
Facade defines a high-level interface that makes the subsystem easier to use.

Design participants:

1.Facade
Facade knows which subsystem classes are responsible for a request.
It delegates client requests to appropriate subsystem objects.

2. Subsystem classes
It implement subsystem functionality.
It handles work assigned by the Facade object.
It has no knowledge of the facade; that is, they keep no references to it.

3. Interface
The Interface in the Facade Design Pattern refers to the set of simplified methods that the facade exposes to
the client. It hides the complexities of the subsystem, ensuring that clients interact only with high-level
operations, without dealing with the underlying details of the system.
 */

/*

Problem:
Let’s consider a hotel. This hotel has a hotel keeper. There are a lot of restaurants inside the hotel
e.g. Veg restaurants, Non-Veg restaurants, and Veg/Non Both restaurants. You, as a client want access to
different menus of different restaurants.

You do not know what are the different menus they have. You just have access to a hotel keeper who knows his
hotel well.Whichever menu you want, you tell the hotel keeper, and he takes it out of the respective restaurants
and hands it over to you.

Hotel-Keeper is Facade and respective Restaurants is system

 */

import javax.sql.DataSource;

class ReportHeader {

}

class ReportFooter {

}

class ReportData {

}

enum ReportType {
    PDF, HTML
}

class Report {

    private ReportHeader header;
    private ReportData data;
    private ReportFooter footer;

    public ReportHeader getHeader() {
        return header;
    }

    public void setHeader(ReportHeader header) {
        System.out.println("Setting report header");
        this.header = header;
    }

    public ReportData getData() {
        return data;
    }

    public void setData(ReportData data) {
        System.out.println("Setting report data");
        this.data = data;
    }

    public ReportFooter getFooter() {
        return footer;
    }

    public void setFooter(ReportFooter footer) {
        System.out.println("Setting report footer");
        this.footer = footer;
    }
}

class ReportWriter {

    public void writeHtmlReport(Report report, String location) {
        System.out.println("HTML Report written");

        //implementation
    }

    public void writePdfReport(Report report, String location) {
        System.out.println("Pdf Report written");

        //implementation
    }
}

class ReportGeneratorFacade {

    //facade method
    public static void generateReport(ReportType type, DataSource dataSource, String location) {
        if (type == null || dataSource == null) {
            //throw some exception
        }
        //Create report
        Report report = new Report();

        //abstracted steps of report subsystem
        report.setHeader(new ReportHeader());
        report.setFooter(new ReportFooter());
        report.setData(new ReportData());

        //abstracted steps of writer subsystem
        ReportWriter writer = new ReportWriter();
        switch (type) {
            case HTML:
                writer.writeHtmlReport(report, location);
                break;

            case PDF:
                writer.writePdfReport(report, location);
                break;
        }
    }
}

public class Facade {

    public static void main(String[] args) throws Exception {
        ReportGeneratorFacade reportGeneratorFacade = new ReportGeneratorFacade();

        ReportGeneratorFacade.generateReport(ReportType.HTML, null, null);

        ReportGeneratorFacade.generateReport(ReportType.PDF, null, null);
    }
}
