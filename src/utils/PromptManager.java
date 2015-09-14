package utils;

import com.mj.lotterymj.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;

/**
 * 提示信息的管�?
 */

public class PromptManager {
	private static ProgressDialog dialog;

	public static void showProgressDialog(Context context) {
		dialog = new ProgressDialog(context);
		dialog.setIcon(R.drawable.icon);
		dialog.setTitle(R.string.app_name);

		dialog.setMessage("请等候，数据加载中�?��??");
		dialog.show();
	}

	public static void closeProgressDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	/**
	 * 当判断当前手机没有网络时使用
	 * 
	 * @param context
	 */
	public static void showNoNetWork(final Context context) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setIcon(R.drawable.icon)
				//
				.setTitle(R.string.app_name)
				//
				.setMessage("TNND现在还没有网")
				.setPositiveButton("设置", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 跳转到系统的网络设置界面
						Intent intent = new Intent();
						intent.setClassName("com.android.settings",
								"com.android.settings.WirelessSettings");
						context.startActivity(intent);

					}
				})
				.setNegativeButton("好了我知道现在没有网了", null)
				.show();
	}

	/**
	 * �?出系�?
	 * 
	 * @param context
	 */
	public static void showExitSystem(Context context) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setIcon(R.drawable.icon)
				//
				.setTitle(R.string.app_name)
				//
				.setMessage("是否�?出应�?")
				.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						android.os.Process.killProcess(android.os.Process
								.myPid());
						// 多个Activity—�?�懒人听书：没有彻底�?出应�?
						// 将所有用到的Activity都存起来，获取全部，干掉
						// BaseActivity—�?�onCreated—�?�放到容器中
					}
				})//
				.setNegativeButton("取消", null)//
				.show();

	}

	/**
	 * 显示错误提示�?
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showErrorDialog(Context context, String msg) {
		new AlertDialog.Builder(context)//
				.setIcon(R.drawable.icon)//
				.setTitle(R.string.app_name)//
				.setMessage(msg)//
				.setNegativeButton(context.getString(R.string.is_positive),
						null)//
				.show();
	}

	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void showToast(Context context, int msgResId) {
		Toast.makeText(context, msgResId, Toast.LENGTH_LONG).show();
	}

	// 当测试阶段时true
	private static final boolean isShow = true;

	/**
	 * 测试�? 在正式投入市场：�?
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToastTest(Context context, String msg) {
		if (isShow) {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}

}
