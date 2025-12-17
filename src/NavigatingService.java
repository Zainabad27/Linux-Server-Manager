import java.util.ArrayList;
import java.util.Scanner;

import Interfaces.IDirectoriesNavigator;
import Interfaces.INavigatingService;

public class NavigatingService implements INavigatingService {
    IDirectoriesNavigator dn;
    Scanner sc;
    ArrayList<String> DirectoriesContainer;

    public NavigatingService(IDirectoriesNavigator dn) {

        this.dn = dn;
        this.sc = new Scanner(System.in);
    }

    public void Naviagte() throws Exception {
        System.out.println("Linux Server Manager");
        dn.ShowCurrentWorkingDirectory();
        String UserInput = "";
        do {
            DirectoriesContainer = dn.ShowChildDirectories();
            if (DirectoriesContainer.isEmpty()) {
                System.out.println("No Child Directories Found");
                System.out.println("Press Any Key to Move to the Parent Directory");
                UserInput = sc.nextLine();
                dn.EnterIntoTheParentDirectory();
                continue;
            }
            System.out.println("Enter the corresponding number to enter a directory ");
            System.out.println("Enter X to Quit");
            System.out.println("Press P to move to parent Directory ");
            UserInput = sc.nextLine();

            if (UserInput.equals("p") || UserInput.equals("P")) {
                dn.EnterIntoTheParentDirectory();
            }
            if (isValidInt(UserInput)) {
                int userinput = Integer.parseInt(UserInput);
                if (userinput > DirectoriesContainer.size()) {
                    System.out
                            .println("Invalid Input Please Enter a Number Smaller than" + DirectoriesContainer.size());
                } else {
                    dn.EnterIntoChildDirectory(DirectoriesContainer.get(userinput));
                }

            }

        } while (!(UserInput.equals("x")));

    }

    public static boolean isValidInt(String s) {
        if (s == null || s.isEmpty())
            return false;

        int start = 0;

        if (s.charAt(0) == '-') {

            return false;

        }

        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
