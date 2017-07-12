package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 *Proxy entity
 *
 * @author janke
 */
@Entity
@Table(name="proxy", uniqueConstraints = {@UniqueConstraint(columnNames={"ip", "port"})})
public class Proxy {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "id")
	private int id;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="port")
	private int port;
	
	@Column(name="last_verificate_times")
	private String lastVerificateTime;
	
	@Column(name="checked")
	private boolean checked;
	
	@Column(name="success_times")
	private int successTimes = 0;

	public Proxy() {
		// TODO Auto-generated constructor stub
	}
	
	public Proxy(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public Proxy(String ip, int port, String lastVerificateTime) {
		this.ip = ip;
		this.port = port;
		this.lastVerificateTime = lastVerificateTime;
	}
	
	public String toString() {
		return " ip: " + ip +
				"    port: " + port + 
				"    vertificationTime: " + lastVerificateTime +
				"    successTimes: " + successTimes;
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

	public String getLastVerificateTime() {
		return lastVerificateTime;
	}

	public void setLastVerificateTime(String lastVerificateTime) {
		this.lastVerificateTime = lastVerificateTime;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public int adcSuccsessTimes(){
		return ++this.successTimes;
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

	public int getSuccessTimes() {
		return successTimes;
	}

	public void setSuccessTimes(int successTimes) {
		this.successTimes = successTimes;
	}
}
