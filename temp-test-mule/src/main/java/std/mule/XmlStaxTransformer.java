package std.mule;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.staxutils.DepthXMLStreamReader;

import std.Operation;

public class XmlStaxTransformer {
	
	public static Operation depthXMLStreamReaderToOperation( DepthXMLStreamReader xsr)
	 {
		  String eltName=null;
		  Operation op = new Operation ();
		    System.out.println("payload:" + xsr);
			System.out.println("type of payload is:" + xsr.getClass().getName());
			try {
				while(xsr.hasNext()){
					xsr.next();
				    if(xsr.getEventType() == XMLStreamReader.START_ELEMENT){
				    	eltName=xsr.getLocalName();
				        System.out.print("<"+eltName+">");
				    }
				    if(xsr.getEventType() == XMLStreamReader.CHARACTERS){
				        System.out.print(xsr.getText());
				        if("x".equals(eltName))
				        		op.setX(Double.parseDouble(xsr.getText()));
				        else 
				        if("y".equals(eltName))
			        		op.setY(Double.parseDouble(xsr.getText()));				     
				    }
				    if(xsr.getEventType() == XMLStreamReader.END_ELEMENT){
				    	eltName=xsr.getLocalName();
				    	if(xsr.getDepth()==-1){
				    	    if("add".equals(eltName))
				        		op.setOp("add");
					         else 
						    if("mult".equals(eltName))
					        		op.setOp("mult");
				    	}
				        System.out.println("</"+eltName+"> depth=" + xsr.getDepth());
				        eltName=null;
				    }
				}
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		  	
		  return op;
	 }

	

}
