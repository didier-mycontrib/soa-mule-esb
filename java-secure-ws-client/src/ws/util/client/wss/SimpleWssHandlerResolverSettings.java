package ws.util.client.wss;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class SimpleWssHandlerResolverSettings {
	
	public static void setWssUsernameTokenHandlerResolver(Service s,String username,String password){
		final WSSUsernameTokenSecurityHandler wSSUsernameTokenSecurityHandler
		 = new WSSUsernameTokenSecurityHandler(username,password);
		
		s.setHandlerResolver( new HandlerResolver() {

	        @Override
	        public List<Handler> getHandlerChain(PortInfo portInfo) {
	            List<Handler> handlerList = new ArrayList<Handler>();
	            handlerList.add(wSSUsernameTokenSecurityHandler);
	            return handlerList;
	        }
	    });
	}

}
