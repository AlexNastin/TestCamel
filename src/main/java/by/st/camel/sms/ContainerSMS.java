package by.st.camel.sms;

import java.util.ArrayList;
import java.util.List;

public final class ContainerSMS {

	private List<SMS> smsList;

	private static ContainerSMS instnace = new ContainerSMS();;

	private ContainerSMS() {
		smsList = new ArrayList<SMS>();
	}

	public List<SMS> getSms() {
		return smsList;
	}

	public void setSms(List<SMS> sms) {
		this.smsList = sms;
	}

	public boolean addSMS(SMS sms) {
		return smsList.add(sms);
	}

	public SMS getSMS(int idSMS) {
		for (SMS sms : smsList) {
			if (sms.getIdSMS() == idSMS) {
				return sms;
			}
		}
		return null;
	}
	
	public SMS getSmsToMid(String mid ) {
		for (SMS sms : smsList) {
			if (sms.getMid().equals(mid)) {
				return sms;
			}
		}
		return null;
	}

	public static ContainerSMS getInstance() {
		return instnace;

	}

}
