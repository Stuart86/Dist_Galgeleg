package klient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import server.GalgelegInterface;


public class BenytGalgelegModServer 
{
	static GalgelegInterface GI;

	public static void main(String[] args) throws MalformedURLException 
	{
		boolean PasswordFors�g = false;
		
		ServerTilslut();
		
		PasswordFors�g = Login(PasswordFors�g);
		
		BegyndSpillet();
	}
		
	public static void BegyndSpillet()
	{
		String mitG�t, Svar;
		boolean G�tFors�g = true;
		boolean SpilIgen = false;
		Scanner Scanner = new Scanner(System.in);
		
		if(SpilIgen == false)
		{
			GI.hentOrdFraDr();
		}
		
		while (G�tFors�g)
        {
            System.out.println("G�t et bogstav");
            mitG�t = Scanner.next();
            System.out.println(GI.g�tBogstav(mitG�t));
            System.out.println(GI.logStatus());  
            
            if (GI.erSpilletSlut())
            {
            	G�tFors�g = false;
            	
            	while(true)
            	{
            		System.out.println("Vil du spille igen?\nSvar j/n");
                	Svar = Scanner.next();
                	
            		if(Svar.equals("j"))
                	{
                		SpilIgen = true;
                		BegyndSpillet();
                	}
                	else if(Svar.equals("n"))
                	{
                		SpilIgen = false;
                		System.out.println("Tak for spillet");
                		break;
                	}
                	else
                	{
                		System.out.println("Forst�r ikke inputtet, pr�v venligst igen");
                	}
            	}
            	
            }
        }
		
	}
	
	public static void ServerTilslut() throws MalformedURLException
	{
		URL url = new URL("http://ubuntu4.saluton.dk:9918/galgeleg?wsdl");
		QName qname = new QName("http://server/", "GalgelogikService");
		Service service = Service.create(url, qname);
		GI = service.getPort(GalgelegInterface.class);
		System.out.println("Der er forbundet til serveren");
	}
	
	public static boolean Login(boolean PasswordFors�g)
	{
		String Brugernavn, Password;
		Scanner Scanner = new Scanner(System.in);
		
		while(!PasswordFors�g) 
		{
            System.out.println("Indtast brugernavn:");
            Brugernavn = Scanner.nextLine();
            System.out.println("Indtast kodeord:");
            Password = Scanner.nextLine();
            
            if(GI.Brugergodkendelse(Brugernavn, Password)) 
            {
                System.out.println("Du er nu logged p� med : " + Brugernavn + " og passwordet: " + Password);
                PasswordFors�g = true;
            } else 
            {
                System.out.println("Forkert brugernavn eller password\nPr�v igen!");
            }
        }
		return PasswordFors�g;
	}
}
