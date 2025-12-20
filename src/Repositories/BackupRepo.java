package Repositories;

import java.sql.Connection;
// import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
// import java.sql.SQLException;
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
            ResultSet rs = st.executeQuery("SELECT * FROM backup_history;");
            ResultSetMetaData metadata = rs.getMetaData();
            // int colCount=metadata.getColumnCount();

            while (rs.next()) {
                System.out.println(metadata.getColumnName(2) + ": " + rs.getString(2) + " " + metadata.getColumnName(3)
                        + ": " + rs.getString(3) + " " + metadata.getColumnName(4) + ": " + rs.getString(4));
            }

        } catch (Exception e) {
            // System.out.println("Database Error Occured: " + e.printStackTrace());
            e.printStackTrace();
        }
    }

    // method to take backup and save data in DATABASE.
    public void TakeBackup(String BackedupRepo, String Destination) {

        String Sql = "INSERT INTO backup_history (backedupfolder,backedupin,createdat) VALUES(?,?,?)";

        Date dt = new Date();
        String date = dt.toString();

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = conn.prepareStatement(Sql);
            pst.setString(1, BackedupRepo);
            pst.setString(2, Destination);
            pst.setString(3, date);

            int rows = pst.executeUpdate();
            System.out.println(rows + " Inserted.");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
