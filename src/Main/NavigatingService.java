package Main;

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
        DirectoriesContainer = new ArrayList<>();
    }

    public void Naviagte() throws Exception {
        System.out.println("Linux Server Manager");

        String UserInput = "";
        do {
            dn.ShowCurrentWorkingDirectory();

            DirectoriesContainer.clear();
            DirectoriesContainer = dn.ShowChildDirectories();
            if (DirectoriesContainer.isEmpty()) {
                System.out.println("No Child Directories Found");
                System.out.println("Press Any Key to Move to the Parent Directory");
                UserInput = sc.nextLine();
                dn.EnterIntoTheParentDirectory();
                continue;
            }
            System.out.println("Enter the corresponding number to enter a directory ");
            System.out.println("Enter Corresponding number Starting with D to delete that File/Directory");
            System.out.println("Enter X to Quit");
            System.out.println("Press P to move to parent Directory ");
            UserInput = sc.nextLine();

            if (UserInput.equals("p") || UserInput.equals("P")) {
                dn.EnterIntoTheParentDirectory();
                // DirectoriesContainer.clear();
                continue;
            }
            if (UserInput.startsWith("D") || UserInput.startsWith("d")) {
                if (UserInput.length() == 2) {
                    char FirstDigit = UserInput.charAt(1);
                    if (isValidInt(String.valueOf(FirstDigit))) {
                        int userinput = Integer.parseInt(String.valueOf(FirstDigit));
                        if (userinput > DirectoriesContainer.size()) {
                            System.out.println("Invalid Input to delete File/Directory");
                            // DirectoriesContainer.clear();
                            continue;
                        }

                        boolean IsDirectory = dn.IsDirectory(DirectoriesContainer.get(userinput - 1));

                        if (IsDirectory) {
                            dn.RemoveDirectory(DirectoriesContainer.get(userinput - 1));

                            // DirectoriesContainer.clear();

                            continue;
                        } else {
                            dn.RemoveFile(DirectoriesContainer.get(userinput - 1));
                            // DirectoriesContainer.clear();

                            continue;
                        }

                    } else {
                        System.out.println("Invalid User Input for deletion purpose.");
                    }
                } else if (UserInput.length() == 3) {
                    char FirstDigit = UserInput.charAt(1);
                    char SecondDigit = UserInput.charAt(2);
                    String num = (String.valueOf(FirstDigit) + String.valueOf(SecondDigit));
                    if (isValidInt(num)) {
                        int userinput = Integer.parseInt(num);
                        if (userinput > DirectoriesContainer.size()) {
                            System.out.println("Invalid Input to delete File/Directory");
                            continue;
                        }

                        boolean IsDirectory = dn.IsDirectory(DirectoriesContainer.get(userinput - 1));

                        if (IsDirectory) {
                            dn.RemoveDirectory(DirectoriesContainer.get(userinput - 1));
                            continue;
                        } else {
                            dn.RemoveFile(DirectoriesContainer.get(userinput - 1));
                            continue;
                        }

                    }
                }
            }
            if (isValidInt(UserInput.trim())) {
                int userinput = Integer.parseInt(UserInput) - 1;
                if (userinput > DirectoriesContainer.size()) {
                    System.out
                            .println("Invalid Input Please Enter a Number Smaller than" + DirectoriesContainer.size());
                } else {
                    dn.EnterIntoChildDirectory(DirectoriesContainer.get(userinput));
                    continue;
                }

            }

            // DirectoriesContainer.clear();

        } while (!(UserInput.equals("x") || UserInput.equals("X")));

    }

    public static boolean isValidInt(String s) {
        // this function checks that the string is a valid integer and also greater than
        // 0.
        if (s.equals("0"))
            return false;

        if (s == null || s.isEmpty())
            return false;

        int start = 0;

        if (s.charAt(0) == '-') {
            System.out.println("Please Enter a Positive Integer.");
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
