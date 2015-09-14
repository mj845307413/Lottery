package test;

import netconn.NetUtil;
import android.test.AndroidTestCase;

public class NetTest extends AndroidTestCase {
public void testNetType(){
	NetUtil.checkNet(getContext());
}
}
