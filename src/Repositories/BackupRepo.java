package Repositories;

import java.sql.Connection;
// import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Interfaces.IBackupRepo;

public class BackupRepo implements IBackupRepo {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");;
    private static final String PASSWORD = System.getenv("DB_PASS");

    // Method to fetch all users
    public void printAllRecords() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM backup_history LIMIT 1;");
            rs.next();

            String s = rs.getString(1);

            System.out.println(s);

        } catch (Exception e) {
            // System.out.println("Database Error Occured: " + e.printStackTrace());
            e.printStackTrace();
        }
    }

    public void TakeBackup(String BackedupRepo, String Destination) {

        String Sql = "INSERT INTO backup_history (BackedupRepo,Destination,dt) VALUES(?,?,?)";

        Date dt = new Date();
        String date = dt.toString();

        try  {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = conn.prepareStatement(Sql);
            pst.setString(1, BackedupRepo);
            pst.setString(2, Destination);
            pst.setString(3, date);

            int rows = pst.executeUpdate(Sql);
            System.out.println(rows +" Inserted.");
            conn.close();

        } catch (Exception e) {
             e.printStackTrace();
             
        }

    }

}
