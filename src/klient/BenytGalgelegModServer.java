package Klient;

import java.rmi.RemoteException;
import java.util.Scanner;

public class BenytGalgelegModServer 
{
	static ServerForbindelse aktivServerForbindelse = new ServerForbindelse();
	
	public static void main(String[] args) throws RemoteException, Exception 
	{

		aktivServerForbindelse.ServerTilslutGalgeLogik();
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
			aktivServerForbindelse.GI.HentOrdFraDRTr�d();
		}
		

		while (G�tFors�g)
        {
            System.out.println("G�t et bogstav");
            mitG�t = Scanner.next();
            System.out.println(aktivServerForbindelse.GI.g�tBogstav(mitG�t));
            System.out.println(aktivServerForbindelse.GI.logStatus());  
            
            if (aktivServerForbindelse.GI.erSpilletSlut())
            {

            	G�tFors�g = false;
            	
            	while(true)
            	{
            		System.out.println("Vil du spille igen?\nSvar j/n");
                	Svar = Scanner.next();
                	
            		if(Svar.equals("j"))
                	{
                		SpilIgen = true;
                		aktivServerForbindelse.GI.nulstil();
                		BegyndSpillet();
                	}
                	else if(Svar.equals("n"))
                	{
                		SpilIgen = false;
                		System.out.println("Tak for spillet");
                		Scanner.close();
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
	
