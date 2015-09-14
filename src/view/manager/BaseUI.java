package view.manager;

import utils.PromptManager;
import net.protocal.Message;
import netconn.NetUtil;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public abstract class BaseUI implements OnClickListener {
	protected Context context;
	protected ViewGroup middleShow;
	protected Bundle bundle;

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	public BaseUI(Context context) {
		super();
		this.context = context;
		init();
		setListener();
		// TODO Auto-generated constructor stub
	}

	public abstract void setListener();

	public abstract void init();

	public View getChild() {
		if (middleShow.getLayoutParams() == null) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.FILL_PARENT);
			middleShow.setLayoutParams(params);
		}

		return middleShow;

	}

	public abstract int getID();

	protected abstract class MyAsyncTask<Params> extends
			AsyncTask<Params, Void, Message> {
		public final AsyncTask<Params, Void, Message> executeProxy(
				Params... params) {

			if (NetUtil.checkNet(context)) {
				return super.execute(params);
			} else {
				PromptManager.showNoNetWork(context);
			}
			return null;
		}

	}

	/**
	 * 要出去的时候
	 * 
	 */
	public void onPause() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * 进入到界面后
	 */
	public void onResume() {
		// TODO Auto-generated method stub

	}
}
