
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Interfaces.IDirectoriesNavigator;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash");
        // Scanner scan=new Scanner(System.out);

        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            IDirectoriesNavigator dn = new DirectoriesNavigator(p, reader, writer);

            dn.ShowCurrentWorkingDirectory();

           ArrayList<String> ChildDirs= dn.ShowChildDirectories();
            // for (int i=0;i<ChildDirs.size();i++) {
            //     System.out.println((i+1)+": "+ChildDirs.get(i) );

            // }


            dn.EnterIntoChildDirectory(ChildDirs.get(0));

            dn.ShowCurrentWorkingDirectory();

            dn.ShowChildDirectories();

            dn.EnterIntoTheParentDirectory();

            dn.ShowCurrentWorkingDirectory();

            String cr=dn.GetPWD();
            System.out.println("CR is: ");
            // System.out.println(cr);

            System.out.println("MAMA");






        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
