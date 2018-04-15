package klient;

import java.rmi.RemoteException;
import java.util.Scanner;
import server.BrugerGodkendelseRMIInterface;

public class ServerLogin 
{
	boolean PasswordForsøg = false;
	
	public void Login(BrugerGodkendelseRMIInterface BGI) throws RemoteException, Exception
	{
		String Brugernavn, Password;
		Scanner Scanner = new Scanner(System.in);
		
		while(!this.PasswordForsøg) 
		{
            System.out.println("Indtast brugernavn:");
            Brugernavn = Scanner.nextLine();
            System.out.println("Indtast kodeord:");
            Password = Scanner.nextLine();
            
            if(BGI.Brugergodkendelse(Brugernavn, Password)) 
            {
                System.out.println("Du er nu logged på med : " + Brugernavn + " og passwordet: " + Password);
                this.PasswordForsøg = true;
//                Scanner.close();
            } else 
            {
                System.out.println("Forkert brugernavn eller password\nPrøv igen!");
            }
        }
	}
}
