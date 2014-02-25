import java.rmi.*;
import java.rmi.registry.*;
public class server {
	public static void main( String[] args) throws Exception{
		Generator Ob = new Generator();
		//System.out.println("merge?");
		Registry reg =LocateRegistry.createRegistry(1099);
		reg.bind("Ob", Ob);
		System.out.println("Server ON!");
	}
}
