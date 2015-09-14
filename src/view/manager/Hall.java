package view.manager;

import interf.constantValue;
import net.protocal.Element;
import net.protocal.Message;
import net.protocal.Oelement;
import org.apache.commons.lang3.StringUtils;
import utils.BeanFactory;
import utils.PromptManager;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mj.lotterymj.R;
import element.CurrentIssueElement;
import engine.CommonInfoEngine;

public class Hall extends BaseUI {
	private TextView ii_hall_ssq_summary;
	private ImageView ii_hall_ssq_bet;

	public Hall(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	public void init() { // TODO Auto-generated method stub

		middleShow = (LinearLayout) View.inflate(context, R.layout.il_hall1,
				null);
		ii_hall_ssq_summary = (TextView) middleShow
				.findViewById(R.id.ii_hall_ssq_summary);
		ii_hall_ssq_bet = (ImageView) middleShow
				.findViewById(R.id.ii_hall_ssq_bet);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return constantValue.VIEW_HALL;
	}

	public void setListener() {
		// TODO Auto-generated method stub
		ii_hall_ssq_bet.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private void getCurrentIssueInfo() {
		new MyAsyncTask<Integer>() {

			@Override
			protected Message doInBackground(Integer... params) {
				// TODO Auto-generated method stub
				CommonInfoEngine infoEngine = BeanFactory
						.getImpl(CommonInfoEngine.class);
				return infoEngine.getCurrentIssueInfo(params[0]);
				// return infoEngine.getCurrentIssueInfo(constantValue.SSQ);
			}

			@Override
			protected void onPostExecute(Message result) {
				// TODO Auto-generated method stub
				if (result != null) {
					Oelement oelement = result.getBody().getOelement();
					if (constantValue.SUCCESS.equals(oelement.getErrorcode())) {
						changeNotice(result.getBody().getElements().get(0));
					} else {
						PromptManager
								.showToast(context, oelement.getErrormsg());
					}
				} else {
					PromptManager.showToast(context, "服务器忙");
				}
				super.onPostExecute(result);
			}
		}.executeProxy(constantValue.SSQ);
		// new MyAsyncTask().execute(constantValue.SSQ);
	}

	protected void changeNotice(Element element) {
		// TODO Auto-generated method stub
		CurrentIssueElement currentIssueElement = (CurrentIssueElement) element;
		String issueString = currentIssueElement.getIssue();
		String time = currentIssueElement.getLasttime();
		time = getLasttime(time);
		String textString = context.getResources().getString(
				R.string.is_hall_common_summary);
		textString = StringUtils.replaceEach(textString, new String[] {
				"ISSUE", "TIME" }, new String[] { issueString, time });
		ii_hall_ssq_summary.setText(textString);

	}

	/**
	 * 将秒时间转换成日时分格式
	 * 
	 * @param lasttime
	 * @return
	 */
	public String getLasttime(String lasttime) {
		StringBuffer result = new StringBuffer();
		if (StringUtils.isNumericSpace(lasttime)) {
			int time = Integer.parseInt(lasttime);
			int day = time / (24 * 60 * 60);
			result.append(day).append("天");
			if (day > 0) {
				time = time - day * 24 * 60 * 60;
			}
			int hour = time / 3600;
			result.append(hour).append("时");
			if (hour > 0) {
				time = time - hour * 60 * 60;
			}
			int minute = time / 60;
			result.append(minute).append("分");
		}
		return result.toString();
	}

	@Override
	public void onPause() {
		getCurrentIssueInfo();
	}
	/*	*//**
	 * 
	 * 异步访问网络的工具
	 * 
	 * @author Administrator params:传输的参数 progress：下载相关，进度提示 result：服务器端回来的数据
	 * 
	 */
	/*
	 * private class MyAsyncTask extends AsyncTask<Integer, Void, Message> {
	 * 
	 * @Override protected Message doInBackground(Integer... params) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * 
	 * public final AsyncTask<Integer, Void, Message> executeProxy( Integer...
	 * params) {
	 * 
	 * if (NetUtil.checkNet(context)) { return super.execute(params); } else {
	 * PromptManager.showNoNetWork(context); } return null; } }
	 */
	// private class Mythread extends Thread {
	//
	// @Override
	// public void run() {
	//
	// super.run();

	// }
	//
	// @Override
	// // 重写父类thread.start()方法
	// public synchronized void start() {
	// // TODO Auto-generated method stub
	// if (NetUtil.checkNet(context)) {
	// super.start();// thread正式开始运行
	// } else {
	// PromptManager.showNoNetWork(context);
	// }
	//
	// }
	// }
}
