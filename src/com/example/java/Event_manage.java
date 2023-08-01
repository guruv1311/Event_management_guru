package com.example.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Event_manage extends EventManager{
    public Event_manage(Connection connection) {
		super(connection);
	}
	public void mainop() {
        try 
        {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("*********************************");

                System.out.println("* Event Management :            *");
                System.out.println("* -------------------           *");
                System.out.println("* 1. Create New Event           *");
                System.out.println("* 2. View Events                *");
                System.out.println("* 3. Enter the Attendee Detail  *");
                System.out.println("* 4. View Event Details         *");
                System.out.println("* 5. Delete Details             *");
                System.out.println("* 6. Update Details             *");
                System.out.println("* 7. Exit                       *");
                System.out.println("*********************************");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createEvent();
                        break;
                    case 2:
                    	ViewEvents();
                    	break;
                    case 3:
                        addAttendee();
                        break;
                    case 4:
                    	scanner.nextLine();
                    	System.out.println("Enter the event name : ");
                    	String eventname=scanner.nextLine();
                    	ViewEvents(eventname);
                        break;
                    case 5:
                        Delete();
                        break;
                    case 6:
                        Update();
                        break;
                    case 7:
                        System.out.println("Thank You !");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

















//
//class EventManager {
//    private final Connection connection;
//
//    public EventManager(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void createEvent() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter event name:");
//        String eventName = scanner.nextLine();
//        System.out.println("Enter event date (YYYY-MM-DD):");
//        String eventDate = scanner.nextLine();
//        System.out.println("Enter event location:");
//        String eventLocation = scanner.nextLine();
//        System.out.println("Enter event time:");
//        String eventTime = scanner.nextLine();
//        
//        String sql = "INSERT INTO events_details (event_name, event_date, event_location, event_time) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, eventName);
//            statement.setString(2, eventDate);
//            statement.setString(3, eventLocation);
//            statement.setString(4, eventTime);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        while(true)
//        {
//        	System.out.println("*****************************");            
//            System.out.println("* 1. Add Event Task         *");
//            System.out.println("* 2. Add Attendees Name     *");
//            System.out.println("* 3. Exit                   *");
//            System.out.println("*****************************");
//
//            System.out.print("Enter your choice: ");
//            int ch = scanner.nextInt();
//
//            switch (ch) {
//                
//	            case 1:
//	            	addtaskDetails(eventName);
//	            	break;
//                case 2:
//                    addeventAttendee(eventName);
//                    break;
//                case 3:
//                    System.out.println("Thank You!");
//		            System.out.println("Event created successfully!");
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//        }
//    }
//
//    public void addAttendee() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter event name:");
//        String eventName = scanner.next();
//        scanner.nextLine();
//        System.out.println("Enter attendee name:");
//        String attendeeName = scanner.nextLine();
//
//        String sql = "INSERT INTO attendees_details (attendee_name, event_name) VALUES (?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, attendeeName);
//            statement.setString(2, eventName);
//            statement.executeUpdate();
//            System.out.println("Attendee added successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void addeventAttendee(String eventName) throws SQLException {
//        Scanner scanner = new Scanner(System.in);
////        System.out.println("Enter event name:");
////        String eventName = scanner.next();
////        scanner.nextLine();
//        System.out.println("Enter attendee name :");
//        String attendeeName = scanner.nextLine();
//
//        String sql = "INSERT INTO event_attendees_details (event_attendee_name, event_name) VALUES (?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, attendeeName);
//            statement.setString(2, eventName);
//            statement.executeUpdate();
//            System.out.println("Attendee added successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void addtaskDetails(String eventName) throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter event task  name:");
//        String taskname = scanner.nextLine();
//       
//        String sql = "INSERT INTO events_task (event_task_name, event_name) VALUES (?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, taskname);
//            statement.setString(2, eventName);
//            statement.executeUpdate();
//            System.out.println("Task added successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void viewEventDetails() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter event name:");
//        String eventname = scanner.next();
//
//        String sql = "SELECT * FROM events_details WHERE event_name = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, eventname);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    System.out.println("-----------------------------------");
//                    System.out.println("Event Name: " + resultSet.getString("event_name"));
//                    System.out.println("Event Date: " + resultSet.getString("event_date"));
//                    System.out.println("Event Location: " + resultSet.getString("event_location"));
//                    System.out.println("Event Time: " + resultSet.getString("event_time"));
//                    System.out.println("-----------------------------------");
//
//                    String attendeesSql1 = "SELECT event_task_name FROM events_task WHERE event_name = ?";
//                    try (PreparedStatement attendeesStatement1 = connection.prepareStatement(attendeesSql1)) {
//                        attendeesStatement1.setString(1, eventname);
//                        try (ResultSet attendeesResultSet1 = attendeesStatement1.executeQuery()) {
//                            System.out.println("Event Task :");
//                            while (attendeesResultSet1.next()) {
//                                System.out.println("- " + attendeesResultSet1.getString("event_task_name"));
//                            }
//                        }
//                    }
//                    System.out.println("-----------------------------------");
//
//                    String attendeesSql = "SELECT attendees_details.attendee_name FROM event_attendees_details INNER JOIN attendees_details ON attendees_details.attendee_name =  event_attendees_details.event_attendee_name AND attendees_details.event_name = ?";
//                    try (PreparedStatement attendeesStatement = connection.prepareStatement(attendeesSql)) {
//                        attendeesStatement.setString(1, eventname);
//                        try (ResultSet attendeesResultSet = attendeesStatement.executeQuery()) {
//                            System.out.println("Attendees:");
//                            while (attendeesResultSet.next()) {
//                                System.out.println("- " + attendeesResultSet.getString("attendee_name"));
//                            }
//                        }
//                    }
//                } else {
//                    System.out.println("Event not found!");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void ViewEvents() throws SQLException {
//       
//        String sql = "SELECT * FROM events_details";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                System.out.println("-------------------------------------------------------------------");
//            	System.out.println("Event Name |     Event Time   |    Event location  |    Event Date ");
//                System.out.println("-------------------------------------------------------------------");
//            	while(resultSet.next())
//                {
//                	System.out.println(resultSet.getString(1)+"           "+resultSet.getString(2)+"            "+resultSet.getString(3)+"             "+resultSet.getString(4));
//                }
//                System.out.println("-------------------------------------------------------------------");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}