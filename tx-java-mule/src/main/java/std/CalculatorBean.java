package std;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import tp.service.Calculateur;

@WebService(endpointInterface="std.Calculator")
public class CalculatorBean implements Calculator {
	
	private String proxyWsUrl;//url du service externe (à transformer) à injecter
	
	private Calculateur calcProxy = null;
	private Service service=null;
	
	
	
	public void setProxyWsUrl(String proxyWsUrl) {
		this.proxyWsUrl = proxyWsUrl;
	}

	public void initProxy(){
		
			QName SERVICE_NAME = new QName("http://service.tp/", "CalculateurImplService");
			QName PORT_NAME = new QName("http://service.tp/", "CalculateurImplPort");
			service = Service.create(SERVICE_NAME);		//javax.xml.ws.Service
			// Endpoint Address
			//this.proxyWsUrl = "http://localhost:8080/wsCalculateur/services/calculateur";
			// Add a port to the Service , javax.xml.ws.soap.SOAPBinding
			service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING,proxyWsUrl);

			calcProxy = (Calculateur) service.getPort(PORT_NAME, Calculateur.class);		
	}

	@Override
	public double add(double x,double y) {
		System.out.println("add(x="+x+",y="+y+")");//trace faculative
		if(calcProxy==null)
			initProxy();
		double res = calcProxy.addition(x,y);
		//double res=x+y;//temp direct computing (debug)
		System.out.println("return res=addition(a,b)=" + res);//trace faculative
		return res;
	}

	@Override
	public double mult(double x,double y) {
		System.out.println("mult(x="+x+",y="+y+")");//trace faculative
		if(calcProxy==null)
			initProxy();
		double res = calcProxy.multiplication(x,y);
		System.out.println("return res=multiplication(a,b)=" + res);//trace faculative
		return res;
	}

}
