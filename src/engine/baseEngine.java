package engine;

import interf.constantValue;

import java.io.InputStream;

import net.protocal.Message;
import netconn.HttpClientUtils;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class baseEngine {
	public Message getMessage(String xml) {
		HttpClientUtils clientUtils = new HttpClientUtils();
		InputStream inputStream = clientUtils.sendXml(
				constantValue.LOTTERY_URI, xml);// 接受服务器的一个返回的值
		if (inputStream != null) {
			Message message2 = new Message();
			XmlPullParser xmlPullParser = Xml.newPullParser();
			try {
				xmlPullParser.setInput(inputStream, constantValue.ENCONDING);
				int eventType = xmlPullParser.getEventType();
				String name;
				while (eventType != XmlPullParser.END_DOCUMENT) {
					switch (eventType) {
					case XmlPullParser.START_TAG:
						name = xmlPullParser.getName();
						if ("timestamp".equals(name)) {
							message2.getHeader().getTimestamp()
									.setTagValue(xmlPullParser.nextText());
						}
						if ("digest".equals(name)) {
							message2.getHeader().getDigest()
									.setTagValue(xmlPullParser.nextText());
						}
						if ("body".equals(name)) {
							message2.getBody().setServiceBodyInsideDESInfo(
									xmlPullParser.nextText());
						}
						break;

					default:
						break;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return message2;
		}
		return null;

	}
}
