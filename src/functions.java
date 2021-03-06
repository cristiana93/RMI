import java.rmi.*;
import java.rmi.server.*;

public class functions extends UnicastRemoteObject implements Interf{
	private double current_state, current_mem;
	
	public functions(double x) throws RemoteException{
		current_state = x;
		current_mem = 0;
	}
	public void add(double x) throws RemoteException {
		current_state = current_state +  x;
	}
	
	public void substract(double x) throws RemoteException {
		current_state = current_state - x;		
	}
	
	public void multiply(double x) throws RemoteException {
		current_state = current_state * x;
	}
	
	public int divide(double x) throws RemoteException {
		try{
			if (x == 0 ) throw new ArithmeticException();
			current_state = current_state / x;
			return 1;
		}
		catch(ArithmeticException e){
			current_state = 0;
			System.out.println("Division by 0!!");
			return 0;
		}
		
	}
	public int invert() throws RemoteException {
		if ( current_state != 0 ) {
			current_state  = 1 / current_state;
			return 1;
		}else return 0;
	}

	public int power(double x) throws RemoteException {
		try{
		current_state = Math.pow(current_state,x);
		return 1;
		}catch(Exception e){
			return 0;
		}
	}

	public int factorial() throws RemoteException {
		if (current_state == (int)current_state && current_state>0 ){
			int aux = 1;
			for (int i = 2; i<=(int)current_state; ++i)
				aux *=i;
			current_state = aux;
			return 1;
		}else{
			System.out.println("Impossible!! Enter a positive interger!");
			return 0;
		}
		
	}

	public int radical() throws RemoteException {
		if (current_state>=0) {
			current_state = Math.sqrt(current_state);
			return 1;
		}else{
			
			System.out.println("Impossible!! Enter a positive number!!");
			return 0;
		}
		
	}

	public double get_result() throws RemoteException {
		return current_state;
	}

	public void mem_plus(double x) throws RemoteException {
		current_mem = current_mem +  x;
		
		
	}

	public void mem_minus(double x) throws RemoteException {
		current_mem = current_mem - x;
		
	}

	public void mem_stock() throws RemoteException {
		current_mem = current_state;
		
	}

	public double mem_read() throws RemoteException {
		return current_mem;
	}

	
	public void mem_reset() throws RemoteException {
		current_mem = 0;
		
	}

	public double get_mem() throws RemoteException {
		return current_mem;
	}
	public void set_state(double x) throws RemoteException{
		current_state = x;
	}
	
}
