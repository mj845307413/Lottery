package element;

import org.xmlpull.v1.XmlSerializer;

import net.protocal.Element;
import net.protocal.Leaf;

public class UserLoginElement extends Element {
	private Leaf actPassword = new Leaf("actPassword");

	public Leaf getActPassword() {
		return actPassword;
	}

	// // <issues>1</issues>
	@Override
	public void serializerElement(XmlSerializer serializer) {
		// TODO Auto-generated method stub
		try {
			serializer.startTag(null, "element");
			actPassword.serializerLeaf(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getTransactionType() {
		// TODO Auto-generated method stub
		return "14001";
	}

}
