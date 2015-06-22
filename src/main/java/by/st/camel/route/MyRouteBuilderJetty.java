package by.st.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import by.st.camel.processor.DeliverSmProcessor;
import by.st.camel.processor.DeliveryReceiptProcessor;
import by.st.camel.processor.SMSProcessor;
import by.st.camel.processor.HttpSMSProcessor;

public class MyRouteBuilderJetty extends RouteBuilder {
	private static final String ROUTER_ADDRESS = "http://localhost:8080/camel/services/webServiceCamelWS";
	private static final String SERVICE_CLASS = "serviceClass=by.st.camel.WebServiceCamelWS";
	private static final String WSDL_LOCATION = "wsdlURL=wsdl/WebServiceCamelI.wsdl";
	private static final String SERVICE_NAME = "serviceName={http://localhost:8080/camel/}WebServiceCamelWS";
	private static final String SOAP_OVER_HTTP_ROUTER = "portName={http://localhost:8080/camel/}WebServiceCamelWSPort";

	private static final String ROUTER_ENDPOINT_URI = "cxf://" + ROUTER_ADDRESS
			+ "?" + SERVICE_CLASS + "&" + WSDL_LOCATION + "&" + SERVICE_NAME
			+ "&" + SOAP_OVER_HTTP_ROUTER;

	@Override
	public void configure() throws Exception {

		SMSProcessor smsProcessor = new SMSProcessor();
		HttpSMSProcessor httpSMSProcessor = new HttpSMSProcessor();
		DeliveryReceiptProcessor deliveryReceiptProcessor = new DeliveryReceiptProcessor();
		DeliverSmProcessor deliverSmProcessor = new DeliverSmProcessor();

		from(
				"smpp://smppclient@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=cp")
				.choice()
				.when(header("CamelSmppMessageType").isEqualTo(
						"DeliveryReceipt")).process(smsProcessor)
				.when(header("CamelSmppMessageType").isEqualTo("DeliverSm"))
				.process(deliverSmProcessor)
				.to("http://localhost:8080/WebTest/TestServlet");

		from("jetty:http://localhost:8010/camel/test")
				.process(httpSMSProcessor)
				.to("smpp://smppclient@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=cp")
				.process(deliveryReceiptProcessor);

		from("cxf://http://localhost:8080/camel/services/webServiceCamelWS?"
						+ "serviceClass=by.st.camel.WebServiceCamelI").process(
				new Processor() {

					@Override
					public void process(Exchange arg0) throws Exception {
						System.out.println("Code run here");
					}
				});

	}
}
