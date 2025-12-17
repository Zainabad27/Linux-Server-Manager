import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import Interfaces.IDirectoriesNavigator;

public class DirectoriesNavigator implements IDirectoriesNavigator {
    private Process p;
    private BufferedReader r;
    private BufferedWriter w;

    private ArrayList<String> ChildDirectories;

    public DirectoriesNavigator(Process p, BufferedReader r, BufferedWriter w) {
        this.p = p;
        this.r = r;
        this.w = w;

        ChildDirectories = new ArrayList<String>();
    }

    public String ShowCurrentWorkingDirectory() throws Exception {

        w.write("clear\n");
        w.write("pwd\n");

        w.write("echo __end__\n");
        w.flush();

        String Line;

        System.out.println("Current Working Directory is: ");
        while (!(Line = r.readLine()).equals("__end__")) {
            System.out.println(Line);

        }

        return Line;

    }

    public String GetPWD() throws Exception {
        w.write("clear\n");
        w.write("pwd\n");

        w.flush();

        String Line;
        Line = r.readLine();
        return Line;
    }

    public ArrayList<String> ShowChildDirectories() throws Exception {
        w.write("ls\n");
        w.write("echo __end__\n");
        w.flush();

        String Line;
        int i = 1;
        System.out.println("Child Directories are: ");
        while (!(Line = r.readLine()).equals("__end__")) {
            System.out.println(i + ": " + Line);
            ChildDirectories.add(Line);
            i++;

        }

        return ChildDirectories;

    }

    public void EnterIntoChildDirectory(String ChildDir) {
        try {
            w.write("cd " + ChildDir + "\n");
            w.flush();

            System.out.println("Entered into the Child Directory: " + ChildDir);

        } catch (Exception e) {
            System.out.println("following exception occured " + e.getMessage());
        }

    }

    @Override
    public void EnterIntoTheParentDirectory() throws Exception {
        w.write("cd ..\n");
        w.flush();
    }

}
