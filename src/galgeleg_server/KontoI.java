package galgeleg_server;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService
public interface KontoI
{
	@WebMethod ArrayList<String> getBrugteBogstaver();
	@WebMethod String getSynligtOrd();
	@WebMethod int getAntalForkerteBogstaver();
	@WebMethod boolean erSidsteBogstavKorrekt();
	@WebMethod boolean erSpilletVundet();
	@WebMethod boolean erSpilletTabt();
	@WebMethod boolean erSpilletSlut();
	@WebMethod void nulstil();
	@WebMethod void opdaterSynligtOrd();
	@WebMethod void gætBogstav(String bogstav);
	@WebMethod void logStatus();
	@WebMethod String hentUrl(String url);
	@WebMethod void hentOrdFraDr();
}