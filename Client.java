import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;	
import java.util.Scanner;
public class Client 
{
    public static void main(String[] args)
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost",Registry.REGISTRY_PORT);
            //getRegistry --> Returns a reference to the "remote object Registry" on the specified host and port.
            //REGISTRY_PORT default value = 1099
            
            RemoteINT intobj = (RemoteINT) reg.lookup("rmi://localhost/login_service"); 
            //lookup method --> returns the remote reference(object) bound to the specified name in this registry.
                      
            //Client attmepts to login using username and password
            Scanner scan = new Scanner(System.in); 
            System.out.println("Enter username : ");
            String username = scan.nextLine();
            System.out.println("Enter password : ");
            String password = scan.nextLine();
            
	    // Here stub object is intobj
            String response = intobj.login(username, password); 
            /*  
            calling remote method of interface "RemoteINT" whose definition is in Server class.
            to authenticate the entered username and password is valid or not
            and finally saving the response in string "response"  
            */
            
            System.out.println("Response from server : " + response); 
            //displaying username correct or not
        }
        catch(RemoteException | NotBoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
