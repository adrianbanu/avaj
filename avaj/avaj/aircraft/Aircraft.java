package avaj.aircraft;

import	avaj.aircraft.*;

class Aircraft {
	protected long	id;
	protected String	name;
	protected Coordinates coordinates;
	private static long idCounter = 0; 

	protected Aircraft(String name, Coordinates coordinates) {
		this.coordinates = coordinates;
		this.name = name;
		this.idCounter = nextid();
		this.id = this.idCounter;
	}
	private long nextid() {
		return (++idCounter);
	}
	public	Coordinates getCoordinates() {
		return this.coordinates;
	}
}