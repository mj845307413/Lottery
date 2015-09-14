package net.protocal;

import org.xmlpull.v1.XmlSerializer;

public class Leaf {
private String tagName;
private String tagValue;

public String getTagName() {
	return tagName;
}

public void setTagName(String tagName) {
	this.tagName = tagName;
}

public String getTagValue() {
	return tagValue;
}

public void setTagValue(String tagValue) {
	this.tagValue = tagValue;
}

public Leaf(String tagName, String tagValue) {
	this.tagName = tagName;
	this.tagValue = tagValue;
}

public Leaf(String tagName) {
	// TODO Auto-generated constructor stub
	this.tagName = tagName;
}

public void serializerLeaf(XmlSerializer serializer) {
	try {
		serializer.startTag(null, getTagName());
		if (tagValue == null) {
			setTagValue("");
		}
		serializer.text(getTagValue());
		System.out.println("leaf:"+getTagValue());
		serializer.endTag(null, getTagName());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
