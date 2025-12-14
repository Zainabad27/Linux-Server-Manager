import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DirectoriesNavigator {
    private ProcessBuilder pb;
    BufferedReader r;
    BufferedWriter w;
    private ArrayList<String> ChildDirectories;

    public DirectoriesNavigator(ProcessBuilder pb) {
        this.pb = pb;
        ChildDirectories = new ArrayList<String>();
    }

    public ArrayList<String> ShowDirectories() {

        try {
            Process p = pb.start();

            BufferedReader Reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            writer.write("ls\n");
            writer.write("echo __end__\n");
            writer.flush();

            String line=new String();
            System.out.println("Current childclear Directories are: ");
            int count = 0;
            while (!(line=Reader.readLine()).equals("__end__")) {
                
                System.out.println(line);
                System.out.println(count);
                count++;
                ChildDirectories.add(line);
             

            }

            writer.close();
            Reader.close();
            return ChildDirectories;
        } catch (Exception e) {
            System.out.println("This exception occured while starting the process. " + e.getMessage());
            ChildDirectories.clear();
            ChildDirectories.add(".");
            return ChildDirectories;

        }
    }

    // public void EnterIntoChildDirectory(String ChildDir) {
    //     try {

    //     } catch (Exception e) {
    //         System.out.println("following exception occured " + e.getMessage());
    //     }

    // }

}
