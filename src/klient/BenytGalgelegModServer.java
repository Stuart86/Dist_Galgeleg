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
		String mitG�t, Svar;
		boolean G�tFors�g = true;
		boolean SpilIgen = false;
		Scanner Scanner = new Scanner(System.in);
		
		if(SpilIgen == false)
		{
			HentOrdFraDR();
		}
		
		while (G�tFors�g)
        {
            System.out.println("G�t et bogstav");
            mitG�t = Scanner.next();
            System.out.println(TS.GI.g�tBogstav(mitG�t));
            System.out.println(TS.GI.logStatus());  
            
            if (TS.GI.erSpilletSlut())
            {
            	G�tFors�g = false;
            	
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
                		System.out.println("Forst�r ikke inputtet, pr�v venligst igen");
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
