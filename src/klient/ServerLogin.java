package klient;

import java.rmi.RemoteException;
import java.util.Scanner;
import server.BrugerGodkendelseInterface;

public class ServerLogin 
{
	boolean PasswordFors�g = false;
	
	public void Login(BrugerGodkendelseInterface bGI) throws RemoteException, Exception
	{
		String Brugernavn, Password;
		Scanner Scanner = new Scanner(System.in);
		
		while(!this.PasswordFors�g) 
		{
            System.out.println("Indtast brugernavn:");
            Brugernavn = Scanner.nextLine();
            System.out.println("Indtast kodeord:");
            Password = Scanner.nextLine();
            
            if(bGI.Brugergodkendelse(Brugernavn, Password)) 
            {
                System.out.println("Du er nu logged p� med : " + Brugernavn + " og passwordet: " + Password);
                this.PasswordFors�g = true;
            } else 
            {
                System.out.println("Forkert brugernavn eller password\nPr�v igen!");
            }
        }
	}
}
