package entity;

/**
 *Proxy entity
 *
 * @author janke
 */
public class Proxy {
	
	private String ip;
	
	private int port;
	
	private int lastVerification;
	
	private double rate;

	public Proxy() {
		// TODO Auto-generated constructor stub
	}
	
	public Proxy(String ip, int port, int lastVerification, double rate) {
		this.ip = ip;
		this.port = port;
		this.lastVerification = lastVerification;
		this.rate = rate;
	}
	
	public String toString() {
		return " ip: " + ip +
				"\nport: " + port + 
				"\nrate: " + rate + 
				"\nvertificationTime: " + lastVerification;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public int getLastVerification() {
		return lastVerification;
	}

	public void setLastVerification(int lastVerification) {
		this.lastVerification = lastVerification;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
