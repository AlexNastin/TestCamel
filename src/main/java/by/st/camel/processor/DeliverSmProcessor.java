package by.st.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DeliverSmProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/html");
		exchange.getIn().setHeader(Exchange.HTTP_CHARACTER_ENCODING, "utf-8");
		String param = exchange.getIn().getHeader("CamelSmppSourceAddr",
				String.class);
		exchange.getIn().setHeader(Exchange.HTTP_QUERY, "sourceAddr=" + param);
		exchange.getIn().setBody(exchange.getIn().getBody(String.class));

	}

}
