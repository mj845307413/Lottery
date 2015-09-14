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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mj.lotterymj.R;

import element.CurrentIssueElement;
import engine.CommonInfoEngine;

public class Hall1 extends BaseUI {
	private ListView ii_hall_lottery_list;
	// private List<View> needUpdateViews;
	private int[] logoResIds = new int[] { R.drawable.id_ssq, R.drawable.id_3d,
			R.drawable.id_qlc };
	private int[] titleResIds = new int[] { R.string.is_hall_ssq_title,
			R.string.is_hall_3d_title, R.string.is_hall_qlc_title };

	public Hall1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	private listAdapter adapter;

	public void init() { // TODO Auto-generated method stub

		middleShow = (LinearLayout) View.inflate(context, R.layout.il_hall2,
				null);
		ii_hall_lottery_list = (ListView) middleShow
				.findViewById(R.id.ii_hall_lottery_list);
		// needUpdateViews = new ArrayList<View>();
		adapter = new listAdapter();
		ii_hall_lottery_list.setAdapter(adapter);

	}

	private class listAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder = null;

			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = View.inflate(context,
						R.layout.il_hall_lottery_item, null);
				viewHolder.logo = (ImageView) convertView
						.findViewById(R.id.ii_hall_lottery_logo);
				viewHolder.title = (TextView) convertView
						.findViewById(R.id.ii_hall_lottery_title);
				viewHolder.summary = (TextView) convertView
						.findViewById(R.id.ii_hall_lottery_summary);
				// needUpdateViews.add(viewHolder.summary);
				viewHolder.bet = (ImageView) convertView
						.findViewById(R.id.ii_hall_lottery_bet);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.logo.setImageResource(logoResIds[position]);
			viewHolder.title.setText(titleResIds[position]);
			//viewHolder.summary.setText(textString);
			viewHolder.summary.setTag(position);
			return convertView;

		}

		class ViewHolder {
			ImageView logo;
			TextView title;
			TextView summary;
			ImageView bet;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return constantValue.VIEW_HALL;
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

//	protected void showChange() {
//		// TODO Auto-generated method stub
//		for (int i = 0; i <12000; i++) {
//			textString=getLasttime(String.valueOf(i));
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			adapter.notifyDataSetChanged();
//		}
//	}

	private String textString;

	protected void changeNotice(Element element) {
		// TODO Auto-generated method stub
		CurrentIssueElement currentIssueElement = (CurrentIssueElement) element;
		String issueString = currentIssueElement.getIssue();
		String time = currentIssueElement.getLasttime();
		time = getLasttime(time);
		textString = context.getResources().getString(
				R.string.is_hall_common_summary);
		textString = StringUtils.replaceEach(textString, new String[] {
				"ISSUE", "TIME" }, new String[] { issueString, time });
		// ii_hall_ssq_summary.setText(textString);
		// TextView textView=(TextView) needUpdateViews.get(0);
		// textView.setText("");
		TextView textView=(TextView) ii_hall_lottery_list.findViewWithTag(0);
		textView.setText(textString);
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
	public void onResume() {
		getCurrentIssueInfo();
		super.onResume();
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub

	}

}
