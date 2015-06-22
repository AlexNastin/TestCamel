package by.st.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.smpp.SmppConstants;

import org.jsmpp.util.DeliveryReceiptState;

import by.st.camel.sms.ContainerSMS;
import by.st.camel.sms.SMS;

public class SMSProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		ContainerSMS containerSMS = ContainerSMS.getInstance();
		
		String mid = (String) exchange.getIn().getHeader(SmppConstants.ID);
		DeliveryReceiptState status = (DeliveryReceiptState) exchange.getIn().getHeader("CamelSmppStatus");
		SMS sms = containerSMS.getSmsToMid(mid);
		sms.setStatus(status.name());

	}

}
