package by.st.camel.route;


import org.apache.camel.builder.RouteBuilder;

import by.st.camel.processor.DeliverSmProcessor;
import by.st.camel.processor.DeliveryReceiptProcessor;
import by.st.camel.processor.SMSProcessor;
import by.st.camel.processor.HttpSMSProcessor;

public class MyRouteBuilderJetty extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		SMSProcessor smsProcessor = new SMSProcessor();
		HttpSMSProcessor httpSMSProcessor = new HttpSMSProcessor();
		DeliveryReceiptProcessor deliveryReceiptProcessor = new DeliveryReceiptProcessor();
		DeliverSmProcessor deliverSmProcessor = new DeliverSmProcessor();

		from("smpp://smppclient@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=cp")
				.choice()
				.when(header("CamelSmppMessageType").isEqualTo(
						"DeliveryReceipt")).process(smsProcessor)
				.when(header("CamelSmppMessageType").isEqualTo("DeliverSm"))
				.process(deliverSmProcessor).to("http://localhost:8080/WebTest/TestServlet");

		from("jetty:http://localhost:8010/camel/test")
				.process(httpSMSProcessor)
				.to("smpp://smppclient@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=cp")
				.process(deliveryReceiptProcessor);



	}
}
