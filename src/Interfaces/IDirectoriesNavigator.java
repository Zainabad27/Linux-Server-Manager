package Interfaces;

import java.util.ArrayList;

public interface IDirectoriesNavigator {

    void EnterIntoChildDirectory(String ChildDir);

    String ShowCurrentWorkingDirectory() throws Exception;

    ArrayList<String>  ShowChildDirectories() throws Exception;

    void EnterIntoTheParentDirectory() throws Exception;

    String GetPWD() throws Exception ;

}
