import java.rmi.*;
import java.rmi.server.*;

public class Generator extends UnicastRemoteObject implements Interf_Generator{
	Generator() throws RemoteException{}
	public Interf server_propriu() throws Exception{
		return new functions(0);
	}
}