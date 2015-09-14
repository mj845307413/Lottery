package view.manager;

import interf.constantValue;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import com.mj.lotterymj.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleManager implements Observer {
	private static TitleManager titleManager = new TitleManager();

	public static TitleManager getTitleManager() {
		return titleManager;
	}

	private RelativeLayout ii_common_container;
	private RelativeLayout ii_unlogin_title;
	private RelativeLayout ii_login_title;
	private ImageView goback;// 返回
	private ImageView help;// 帮助
	private ImageView login;// 登录

	private TextView titleContent;// 标题内容
	private TextView userInfo;// 用户信息

	public void init(Activity activity) {
		ii_common_container = (RelativeLayout) activity
				.findViewById(R.id.ii_common_container);
		ii_login_title = (RelativeLayout) activity
				.findViewById(R.id.ii_login_title);
		ii_unlogin_title = (RelativeLayout) activity
				.findViewById(R.id.ii_unlogin_title);
		goback = (ImageView) activity.findViewById(R.id.ii_title_goback);
		help = (ImageView) activity.findViewById(R.id.ii_title_help);
		login = (ImageView) activity.findViewById(R.id.ii_title_login);

		titleContent = (TextView) activity.findViewById(R.id.ii_title_content);
		userInfo = (TextView) activity.findViewById(R.id.ii_top_user_info);
		setOnListener();
	}

	private void setOnListener() {
		goback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MiddleManager.getMiddleManager().showChangeUI(Hall.class);
			}
		});
	}

	public void initTitle() {
		ii_common_container.setVisibility(View.GONE);
		ii_login_title.setVisibility(View.GONE);
		ii_unlogin_title.setVisibility(View.GONE);
	}

	public void showCommonTitle() {
		initTitle();
		ii_common_container.setVisibility(View.VISIBLE);
	}

	public void showUnloginTitle() {
		initTitle();
		ii_unlogin_title.setVisibility(View.VISIBLE);
	}

	public void showLoginCommonTitle() {
		initTitle();
		ii_login_title.setVisibility(View.VISIBLE);
	}

	public void changeTextview(String string) {
		titleContent.setText(string);
	}

	public void changeUserInfoTextview(String string) {
		userInfo.setText(string);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if (data != null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case constantValue.VIEW_FIRST:
				showUnloginTitle();
				break;
			case constantValue.VIEW_SECOND:
				showLoginCommonTitle();
				break;
			case constantValue.VIEW_HALL:
				showUnloginTitle();
				break;
			case constantValue.VIEW_SHOPPING:
			case constantValue.VIEW_LOGIN:
				showLoginCommonTitle();
				break;
			case constantValue.VIEW_SSQ:
				showCommonTitle();
				break;
			
			default:
				break;
			}
		}

	}
}
