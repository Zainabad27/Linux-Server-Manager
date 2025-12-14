public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("bash");
        DirectoriesNavigator dn = new DirectoriesNavigator(pb);
        String dir = dn.ShowDirectories();
        System.out.println(dir);

        System.out.println("mama");
        // dn.EnterIntoChildDirectory(dir);

    }
}
