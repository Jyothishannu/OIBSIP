//JAVA PROGRAM ON ONLINE RESERVATION SYSTEM USING DATABASE (MySQL). 


import java.sql.*;
import java.util.Random;
import java.util.Scanner;

 class Train {

    private static final int min = 1000;
    private static final int max = 9999;

    public static class user {
        private String username;
        private String password;

        Scanner sc = new Scanner(System.in);

        public String getUserName() {
            System.out.println("Enter username: ");
            username = sc.nextLine();
            return username;
        }

        public String getPassword() {
            System.out.println("Enter password: ");
            password = sc.nextLine();
            return password;
        }
    }

    public static class pnrRecord {
        private int PNR;
        private String PASSENGERNAME;
        private String TRAIN_NUMBER;
        private String CLASSTYPE;
        private String DO_JOURNEY;
        private String START;
        private String END;

        Scanner sc = new Scanner(System.in);

        public int getpnrNumber() {
            Random r = new Random();
            PNR = r.nextInt(max) + min;
            return PNR;
        }

        public String getPassengerName() {
            System.out.println("Enter the passenger Name: ");
            PASSENGERNAME = sc.nextLine();
            return PASSENGERNAME;
        }

        public String getTrainNumber() {
            System.out.println("Enter the train Number: ");
            TRAIN_NUMBER = sc.nextLine();
            return TRAIN_NUMBER;
        }

        public String getClassType() {
            System.out.println("Enter the class type : ");
            CLASSTYPE = sc.nextLine();
            return CLASSTYPE;
        }

        public String getJourneyDate() {
            System.out.println("Enter the journey date as 'YYYY-MM-DD' format: ");
            DO_JOURNEY = sc.nextLine();
            return DO_JOURNEY;
        }

        public String getStart() {
            System.out.println("Enter the starting place: ");
            START = sc.nextLine();
            return START;
        }

        public String getEnd() {
            System.out.println("Enter the destination place: ");
            END = sc.nextLine();
            return END;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        user u1 = new user();

        boolean q = true;
        String username = u1.getUserName();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinereservation", username, "")) {
                System.out.println("User accessed\n");
                while (q) {
                
                System.out.println("\n1.Reservation\n2.Cancellation\n3.exit\n\n");
                System.out.println("Please Enter the choice : ");
                int ch = sc.nextInt();


                    if (ch == 1) {
                        
                            String InsertQuery = "insert into details values(?,?,?,?,?,?,?)";

                            String DeleteQuery = "delete from details where PNR=?"; // Corrected column name

                            String ShowQuery = "select * from details";

                            String ShowpassenDetails = "select * from details where PNR=?";

                            System.out.println("1.Book ticket\n2.Show All Passenger Details\n3.Show Passenger details\n4.Exit The Application");
                            System.out.println("Please Enter the choice : ");

                            int choice = sc.nextInt();

                            switch (choice) {
                                case 1:
                                    pnrRecord p1 = new pnrRecord();

                                    int pnrNum = p1.getpnrNumber();
                                    String pname = p1.getPassengerName();
                                    String tnum = p1.getTrainNumber();
                                    String classtype2 = p1.getClassType();
                                    String jdate = p1.getJourneyDate();
                                    String startplace = p1.getStart();
                                    String endplace = p1.getEnd();

                                    try (PreparedStatement ps1 = con.prepareStatement(InsertQuery)) {
                                        ps1.setInt(1, pnrNum);
                                        ps1.setString(2, pname);
                                        ps1.setString(3, tnum);
                                        ps1.setString(4, classtype2);
                                        ps1.setString(5, jdate);
                                        ps1.setString(6, startplace);
                                        ps1.setString(7, endplace);

                                        int changeRowsInTable = ps1.executeUpdate();
                                        if (changeRowsInTable > 0) {
                                            System.out.println("The ticket is booked successfully\n Your respective PNR number is:" + pnrNum);
                                        } else {
                                            System.out.println("Sorry, Your Ticket has not booked yet.");
                                        }

                                    } catch (SQLException e) {
                                        System.out.println("SQLException: " + e.getMessage());
                                    }

                                    break;
                                case 2:
                                    try (PreparedStatement ps3 = con.prepareStatement(ShowQuery)) {
                                        ResultSet rs3 = ps3.executeQuery();
                                        while (rs3.next()) {
                                            int pnrNumb = rs3.getInt(1);
                                            String PasName = rs3.getString(2);
                                            String Trainname = rs3.getString(3);
                                            String classtyp = rs3.getString(4);
                                            String journeydat = rs3.getString(5);
                                            String started = rs3.getString(6);
                                            String ended = rs3.getString(7);

                                            System.out.print("PNR     Number: " + pnrNumb);
                                            System.out.print("Passe   Name: " + PasName);
                                            System.out.print("Train   Name: " + Trainname);
                                            System.out.print("Class   Type: " + classtyp);
                                            System.out.print("Journey Date: " + journeydat);
                                            System.out.print("From    Location: " + started);
                                            System.out.print("To Location: " + ended);

                                            System.out.println();

                                        }

                                    } catch (SQLException e) {
                                        System.out.println("SQL Exception " + e.getMessage());
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter PNR Number to get passenger details: ");

                                    int pnrnum1 = sc.nextInt();
                                    try (PreparedStatement ps4 = con.prepareStatement(ShowpassenDetails)) {
                                        ps4.setInt(1, pnrnum1);
                                        ResultSet rs4 = ps4.executeQuery();
                                        rs4.next();
                                        int pnrNumb1 = rs4.getInt(1);
                                        String PasName1 = rs4.getString(2);
                                        String Trainname1 = rs4.getString(3);
                                        String classtyp1 = rs4.getString(4);
                                        String journeydat1 = rs4.getString(5);
                                        String started1 = rs4.getString(6);
                                        String ended1 = rs4.getString(7);

                                        System.out.println("PNR     Number: " + pnrNumb1 + "   ");
                                        System.out.println("Passe   Name:   " + PasName1 + "   ");
                                        System.out.println("Train   Name:   " + Trainname1 + "   ");
                                        System.out.println("Class   Type:   " + classtyp1 + "   ");
                                        System.out.println("Journey Date:   " + journeydat1 + "   ");
                                        System.out.println("From    Location:" + started1 + "   ");
                                        System.out.println("To Location:     " + ended1);

                                    } catch (SQLException e) {
                                        System.out.println("SQL Exception " + e.getMessage());
                                    }
                                    break;
                                case 4:
                                    System.out.println("Exiting the program - Thank You");
                                    q = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice, Please Enter valid choice ");
                            }
                        
                    } else if (ch == 2) {
                        
                            String InsertQuery = "insert into details values(?,?,?,?,?,?,?)";

                            String DeleteQuery = "delete from details where PNR=?";

                            String ShowQuery = "select * from details";

                            String ShowpassenDetails = "select * from details where PNR=?";

                            System.out.println("Enter PNR Number to delete passenger details: ");

                            int pnrnum = sc.nextInt();
                            String button = "OK";
                            System.out.println("TYPE OK BUTTON IF YOU WANT TO CANCEL YOUR TICKET\n");
                            String s = sc.next();
                            if (s.equals(button)) {
                                try (PreparedStatement ps2 = con.prepareStatement(DeleteQuery)) {
                                    ps2.setInt(1, pnrnum);

                                    int rowsAffected = ps2.executeUpdate();
                                    if (rowsAffected > 0) {
                                        System.out.println("Passenger Details deleted successfully");
                                    } else {
                                        System.out.println("Passenger are not deleted from DataBase");
                                    }

                                } catch (SQLException e) {
                                    System.out.println("SQL Exception " + e.getMessage());
                                }
                            } else {
                                System.out.println("cancellation is not successful");
                                break;
                            }
                        }
                     else if (ch==3)
                     {
                       break;}
                    else {
                        System.out.println("Invalid choice\n");
                        q = false;
                    }
                }

            } catch (SQLException e) {
                System.out.println("SQL Exception : " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error Loading JDBC driver : " + e.getMessage());
        }
        sc.close();
    }
}
