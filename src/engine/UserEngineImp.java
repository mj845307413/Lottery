package engine;

import interf.constantValue;

import java.io.StringReader;

import net.protocal.Message;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlPullParser;

import utils.DES;
import android.util.Xml;
import bean.User;
import element.UserLoginElement;

public class UserEngineImp extends baseEngine implements UserEngine{
	public Message login(User user) {
		UserLoginElement userLoginElement = new UserLoginElement();
		userLoginElement.getActPassword().setTagValue(user.getPassword());
		Message message = new Message();
		message.getHeader().getUsername().setTagValue(user.getUsername());
		String xml = message.getXML(userLoginElement);

		Message message2 = getMessage(xml);
		DES des = new DES();
		String bodyString = "<body>"
				+ des.authcode(
						message2.getBody().getServiceBodyInsideDESInfo(),
						"ENCODE", constantValue.DES_PASSWORD) + "</body>";
		String orgInfo = message2.getHeader().getTimestamp().getTagValue()
				+ constantValue.AGENTER_PASSWORD + bodyString;
		String md5Hex = DigestUtils.md5Hex(orgInfo);
		if (md5Hex.equals(message2.getHeader().getDigest().getTagValue())) {
			XmlPullParser xmlPullParser = Xml.newPullParser();
			try {
				xmlPullParser.setInput(new StringReader(bodyString));
				int eventType = xmlPullParser.getEventType();
				String name;
				while (eventType != XmlPullParser.END_DOCUMENT) {
					switch (eventType) {
					case XmlPullParser.START_TAG:
						name = xmlPullParser.getName();
						if ("errorcode".equals(name)) {
							message2.getBody().getOelement()
									.setErrorcode(xmlPullParser.nextText());
						}
						if ("errormsg".equals(name)) {
							message2.getBody().getOelement()
									.setErrormsg(xmlPullParser.nextText());

						}
						break;

					default:
						break;
					}
				}
				return message2;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public Message getBalance(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message bet(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
