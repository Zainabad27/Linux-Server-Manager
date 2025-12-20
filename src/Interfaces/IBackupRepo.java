package Interfaces;

public interface IBackupRepo {
    void printAllRecords();
    void TakeBackup(String BackedupRepo, String Destination);
}
