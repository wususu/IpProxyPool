package pool;

import javax.persistence.Entity;

/**
 *Proxy entity
 *
 * @author janke
 */
@Entity
public class Proxy {
	
	private String ip;
	
	private int port;
	
	private int lastVerification;
	
	private double rate;
	
	private boolean checked;

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
				"    port: " + port + 
				"    rate: " + rate + 
				"    vertificationTime: " + lastVerification +
				"    checked: " + checked;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	@Override
	public boolean equals(Object object){
		boolean result = false;
		if (object != null && object.getClass() == Proxy.class) {
			if (((Proxy)object).getIp().equals(this.getIp()) && ((Proxy)object).getPort() == this.getPort()){
				result = true;
			}
		}
		return result;
	}
}
