package server;

import javax.xml.ws.Endpoint;

class GalgeServer 
{
	public static void main(String[] args)
	{

		System.out.println("Publicerer Galgeleg over SOAP");

		Galgelogik gl = new Galgelogik();
		BrugerGodkendelseRMI bg = new BrugerGodkendelseRMI();
		
		Endpoint.publish("http://[::]:9918/galgeleg", gl);
		Endpoint.publish("http://[::]:9918/brugergodkendelse", bg);
		System.out.println("Galgeleg publiceret over SOAP");
	}
	
}
