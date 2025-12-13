public class Main {
    public static void main(String[] args) {

        DirectoriesNavigator dn=new DirectoriesNavigator();
        String dir=dn.ShowDirectories();
        System.out.println(dir);

        System.out.println("mama");
        dn.EnterIntoChildDirectory(dir);

    }
}
