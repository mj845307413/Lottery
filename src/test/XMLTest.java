package test;

import utils.BeanFactory;
import net.protocal.Message;
import android.test.AndroidTestCase;
import android.util.Log;
import bean.User;
import element.CurrentIssueElement;
import engine.UserEngine;
import engine.UserEngineImp;

public class XMLTest extends AndroidTestCase {
	private static final String TAG = "XMLTest";

	public void creatXML() {
		Message message = new Message();
		CurrentIssueElement element = new CurrentIssueElement();
		element.getLotteryid().setTagValue("118");
		String xmlString = message.getXML(element);
		System.out.println(xmlString);
	}

	public void testUserLogin() {
//		UserEngineImp impl = new UserEngineImp();
//		User user = new User();
//		user.setUsername("13200000000");
//		user.setPassword("0000000");
//		Message login = impl.login(user);
//		Log.i(TAG, login.getBody().getOelement().getErrorcode());
//
		 UserEngine engine = BeanFactory.getImpl(UserEngine.class);
		//
		 User user = new User();
		 user.setUsername("13200000000");
		 user.setPassword("0000000");
		 Message login = engine.login(user);
		 Log.i(TAG, login.getBody().getOelement().getErrorcode());
	}

}
