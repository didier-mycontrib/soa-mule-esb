package std;

import org.mule.api.annotations.param.Payload;

public class SimplePojoCalculator {

	public String computeOperation(@Payload Operation op){
		String res=null;
		String valRes="0";
		System.out.println("payload:" + op);
		System.out.println("type of payload is:" + op.getClass().getName());
		if("add".equals(op.getOp())){
		   valRes=String.valueOf(op.getX() + op.getY());
		   res="<addResponse xmlns='http://standard/'><return>"+valRes+"</return></addResponse>";
		}
		else if("mult".equals(op.getOp())){
			   valRes=String.valueOf(op.getX() * op.getY());
			   res="<multResponse xmlns='http://standard/'><return>"+valRes+"</return></multResponse>";
		}
		
		return res;
	}
	
}
