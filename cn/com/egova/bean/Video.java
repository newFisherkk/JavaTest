package cn.com.egova.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class Video {
	
    @XStreamAlias("deviceID")
	private String deviceID;

    @XStreamAlias("address")
	private String address;

    @XStreamAlias("lng")
	private String lng;

    @XStreamAlias("lat")
	private String lat;

    @XStreamAlias("ip")
    private String ip;

    @XStreamAlias("model")
    private String model;
	
    @XStreamAlias("manufacturer")
	private String manufacturer;

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Video{" +
				"deviceID='" + deviceID + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
