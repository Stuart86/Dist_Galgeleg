package Server;

import javax.xml.ws.Endpoint;

class Kontoserver 
{
	public static void main(String[] args)
	{

		System.out.println("Publicerer Galgeleg over SOAP");

		Galgelogik gl = new Galgelogik();
		
		Endpoint.publish("http://[::]:9918/galgeleg", gl);
		System.out.println("Galgeleg publiceret over SOAP");
	}
	
}
