package galgeleg_server;

import javax.xml.ws.Endpoint;

class Kontoserver {
	public static void main(String[] args) {
		System.out.println("publicerer kontotjeneste");
		Galgelogik k = new Galgelogik();
		// Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og IP-adresser
		Endpoint.publish("http://[::]:9918/kontotjeneste", k);
		System.out.println("Kontotjeneste publiceret.");
	}
}
