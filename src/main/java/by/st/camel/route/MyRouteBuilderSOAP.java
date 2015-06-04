package by.st.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

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
		// from(
		// "smpp://SYStEK@127.0.0.1:6677?password=SysTek12&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
		// .process(new Processor() {
		//
		// @Override
		// public void process(Exchange arg0) throws Exception {
		// System.out.println("code run here");
		//
		// }
		// });
		from(
				"smpp://SYStEK@127.0.0.1:6677?password=SysTek12&enquireLinkTimer=3000&transactionTimer=5000&systemType=consumer")
				.setHeader(Exchange.HTTP_METHOD, constant("POST")).to(
						"http://localhost:8080/camel/jetty/test");
	}

}
