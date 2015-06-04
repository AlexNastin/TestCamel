package by.st.camel;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceCamelI {
	
	@WebMethod
	public String print();

}
