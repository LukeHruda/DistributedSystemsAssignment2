import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NumberInterface extends Remote {
    public int fibonacci(int n) throws RemoteException;
    public String primeNum(int n) throws RemoteException;
    public String threeNPO(int n) throws RemoteException;
    public String nSidedDie(int n, int c) throws RemoteException;
    public String RPS(int n) throws RemoteException;
    public String stats() throws RemoteException;
    public String joke() throws RemoteException;
}