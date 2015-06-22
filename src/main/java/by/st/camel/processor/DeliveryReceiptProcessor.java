package by.st.camel.processor;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.smpp.SmppConstants;

import by.st.camel.sms.ContainerSMS;
import by.st.camel.sms.SMS;

public class DeliveryReceiptProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		ContainerSMS containerSMS = ContainerSMS.getInstance();
		SMS sms = containerSMS.getSms().get(containerSMS.getSms().size() - 1);
		ArrayList<String> arrayList = (ArrayList<String>) exchange.getIn().getHeader(SmppConstants.ID);
		sms.setMid(arrayList.get(0));
	}

}
