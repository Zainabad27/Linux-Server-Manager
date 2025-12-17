
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Interfaces.IDirectoriesNavigator;
import Interfaces.INavigatingService;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash");
        // Scanner scan=new Scanner(System.out);

        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            IDirectoriesNavigator dn = new DirectoriesNavigator(p, reader, writer);

            INavigatingService Navigator = new NavigatingService(dn);

            Navigator.Naviagte();

        } catch (Exception e) {
            System.out.println("App Crashed.");
            System.out.println("Following Exception occured: " + e.getMessage());
        }

    }
}
