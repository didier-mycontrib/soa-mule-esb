package std.mule;

import org.apache.cxf.staxutils.DepthXMLStreamReader;
import org.mule.api.annotations.ContainsTransformerMethods;
import org.mule.api.annotations.Transformer;
import org.mule.api.annotations.param.Payload;

import std.Operation;

//ce bean doit simplement être enregistré au sein de mule comme un bean de spring
// <spring:bean name="automaticAnotatedTransformerBean" class="std.mule.AutomaticAnotatedTransformerBean" />
//pas besoin de référencement explicite dans le flow : automatisme suivant type de données
//à transformer

@ContainsTransformerMethods
public class AutomaticAnotatedTransformerBean{
	
	@Transformer
     public Operation depthXMLStreamReaderToOperation(@Payload DepthXMLStreamReader xsr)
	 {
		System.out.println("via depthXMLStreamReaderToOperation of AutomaticAnotatedTransformerBean");
		return XmlStaxTransformer.depthXMLStreamReaderToOperation(xsr);
	 }
}
