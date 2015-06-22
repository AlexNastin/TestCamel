package by.st.camel.processor;

import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppMessageType;
import org.jsmpp.util.DeliveryReceiptState;

import by.st.camel.sms.ContainerSMS;
import by.st.camel.sms.SMS;

public class SMSProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("START SMSProcessor!");
		ContainerSMS containerSMS = ContainerSMS.getInstance();

		Map<String, DataHandler> maps3 = exchange.getIn().getAttachments();
		System.out.println("-----------------------------------");
//		System.out.println(exchange.getIn().getHeader(
//				SmppConstants.MESSAGE_TYPE));
//		System.out.println("1 "
//				+ exchange.getIn().getHeader(SmppConstants.COMMAND_STATUS));
//		System.out.println("2 "
//				+ exchange.getIn().getHeader(SmppConstants.COMMAND_ID));

//		Map<String, Object> maps = exchange.getIn().getHeaders();
//		for (Map.Entry<String, Object> mEntry : maps.entrySet()) {
//			System.out.println(mEntry.getKey() + " --------|--------- "
//					+ mEntry.getValue());
//		}
		String mid = (String) exchange.getIn().getHeader(SmppConstants.ID);
//		System.out.println(mid);
		DeliveryReceiptState status = (DeliveryReceiptState) exchange.getIn()
				.getHeader("CamelSmppStatus");

//		System.out.println(exchange.getIn().getHeader("CamelSmppStatus"));

		SMS sms = containerSMS.getSmsToMid(mid);
//		System.out.println(sms);
		sms.setStatus(status.name());

		System.out.println(sms);

	}

}
