package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import Interfaces.IBackupRepo;
import Interfaces.IDirectoriesNavigator;
import Interfaces.INavigatingService;
import Repositories.BackupRepo;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash");
        IBackupRepo repo = new BackupRepo();

        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            IDirectoriesNavigator dn = new DirectoriesNavigator(p, reader, writer);

            INavigatingService Navigator = new NavigatingService(dn, repo);

            Navigator.RunApp();

        } catch (Exception e) {
            System.out.println("App Crashed.");
            System.out.println("Following Exception occured: " + e.getMessage());
        }

    }
}
