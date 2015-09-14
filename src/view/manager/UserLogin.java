package view.manager;

import utils.BeanFactory;
import utils.PromptManager;
import net.protocal.Message;
import net.protocal.Oelement;
import interf.constantValue;

import bean.User;

import com.mj.lotterymj.R;

import engine.UserEngine;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UserLogin extends BaseUI {
	private EditText username;
	private ImageView clear;// 清空用户名
	private EditText password;
	private Button login;

	public UserLogin(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ii_clear:
			// 清除用户名
			username.setText("");
			clear.setVisibility(View.INVISIBLE);
			break;
		case R.id.ii_user_login:
			if (checkUserInfo()) {
				User user = new User();
				user.setUsername(username.getText().toString());
				user.setPassword(password.getText().toString());
				new MyAsyncTask<User>() {
					@Override
					protected void onPreExecute() {
						// TODO Auto-generated method stub
						PromptManager.showProgressDialog(context);
						super.onPreExecute();
					}

					@Override
					protected Message doInBackground(User... params) {
						// TODO Auto-generated method stub
						UserEngine engine=BeanFactory.getImpl(UserEngine.class);
						Message message=engine.login(params[0]);
						if (login != null) {
							Oelement oelement = login.get().getOelement();
							if (ConstantValue.SUCCESS.equals(oelement.getErrorcode())) {
								// 登录成功了
								GlobalParams.isLogin = true;
								GlobalParams.USERNAME=params[0].getUsername();

								// 成功了获取余额
								Message balance = engine.getBalance(params[0]);
								if (balance != null) {
									oelement = balance.getBody().getOelement();
									if (ConstantValue.SUCCESS.equals(oelement.getErrorcode())) {
										BalanceElement element = (BalanceElement) balance.getBody().getElements().get(0);
										GlobalParams.MONEY = Float.parseFloat(element.getInvestvalues());
										return balance;
									}
								}
							}
						}

						return null;
					}

					@Override
					protected void onPostExecute(Message result) {
						// TODO Auto-generated method stub
						PromptManager.closeProgressDialog();
						super.onPostExecute(result);
					}
				}.execute(user);

			}
			// case R.id.ii_user_login:
			// // 登录
			// // 用户输入信息
			// if (checkUserInfo()) {
			// // 登录
			// User user = new User();
			// user.setUsername(username.getText().toString());
			// user.setPassword(password.getText().toString());
			// new MyHttpTask<User>() {
			// @Override
			// protected void onPreExecute() {
			// PromptManager.showProgressDialog(context);
			// super.onPreExecute();
			// }
			//
			// @Override
			// protected Message doInBackground(User... params) {
			// UserEngine engine = BeanFactory
			// .getImpl(UserEngine.class);
			// Message login = engine.login(params[0]);
			//
			// if (login != null) {
			// Oelement oelement = login.getBody().getOelement();
			// if (ConstantValue.SUCCESS.equals(oelement
			// .getErrorcode())) {
			// // 登录成功了
			// GlobalParams.isLogin = true;
			// GlobalParams.USERNAME = params[0].getUsername();
			//
			// // 成功了获取余额
			// Message balance = engine.getBalance(params[0]);
			// if (balance != null) {
			// oelement = balance.getBody().getOelement();
			// if (ConstantValue.SUCCESS.equals(oelement
			// .getErrorcode())) {
			// BalanceElement element = (BalanceElement) balance
			// .getBody().getElements().get(0);
			// GlobalParams.MONEY = Float
			// .parseFloat(element
			// .getInvestvalues());
			// return balance;
			// }
			// }
			// }
			// }
			//
			// return null;
			// }
			//
			// @Override
			// protected void onPostExecute(Message result) {
			// PromptManager.closeProgressDialog();
			// if (result != null) {
			// // 界面跳转
			// PromptManager.showToast(context, "登录成功");
			// MiddleManager.getInstance().goBack();
			// } else {
			// PromptManager.showToast(context, "服务忙……");
			// }
			// super.onPostExecute(result);
			// }
			// }.executeProxy(user);
			//
			// }
			// break;
		}
	}

	private boolean checkUserInfo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		username.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (username.getText().toString().length() > 0) {
					clear.setVisibility(View.VISIBLE);
				} else {

				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		clear.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		middleShow = (ViewGroup) View.inflate(context, R.layout.il_user_login,
				null);
		username = (EditText) middleShow
				.findViewById(R.id.ii_user_login_username);
		clear = (ImageView) middleShow.findViewById(R.id.ii_clear);
		password = (EditText) middleShow
				.findViewById(R.id.ii_user_login_password);
		login = (Button) middleShow.findViewById(R.id.ii_user_login);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return constantValue.VIEW_LOGIN;
	}
}
