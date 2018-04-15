package rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/LoginService")
public class LoginService 
{
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String showMessage() 
	{
		return "it works 2!";
	}

}
