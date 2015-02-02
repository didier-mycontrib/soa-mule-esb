package ws.util.client;

import java.util.Map;

import javax.xml.ws.BindingProvider;

public class WsBindingProviderUtil {
	
	public static void setSoapEndpointUrl(Object wsProxy,String soapUrl){
		javax.xml.ws.BindingProvider bp = (javax.xml.ws.BindingProvider) wsProxy /*port*/;
		Map<String,Object> context = bp.getRequestContext();
		context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, soapUrl);
	}
	
	public static void setBasicHttpAuth(Object wsProxy,String username,String password){
		javax.xml.ws.BindingProvider bp = (javax.xml.ws.BindingProvider) wsProxy /*port*/;
		Map<String,Object> context = bp.getRequestContext();
		context.put(BindingProvider.USERNAME_PROPERTY,username);
		context.put(BindingProvider.PASSWORD_PROPERTY,password);
	}

}
