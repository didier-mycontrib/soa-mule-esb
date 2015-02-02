package tp;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.client.DefaultLocalMuleClient;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;

public class JavaTestApp {
	
	public static void main(String[] args) {
		try {
			DefaultMuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
			SpringXmlConfigurationBuilder configBuilder = new SpringXmlConfigurationBuilder("tx-java-mule.xml");
			//     new SpringXmlConfigurationBuilder(new String[] { "mule-config.xml", "another-config.xml" });
			MuleContext muleContext = muleContextFactory.createMuleContext(configBuilder);
			muleContext.start();

			
			//MuleContext muleContext = (MuleContext) servletContext.getAttribute(MuleProperties.MULE_CONTEXT_PROPERTY);
			
			MuleClient client = new DefaultLocalMuleClient(muleContext);

			// send a jms message asynchronously
			//client.dispatch("jms://my.queue", "some data", null);

			// or to receive a pop3 message via a configured mailbox
			//MuleMessage message = client.receive("pop3://myInboxProvider", 3000);

			// or synchronous send a inter-vm message
			//MuleMessage message2 = client.send("vm://my.object", "Some more data", null);
			
			Double[] soapArgs = new Double[]{5.0, 6.0};
			MuleMessage msgResult = client.send
			        ("cxf:http://localhost:8081/tx-java-mule/StdCalculateurPort?method=add", soapArgs, null);
			
			System.out.println("result:"+msgResult.getPayloadAsString());
			
			//to stop :
			//muleContext.stop();
			//muleContext.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
