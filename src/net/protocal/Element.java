package net.protocal;

import org.xmlpull.v1.XmlSerializer;

public abstract class Element {
//	private Leaf lotteryid = new Leaf("lotteryid");
//	// <issues>1</issues>
//	private Leaf issues = new Leaf("issues", "1");
//	public Leaf getLotteryid() {
//	return lotteryid;
//}
//
//	public void serializerElement(XmlSerializer serializer) {
//		try {
//			serializer.startTag(null, "element");
//			lotteryid.serializerLeaf(serializer);
//			issues.serializerLeaf(serializer);
//			serializer.endTag(null, "element");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public String getTransactionType() {
//		return "12002";
//	}
	public abstract void serializerElement(XmlSerializer serializer);
public abstract String getTransactionType();
}
