package by.st.camel.processor;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.StreamCache;
import org.apache.camel.converter.stream.InputStreamCache;
import org.apache.cxf.ws.policy.AlternativeSelector;
import org.eclipse.jetty.server.Request;
import org.jsmpp.bean.AlertNotification;

import by.st.camel.sms.ContainerSMS;
import by.st.camel.sms.SMS;

public class HttpSMSProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("START HttpSMSProcessor!");

		ContainerSMS containerSMS = ContainerSMS.getInstance();
		SMS sms = new SMS();

		String deliveryAddress = (String) exchange.getIn().getHeader(
				"deliveryAddress");
		String smsId = (String) exchange.getIn().getHeader("smsId");
		String text = exchange.getIn().getBody(String.class);

		exchange.getIn().setHeader("CamelSmppDestAddr", deliveryAddress);
//		System.out.println(deliveryAddress);
//		System.out.println(smsId);
//		System.out.println(text);

		sms.setNumberPhone(deliveryAddress);
		sms.setIdSMS(Integer.parseInt(smsId));
		sms.setText(text);
		containerSMS.addSMS(sms);

	}

}
