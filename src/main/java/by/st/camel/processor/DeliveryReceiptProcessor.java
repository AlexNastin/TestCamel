package by.st.camel.processor;

import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppMessageType;

public class DeliveryReceiptProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("START3!");

		System.out.println("OUT " + exchange.getOut().hasHeaders());
		System.out.println("OUT " + exchange.getOut().hasAttachments());

		System.out.println("IN" + exchange.getIn().hasHeaders());
		System.out.println("IN" + exchange.getIn().hasAttachments());

		System.out.println("asdsa " + exchange.getIn());
		System.out.println(exchange.getIn().getMandatoryBody());
		// System.out.println(exchange.getOut().getBody());

		Set<String> set = exchange.getOut().getAttachmentNames();
		System.out.println(set.isEmpty());
		for (String string : set) {
			System.out.println(string);
		}

		System.out.println("BODY" + exchange.getOut().getBody());
		Map<String, Object> maps2 = exchange.getProperties();

		System.out.println(maps2.isEmpty());
		for (Map.Entry<String, Object> mEntry : maps2.entrySet()) {
			System.out.println(mEntry.getKey() + " || " + mEntry.getValue());
		}

		Map<String, DataHandler> maps3 = exchange.getIn().getAttachments();
		System.out.println("MAPS 3 " + maps3.isEmpty());

		System.out.println(SmppMessageType.DeliveryReceipt);
		System.out.println(exchange.getIn().getHeader(
				SmppConstants.MESSAGE_TYPE));
		System.out.println(exchange.getIn().getHeader(
				SmppConstants.MESSAGE_STATE));
		System.out.println(exchange.getIn().getBody());

		Map<String, Object> maps = exchange.getIn().getHeaders();
		System.out.println(maps.isEmpty());
		for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
			System.out.println(mEntry.getKey() + " | " + mEntry.getValue());
		}

		System.out.println("____________________________________________");
		System.out.println(exchange.getIn().getHeader(
				"CamelSmppRegisteredDelivery"));
		System.out.println(exchange.getIn().getHeader(SmppConstants.ID));
		System.out.println(exchange.getIn().getHeader(SmppConstants.SUBMITTED));
		System.out.println(exchange.getIn().getHeader(SmppConstants.DELIVERED));
		System.out.println(exchange.getIn().getHeader(SmppConstants.DONE_DATE));
		System.out.println(exchange.getIn()
				.getHeader(SmppConstants.SUBMIT_DATE));
		System.out.println(exchange.getIn().getHeader(SmppConstants.ERROR));
	}

}
