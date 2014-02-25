import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interf extends Remote{

	void add( double x ) throws RemoteException;
	void substract( double x ) throws RemoteException;
	void multiply( double x ) throws RemoteException;
	int divide( double x ) throws RemoteException;
	int invert() throws RemoteException;
	int power( double x ) throws RemoteException;
	int factorial() throws RemoteException;
	int radical() throws RemoteException;
	double get_result() throws RemoteException;
	void mem_plus( double x ) throws RemoteException;
	void mem_minus( double x ) throws RemoteException;
	void mem_stock() throws RemoteException;
	double mem_read() throws RemoteException;
	void mem_reset() throws RemoteException;
	double get_mem() throws RemoteException;
	void set_state(double x) throws RemoteException;
}