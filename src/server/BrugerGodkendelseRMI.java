package server;

import java.rmi.RemoteException;

import javax.jws.WebService;

import brugerautorisation.transport.rmi.Brugeradminklient;

@WebService(endpointInterface = "server.BrugerGodkendelseRMIInterface")
public class BrugerGodkendelseRMI 
{
	public boolean Brugergodkendelse(String Brugernavn, String Password) throws RemoteException, Exception
	{
		
		Brugeradminklient BAK = new Brugeradminklient();
		
		try 
		{
			BAK.RMIforbindelse().hentBruger(Brugernavn, Password);
	        return true;
	    } 
		catch (Exception e) 
		{
	    	System.out.println("fejl");
	        return false;
	    }
		
	}

}
