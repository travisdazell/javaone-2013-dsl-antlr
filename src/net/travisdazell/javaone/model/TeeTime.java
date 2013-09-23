package net.travisdazell.javaone.model;

import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TeeTime {
	private Date time;
	private Golfer golfer;

	public Date getTime() {
		return time;
	}

	@XmlElement
	public void setTime(Date time) {
		this.time = time;
	}

	public Golfer getGolfer() {
		return golfer;
	}

	@XmlElement
	public void setGolfer(Golfer golfer) {
		this.golfer = golfer;
	}
	
	public String bookTeeTime() {
		String result = new String();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TeeTime.class);
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(this, sw);
			
			result = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
