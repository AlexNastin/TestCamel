package by.st.camel.sms;

import java.io.Serializable;

public final class SMS implements Cloneable, Serializable {

	private long smsId;
	private String mid;
	private String numberPhone;
	private String text;
	private String status;
	private String finalStatus;

	public SMS() {
	}

	public long getSmsId() {
		return smsId;
	}

	public void setSmsId(long smsId) {
		this.smsId = smsId;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public long getIdSMS() {
		return smsId;
	}

	public void setIdSMS(long idSMS) {
		this.smsId = idSMS;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	@Override
	public String toString() {
		return "SMS [smsId=" + smsId + ", mid=" + mid + ", numberPhone="
				+ numberPhone + ", text=" + text + ", status=" + status + "]";
	}

}
