package std;

import javax.jws.WebService;

import tp.service.Calculateur;

@WebService(endpointInterface="std.Calculator")
public class CalculatorBean implements Calculator {
		
	private Calculateur calcProxy =null;
	
	//injection of "calcProxy" property 
	// via spring or via "mule component binding"
	public void setCalcProxy(Calculateur calcProxy) {
		this.calcProxy = calcProxy;
	}
	
	public Calculateur getCalcProxy() {
		return calcProxy;
	}



	@Override
	public double add(double x,double y) {
		System.out.println("add(x="+x+",y="+y+")");//trace faculative
		double res = calcProxy.addition(x,y);
		//double res=x+y;//temp direct computing (debug)
		System.out.println("return res=addition(a,b)=" + res);//trace faculative
		return res;
	}

	@Override
	public double mult(double x,double y) {
		System.out.println("mult(x="+x+",y="+y+")");//trace faculative
		double res = calcProxy.multiplication(x,y);
		System.out.println("return res=multiplication(a,b)=" + res);//trace faculative
		return res;
	}

}
