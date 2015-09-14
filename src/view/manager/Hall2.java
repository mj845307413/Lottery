package view.manager;

import interf.constantValue;

import java.util.ArrayList;
import java.util.List;

import net.protocal.Element;
import net.protocal.Message;
import net.protocal.Oelement;

import org.apache.commons.lang3.StringUtils;
import utils.BeanFactory;
import utils.PromptManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.mj.lotterymj.GlobalParams;
import com.mj.lotterymj.R;
import element.CurrentIssueElement;
import engine.CommonInfoEngine;

public class Hall2 extends BaseUI {
	private ListView ii_hall_lottery_list;
	// private List<View> needUpdateViews;
	private int[] logoResIds = new int[] { R.drawable.id_ssq, R.drawable.id_3d,
			R.drawable.id_qlc };
	private int[] titleResIds = new int[] { R.string.is_hall_ssq_title,
			R.string.is_hall_3d_title, R.string.is_hall_qlc_title };

	public Hall2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	private listAdapter adapter;
	private ViewPagerAdapter pagerAdapter;
	// viewPager配置
	private ViewPager viewPager;
	private List<View> pagers;

	private TextView fcTitle;// 福彩
	private TextView tcTitle;// 体彩
	private TextView gpcTitle;// 高频彩

	private ImageView underLine;

	public void init() { // TODO Auto-generated method stub

		middleShow = (LinearLayout) View.inflate(context, R.layout.il_hall,
				null);
		viewPager = (ViewPager) middleShow.findViewById(R.id.ii_viewpager);
		pagerAdapter = new ViewPagerAdapter();
		ii_hall_lottery_list = new ListView(context);

		adapter = new listAdapter();
		ii_hall_lottery_list.setAdapter(adapter);
		initPagers();
		viewPager.setAdapter(pagerAdapter);

		initTabStrip();

	}

	private void initTabStrip() {
		// TODO Auto-generated method stub
		fcTitle = (TextView) middleShow.findViewById(R.id.ii_category_fc);
		tcTitle = (TextView) middleShow.findViewById(R.id.ii_category_tc);
		gpcTitle = (TextView) middleShow.findViewById(R.id.ii_category_gpc);

		fcTitle.setTextColor(Color.RED);
		underLine = (ImageView) middleShow
				.findViewById(R.id.ii_category_selector);
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.id_category_selector);
		// bitmap.getWidth();

		int offset = (GlobalParams.WIN_WIDTH / 3 - bitmap.getWidth()) / 2;
		// 设置图片初始位置——向右偏移
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		underLine.setImageMatrix(matrix);
	}

	private void initPagers() {
		// TODO Auto-generated method stub
		pagers = new ArrayList<View>();
		pagers.add(ii_hall_lottery_list);
		TextView item = new TextView(context);
		item.setText("体彩");
		pagers.add(item);

		item = new TextView(context);
		item.setText("高频彩");
		pagers.add(item);
	}

	private class ViewPagerAdapter extends PagerAdapter {

		public Object instantiateItem(ViewGroup container, int position) {
			View view = pagers.get(position);
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = pagers.get(position);

			container.removeView(view);
		}

		@Override
		public int getCount() {
			return pagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
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
		public View getView(final int position, View convertView, ViewGroup parent) {
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
			// viewHolder.summary.setText(textString);
			viewHolder.summary.setTag(position);
			viewHolder.bet.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (position==0) {
						MiddleManager.getMiddleManager().showChangeUI(PlaySQQ.class,sqqBundle);
						
					}
				}
			});
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

	// protected void showChange() {
	// // TODO Auto-generated method stub
	// for (int i = 0; i <12000; i++) {
	// textString=getLasttime(String.valueOf(i));
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// adapter.notifyDataSetChanged();
	// }
	// }

	private String textString;
private Bundle sqqBundle;
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
		TextView textView = (TextView) ii_hall_lottery_list.findViewWithTag(0);
		textView.setText(textString);
		
		sqqBundle=new Bundle();
		sqqBundle.putString("issue", issueString);
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

	private int lastPosition = 0;

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		fcTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(0);
			}
		});
		tcTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(1);
			}
		});
		gpcTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(2);
			}
		});
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

				TranslateAnimation animation = new TranslateAnimation(
						lastPosition * GlobalParams.WIN_WIDTH / 3, position
								* GlobalParams.WIN_WIDTH / 3, 0, 0);
				animation.setDuration(300);
				animation.setFillAfter(true);// 移动完后停留到终点

				underLine.startAnimation(animation);
				lastPosition = position;
				fcTitle.setTextColor(Color.BLACK);
				tcTitle.setTextColor(Color.BLACK);
				gpcTitle.setTextColor(Color.BLACK);

				switch (position) {
				case 0:
					fcTitle.setTextColor(Color.RED);
					break;
				case 1:
					tcTitle.setTextColor(Color.RED);
					break;
				case 2:
					gpcTitle.setTextColor(Color.RED);
					break;

				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}
		});
	}

}
