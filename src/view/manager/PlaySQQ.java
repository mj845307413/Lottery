package view.manager;

import interf.constantValue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.protocal.Message;
import net.protocal.Oelement;
import sensor.ShakeListener1;
import utils.BeanFactory;
import utils.PromptManager;
import view.manager.MyGridView.OnActionUPListener;
import adapter.PoolAdapter;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import bean.ShoppingCart;
import bean.Ticket;

import com.mj.lotterymj.R;

import element.CurrentIssueElement;
import engine.CommonInfoEngine;

public class PlaySQQ extends BaseUI implements PlayGame {
	private Button randomRed;
	private Button randomBlue;
	// 选号容器
	private MyGridView redContainer;
	private GridView blueContainer;
	private PoolAdapter redAdapter;
	private PoolAdapter blueAdapter;
	private List<Integer> redSlectedNums;
	private List<Integer> blueSlectedNums;
	private SensorManager sensorManager;
	private ShakeListener1 listener;

	public PlaySQQ(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Random random = new Random();
		switch (v.getId()) {
		case R.id.ii_ssq_random_red:
			while (redSlectedNums.size() < 6) {
				int num = random.nextInt(33) + 1;
				if (redSlectedNums.contains(num)) {
					continue;
				}
				redSlectedNums.add(num);
			}
			changeNotice();
			redAdapter.notifyDataSetChanged();// 其实就是再次实施一遍getview
			break;
		case R.id.ii_ssq_random_blue:
			while (blueSlectedNums.size() < 1) {
				int num = random.nextInt(16) + 1;
				if (blueSlectedNums.contains(num)) {
					continue;
				}
				blueSlectedNums.add(num);
			}
			changeNotice();
			blueAdapter.notifyDataSetChanged();// 其实就是再次实施一遍getview
			break;
		default:
			break;
		}
	}

	private void changeTitle() {
		String titleString = "";
		if (bundle != null) {
			titleString = "双色球第" + bundle.getString("issue") + "期";
		} else {
			titleString = "奶奶的居然没网";
		}
		TitleManager.getTitleManager().changeTextview(titleString);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		changeTitle();
		clear();
		listener = new ShakeListener1(context) {

			@Override
			public void randomCure() {
				// TODO Auto-generated method stub
				randomSSQ();
			}

		};
		sensorManager.registerListener(listener, sensorManager
				.getDefaultSensor(SensorManager.SENSOR_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);

		super.onResume();
	}

	protected void randomSSQ() {
		// TODO Auto-generated method stub
		redSlectedNums.clear();
		blueSlectedNums.clear();
		Random random = new Random();
		while (redSlectedNums.size() < 6) {
			int num = random.nextInt(33) + 1;
			if (redSlectedNums.contains(num)) {
				continue;
			}
			redSlectedNums.add(num);
		}
		while (blueSlectedNums.size() < 1) {
			int num = random.nextInt(16) + 1;
			if (blueSlectedNums.contains(num)) {
				continue;
			}
			blueSlectedNums.add(num);
		}
		changeNotice();
		blueAdapter.notifyDataSetChanged();// 其实就是再次实施一遍getview
		redAdapter.notifyDataSetChanged();// 其实就是再次实施一遍getview
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		sensorManager.unregisterListener(listener);
		super.onPause();
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		randomRed.setOnClickListener(this);
		randomBlue.setOnClickListener(this);
		redContainer.setActionUPListener(new OnActionUPListener() {

			@Override
			public void actionUp(View view, int position) {
				// TODO Auto-generated method stub
				if (!redSlectedNums.contains(position + 1)) {
					view.setBackgroundResource(R.drawable.id_redball);
					Animation animation = AnimationUtils.loadAnimation(context,
							R.anim.ball_shake);
					view.startAnimation(animation);
					redSlectedNums.add(position + 1);
				} else {
					view.setBackgroundResource(R.drawable.id_defalut_ball);
					redSlectedNums.remove((Object) (position + 1));
				}
				changeNotice();
			}
		});
		/*
		 * redContainer.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { // TODO Auto-generated method stub if
		 * (!redSlectedNums.contains(position + 1)) {
		 * view.setBackgroundResource(R.drawable.id_redball);
		 * 
		 * TranslateAnimation animation=new TranslateAnimation(0, 10, 0, 0);
		 * animation.setDuration(500); animation.setRepeatCount(6);
		 * view.setAnimation(animation);
		 * 
		 * Animation animation = AnimationUtils.loadAnimation(context,
		 * R.anim.ball_shake); view.startAnimation(animation);
		 * redSlectedNums.add(position + 1); } else {
		 * view.setBackgroundResource(R.drawable.id_defalut_ball);
		 * redSlectedNums.remove((Object) (position + 1)); } } });
		 */
		blueContainer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (!blueSlectedNums.contains(position + 1)) {
					view.setBackgroundResource(R.drawable.id_blueball);
					/*
					 * TranslateAnimation animation=new TranslateAnimation(0,
					 * 10, 0, 0); animation.setDuration(500);
					 * animation.setRepeatCount(6);
					 * view.setAnimation(animation);
					 */
					Animation animation = AnimationUtils.loadAnimation(context,
							R.anim.ball_shake);
					view.startAnimation(animation);
					blueSlectedNums.add(position + 1);
				} else {
					view.setBackgroundResource(R.drawable.id_defalut_ball);
					blueSlectedNums.remove((Object) (position + 1));
				}
				changeNotice();

			}
		});
		/*
		 * redContainer.setOnItemLongClickListener(new OnItemLongClickListener()
		 * {
		 * 
		 * @Override public boolean onItemLongClick(AdapterView<?> parent, View
		 * view, int position, long id) { // TODO Auto-generated method stub
		 * Toast.makeText(context, "长按了", 1).show(); return true; } });
		 */
	}

	@Override
	public void init() {
		redSlectedNums = new ArrayList<Integer>();
		blueSlectedNums = new ArrayList<Integer>();

		middleShow = (ViewGroup) View.inflate(context, R.layout.il_playssq,
				null);
		redContainer = (MyGridView) middleShow
				.findViewById(R.id.ii_ssq_red_number_container);
		blueContainer = (GridView) middleShow
				.findViewById(R.id.ii_ssq_blue_number_container);
		randomRed = (Button) middleShow.findViewById(R.id.ii_ssq_random_red);
		randomBlue = (Button) middleShow.findViewById(R.id.ii_ssq_random_blue);
		redAdapter = new PoolAdapter(context, 33, redSlectedNums,
				R.drawable.id_redball);
		redContainer.setAdapter(redAdapter);
		blueAdapter = new PoolAdapter(context, 16, blueSlectedNums,
				R.drawable.id_blueball);
		blueContainer.setAdapter(blueAdapter);
		sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return constantValue.VIEW_SSQ;
	}

	private void changeNotice() {
		String notice = null;
		if (redSlectedNums.size() < 6) {
			notice = "你他妈还要选" + (6 - redSlectedNums.size()) + "个红球";
		} else if (blueSlectedNums.size() == 0) {
			notice = "你他妈还要选1个蓝球";

		} else {
			notice = "共" + nums() + "注" + 2 * nums() + "元";
		}
		BottomManager.getBottomManager().changeGameBottomNotice(notice);
	}

	private int nums() {
		int redC = (int) (factorial(redSlectedNums.size()) / (factorial(6) * factorial(redSlectedNums
				.size() - 6)));
		int blueC = blueSlectedNums.size();
		return redC * blueC;
	}

	private long factorial(int num) {
		if (num > 1) {
			return num * factorial(num - 1);
		} else if (num == 1 || num == 0) {
			return 1;
		} else {
			throw new IllegalArgumentException("num >= 0");
		}
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		if (redSlectedNums.size() >= 6 && blueSlectedNums.size() >= 1) {
			if (bundle != null) {
				Ticket ticket = new Ticket();
				DecimalFormat format=new DecimalFormat("00");
				StringBuffer redNumber = new StringBuffer();
				for (Integer SelectNumber : redSlectedNums) {
						redNumber.append(" ").append(format.format(SelectNumber));
				}
				ticket.setRedNumber(redNumber.substring(1));
				StringBuffer blueNumber = new StringBuffer();
				for (Integer SelectNumber : blueSlectedNums) {
						blueNumber.append(" ").append(format.format(SelectNumber));
				}
				ticket.setBlueNumber(blueNumber.substring(1));
				ticket.setNum(nums());
				ShoppingCart.getShoppingCart().getTickets().add(ticket);
				ShoppingCart.getShoppingCart().setIssueString(bundle.getString("issue"));
				ShoppingCart.getShoppingCart().setLotteryID(constantValue.SSQ);
				MiddleManager.getMiddleManager().showChangeUI(Shopping.class,bundle);
			} else {
				getCurrentIssueInfo();
				PromptManager.showToast(context, "没网啊还买个屁啊");
				Ticket ticket = new Ticket();
				DecimalFormat format=new DecimalFormat("00");
				StringBuffer redNumber = new StringBuffer();
				for (Integer SelectNumber : redSlectedNums) {
						redNumber.append(" ").append(format.format(SelectNumber));
				}
				ticket.setRedNumber(redNumber.substring(1));
				StringBuffer blueNumber = new StringBuffer();
				for (Integer SelectNumber : blueSlectedNums) {
						blueNumber.append(" ").append(format.format(SelectNumber));
				}
				ticket.setBlueNumber(blueNumber.substring(1));
				ticket.setNum(nums());
				ShoppingCart.getShoppingCart().getTickets().add(ticket);
				ShoppingCart.getShoppingCart().setLotteryID(constantValue.SSQ);
				MiddleManager.getMiddleManager().showChangeUI(Shopping.class,bundle);
				MiddleManager.getMiddleManager().showChangeUI(Shopping.class,bundle);
			}
		} else {
			PromptManager.showToast(context, "不满足投注要求");
		}
	}

	private void getCurrentIssueInfo() {
		new MyAsyncTask<Integer>() {
			protected void onPreExecute() {
				PromptManager.showProgressDialog(context);
			};

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
						CurrentIssueElement element = (CurrentIssueElement) result
								.getBody().getElements().get(0);

						bundle = new Bundle();
						bundle.putString("issue", element.getIssue());
						changeTitle();
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

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		redSlectedNums.clear();
		blueSlectedNums.clear();
		changeNotice();
		blueAdapter.notifyDataSetChanged();// 其实就是再次实施一遍getview
		redAdapter.notifyDataSetChanged();

	}

}
