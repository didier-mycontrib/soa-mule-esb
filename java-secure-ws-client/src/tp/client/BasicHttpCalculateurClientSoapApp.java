package tp.client;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import tp.service.Calculateur;
import tp.service.CalculateurImplService;
import ws.util.client.JaxWsClientPropertiesUtil;
import ws.util.client.WsBindingProviderUtil;

public class BasicHttpCalculateurClientSoapApp {
	
	public static void main(String[] args) {
		//code qui s'appuie sur le résultat  de wsimport du jdk >= 1.6
		//(lancerWsImport.sh depuis linux + Refresh eclipse)
		try {
			JaxWsClientPropertiesUtil<CalculateurImplService> jaxWsClientPropertiesUtil 
			= new JaxWsClientPropertiesUtil<CalculateurImplService>("wsBasicHttpCalculateur.properties",CalculateurImplService.class);
			
			CalculateurImplService calculateurSoapService = jaxWsClientPropertiesUtil.getSoapService();
			
		    Calculateur calculateur = calculateurSoapService.getCalculateurImplPort();
		    jaxWsClientPropertiesUtil.setBasicHttpAuthFromProperties(calculateur);
		    jaxWsClientPropertiesUtil.setEndpointUrlFromProperties(calculateur);

			System.out.println("3+5="+calculateur.addition(3, 5));
			System.out.println("Auteur="+calculateur.getAuteur());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public static void mainV1(String[] args) {
	//code qui s'appuie sur le résultat  de wsimport du jdk >= 1.6
	//(lancerWsImport.sh depuis linux + Refresh eclipse)
	try {
	Properties props = new Properties(); //java.util
	props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wsBasicHttpCalculateur.properties"));
	URL wsdlURL= new URL(props.getProperty("wsdl.url"));
	Calculateur calculateur = 
			new CalculateurImplService(wsdlURL).getCalculateurImplPort();
	/*
	javax.xml.ws.BindingProvider bp = (javax.xml.ws.BindingProvider) calculateur;
	Map<String,Object> context = bp.getRequestContext();
	context.put(BindingProvider.USERNAME_PROPERTY,props.getProperty("username"));
	context.put(BindingProvider.PASSWORD_PROPERTY,props.getProperty("password"));
	context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, props.getProperty("soap.url"));
	*/
	WsBindingProviderUtil.setBasicHttpAuth(calculateur,props.getProperty("username"), props.getProperty("password"));
	WsBindingProviderUtil.setSoapEndpointUrl(calculateur, props.getProperty("soap.url"));
	
	System.out.println("3+5="+calculateur.addition(3, 5));
	System.out.println("Auteur="+calculateur.getAuteur());
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}
