package tp.rmi.server.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import tp.rmi.service.RemoteCalculateur;
import tp.rmi.service.impl.RemoteCalculateurImpl;

public class RmiServApp {

	public static void main(String[] args) {
		try {
			//System.out.println("Getting registry (from RmiServApp)");
			//Registry registry = LocateRegistry.getRegistry("localhost", 12345);
			
			//default rmi port is 1099 (not 12345)
			
			System.out.println("Creating Rmi registry on port 12345 (from RmiServApp)");
			Registry registry = LocateRegistry.createRegistry( 12345);
			
			System.out.println("Building RemoteCalculateurImpl in RmiServApp");
			RemoteCalculateur calc = new RemoteCalculateurImpl();
			
			System.out.println("Store/rebind rmiRemoteCalculateur in registry (from RmiServApp)");
			registry.rebind("rmiRemoteCalculateur", calc);
			
			System.out.println("RmiServApp started , waiting for request ...");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
