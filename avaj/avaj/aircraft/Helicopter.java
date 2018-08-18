package avaj.aircraft;

import	avaj.weather.*;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				Logger.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): What a sunny day!");
				break;
			case "RAIN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
				Logger.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): Good thing I am not in a balloon :D");
				break;
			case "FOG":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
				Logger.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): I put my anti-fog glasses");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				Logger.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): Snowing, snowing...");
			break;
			default:
			Logger.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): Tower is not responding...");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			Logger.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + ") landing.");
			Logger.getWriteFile().writetofile("Tower  says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		Logger.getWriteFile().writetofile("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		weatherTower.register(this);
	}

}