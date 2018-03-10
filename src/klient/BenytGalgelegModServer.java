package klient;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import server.GalgelegInterface;


public class BenytGalgelegModServer 
{
	static ServerForbindelse TS = new ServerForbindelse();
	
	public static void main(String[] args) throws RemoteException, Exception 
	{
		TS.ServerTilslut1();
		BegyndSpillet();
	}
	public static void BegyndSpillet()
	{
		String mitGæt, Svar;
		boolean GætForsøg = true;
		boolean SpilIgen = false;
		Scanner Scanner = new Scanner(System.in);
		
		if(SpilIgen == false)
		{
			HentOrdFraDR();
		}
		
		while (GætForsøg)
        {
            System.out.println("Gæt et bogstav");
            mitGæt = Scanner.next();
            System.out.println(TS.GI.gætBogstav(mitGæt));
            System.out.println(TS.GI.logStatus());  
            
            if (TS.GI.erSpilletSlut())
            {
            	GætForsøg = false;
            	
            	while(true)
            	{
            		System.out.println("Vil du spille igen?\nSvar j/n");
                	Svar = Scanner.next();
                	
            		if(Svar.equals("j"))
                	{
                		SpilIgen = true;
                		TS.GI.nulstil();
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
                		System.out.println("Forstår ikke inputtet, prøv venligst igen");
                	}
            		
            	}
            	
            }
        }
		
	}
	
	public static void HentOrdFraDR() 
	{
	    new Thread(new Runnable() 
	    {
	        public void run()
	        {
	        	TS.GI.hentOrdFraDr();
	        }
	    }).start();
	}
}
