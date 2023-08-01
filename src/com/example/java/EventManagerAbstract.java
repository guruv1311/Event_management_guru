package com.example.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

abstract class EventManagerAbstract {
    protected Connection connection;

    public EventManagerAbstract(Connection connection) {
        this.connection = connection;
    }

    public abstract void createEvent() throws SQLException;
    public abstract void Update() throws SQLException;
    public abstract void addAttendee() throws SQLException;
    public abstract void addeventAttendee(String eventName) throws SQLException;
    public abstract void addtaskDetails(String eventName) throws SQLException;
    public abstract void Delete() throws SQLException;
    public abstract void ViewEvents(String eventname) throws SQLException;
    public abstract void ViewEvents() throws SQLException;
    
}
