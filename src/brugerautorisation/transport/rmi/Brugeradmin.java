package brugerautorisation.transport.rmi;

import brugerautorisation.data.Bruger;

public interface Brugeradmin extends java.rmi.Remote {
	/**
	 * Henter en brugers offentlige data
	 * @return et Bruger-objekt med de offentlige data
	 */
	//Bruger hentBrugerOffentligt(String brugernavn) throws java.rmi.RemoteException;

	/**
	 * Henter alle en brugers data
	 * @return et Bruger-objekt med alle data
	 */
	Bruger hentBruger(String brugernavn, String adgangskode) throws java.rmi.RemoteException;

	/**
	 * ï¿½ndrer en brugers adgangskode
	 * @return et Bruger-objekt med alle data
	 */
	Bruger ÆndrAdgangskode(String brugernavn, String glAdgangskode, String nyAdgangskode) throws java.rmi.RemoteException;

	/**
	 * Sender en email til en bruger
	 * @param brugernavn Brugeren, som emailen skal sendes til
	 * @param emne Emnet - teksten DIST: bliver foranstillet i mailen
	 * @param tekst Brï¿½dteksten - teksten 'Sendt fra xxxx ' bliver tilfï¿½jet  i mailen
	 * @throws java.rmi.RemoteException
	 */
	
	void setEkstraFelt(String brugernavn, String adgangskode, String feltnavn, Object værdi) throws java.rmi.RemoteException;

	/**
	 * Aflï¿½ser et ekstra felt. Se setEkstraFelt
	 */
	Object getEkstraFelt(String brugernavn, String adgangskode, String feltnavn) throws java.rmi.RemoteException;

}
