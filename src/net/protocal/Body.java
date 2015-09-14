package net.protocal;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import utils.DES;
import android.util.Xml;

public class Body {
	List<Element> elements = new ArrayList<Element>();

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	private Oelement oelement=new Oelement();
	
	public Oelement getOelement() {
		return oelement;
	}

	public void setOelement(Oelement oelement) {
		this.oelement = oelement;
	}

	private String serviceBodyInsideDESInfo;

	
	public String getServiceBodyInsideDESInfo() {
		return serviceBodyInsideDESInfo;
	}

	public void setServiceBodyInsideDESInfo(String serviceBodyInsideDESInfo) {
		this.serviceBodyInsideDESInfo = serviceBodyInsideDESInfo;
	}

	// public List<Element> getElements() {
	// return elements;
	// }
	/**
	 * –Ú¡–ªØ«Î«Û
	 */
	public void serializerBody(XmlSerializer serializer) {
		/**
		 * <body> <elements> <element> <lotteryid>118</lotteryid>
		 * <issues>1</issues> </element> </elements> </body>
		 */

		try {
			serializer.startTag(null, "body");
			serializer.startTag(null, "elements");

			for (Element item : elements) {
				item.serializerElement(serializer);
			}
			serializer.endTag(null, "elements");
			serializer.endTag(null, "body");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getWholeBody() {
		StringWriter writer = new StringWriter();
		XmlSerializer xmlSerializer = Xml.newSerializer();
		try {
			xmlSerializer.setOutput(writer);
			this.serializerBody(xmlSerializer);
			xmlSerializer.flush();
			return writer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getBodyInsideDESInfo() {
		String wholeBody = getWholeBody();
		String orgDESInfo = StringUtils.substringBetween(wholeBody, "<body>",
				"</body>");
		DES des = new DES();
		String deString = des
				.authcode(orgDESInfo, "DECODE", "0102030405060708");
		return deString;
	}
}
