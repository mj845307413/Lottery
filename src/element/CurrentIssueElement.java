package element;

import org.xmlpull.v1.XmlSerializer;

import net.protocal.Element;
import net.protocal.Leaf;

public class CurrentIssueElement extends Element {
	private Leaf lotteryid = new Leaf("lotteryid");
//	// <issues>1</issues>
	private Leaf issues = new Leaf("issues", "1");
	public Leaf getLotteryid() {
	return lotteryid;
	}
	@Override
	public void serializerElement(XmlSerializer serializer) {
		// TODO Auto-generated method stub
		try {
			serializer.startTag(null, "element");
			lotteryid.serializerLeaf(serializer);
			issues.serializerLeaf(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getTransactionType() {
		// TODO Auto-generated method stub
		return "12002";
	}
	private String issue;
	private String lasttime;
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

}
