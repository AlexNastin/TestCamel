package by.st.camel.route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.SysPropertiesFunction;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppMessageType;
import org.jsmpp.bean.DeliverSm;
import org.apache.camel.component.smpp.SmppSubmitSmCommand;

import by.st.camel.processor.DeliveryReceiptProcessor;
import by.st.camel.processor.SMSProcessor;
import by.st.camel.processor.HttpSMSProcessor;
import by.st.camel.sms.SMS;

public class MyRouteBuilderSOAP extends RouteBuilder {
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
		System.out.println("JettyHTTP RouteBuilder");
		// http://localhost:8080/camel/test?deliveryAddress=1231313&smsId=123

		SMSProcessor smsProcessor = new SMSProcessor();
		HttpSMSProcessor httpSMSProcessor = new HttpSMSProcessor();
		DeliveryReceiptProcessor deliveryReceiptProcessor = new DeliveryReceiptProcessor();

		
		from("smpp://smppclient@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=cp")
				.process(smsProcessor);
		
		from("jetty:http://localhost:8080/camel/test")
				.process(httpSMSProcessor)
				.to("smpp://smppclient@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=cp")
				.process(deliveryReceiptProcessor);

		

		// from("jetty:http://localhost:8080/camel/test")
		// .process(httpSMSProcessor)
		// .to("smpp://pavel@localhost:2200?password=wpsd&enquireLinkTimer=3000&transactionTimer=5000&systemType=producer");
		//
		// from(
		// "smpp://pavel@localhost:2200?password=wpsd&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
		// .process(smsProcessor);

	}
}
