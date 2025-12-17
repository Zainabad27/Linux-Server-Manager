package Interfaces;

import java.util.ArrayList;

public interface IDirectoriesNavigator {

    Process GetProcess();

    boolean RemoveDirectory(String Directory) throws Exception;

    boolean RemoveFile(String Filename) throws Exception;

    boolean IsDirectory(String Directory) throws Exception;

    void EnterIntoChildDirectory(String ChildDir);

    String ShowCurrentWorkingDirectory() throws Exception;

    ArrayList<String> ShowChildDirectories() throws Exception;

    void EnterIntoTheParentDirectory() throws Exception;

    String GetPWD() throws Exception;

}
