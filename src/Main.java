import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash");
        DirectoriesNavigator dn = new DirectoriesNavigator(pb);
        ArrayList<String> dirs = dn.ShowDirectories();
        
        System.out.println(dirs.get(0));

        System.out.println("mama");
        // dn.EnterIntoChildDirectory(dir);

    }
}
