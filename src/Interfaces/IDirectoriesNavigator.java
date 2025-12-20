package Interfaces;

import java.util.ArrayList;

public interface IDirectoriesNavigator {

    Process GetProcess();

    void backupFile(String File) throws Exception;

    void backupDirectory(String Directory) throws Exception;

    void RemoveDirectory(String Directory) throws Exception;

    void RemoveFile(String Filename) throws Exception;

    boolean IsDirectory(String Directory) throws Exception;

    void EnterIntoChildDirectory(String ChildDir);

    String ShowCurrentWorkingDirectory() throws Exception;

    ArrayList<String> ShowChildDirectories() throws Exception;

    void EnterIntoTheParentDirectory() throws Exception;

    String GetPWD() throws Exception;

}
