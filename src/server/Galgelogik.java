package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import javax.jws.WebService;

import brugerautorisation.transport.rmi.Brugeradminklient;
import brugerautorisationold.Brugeradmin;
import brugerautorisationold.BrugeradminImplService;


@WebService(endpointInterface = "server.GalgelegInterface")
public class Galgelogik {
  /** AHT afprøvning er muligeOrd synlig på pakkeniveau */
	
  ArrayList<String> klientOutput = new ArrayList<String>();
  ArrayList<String> muligeOrd = new ArrayList<String>();
  private String ordet;
  private ArrayList<String> brugteBogstaver = new ArrayList<String>();
  private String synligtOrd;
  private int antalForkerteBogstaver;
  private boolean sidsteBogstavVarKorrekt;
  private boolean spilletErVundet;
  private boolean spilletErTabt;

  public ArrayList<String> getBrugteBogstaver() 
  {
    return brugteBogstaver;
  }

  public String getSynligtOrd() {
    return synligtOrd;
  }

  public String getOrdet() {
    return ordet;
  }

  public int getAntalForkerteBogstaver() {
    return antalForkerteBogstaver;
  }

  public boolean erSidsteBogstavKorrekt() {
    return sidsteBogstavVarKorrekt;
  }

  public boolean erSpilletVundet() {
    return spilletErVundet;
  }

  public boolean erSpilletTabt() {
    return spilletErTabt;
  }

  public boolean erSpilletSlut() {
    return spilletErTabt || spilletErVundet;
  }


  public Galgelogik() {
    muligeOrd.add("bil");
    muligeOrd.add("computer");
    muligeOrd.add("programmering");
    muligeOrd.add("motorvej");
    muligeOrd.add("busrute");
    muligeOrd.add("gangsti");
    muligeOrd.add("skovsnegl");
    muligeOrd.add("solsort");
    muligeOrd.add("seksten");
    muligeOrd.add("sytten");
    nulstil();
  }

  public void nulstil() 
  {
    brugteBogstaver.clear();
    antalForkerteBogstaver = 0;
    spilletErVundet = false;
    spilletErTabt = false;
    ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
    opdaterSynligtOrd();
  }

  public void opdaterSynligtOrd() {
    synligtOrd = "";
    spilletErVundet = true;
    for (int n = 0; n < ordet.length(); n++) {
      String bogstav = ordet.substring(n, n + 1);
      if (brugteBogstaver.contains(bogstav)) {
        synligtOrd = synligtOrd + bogstav;
      } else {
        synligtOrd = synligtOrd + "*";
        spilletErVundet = false;
      }
    }
  }

  public String gætBogstav(String bogstav) {
	
	klientOutput.clear();
	  
    if (bogstav.length() != 1) return klientOutput.toString();
    System.out.println("Der gættes på bogstavet: " + bogstav);
    klientOutput.add("Der gættes på bogstavet: " + bogstav);
    if (brugteBogstaver.contains(bogstav)) return klientOutput.toString();
    if (spilletErVundet || spilletErTabt) return klientOutput.toString();

    brugteBogstaver.add(bogstav);

    if (ordet.contains(bogstav)) {
      sidsteBogstavVarKorrekt = true;
      System.out.println("Bogstavet var korrekt: " + bogstav);
      klientOutput.add("Bogstavet var korrekt: " + bogstav);
    
    } else {
      // Vi gættede på et bogstav der ikke var i ordet.
      sidsteBogstavVarKorrekt = false;
      System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
      klientOutput.add("Bogstavet var IKKE korrekt: " + bogstav);
      antalForkerteBogstaver = antalForkerteBogstaver + 1;
      if (antalForkerteBogstaver > 6) {
        spilletErTabt = true;
      }
    }
    opdaterSynligtOrd();
    return klientOutput.toString();
  }

  public String logStatus() {
	  
	klientOutput.clear();
    System.out.println("---------- ");
    System.out.println("- ordet (skult) = " + ordet);
    System.out.println("- synligtOrd = " + synligtOrd);
    System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
    System.out.println("- brugeBogstaver = " + brugteBogstaver);
    if (spilletErTabt) System.out.println("- SPILLET ER TABT");
    if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
    System.out.println("---------- ");
    
    klientOutput.add("---------- \n"); // klient output
    klientOutput.add("- ordet (skult) = " + ordet + "\n"); // klient output
    klientOutput.add("- synligtOrd = " + synligtOrd + "\n"); // klient output
    klientOutput.add("- forkerteBogstaver = " + antalForkerteBogstaver + "\n"); // klient output
    klientOutput.add("- brugteBogstaver = " + brugteBogstaver + "\n"); // klient output
    if (spilletErTabt) klientOutput.add("- SPILLET ER TABT\n"); // klient output
    if (spilletErVundet) klientOutput.add("- SPILLET ER VUDNET\n"); // klient output
    klientOutput.add("--------------- \n");
    
	return klientOutput.toString();
  }

  public static String hentUrl(String adresse) throws IOException 
  {
	  StringBuilder sb = null;
	  
	  try 
	    {
          URL url = new URL(adresse);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          sb  = new StringBuilder();
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Accept", "application/json");
          
          if (conn.getResponseCode() != 200) 
          {
              throw new RuntimeException("Mislykkes : HTTP fejl kode : "
                      + conn.getResponseCode());
          }
          
          InputStreamReader in = new InputStreamReader(conn.getInputStream());
          BufferedReader br = new BufferedReader(in);
          String output;
          
          while ((output = br.readLine()) != null) 
          {
              System.out.println(output);
              sb.append(output + "\n");
          }
          conn.disconnect();
          
      } catch (Exception e) 
	  {
          System.out.println("Exception in NetClientGet:- " + e);
      }
	  
	return sb.toString();

  }


  public void hentOrdFraDr() throws Exception {
    String data = hentUrl("https://dr.dk");
    //System.out.println("data = " + data);

    data = data.substring(data.indexOf("<body")). // fjern headere
            replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
            replaceAll("&#198;", "æ"). // erstat HTML-tegn
            replaceAll("&#230;", "æ"). // erstat HTML-tegn
            replaceAll("&#216;", "ø"). // erstat HTML-tegn
            replaceAll("&#248;", "ø"). // erstat HTML-tegn
            replaceAll("&oslash;", "ø"). // erstat HTML-tegn
            replaceAll("&#229;", "å"). // erstat HTML-tegn
            replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
            replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    System.out.println("data = " + Arrays.asList(data.split("\\s+")));
    muligeOrd.clear();
    muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("muligeOrd = " + muligeOrd);
    nulstil();
  }
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
