package generic;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class WsDynProxyFactory {
	
	private String proxyWsUrl;//url du service externe (à transformer) à injecter
	private String namespace;//"http://service.tp/" ou ...
	private String portName;//"CalculateurImplPort" ou ...
	private String serviceName;//"CalculateurImplService" ou ...
	private String interfaceName; //tp.service.Calculateur ou ...
	
	private Object wsProxy;

	
	public String getNamespace() {
		return namespace;
	}


	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}


	public String getPortName() {
		return portName;
	}


	public void setPortName(String portName) {
		this.portName = portName;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}


	public String getProxyWsUrl() {
		return proxyWsUrl;
	}


	
	public void setProxyWsUrl(String proxyWsUrl) {
		this.proxyWsUrl = proxyWsUrl;
	}
	
	
	public Object getWsPort(){
		
		if(this.wsProxy==null){
			QName SERVICE_NAME = new QName(namespace,serviceName );
			QName PORT_NAME = new QName(namespace, portName);
			Service service = Service.create(SERVICE_NAME);		//javax.xml.ws.Service
			// Endpoint Address
			//this.proxyWsUrl = "http://localhost:8080/wsCalculateur/services/calculateur";
			// Add a port to the Service , javax.xml.ws.soap.SOAPBinding
			service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING,proxyWsUrl);
			try {
				this.wsProxy = service.getPort(PORT_NAME, Class.forName(interfaceName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return 	wsProxy;	
	}

}
