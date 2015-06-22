package by.st.camel.processor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppMessageType;

import by.st.camel.sms.ContainerSMS;
import by.st.camel.sms.SMS;

public class DeliveryReceiptProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("DeliveryReceiptProcessor START!");
		ContainerSMS containerSMS = ContainerSMS.getInstance();
		SMS sms = containerSMS.getSms().get(containerSMS.getSms().size() - 1);
		System.out.println(sms);
//		System.out.println(exchange.getIn().getHeader(SmppConstants.ID));
		ArrayList<String> arrayList = (ArrayList<String>) exchange.getIn()
				.getHeader(SmppConstants.ID);
//		System.out.println(arrayList.get(0));
		sms.setMid(arrayList.get(0));
	}

}
