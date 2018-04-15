package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BrugerGodkendelseRMIInterface 
{
	@WebMethod boolean Brugergodkendelse(String Brugernavn, String Password);
}
