package com.beertemp.monitor;


public class Temperature {
	private float temperatureFarenheit = 0;
	private long epochTime = 0; 
	private short sensorID = 0;
	
	public float getTemp() {
		return(temperatureFarenheit);
	}
	public void setTemp(float Temp) {
		this.temperatureFarenheit=Temp;
	}
	public long getTime() {
		return(epochTime);
	}
	public void setTime(long time) {
		this.epochTime=time;
	}
	public short getSensorID() {
		return(sensorID);
	}
	public void setSensorID(short sensor) {
		this.sensorID=sensor;
	}

}
