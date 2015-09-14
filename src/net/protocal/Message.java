package net.protocal;

import interf.constantValue;

import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;


public class Message {
	private Header header = new Header();
	private Body body = new Body();
	
	

	public Header getHeader() {
		return header;
	}

	public Body getBody() {
		return body;
	}

	/**
	 * 序列化协议
	 */
	public void serializerMessage(XmlSerializer serializer) {
		try {
			// <message version="1.0">
			serializer.startTag(null, "message");
			// MUST follow a call to startTag() immediately
			serializer.attribute(null, "version", "1.0");

			header.serializerHeader(serializer,body.getWholeBody());
			//body.serializerBody(serializer);
			serializer.startTag(null, "body");
			serializer.text(body.getBodyInsideDESInfo());
			serializer.endTag(null, "body");

			serializer.endTag(null, "message");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getXML(Element element) {
		if (element==null) {
			throw new IllegalArgumentException("element is null");
		}
		header.getTransactiontype().setTagValue(element.getTransactionType());
		body.getElements().add(element);
		XmlSerializer xmlSerializer=Xml.newSerializer();
		StringWriter writer=new StringWriter();
		try {
			xmlSerializer.setOutput(writer);
			xmlSerializer.startDocument(constantValue.ENCONDING, null);
			this.serializerMessage(xmlSerializer);
			xmlSerializer.endDocument();
			return writer.toString();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
