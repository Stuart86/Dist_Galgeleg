package server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BrugerGodkendelseInterface 
{
	@WebMethod boolean Brugergodkendelse(String Brugernavn, String Password);
}
