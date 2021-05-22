package main.java.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import main.java.util.exceptions.ConfigException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXBPostgresConfig {
	private String url;
	private String username;
	private String password;
	
	public JAXBPostgresConfig() {
		
	}
	
	public JAXBPostgresConfig(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
	public String toString() {
		return "JAXBPostgresConfig [url=" + url + ", username=" + username + ", password=" + password + "]";
	}
	
	public static JAXBPostgresConfig getConfig(){
		try {
			JAXBContext context = JAXBContext.newInstance(JAXBPostgresConfig.class);
			Unmarshaller unMarshaller = context.createUnmarshaller();
			return (JAXBPostgresConfig) unMarshaller.unmarshal(new File("config.xml"));
		} catch(JAXBException ex) {
			System.err.println(ex.getMessage());
			throw new ConfigException("Retrieving config failed");
		}
	}
	
	
	

}
