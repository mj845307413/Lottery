package view.manager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import utils.PromptManager;

import interf.constantValue;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import bean.ShoppingCart;
import bean.Ticket;

import com.mj.lotterymj.GlobalParams;
import com.mj.lotterymj.R;

public class Shopping extends BaseUI {
	private Button addOptional;// 添加自选
	private Button addRandom;// 添加机选

	private ListView shoppingList;// 用户选择信息列表

	private ImageButton shoppingListClear;// 清空购物车
	private TextView notice;// 提示信息
	private Button buy;// 购买

	private ShoppingAdapter adapter;

	public Shopping(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ii_add_optional:
			// 添加自选
			MiddleManager.getMiddleManager().goBack();
			changeNotice();
			break;
		case R.id.ii_add_random:
			// 添加机选
			addRandom();
			changeNotice();
			break;
		case R.id.ii_shopping_list_clear:
			// 清空
			ShoppingCart.getShoppingCart().getTickets().clear();
			adapter.notifyDataSetChanged();
			changeNotice();
			break;
		case R.id.ii_lottery_shopping_buy:
			// 购买
			// ①判断：购物车中是否有投注
			if (ShoppingCart.getShoppingCart().getTickets().size() >= 1) {
				// ②判断：用户是否登录——被动登录
				if (GlobalParams.isLogin) {
					// ③判断：用户的余额是否满足投注需求
					if (ShoppingCart.getShoppingCart().getLotteryValue() <= GlobalParams.MONEY) {
						// ④界面跳转：跳转到追期和倍投的设置界面
						MiddleManager.getMiddleManager().showChangeUI(
								PreBet.class, bundle);
					} else {
						// 提示用户：充值去；界面跳转：用户充值界面
						PromptManager.showToast(context, "充值去");
					}
				} else {
					// 提示用户：登录去；界面跳转：用户登录界面
					PromptManager.showToast(context, "登录去");
					MiddleManager.getMiddleManager().showChangeUI(
							UserLogin.class, bundle);
				}
			} else {
				// 分支
				// 提示用户需要选择一注
				PromptManager.showToast(context, "需要选择一注");
			}

			break;
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		changeNotice();
		super.onResume();
	}

	private void changeNotice() {
		// TODO Auto-generated method stub
		Integer num1 = ShoppingCart.getShoppingCart().getLotteryValue();
		Integer num2 = ShoppingCart.getShoppingCart().getLotteryNumber();
		String noticeInfo = context.getResources().getString(
				R.string.is_shopping_list_notice);
		noticeInfo = StringUtils.replaceEach(noticeInfo, new String[] { "NUM",
				"MONEY" }, new String[] { num2.toString(), num1.toString() });
		notice.setText(Html.fromHtml(noticeInfo));
	}

	private void addRandom() {
		// TODO Auto-generated method stub
		List<Integer> redSlectedNums = new ArrayList<Integer>();
		List<Integer> blueSlectedNums = new ArrayList<Integer>();
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
		Ticket ticket = new Ticket();
		DecimalFormat format = new DecimalFormat("00");
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
		int redC = (int) (factorial(redSlectedNums.size()) / (factorial(6) * factorial(redSlectedNums
				.size() - 6)));
		int blueC = blueSlectedNums.size();
		ticket.setNum(redC * blueC);
		ShoppingCart.getShoppingCart().getTickets().add(ticket);
		adapter.notifyDataSetChanged();
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
	public void setListener() {
		// TODO Auto-generated method stub
		addOptional.setOnClickListener(this);
		addRandom.setOnClickListener(this);
		shoppingListClear.setOnClickListener(this);
		buy.setOnClickListener(this);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		middleShow = (ViewGroup) View.inflate(context, R.layout.il_shopping,
				null);

		addOptional = (Button) middleShow.findViewById(R.id.ii_add_optional);
		addRandom = (Button) middleShow.findViewById(R.id.ii_add_random);
		shoppingListClear = (ImageButton) middleShow
				.findViewById(R.id.ii_shopping_list_clear);
		notice = (TextView) middleShow
				.findViewById(R.id.ii_shopping_lottery_notice);
		buy = (Button) middleShow.findViewById(R.id.ii_lottery_shopping_buy);
		shoppingList = (ListView) middleShow
				.findViewById(R.id.ii_shopping_list);

		adapter = new ShoppingAdapter();
		shoppingList.setAdapter(adapter);
	}

	private class ShoppingAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ShoppingCart.getShoppingCart().getTickets().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return ShoppingCart.getShoppingCart().getTickets().get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.il_shopping_row,
						null);
				holder.delete = (ImageButton) convertView
						.findViewById(R.id.ii_shopping_item_delete);
				holder.redNum = (TextView) convertView
						.findViewById(R.id.ii_shopping_item_reds);
				holder.blueNum = (TextView) convertView
						.findViewById(R.id.ii_shopping_item_blues);
				holder.num = (TextView) convertView
						.findViewById(R.id.ii_shopping_item_money);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			System.out.println("list的size="
					+ ShoppingCart.getShoppingCart().getTickets().size());
			Ticket ticket = ShoppingCart.getShoppingCart().getTickets()
					.get(position);
			holder.redNum.setText(ticket.getRedNumber());
			holder.blueNum.setText(ticket.getBlueNumber());
			holder.num.setText(ticket.getNum() + "注");
			holder.delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ShoppingCart.getShoppingCart().getTickets()
							.remove(position);
					notifyDataSetChanged();
				}
			});
			return convertView;
		}

		class ViewHolder {
			ImageButton delete;
			TextView redNum;
			TextView blueNum;
			TextView num;
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return constantValue.VIEW_SHOPPING;
	}

}
