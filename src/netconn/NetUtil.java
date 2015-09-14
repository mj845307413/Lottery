package netconn;

import com.mj.lotterymj.GlobalParams;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class NetUtil {
	public static boolean checkNet(Context context) {
		boolean isWIFI = isWIFIConnection(context);
		boolean isMobile = isMoobileConnect(context);
		if (isMobile) {
			readAPN(context);
		}
		if (isMobile || isWIFI) {
			System.out.println("wifi=true");
			return true;
		}

		return false;

	}

	private static void readAPN(Context context) {
		Uri PREFERRED_APN_URI = Uri
				.parse("content://telephony/carriers/preferapn");
		ContentResolver cResolver = context.getContentResolver();
		Cursor cr = cResolver.query(PREFERRED_APN_URI, null, null, null, null);
		if (cr != null && cr.moveToFirst()) {
			GlobalParams.PROXY = cr.getString(cr.getColumnIndex("proxy"));
			GlobalParams.PORT = cr.getInt(cr.getColumnIndex("port"));
		}
		cr.close();
		// 然后对获取的用户名和密码做相关处理获取对应的apn状态
	}

	private static boolean isMoobileConnect(Context context) {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}

	private static boolean isWIFIConnection(Context context) {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}
}
