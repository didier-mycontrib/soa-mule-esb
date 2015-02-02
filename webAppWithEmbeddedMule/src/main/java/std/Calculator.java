package std;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Calculator {

	public double add(@WebParam(name="x")double x,
			               @WebParam(name="y")double y);
	public double mult(@WebParam(name="x")double x,
			               @WebParam(name="y")double y);
}

