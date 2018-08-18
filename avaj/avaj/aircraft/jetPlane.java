package avaj.aircraft;

import	avaj.weather.*;

public class jetPlane extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;

	jetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);		
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				Logger.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): So close to the sun...");
				break;
			case "RAIN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
				Logger.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Rainy days, man...");
				break;
			case "FOG":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
				Logger.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): It is getting foggy");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				Logger.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): I am freezing here!");
				break;
			default:
			Logger.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Tower is not responding...");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			Logger.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + ") landing.");
			Logger.getWriteFile().writetofile("Tower  says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		Logger.getWriteFile().writetofile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		this._weatherTower.register(this);
	}
	
}