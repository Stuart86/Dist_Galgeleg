package klient;

import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import server.BrugerGodkendelseRMIInterface;
import server.GalgelogikInterface;

public class ServerForbindelse 
{
	GalgelogikInterface GI;
	BrugerGodkendelseRMIInterface BGI;
	ServerLogin SL = new ServerLogin();
	
	public  void ServerTilslutGalgeLogik() throws RemoteException, Exception
	{
		URL url = new URL("http://ubuntu4.saluton.dk:9918/galgeleg?wsdl");
		QName qname = new QName("http://server/", "GalgelogikService");
		Service service = Service.create(url, qname);
		GI = service.getPort(GalgelogikInterface.class);
		System.out.println("Der er forbundet til serveren");
		
		ServerTilslutBrugerLogin();
	}
	public  void ServerTilslutBrugerLogin() throws RemoteException, Exception
	{
		URL url = new URL("http://ubuntu4.saluton.dk:9918/brugergodkendelse?wsdl");
		QName qname = new QName("http://server/", "BrugerGodkendelseRMIService");
		Service service = Service.create(url, qname);
		BGI= service.getPort(BrugerGodkendelseRMIInterface.class);

		SL.Login(BGI);
	}

}
