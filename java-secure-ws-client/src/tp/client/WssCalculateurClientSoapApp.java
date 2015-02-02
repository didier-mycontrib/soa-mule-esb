package tp.client;

import java.io.IOException;
import java.util.Properties;

import tp.service.Calculateur;
import tp.service.CalculateurImplService;
import ws.util.client.JaxWsClientPropertiesUtil;
import ws.util.client.WsBindingProviderUtil;
import ws.util.client.wss.SimpleWssHandlerResolverSettings;

public class WssCalculateurClientSoapApp {
	
public static void main(String[] args) {
	//code qui s'appuie sur le résultat  de wsimport du jdk >= 1.6
	//(lancerWsImport.sh depuis linux + Refresh eclipse)
	
	try {
	/*Properties props = new Properties(); //java.util
	props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wssCalculateur.properties"));
	URL wsdlURL= new URL(props.getProperty("wsdl.url"));
	CalculateurImplService calculateurSoapService = new CalculateurImplService(wsdlURL);
	SimpleWssHandlerResolverSettings.setWssUsernameTokenHandlerResolver(calculateurSoapService,
			                        props.getProperty("username"),props.getProperty("password"));
	Calculateur calculateur = calculateurSoapService.getCalculateurImplPort();
	WsBindingProviderUtil.setSoapEndpointUrl(calculateur, props.getProperty("soap.url"));*/

	JaxWsClientPropertiesUtil<CalculateurImplService> jaxWsClientPropertiesUtil 
	= new JaxWsClientPropertiesUtil<CalculateurImplService>("wssCalculateur.properties",CalculateurImplService.class);
	
	CalculateurImplService calculateurSoapService = jaxWsClientPropertiesUtil.getSoapService();
	
	jaxWsClientPropertiesUtil.setWssAuthFromProperties(calculateurSoapService);
	
    Calculateur calculateur = calculateurSoapService.getCalculateurImplPort();
    
    jaxWsClientPropertiesUtil.setEndpointUrlFromProperties(calculateur);

	System.out.println("3+5="+calculateur.addition(3, 5));
	System.out.println("Auteur="+calculateur.getAuteur());
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
