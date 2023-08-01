package com.example.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class EventManager extends EventManagerAbstract{
//    private static Connection connection = null;

    public EventManager(Connection connection) {
        super(connection);
    }
    @Override
    public  void createEvent() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name:");
        String eventName = scanner.nextLine();
        System.out.println("Enter event date (YYYY-MM-DD):");
        String eventDate = scanner.nextLine();
        System.out.println("Enter event location:");
        String eventLocation = scanner.nextLine();
        System.out.println("Enter event time:");
        String eventTime = scanner.nextLine();
        
        String sql = "INSERT INTO events_details (event_name, event_date, event_location, event_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventName);
            statement.setString(2, eventDate);
            statement.setString(3, eventLocation);
            statement.setString(4, eventTime);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        while(true)
        {
        	System.out.println("*****************************");            
            System.out.println("*  1. Add Event Task        *");
            System.out.println("*  2. Add Attendees Name    *");
            System.out.println("*  3. Exit                  *");
            System.out.println("*****************************");

            System.out.print("Enter your choice: ");
            int ch = scanner.nextInt();

            switch (ch) {
                
	            case 1:
	            	addtaskDetails(eventName);
	            	break;
                case 2:
                    addeventAttendee(eventName);
                    break;
                case 3:
                    System.out.println("Thank You!");
		            System.out.println("Event created successfully!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    @Override
    public  void Update() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name:");
        String eventName = scanner.nextLine();
        String check = "select event_name from events_details where event_name in (select event_name from events_details) and event_name = ?";
    	PreparedStatement st1 = connection.prepareStatement(check);
    	st1.setString(1,eventName);
    	ResultSet rs = st1.executeQuery();
    	if(!rs.next()) {
    		System.out.println();
    		System.out.println("No event found with name '" + eventName + "'");
    		return;
    	}
        System.out.println("Enter event date (YYYY-MM-DD):");
        String eventDate = scanner.nextLine();
        System.out.println("Enter event location:");
        String eventLocation = scanner.nextLine();
        System.out.println("Enter event time:");
        String eventTime = scanner.nextLine();
        
        String sql = "UPDATE events_details SET  event_date = ?, event_location = ? ,event_time = ? WHERE event_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(4, eventName);
            statement.setString(1, eventDate);
            statement.setString(2, eventLocation);
            statement.setString(3, eventTime);
            statement.executeUpdate();
            System.out.println("Updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void addAttendee() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name:");
        String eventName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter attendee name:");
        String attendeeName = scanner.nextLine();

        String sql = "INSERT INTO attendees_details (attendee_name, event_name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, attendeeName);
            statement.setString(2, eventName);
            statement.executeUpdate();
            System.out.println("Attendee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void addeventAttendee(String eventName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter attendee name :");
        String attendeeName = scanner.nextLine();

        String sql = "INSERT INTO event_attendees_details (event_attendee_name, event_name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, attendeeName);
            statement.setString(2, eventName);
            statement.executeUpdate();
            System.out.println("Attendee added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void addtaskDetails(String eventName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event task  name:");
        String taskname = scanner.nextLine();
       
        String sql = "INSERT INTO events_task (event_task_name, event_name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taskname);
            statement.setString(2, eventName);
            statement.executeUpdate();
            System.out.println("Task added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void Delete() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the event name:");
        String eventName = scanner.nextLine();
       
        String sql = "DELETE from events_details WHERE event_name= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventName);
            statement.executeUpdate();
//            System.out.println("Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql1 = "DELETE from event_attendees_details WHERE event_name= ?";
        try (PreparedStatement statement1 = connection.prepareStatement(sql1)) {
            statement1.setString(1, eventName);
            statement1.executeUpdate();
//            System.out.println("Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "DELETE from attendees_details WHERE event_name= ?";
        try (PreparedStatement statement2 = connection.prepareStatement(sql2)) {
            statement2.setString(1, eventName);
            statement2.executeUpdate();
//            System.out.println("Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql3 = "DELETE from events_task WHERE event_name= ?";
        try (PreparedStatement statement3 = connection.prepareStatement(sql3)) {
            statement3.setString(1, eventName);
            statement3.executeUpdate();
            System.out.println("Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public   void ViewEvents(String eventname) throws SQLException {
  
        String sql = "SELECT * FROM events_details WHERE event_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                	System.out.println(" ________________________________");
        			System.out.println("|         Event Details          |");
        			System.out.println("|--------------------------------|");
        			System.out.printf("| %-30s |\n",("Event name     : " +resultSet.getString("event_name")));
        			System.out.printf("| %-30s |\n",("Event location : " + resultSet.getString("event_location")));
        			System.out.printf("| %-30s |\n",("Event date     : " + resultSet.getString("event_date")));
        			System.out.printf("| %-30s |\n",("Event time     : " +resultSet.getString("event_time")));
        			System.out.println("|________________________________|");
                    String attendeesSql = "SELECT event_task_name FROM events_task WHERE event_name = ?";
                    try (PreparedStatement attendeesStatement = connection.prepareStatement(attendeesSql)) {
                        attendeesStatement.setString(1, eventname);
                        try (ResultSet attendeesResultSet = attendeesStatement.executeQuery()) {
                        	System.out.println(" ___________________________ ");
            				System.out.println("|    Event Task             |");
            				System.out.println("|---------------------------|");
                            
                            while (attendeesResultSet.next()) {
                                System.out.printf("|     %-17s     |\n",attendeesResultSet.getString("event_task_name"));
                            }
                            System.out.println("|___________________________|");
                        }
                    }

                    String attendeesSql1 = "SELECT a.attendee_name FROM attendees_details a INNER JOIN event_attendees_details b ON a.attendee_name = b.event_attendee_name where a.event_name= ? and b.event_name= ?";
                    try (PreparedStatement attendeesStatement1 = connection.prepareStatement(attendeesSql1)) {
                        attendeesStatement1.setString(1, eventname);
                        attendeesStatement1.setString(2, eventname);
                        try (ResultSet attendeesResultSet1 = attendeesStatement1.executeQuery()) {
                        	System.out.println(" _________________ ");
            				System.out.println("|  Attended List  |");
            				System.out.println("|-----------------|");
                            while (attendeesResultSet1.next()) {
                                System.out.printf("|   %-12s  |\n", attendeesResultSet1.getString("attendee_name"));
                            }
                            System.out.println("|_________________|");
                        }
                    }

                    String attendeesSq2 = "SELECT b.event_attendee_name FROM event_attendees_details b LEFT JOIN attendees_details a ON b.event_attendee_name= a.attendee_name WHERE a.attendee_name IS NULL  and b.event_name= ?";
                    try (PreparedStatement attendeesStatement2 = connection.prepareStatement(attendeesSq2)) {
                        attendeesStatement2.setString(1, eventname);
                        try (ResultSet attendeesResultSet2 = attendeesStatement2.executeQuery()) {
                        	System.out.println(" ______________________ ");
            				System.out.println("|  Not Attendeed List  |");
            				System.out.println("|----------------------|");                            
            				while (attendeesResultSet2.next()) {
                                System.out.printf("|    %-12s      |\n" ,attendeesResultSet2.getString("event_attendee_name"));
                            }
                            System.out.println("|______________________|");

                        }
                    }
                    
                    String attendeesSq3 = " select A.attendee_name from event_attendees_details E right join attendees_details A on A.attendee_name = E.event_attendee_name where E.event_attendee_name is null and A.event_name = ?";
                    try (PreparedStatement attendeesStatement3 = connection.prepareStatement(attendeesSq3)) {
                        attendeesStatement3.setString(1, eventname);
                        try (ResultSet attendeesResultSet3 = attendeesStatement3.executeQuery()) {
                        	System.out.println(" ____________________ ");
            				System.out.println("|  Not Invited List  |");
            				System.out.println("|--------------------|");                            
            				while (attendeesResultSet3.next()) {
                                System.out.printf("|    %-12s    |\n" ,attendeesResultSet3.getString("attendee_name"));
                            }
                            System.out.println("|____________________|");

                        }
                    }
                } else {
                    System.out.println("Event not found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void ViewEvents() throws SQLException {
       
        String sql = "SELECT * FROM events_details";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
            	System.out.println(" _____________________________________________________________");
				System.out.printf("| %-15s | %-15s | %-10s | %-10s |\n","Event Name","Event Location","Event Date","Event Time");
				System.out.println("|-------------------------------------------------------------|");
		
            	while(resultSet.next())
                {
        			System.out.printf("| %-15s | %-15s | %-10s | %-10s |\n",resultSet.getString(1),resultSet.getString(3),resultSet.getString(2),resultSet.getString(4));
                }
        		System.out.println("|_________________|_________________|____________|____________|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}