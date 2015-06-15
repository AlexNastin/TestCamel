package by.st.camel.route;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.SysPropertiesFunction;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppMessageType;
import org.jsmpp.bean.DeliverSm;
import org.apache.camel.component.smpp.SmppSubmitSmCommand;

import by.st.camel.processor.DeliveryReceiptProcessor;
import by.st.camel.processor.SMSProcessor;

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

		System.out.println("SOAP RouteBuilder");

		SMSProcessor smsProcessor = new SMSProcessor();
		SMSProcessor smsProcessor2 = new SMSProcessor();
		DeliveryReceiptProcessor deliveryReceiptProcessor = new DeliveryReceiptProcessor();

		// from("jetty:http://localhost:8080/camel/test")
		// .process(new Processor() {
		//
		// @Override
		// public void process(Exchange exchange) throws Exception {
		// exchange.getIn().setHeader("CamelSmppDestAddr", "456");
		// exchange.getIn()
		// .setHeader("CamelSmppSourceAddr", "123");
		//
		// exchange.getIn().setHeader(
		// "CamelSmppRegisteredDelivery",
		// new Byte((byte) 1)); // registered_delivery
		//
		// }
		// })
		// .setHeader("CamelSmppDestAddr", header("deliveryAddress"))
		// .setHeader("CamelSmppAlphabet", constant(4))
		// .to("smpp://SYStEK@127.0.0.1:6677?password=SysTek12&enquireLinkTimer=3000&transactionTimer=5000&registeredDelivery=1&systemType=producer");

		// from(
		// "smpp://SYStEK@127.0.0.1:6677?password=SysTek12&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
		// .process(new Processor() {
		//
		// @Override
		// public void process(Exchange exchange) throws Exception {
		// System.out.println("START!");
		// System.out.println(SmppMessageType.DeliveryReceipt);
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.MESSAGE_TYPE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.MESSAGE_STATE));
		// System.out.println(exchange.getIn().getBody());
		//
		// Map<String, Object> maps = exchange.getIn().getHeaders();
		// System.out.println(maps.isEmpty());
		// for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
		// System.out.println(mEntry.getKey() + " | "
		// + mEntry.getValue());
		// }
		//
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.ID));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.SUBMITTED));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.DELIVERED));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.DONE_DATE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.SUBMIT_DATE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.ERROR));
		// }
		// });

		// from("smpp://pavel@localhost:2200?password=wpsd&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
		// .process(new Processor() {
		// @Override
		// public void process(Exchange exchange) throws Exception {
		// System.out.println("START!");
		//
		// System.out.println(exchange.getUnitOfWork());
		// System.out.println(exchange.getIn());
		//
		// System.out.println("OUT "
		// + exchange.getOut().hasHeaders());
		// System.out.println("OUT "
		// + exchange.getOut().hasAttachments());
		//
		// System.out
		// .println("IN" + exchange.getIn().hasHeaders());
		// System.out.println("IN"
		// + exchange.getIn().hasAttachments());
		//
		// System.out.println(exchange.getIn().getMandatoryBody());
		//
		// Set<String> set = exchange.getOut()
		// .getAttachmentNames();
		// System.out.println(set.isEmpty());
		// for (String string : set) {
		// System.out.println(string);
		// }
		//
		// System.out
		// .println("BODY" + exchange.getOut().getBody());
		// Map<String, Object> maps2 = exchange.getProperties();
		//
		// System.out.println(maps2.isEmpty());
		// for (Map.Entry<String, Object> mEntry : maps2
		// .entrySet()) {
		// System.out.println(mEntry.getKey() + " || "
		// + mEntry.getValue());
		// }
		//
		// Map<String, DataHandler> maps3 = exchange.getIn()
		// .getAttachments();
		// System.out.println("MAPS 3 " + maps3.isEmpty());
		// System.out
		// .println("-----------------------------------");
		// System.out.println(SmppMessageType.DeliveryReceipt);
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.MESSAGE_TYPE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.MESSAGE_STATE));
		//
		// System.out.println(exchange.getIn().getBody());
		//
		// Map<String, Object> maps = exchange.getIn()
		// .getHeaders();
		// System.out.println(maps.isEmpty());
		// for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
		// System.out.println(mEntry.getKey() + " | "
		// + mEntry.getValue());
		// }
		// System.out.println(exchange.getIn().getHeader(
		// "CamelSmppRegisteredDelivery"));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.ID));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.SUBMITTED));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.DELIVERED));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.DONE_DATE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.SUBMIT_DATE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.ERROR));
		// }
		// });
		from("jetty:http://localhost:8080/camel/test").process(
				smsProcessor2).to("smpp://smppclient1@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=producer");

		
		from("smpp://smppclient1@localhost:2775?password=password&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
				.process(smsProcessor);
		
		
		// from("jetty:http://localhost:8080/camel/test")
		// .process(new Processor() {
		// @Override
		// public void process(Exchange exchange) throws Exception {
		// System.out.println("START 2!");
		// Map<String, Object> maps = exchange.getIn()
		// .getHeaders();
		// System.out.println(maps.isEmpty());
		// for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
		// System.out.println(mEntry.getKey() + " | "
		// + mEntry.getValue());
		// }
		//
		// exchange.getIn().setHeader(
		// "CamelSmppRegisteredDelivery",
		// new Byte((byte) 1)); // registered_delivery
		// exchange.getIn().setBody("AAAAAA");
		//
		//
		// }
		//
		// })
		// .to("smpp://pavel@localhost:2200?password=wpsd&enquireLinkTimer=3000&transactionTimer=5000&systemType=producer")
		// .process(new Processor() {
		//
		// @Override
		// public void process(Exchange exchange) throws Exception {
		// System.out.println("START3!");
		//
		//
		// System.out.println("OUT "
		// + exchange.getOut().hasHeaders());
		// System.out.println("OUT "
		// + exchange.getOut().hasAttachments());
		//
		// System.out
		// .println("IN" + exchange.getIn().hasHeaders());
		// System.out.println("IN"
		// + exchange.getIn().hasAttachments());
		//
		// System.out.println("asdsa " + exchange.getIn());
		// System.out.println(exchange.getIn().getMandatoryBody());
		// // System.out.println(exchange.getOut().getBody());
		//
		// Set<String> set = exchange.getOut()
		// .getAttachmentNames();
		// System.out.println(set.isEmpty());
		// for (String string : set) {
		// System.out.println(string);
		// }
		//
		//
		// System.out
		// .println("BODY" + exchange.getOut().getBody());
		// Map<String, Object> maps2 = exchange.getProperties();
		//
		// System.out.println(maps2.isEmpty());
		// for (Map.Entry<String, Object> mEntry : maps2
		// .entrySet()) {
		// System.out.println(mEntry.getKey() + " || "
		// + mEntry.getValue());
		// }
		//
		// Map<String, DataHandler> maps3 = exchange.getIn()
		// .getAttachments();
		// System.out.println("MAPS 3 " + maps3.isEmpty());
		//
		// System.out.println(SmppMessageType.DeliveryReceipt);
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.MESSAGE_TYPE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.MESSAGE_STATE));
		// System.out.println(exchange.getIn().getBody());
		//
		// Map<String, Object> maps = exchange.getIn()
		// .getHeaders();
		// System.out.println(maps.isEmpty());
		// for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
		// System.out.println(mEntry.getKey() + " | "
		// + mEntry.getValue());
		// }
		//
		// System.out
		// .println("____________________________________________");
		// System.out.println(exchange.getIn().getHeader(
		// "CamelSmppRegisteredDelivery"));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.ID));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.SUBMITTED));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.DELIVERED));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.DONE_DATE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.SUBMIT_DATE));
		// System.out.println(exchange.getIn().getHeader(
		// SmppConstants.ERROR));
		// }
		// });

		// @Override
		// public void process(Exchange exchange)
		// throws Exception {
		//
		// System.out.println(exchange.getProperties());
		// Map<String, Object> maps = exchange
		// .getProperties();
		// for (Map.Entry<String, Object> mEntry : maps
		// .entrySet()) {
		// System.out.println(mEntry.getKey() + " | "
		// + mEntry.getValue());
		// }
		//
		// System.out.println(exchange.getIn().getBody()
		// .toString());
		//
		// }
		// });

		// from(ROUTER_ENDPOINT_URI).to("mock:end");
		// from("cxf://http://localhost:8080/camel/services/webServiceCamelWS?"
		// +
		// "serviceClass=by.st.camel.WebServiceCamelI" +
		// "&serviceName={http://localhost:8080/camel/services/webServiceCamelWS}WebServiceCamelWS"
		// +
		// "&portName={http://localhost:8080/camel/services/webServiceCamelWS}WebServiceCamelWSPort"
		// +
		// "&wsdlURL=http://localhost:8080/camel/services/webServiceCamelWS?wsdl&dataFormat=POJO").process(new
		// Processor() {
		//
		// @Override
		// public void process(Exchange arg0) throws Exception {
		// System.out.println("code run here");
		//
		// }
		// });
		// from("cxf://http://localhost:8080/camel/services/webServiceCamelWS?"
		// +
		// "serviceClass=by.st.camel.WebServiceCamelI").process(new Processor()
		// {
		//
		// @Override
		// public void process(Exchange arg0) throws Exception {
		// System.out.println("code run here");
		//
		// }
		// });
		//
		// from(
		// "smpp://SYStEK@127.0.0.1:6677?password=SysTek12&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
		// .setHeader(Exchange.HTTP_METHOD, constant("POST")).to(
		// "http://localhost:8080/camel/jetty/test");
	}
}
