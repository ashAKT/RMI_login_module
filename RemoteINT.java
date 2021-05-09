import java.rmi.Remote;
import java.rmi.RemoteException;
public interface RemoteINT extends Remote
{
     public String login(String username, String password) throws RemoteException;
    //Here you are calling login method of String class
    //This method is defined in Server class
}
