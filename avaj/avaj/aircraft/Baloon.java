package avaj.aircraft; 

import	avaj.weather.*;
import	avaj.aircraft.*;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower _weatherTower; //de ce pus underscore?
	
	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
				this.coordinates.setHeight(this.coordinates.getHeight() + 4);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setLongitude(100);
				Logger.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): It is getting warmer");
				break;
			case "RAIN":
				this.coordinates.setHeight(this.coordinates.getHeight() - 5);
				Logger.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): I need an umbrella!");
				break;
			case "FOG":
				this.coordinates.setHeight(this.coordinates.getHeight() - 2);
				Logger.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): I do not see a thing");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 15);
				Logger.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): It is getting colder");
				break;
			default:
				Logger.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): Tower is not responding...");
				break;
		}
		if (this.coordinates.getHeight() <= 0){
			Logger.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + ") landing.");
			Logger.getWriteFile().writetofile("Tower  says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		Logger.getWriteFile().writetofile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		weatherTower.register(this);
	}
}