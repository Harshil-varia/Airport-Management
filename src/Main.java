/*\
@author Harshil Rakeshkumar Varia

I had an concept of developing and managing a airport database,
 so came up with a fun project trying to implement my self-learned dbms and sql skills.

 What does this project do? what will I do with it later this year?

-Essentially this project manages and keeps track of all Arriving and Departing flight on a particular airport,
 which can be used by customers to manage thier valuable time on airport and prepare for rest of their journey.
 And same way ATC(Air Traffic Control) can look at all the flight arriving or departing with less time difference and free up the runway.

-Later this year I plan to deploy a fully working airline website using my webdevelopment skills and this project will act as the backend.

How did I plan this project and finish working on it in a set time?
- every day I use to set a deadline for finishing a part from the project and think about what should I do next and avoid bugs.

Thats enough talk please feel free to tryout my project and let me know the feedbacks.
 */

// importing all required packages..
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;


public class Main{
    public static void main(String[] args) throws SQLException {
        Menu(); // lets keep the main method clean and define static methods.
    }


    public static void Menu() throws SQLException { // most crucial part of source code

        Scanner choice= new Scanner(System.in);
        System.out.println("----------------- MENU --------------------");
        System.out.println();
        System.out.println();
        System.out.println("1. ARRIVALS");
        System.out.println("2. DEPARTURE");
        System.out.println("ENTER CHOICE FROM(1/2):");
        int userC= choice.nextInt();

        if(userC==1){
            arrivals(choice);

        }else if (userC==2){
            departure(choice);
        }


    }

// ARRIVALS FUNCTIONS

    public static void arrivals(Scanner choice) throws SQLException {
        // ARRIVAL MENU
        System.out.println("PLEASE ENTER YOUR CHOICE FROM:");
        System.out.println("         1. SHOW ARRIVALS");
        System.out.println("         2. ADD TO ARRIVALS");
        System.out.println("         3. SEARCH FOR FLIGHTS");
        System.out.println("         4. UPDATE ARRIVALS");
        System.out.println("         5. DELETE FROM ARRIVALS");
        System.out.println("         6. SEE OVERALL FLIGHTS ARRIVED");
        System.out.println("         7. GO BACK");
        System.out.println("ENTER CHOICE FROM(1/7):");
        int userC1= choice.nextInt();

        if(userC1==1) {
            showArrival();
        }else if(userC1==2){
            addArrival(choice);
        }else if (userC1==3){
            searchArrival(choice);
        }else if(userC1==4){
            updateArrival(choice);
        }else if(userC1==5){
            deleteArrival(choice);
        }else if( userC1==6){
            arrivedFlights();
        }else if(userC1==7){
            Menu();
        } else{
            System.out.println("INVALID INPUT TRY AGAIN.....");
            arrivals(choice);

        }

    }
    // DEFINING METHODS FOR ARRIVAL MENU
    public static void showArrival() throws SQLException {
        System.out.println("ARRIVALS");
        selectS("select * from Arrival;"); // DISPLAYS ALL RECORDS FROM ARRIVAL TABLES

    }


    public static void addArrival(Scanner choice) throws SQLException {
        System.out.println("Add ARRIVALS");
        System.out.println("ENTER FLIGHT NUMBER (FORMAT- XY0000): ");
        String flight_no=choice.next();
        System.out.println(" AIRINDIA, INDIGO, QATAR, ETIHAD, EMIRATES, RYAN");
        String flight_name=choice.next();

        System.out.println("ENTER ARRIVING TO LOCATION");
        String arrivingF= choice.next();

        System.out.println("ENTER YYYY MM DD HH MM SS(DATETIME):");

        LocalDateTime myDateObj = LocalDateTime.of(choice.nextInt(),choice.nextInt(),choice.nextInt(), choice.nextInt(), choice.nextInt(), choice.nextInt());

        System.out.println("CHOOSE FROM: T1,T2,T3");
        String terminal_G= choice.next();

        System.out.println("ENTER CURRENT STATUS");
        String stat=choice.next();

        String query="insert into Arrival values (?,?,?,?,?,?)";
        insertS(query,flight_no,flight_name,arrivingF,myDateObj,terminal_G,stat);

        System.out.println("Query Executed..");
        arrivals(choice);
    }

    public static void searchArrival(Scanner choice) throws SQLException {
        System.out.println("Searching");
        System.out.println("WHAT WOULD YOU LIKE TO SEARCH FOR FLIGHTS BY- 1.FLIGHT NAME OR 2.ARRIVING FROM:");
        System.out.println("ENTER CHOICE FROM (1/2): ");
        int userC2= choice.nextInt();
        if (userC2==1){
            System.out.println(" YOU CAN CHOOSE FROM:");
            System.out.println("1.AIRINDIA");
            System.out.println("2.EMIRATES");
            System.out.println("3.QATAR");
            System.out.println("4.ETIHAD");
            System.out.println("5.SPICEJET");
            System.out.println("6.RYAN");
            System.out.println("7.INDIGO");
            String flightName=choice.next();
            String query= "select * from Arrival where Name=?;";
            searchS(query,flightName);
        }else if(userC2==2){
            System.out.println("ENTER THE FLIGHT ARRIVING FROM LOCATION:");
            String arrivingFrom=choice.next();
            String query= "select * from Arrival where Arriving_from=?;";
            searchS(query,arrivingFrom);
        }
    }

    public static void updateArrival(Scanner choice) throws SQLException{
        System.out.println("update Arrr");
        System.out.println("WHAT WOULD YOU LIKE TO UPDATE:");
        System.out.println("1.CURRENT STATUS OF FLIGHT.");
        System.out.println("2.ARRIVING TIME OF THE FLIGHT");
        System.out.println("3.TERMINAL NUMBER");
        System.out.println("ENTER YOUR CHOICE(1/3):");
        int userC3= choice.nextInt();
        if(userC3==1){
            System.out.println("ENTER FLIGHT NUMBER");
            String flight_no=choice.next();

            System.out.println("ENTER NEW STATUS");
            String newStat=choice.next();

            String query="update Arrival set Status=? where flight_number=?";
            updateS(query,newStat,flight_no);
        }else if(userC3==2) {
            System.out.println("ENTER FLIGHT NUMBER");
            String flightno=choice.next();
            System.out.println("ENTER NEW DATE AND TIME OF ARRIVAL - YYYY MM DD HH MM SS");
            LocalDateTime myDateObj = LocalDateTime.of(choice.nextInt(),choice.nextInt(),choice.nextInt(), choice.nextInt(), choice.nextInt(), choice.nextInt());
            String query="update Arrival set Arriving_time=? where flight_number=?";
            updateS(query,myDateObj,flightno);
        }else if(userC3==3){
            System.out.println("ENTER FLIGHT NUMBER");
            String flightno=choice.next();
            System.out.println("ENTER NEW TERMINAL NUMBER(1-3):");
            String newGate= choice.next();
            String query="update Arrival set Terminal=? where flight_number=?";
            updateS(query,newGate,flightno);
        }
        System.out.println("");
    }

    public static void deleteArrival(Scanner choice) throws SQLException {
        System.out.println("DELETE");
        System.out.println("DELETE");
        showArrival();
        System.out.println("SELECT FLIGHT NUMBER FROM THE TABLE TO BE DELETED:");
        String flight_no=choice.next();
        String query="delete from Arrival where Flight_number=?";
        deleteS(query,flight_no);
    }

    public static void arrivedFlights() throws SQLException {
        String query="select * from Ar" +
                "rival where Status='ARRIVED';";
        statusS(query);
    }



    // DEPARTURES
    public static void departure(Scanner choice) throws SQLException {
        System.out.println("PLEASE ENTER YOUR CHOICE FROM:");
        System.out.println("         1. SHOW DEPARTURES");
        System.out.println("         2. ADD TO DEPARTURES");
        System.out.println("         3. SEARCH FOR DEPARTURES");
        System.out.println("         4. UPDATE DEPARTURES");
        System.out.println("         5. DELETE FROM DEPARTURES");
        System.out.println("         6. SEE OVERALL FLIGHTS DEPARTED");
        System.out.println("         7. GO BACK");
        System.out.println("ENTER CHOICE FROM(1/7):");
        int userC1= choice.nextInt();

        if(userC1==1) {
            showDeparture();
        }else if(userC1==2){
            addDeparture(choice);
        }else if (userC1==3){
            searchDeparture(choice);
        }else if(userC1==4){
            updateDeparture(choice);
        }else if(userC1==5){
            deleteDeparture(choice);
        }else if( userC1==6){
            departedFlight();
        }else if(userC1==7){
            Menu();
        } else{
            System.out.println("INVALID INPUT TRY AGAIN.....");
            departure(choice);

        }

    }
    public static void showDeparture() throws SQLException {
        System.out.println("Departure");
        selectS("select * from Departure");
    }


    public static void addDeparture(Scanner choice) throws SQLException {
        System.out.println("Add Departure");
        System.out.println("ENTER FLIGHT NUMBER (FORMAT- XY0000): ");
        String flight_no=choice.next();
        System.out.println(" AIRINDIA, INDIGO, QATAR, ETIHAD, EMIRATES, RYAN");
        String flight_name=choice.next();

        System.out.println("ENTER DEPARTING LOCATION");
        String arrivingF= choice.next();

        System.out.println("ENTER YYYY MM DD HH MM SS(DATETIME):");

        LocalDateTime myDateObj = LocalDateTime.of(choice.nextInt(),choice.nextInt(),choice.nextInt(), choice.nextInt(), choice.nextInt(), choice.nextInt());


        System.out.println(" CHOOSE FROM (1-40): ");
        String terminal_G= choice.next();

        System.out.println("ENTER CURRENT STATUS");
        String stat=choice.next();

        String query="insert into Departure values (?,?,?,?,?,?)";
        insertS(query,flight_no,flight_name,arrivingF,myDateObj,terminal_G,stat);

        System.out.println("Query Executed..");
        departure(choice);

    }

    public static void searchDeparture(Scanner choice) throws SQLException {
        System.out.println("Searching");
        System.out.println("Searching");
        System.out.println("WHAT WOULD YOU LIKE TO SEARCH FOR FLIGHTS BY- 1.FLIGHT NAME OR 2.ARRIVING FROM:");
        System.out.println("ENTER CHOICE FROM (1/2): ");
        int userC2= choice.nextInt();
        if (userC2==1){
            System.out.println(" YOU CAN CHOOSE FROM:");
            System.out.println("1.AIRINDIA");
            System.out.println("2.EMIRATES");
            System.out.println("3.QATAE");
            System.out.println("4.ETIHAD");
            System.out.println("5.SPICEJET");
            System.out.println("6.RYAN");
            System.out.println("7.INDIGO");
            String flightName=choice.next();
            String query= "select * from Departure where Name=?;";
            searchS(query,flightName);
        }else if(userC2==2){
            System.out.println("ENTER THE FLIGHT DEPARTING TO LOCATION:");
            String departingTo=choice.next();
            String query= "select * from Arrival where Arriving_from=?;";
            searchS(query,departingTo);
        }
    }

    public static void updateDeparture(Scanner choice) throws SQLException {
        System.out.println("update DEPART");
        System.out.println("WHAT WOULD YOU LIKE TO UPDATE:");
        System.out.println("1.CURRENT STATUS OF FLIGHT.");
        System.out.println("2.DEPARTING TIME OF THE FLIGHT");
        System.out.println("3.GATE NUMBER");
        System.out.println("ENTER YOUR CHOICE(1/3):");
        int userC3= choice.nextInt();
        if(userC3==1){
            System.out.println("ENTER FLIGHT NUMBER");
            String flight_no=choice.next();

            System.out.println("ENTER NEW STATUS");
            String newStat=choice.next();

            String query="update Departure set Status=? where flight_number=?";
            updateS(query,newStat,flight_no);
        }else if(userC3==2){
            System.out.println("ENTER FLIGHT NUMBER");
            String flightno=choice.next();
            System.out.println("ENTER NEW DATE AND TIME OF DEPARTURE - YYYY MM DD HH MM SS");
            LocalDateTime myDateObj = LocalDateTime.of(choice.nextInt(),choice.nextInt(),choice.nextInt(), choice.nextInt(), choice.nextInt(), choice.nextInt());
            String query="update Departure set Departing_time=? where flight_number=?";
            updateS(query,myDateObj,flightno);
        }else if(userC3==3){
            System.out.println("ENTER FLIGHT NUMBER");
            String flightno=choice.next();
            System.out.println("ENTER NEW GATE NUMBER(1-40):");
            String newGate= choice.next();
            String query="update Departure set Gate=? where flight_number=?";
            updateS(query,newGate,flightno);
        }
        System.out.println("");
    }



    public static void deleteDeparture(Scanner choice) throws SQLException {
        System.out.println("DELETE");
        showDeparture();
        System.out.println("SELECT FLIGHT NUMBER FROM THE TABLE TO BE DELETED:");
        String flight_no=choice.next();
        String query="delete from Departure where Flight_number=?";
        deleteS(query,flight_no);

    }

    public static void departedFlight() throws SQLException {
        String query="select * from Departure where Status='DEPARTED';";
        statusS(query);
    }


    // Printing all the fields

    public static void selectS(String query) throws SQLException {

        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        Statement q= con.createStatement();



        ResultSet res= q.executeQuery(query);
        System.out.println();


        while(res.next()){
            String flight_no=res.getString(1);
            String name= res.getString(2);
            String arrival=res.getString(3);
            String arrivalTime= String.valueOf(res.getTimestamp(4));
            String terminal=res.getString(5);
            String status=res.getString(6);

            System.out.println(flight_no+"  "+ name+"   "+arrival+"   "+arrivalTime+"  "+terminal+"   "+status);
        }
        con.close();


    }

    public static void insertS(String query, String flight_no, String flight_name, String arrivingF, LocalDateTime myDateObj, String terminal_G,String stat) throws SQLException{

        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        Statement q= con.createStatement();

        PreparedStatement stmt= con.prepareStatement(query);

        stmt.setString(1,flight_no.toUpperCase());
        stmt.setString(2,flight_name.toUpperCase());
        stmt.setString(3, arrivingF.toUpperCase());
        stmt.setObject(4, myDateObj);
        stmt.setString(5,terminal_G.toUpperCase());
        stmt.setString(6,stat.toUpperCase());

        stmt.executeUpdate();
        System.out.println("");

        con.close();

    }


    public static void searchS(String query, String flightName) throws SQLException{

        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        Statement q= con.createStatement();

        PreparedStatement stmt= con.prepareStatement(query);

        stmt.setString(1,flightName.toUpperCase());


        ResultSet res= stmt.executeQuery();

        boolean flag=true;

        while(res.next()){
            String flight_no=res.getString(1);
            String name= res.getString(2);
            String arrival=res.getString(3);
            String arrivalTime= String.valueOf(res.getTimestamp(4));
            String terminal=res.getString(5);
            String status=res.getString(6);

            System.out.println(flight_no+"  "+ name+"   "+arrival+"   "+arrivalTime+"  "+terminal+"   "+status);
            flag=false;

        }

        if(flag){
            System.out.println("----------NO RECORDS FOUND!!!!--------------");
        }

        con.close();
    }

    public static void updateS(String query, String newValue, String flight_no) throws SQLException{

        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        PreparedStatement stmt= con.prepareStatement(query);

        stmt.setString(1,newValue);
        stmt.setString(2,flight_no.toUpperCase());

        stmt.executeUpdate();
        System.out.println("...UPDATED....");

        con.close();



    }

    public static void updateS(String query, Object newValue, String flight_no) throws SQLException{ //for objects
        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        PreparedStatement stmt= con.prepareStatement(query);

        stmt.setObject(1,newValue);
        stmt.setString(2,flight_no.toUpperCase());

        stmt.executeUpdate();
        System.out.println("...UPDATED....");

        con.close();


    }

    public static void deleteS(String query, String flight_no) throws SQLException {

        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        PreparedStatement stmt= con.prepareStatement(query);

        stmt.setObject(1,flight_no);


        stmt.executeUpdate();
        System.out.println("...DELETED....");

        con.close();

    }


    public static void statusS(String query) throws SQLException{

        Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/Airport","root","root@2023");

        Statement q= con.createStatement();

        ResultSet res= q.executeQuery(query);
        System.out.println();


        while(res.next()){
            String flight_no=res.getString(1);
            String name= res.getString(2);
            String arrival=res.getString(3);
            String arrivalTime= String.valueOf(res.getTimestamp(4));
            String terminal=res.getString(5);
            String status=res.getString(6);

            System.out.println(flight_no+"  "+ name+"   "+arrival+"   "+arrivalTime+"  "+terminal+"   "+status);
        }

        con.close();


    }



}




