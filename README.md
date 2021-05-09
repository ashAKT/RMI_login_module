# RMI_login_module
## Implement a simple login module using RMI (Remote Method Invocation) API in java.

RMI is an API that allows an object to invoke another method that exists in a separate address space.
It provides a mechanism to create distributed application in java.

**RMI Registry (class)** is a place for the server to register services(it offers to the client) and a place for clients to query for those services.
**The communication between client and server is handled by using two intermediate objects:**
- Stub object (on client side) and 
- Skeleton object (on server side).

![asheet](https://user-images.githubusercontent.com/25420334/117572519-db21d800-b0f0-11eb-8076-6c3677e2adb9.png)

The **Stub Object** on the client machine builds an **information block** and **sends** this information **to the server**. The information block consists of:
1.	An identifier of the remote object to be used.
2.	Method name which is to be invoked.
3.	Parameters to pass to the remote method (if any).

The **Skeleton object passes** the **request** from the stub object **to the remote object**. It performs following tasks:
1.	It calls the desired method on the real object present on the server.
2.	It forwards the parameters received from the stub object to the method.
3.	Returns the value to the stub object.

# RMI Working

![asheet](https://user-images.githubusercontent.com/25420334/117572700-ac583180-b0f1-11eb-85f8-2d9c6d9d0107.png)

# RMI Implementation
![asheet Implementation](https://user-images.githubusercontent.com/25420334/117572733-d4e02b80-b0f1-11eb-82f7-c58c6b411bb1.png)

**CLASS:**

Remote Interface
1.	Declaration of Remote method (definition is in Server class).

Server:
1.	Implement the Remote Interface and provide definition of the remote method (override). 
2.	Request RMI Registry to create Registry and also specify the port number to accept request on the specified port. “createRegistry(int port)”
3.	Specify the service name and bind services with the server object by calling bind method.
“Server obj= new Server(); 
reg.bind("rmi://localhost/login_service”,obj);”
4.	Accept request from client and provide Services (Login successful or not, Wrong Password, User doesn’t exists)

Client:
1.	Get the reference of the registry on the specified host and port number.
“Registry rej=LocateRegistry.getRegistry(String host, int port)”
2.	Using the registry reference, search for the particular service to query and store the  reference that is bind to the registered service.
“LoginINT intobj = (LoginINT) reg.lookup("rmi://localhost/login_service");”
3.	Using the above reference call the remote method and pass any parameters (if any)
“intobj.login(username, password)”
4.	Finally get the response from the Server

# Adding user and password

![Screenshot (80)](https://user-images.githubusercontent.com/25420334/117573131-ea565500-b0f3-11eb-9e3b-91a7ab4eacd0.png)

# Output

**SERVER:**

`javac Server.java`

`java Server`

![asheet](https://user-images.githubusercontent.com/25420334/117572917-c34b5380-b0f2-11eb-9b2f-cb37bf3c5ed6.png)

**CLIENT:**

`javac Client.java`

`java Client`

![asheet](https://user-images.githubusercontent.com/25420334/117572945-efff6b00-b0f2-11eb-9ae6-2ef7656e21fa.png)

If you are using any IDE then, 
make sure their is a package name on the first line and then run the program.
