import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DirectoriesNavigator {

    public DirectoriesNavigator(){
       
    }

    public String ShowDirectories(){
        ProcessBuilder pb=new ProcessBuilder("ls");
        try {
        Process p=pb.start();

        BufferedReader Reader= new BufferedReader(new InputStreamReader(p.getInputStream()));


            String line;
            String LastLine="";
            while((line=Reader.readLine())!=null){
                System.out.println(line);
                LastLine=line;
            }


        Reader.close();
        return LastLine;
        } catch (Exception e) {
            System.out.println("This exception occured while starting the process. "+e.getMessage());
            return e.getMessage();
           
        }
    }


    public void EnterIntoChildDirectory(String ChildDir){
        try {
            
            ProcessBuilder pb=new ProcessBuilder("cd",ChildDir);
            Process p=pb.start();
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));


            BufferedWriter Writer=new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            String line;
            line=reader.readLine();

            System.out.println("Entering into "+line+" Directory");

            Writer.write("ls\n");
            Writer.flush();

            Writer.write("mkdir newdir\n");
            Writer.flush();

            Writer.write("ls\n");
            Writer.flush();

            System.out.println("Directories in this folder");

            while((
            line=reader.readLine())!=null){
                System.out.println(line);
            }



            Writer.close();
            reader.close();


            
        } catch (Exception e) {
            System.out.println("following exception occured "+e.getMessage());
        }




    }



    
}








