package brugerautorisation;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import server.BrugerGodkendelseInterface;
import server.GalgelegInterface;

public class tet 
{
	static BrugerGodkendelseInterface BGI;
	static GalgelegInterface G;
	
	public static void main(String[] args) throws MalformedURLException 
	{
		URL url = new URL("http://ubuntu4.saluton.dk:9918/brugergodkendelse?wsdl");
		QName qname = new QName("http://server/", "BrugerGodkendelseRMIService");
		Service service = Service.create(url, qname);
		BGI= service.getPort(BrugerGodkendelseInterface.class);
		System.out.println("Der er forbundet til serveren");
	}

}
