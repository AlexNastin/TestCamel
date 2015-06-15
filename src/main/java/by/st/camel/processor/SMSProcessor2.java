package by.st.camel.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SMSProcessor2 implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("START 2!");
		Map<String, Object> maps = exchange.getIn()
				.getHeaders();
		System.out.println(maps.isEmpty());
		for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
			System.out.println(mEntry.getKey() + " | "
					+ mEntry.getValue());
		}
		
		exchange.getIn().setHeader(
				"CamelSmppRegisteredDelivery",
				new Byte((byte) 1)); // registered_delivery
		exchange.getIn().setBody("AAAAAA");
		
	}

}
