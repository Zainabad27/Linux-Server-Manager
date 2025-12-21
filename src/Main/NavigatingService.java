package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Interfaces.IBackupRepo;
import Interfaces.IDirectoriesNavigator;
import Interfaces.INavigatingService;

public class NavigatingService implements INavigatingService {
    IDirectoriesNavigator dn;
    IBackupRepo br;
    Scanner sc;
    ArrayList<String> DirectoriesContainer;

    public NavigatingService(IDirectoriesNavigator dn, IBackupRepo br) {

        this.dn = dn;
        this.br = br;
        this.sc = new Scanner(System.in);
        DirectoriesContainer = new ArrayList<>();
    }

    public void RunApp() throws Exception {
        System.out.println("Linux Server Manager");

        String UserInput = "";
        do {
            dn.ShowCurrentWorkingDirectory();

            DirectoriesContainer.clear();
            DirectoriesContainer = dn.ShowChildDirectories();
            if (DirectoriesContainer.isEmpty()) {
                System.out.println("No Child Directories Found");
                System.out.println("Press N to Create new Directory.");
                System.out.println("Press F to Create new File.");
                System.out.println("Press Any Other Key to Move to the Parent Directory");
                UserInput = sc.nextLine();
                if (UserInput.equals("F") || UserInput.equals("f")) {
                    NewFileModule(sc, dn);
                    continue;
                }
                if (UserInput.equals("N") || UserInput.equals("n")) {
                    NewDirModule(sc, dn);
                    continue;
                }
                dn.EnterIntoTheParentDirectory();
                continue;
            }
            System.out.println("Press C to get Current Directory.");
            System.out.println("Enter the corresponding number to enter a directory ");
            System.out.println("Press Corresponding number Starting with D to delete that File/Directory");
            System.out.println("Press P to move to parent Directory ");
            System.out.println("Press Corresponding number Starting with B to take Backup.");
            System.out.println("Press H to see All Backup History.");
            System.out.println("Press N to Make a new Directory.");
            System.out.println("Press f to make a new File.");
            System.out.println("Enter X to Quit");
            UserInput = sc.nextLine();
            if (UserInput.equals("h") || UserInput.equals("H")) {
                br.printAllRecords();
                sc.nextLine();
                continue;
            }
            if (UserInput.equals("C") || UserInput.equals("c")) {
                dn.ShowCurrentWorkingDirectory();
                sc.nextLine();
                continue;
            }
            if (UserInput.startsWith("B") || UserInput.startsWith("b")) {
                BackupModule(UserInput, DirectoriesContainer, sc, dn, br);
                continue;
            }
            if (UserInput.equals("N") || UserInput.equals("n")) {
                NewDirModule(sc, dn);
                continue;
            }
            if (UserInput.equals("F") || UserInput.equals("f")) {
                NewFileModule(sc, dn);
                continue;
            }

            if (UserInput.equals("p") || UserInput.equals("P")) {
                dn.EnterIntoTheParentDirectory();
                continue;
            }
            if (UserInput.startsWith("D") || UserInput.startsWith("d")) {
                DeletionModule(UserInput, DirectoriesContainer, sc, dn, br);
                continue;
            }
            if (isValidInt(UserInput.trim())) {
                NavigatingModule(UserInput, DirectoriesContainer, dn, sc);
                continue;
            }
            if (!(UserInput.equals("x") || UserInput.equals("X"))) {
                System.out.println("Invalid User Input!!!!!");
                sc.nextLine();
            }

        } while (!(UserInput.equals("x") || UserInput.equals("X")));
        System.out.println("App Stopped.");
    }

    public static void NavigatingModule(String UserInput, ArrayList<String> DirectoriesContainer,
            IDirectoriesNavigator dn, Scanner sc) throws Exception {
        int userinput = Integer.parseInt(UserInput) - 1;
        if (userinput > DirectoriesContainer.size()) {
            System.out
                    .println("Invalid Input Please Enter a Number Smaller than" + DirectoriesContainer.size());
        } else {
            if (!(dn.IsDirectory(DirectoriesContainer.get(userinput)))) {
                // System.out.println("Not a Directory.");
                sc.nextLine();
                return;
            }
            dn.EnterIntoChildDirectory(DirectoriesContainer.get(userinput));

            return;
        }
    }

    public static void DeletionModule(String UserInput, ArrayList<String> DirectoriesContainer, Scanner sc,
            IDirectoriesNavigator dn, IBackupRepo br) throws Exception {
        if (UserInput.length() == 2) {
            char FirstDigit = UserInput.charAt(1);
            if (isValidInt(String.valueOf(FirstDigit))) {
                int userinput = Integer.parseInt(String.valueOf(FirstDigit));
                if (userinput > DirectoriesContainer.size()) {
                    System.out.println("Invalid Input to delete File/Directory");
                    sc.nextLine();
                    return;
                }

                boolean IsDirectory = dn.IsDirectory(DirectoriesContainer.get(userinput - 1));

                if (IsDirectory) {
                    String Confirmation;
                    System.out.println("Enter the Directory Name To delete it: ");
                    Confirmation = sc.nextLine();
                    if (Confirmation.equals(DirectoriesContainer.get(userinput - 1))) {
                        dn.RemoveDirectory(DirectoriesContainer.get(userinput - 1));
                        System.out.println("Removed.");
                    } else {
                        System.out.println("Directory Name Not Matched");
                    }

                    sc.nextLine();
                    return;

                } else {
                    dn.RemoveFile(DirectoriesContainer.get(userinput - 1));
                    System.out.println("Removed.");
                    sc.nextLine();
                    return;
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
                    return;
                }

                boolean IsDirectory = dn.IsDirectory(DirectoriesContainer.get(userinput - 1));

                if (IsDirectory) {
                    String Confirmation;
                    System.out.println("Enter the Directory Name To delete it: ");
                    Confirmation = sc.nextLine();
                    if (Confirmation.equals(DirectoriesContainer.get(userinput - 1))) {
                        dn.RemoveDirectory(DirectoriesContainer.get(userinput - 1));

                        System.out.println("Removed");
                    } else {
                        System.out.println("Directory Name Not Matched");
                    }

                    sc.nextLine();
                    return;
                } else {
                    String Confirmation;
                    System.out.println("Enter the File Name To delete it: ");
                    Confirmation = sc.nextLine();
                    if (Confirmation.equals(DirectoriesContainer.get(userinput - 1))) {
                        dn.RemoveFile(DirectoriesContainer.get(userinput - 1));
                        System.out.println("Removed");
                    } else {
                        System.out.println("File Name Not Matched");
                    }

                    sc.nextLine();
                    return;
                }

            } else {
                System.out.println("Invalid Input");
                sc.nextLine();
                return;
            }
        } else {
            System.out.println("Invalid Input");
            sc.nextLine();
            return;
        }

    }

    public static void NewFileModule(Scanner sc, IDirectoriesNavigator dn) throws Exception {
        System.out.println("Enter the File name that you want to create: ");
        String NewFile = sc.nextLine();
        if (NewFile.trim().length() == 0) {
            System.out.println("File Name Cannot be Empty.");
            sc.nextLine();
            return;
        }
        dn.MakeNewFile(NewFile);
        System.out.println(NewFile + " Created.");
        sc.nextLine();
        return;
    }

    public static void NewDirModule(Scanner sc, IDirectoriesNavigator dn) throws Exception {
        System.out.println("Enter the Directory name that you want to create: ");
        String NewDir = sc.nextLine();
        if (NewDir.trim().length() == 0) {
            System.out.println("Directory Name Cannot be Empty.");
            sc.nextLine();
            return;
        }
        dn.MakeNewDir(NewDir);
        System.out.println(NewDir + " Created.");
        sc.nextLine();
        return;
    }

    public static void BackupModule(String UserInput, ArrayList<String> DirectoriesContainer, Scanner sc,
            IDirectoriesNavigator dn, IBackupRepo br) throws Exception {
        if (UserInput.length() == 2) {
            char FirstDigit = UserInput.charAt(1);
            if (isValidInt(String.valueOf(FirstDigit))) {
                int userinput = Integer.parseInt(String.valueOf(FirstDigit));
                if (userinput > DirectoriesContainer.size()) {
                    System.out.println("Invalid Input to Backup File/Directory");
                    sc.nextLine();
                    return;
                }

                boolean IsDirectory = dn.IsDirectory(DirectoriesContainer.get(userinput - 1));

                if (IsDirectory) {

                    String destination = dn.backupDirectory(DirectoriesContainer.get(userinput - 1));

                    br.TakeBackup(DirectoriesContainer.get(userinput - 1), destination);

                    System.out.println("Directory Backup Successfull.");
                    sc.nextLine();
                    return;

                } else {
                    dn.backupFile(DirectoriesContainer.get(userinput - 1));

                    System.out.println("File Backup Successfull.");
                    sc.nextLine();
                    return;
                }

            } else {
                System.out.println("Invalid User Input for Backup.");
            }
        } else if (UserInput.length() == 3) {
            char FirstDigit = UserInput.charAt(1);
            char SecondDigit = UserInput.charAt(2);
            String num = (String.valueOf(FirstDigit) + String.valueOf(SecondDigit));
            if (isValidInt(num)) {
                int userinput = Integer.parseInt(num);
                if (userinput > DirectoriesContainer.size()) {
                    System.out.println("Invalid Input to backup File/Directory");
                    return;
                }

                boolean IsDirectory = dn.IsDirectory(DirectoriesContainer.get(userinput - 1));

                if (IsDirectory) {

                    dn.backupDirectory(DirectoriesContainer.get(userinput - 1));
                    System.out.println("Directory Backup Successfull.");
                    sc.nextLine();
                    return;
                } else {

                    dn.backupFile(DirectoriesContainer.get(userinput - 1));
                    System.out.println("File Backup Successfull.");
                    sc.nextLine();
                    return;
                }

            } else {
                System.out.println("Invalid Input");
                sc.nextLine();
                return;
            }
        } else {
            System.out.println("Invalid Input");
            sc.nextLine();
            return;
        }
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
