package by.st.camel.processor;

import org.apache.camel.Exchange;

import org.apache.camel.Processor;

import by.st.camel.sms.ContainerSMS;
import by.st.camel.sms.SMS;

public class HttpSMSProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		ContainerSMS containerSMS = ContainerSMS.getInstance();
		SMS sms = new SMS();

		String deliveryAddress = (String) exchange.getIn().getHeader("deliveryAddress");
		String smsId = (String) exchange.getIn().getHeader("smsId");
		String text = exchange.getIn().getBody(String.class);

		exchange.getIn().setHeader("CamelSmppDestAddr", deliveryAddress);

		sms.setNumberPhone(deliveryAddress);
		sms.setIdSMS(Integer.parseInt(smsId));
		sms.setText(text);
		containerSMS.addSMS(sms);

	}

}
