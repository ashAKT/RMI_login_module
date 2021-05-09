import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server extends UnicastRemoteObject implements LoginINT
{
    /*
    UnicastRemoteObject --> Used for exporting a remote object 
    with JRMP(Java Remote Method Protocol) 
    and obtaining a stub(Client object) that communicates to the remote object. 
    */
    
    private TreeMap clients = new TreeMap<String,String>(); // create key value pair
    //Treemap stores the key in sorted order, useful for finding ceiling and floor keys

    public Server() throws RemoteException
    {
        super(); // super method will invoke parent methods
    }
    
    public static void main(String[] args) 
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            /*   createRegistry(int port)
            Creates and exports a Registry instance on the "local_host" that accepts requests on the specified port
            REGISTRY_PORT default value = "1099"   */
            
            Server obj = new Server();         
            
            reg.rebind("rmi://localhost/login_service", obj); //obj
            /*   rebind(String name,Remote object)
            Replaces the binding for the specified name in this registry with the supplied remote reference. 
            Imp note --> "If there is an existing binding for the specified name, it is discarded"   */
            
            System.out.println("Server Running...");
        }
        catch (RemoteException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
       
    public String login(String username, String password) throws RemoteException
    {
        init();
        String response = search(username, password);        
        return response;
    }
    private void init()
    {
        clients.put("user1", "1234");
        clients.put("user2", "1234");
    }
    
    private String search(String username , String password)
    {
        String response = "";
        
        Set set = clients.entrySet(); // java.util.TreeMap.entrySet()
        /*  
        entrySet() --> create set out of the same elements contained in the treemap.
        to store the set ,we create a Set object
        Set --> no duplicate elements 
        */
        
        Iterator itr = set.iterator();
        /* Iterator enables you to cycle through a collection, obtaining or removing elements.
        iterator( ) method --> returns an iterator to the "start of the collection".  */
   
        boolean flag = false;
        
        while(itr.hasNext()) // hasNext() returns true if there are more elements
        {
            response = "";
            Map.Entry entry = (Map.Entry) itr.next();
            /*  Map.Entry interface enables you to work with a map entry
            Map.Entry objects are valid only for the duration of the iteration */
           
            String user = entry.getKey().toString();
            String pass = entry.getValue().toString();
            
            if(username.equals(user))
            {
                flag = true;
                if(password.equals(pass))
                {
                    response = "LOGIN_SUCCESFUL";
                }
                else
                {
                    response = "PASSWORD_INCORRECT";
                }                
                break;
            }
        }
        if(! flag)
        {
            response = "USER_NOT_EXISTS";
        }
        return response;
    }
}
