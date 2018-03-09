package brugerautorisation.transport.rmi;

import brugerautorisation.data.Bruger;
import java.rmi.Naming;

public class Brugeradminklient 
{
	public Brugeradmin RMIforbindelse() throws Exception 
	{
		Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
		return ba;
	}
}
