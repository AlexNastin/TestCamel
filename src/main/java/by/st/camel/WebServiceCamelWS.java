package by.st.camel;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://localhost:8080/camel", endpointInterface = "by.st.camel.WebServiceCamelI", serviceName = "WebServiceCamelWS")
public class WebServiceCamelWS implements WebServiceCamelI {

	@WebMethod(action = "print")
	@Override
	public String print() {
		return "TEST WEB SERVICE";
	}
}
