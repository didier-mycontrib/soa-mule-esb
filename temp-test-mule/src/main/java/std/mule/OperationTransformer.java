package std.mule;

import org.apache.cxf.staxutils.DepthXMLStreamReader;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

//transformateur spécifique (codé en java) et devant être explicitement référencé dans le flow
//via 
//<custom-transformer class="std.mule.OperationTransformer" doc:name="Java specif transformer"/> 

public class OperationTransformer  extends AbstractMessageTransformer{
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		Object resObj = XmlStaxTransformer.depthXMLStreamReaderToOperation((DepthXMLStreamReader)message.getPayload());
		for(String propName : message.getInboundPropertyNames()){
			System.out.println("inboundProperty: " + propName + "="+message.getInboundProperty(propName));
		}
			
		return resObj;
	}
	
     

}
