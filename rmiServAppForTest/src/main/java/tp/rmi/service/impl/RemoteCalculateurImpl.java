package tp.rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tp.rmi.service.RemoteCalculateur;


public class RemoteCalculateurImpl extends UnicastRemoteObject implements RemoteCalculateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoteCalculateurImpl() throws RemoteException {
		super();// (important call to UnicastRemoteObject constructor) for rmi export
	}

	@Override
	public double addition(double a,double b) {
		double res=a+b;
		System.out.println("addition("+a+","+b+")="+res);
		return res;
	}
	
	@Override
	public double soustraction(double a,double b) {
		double res=a-b;
		System.out.println("soustraction("+a+","+b+")="+res);
		return res;
	}

	@Override
	public double multiplication(double a,double b) {
		double res=a*b;
		System.out.println("multiplication("+a+","+b+")="+res);
		return res;
	}

	@Override
	public double division(double a,double b) {
		double res=a/b;
		System.out.println("division("+a+","+b+")="+res);
		return res;
	}

}
