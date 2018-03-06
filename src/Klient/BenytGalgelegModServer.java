package Klient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import Server.GalgelegInterface;
//test//

public class BenytGalgelegModServer 
{
	static GalgelegInterface GI;
	
	public static void main(String[] args) throws MalformedURLException 
	{
	
		String mitGæt, Brugernavn, Password;
		boolean PasswordForsøg = false;
		boolean GætForsøg = true;
		Scanner Scanner = new Scanner(System.in);
		
		ServerTilslut();
		
		while(!PasswordForsøg) 
		{
            System.out.println("Indtast brugernavn:");
            Brugernavn = Scanner.nextLine();
            System.out.println("Indtast kodeord:");
            Password = Scanner.nextLine();
            
            if(GI.Brugergodkendelse(Brugernavn, Password)) 
            {
                System.out.println("Du er nu logged på med : " + Brugernavn + " og passwordet: " + Password);
                PasswordForsøg = true;
            } else 
            {
                System.out.println("Forkert brugernavn eller password\nPrøv igen!");
            }
        }
		
		while (GætForsøg)
        {
            System.out.println("Gæt et bogstav");
            mitGæt = Scanner.next();
            System.out.println(GI.gætBogstav(mitGæt));
            System.out.println(GI.logStatus());  
            
            if (GI.erSpilletSlut()) GætForsøg = false;
        }
		
 
	}
	public static void ServerTilslut() throws MalformedURLException
	{
		URL url = new URL("http://ubuntu4.saluton.dk:9918/galgeleg?wsdl");
		QName qname = new QName("http://Server/", "GalgelogikService");
		Service service = Service.create(url, qname);
		GI = service.getPort(GalgelegInterface.class);
		System.out.println("Der er forbundet til serveren");
	}
}
