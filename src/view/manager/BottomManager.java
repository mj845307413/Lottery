package view.manager;

import interf.constantValue;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import com.mj.lotterymj.R;

import android.app.Activity;
import android.opengl.Visibility;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BottomManager implements Observer {
	private RelativeLayout bottomMenuContainer;
	private LinearLayout commonBottom;
	private LinearLayout playBottom;
	private ImageButton cleanButton;
	private ImageButton addButton;

	private TextView playBottomNotice;
	private ImageButton homeButton;
	private ImageButton hallButton;
	private ImageButton rechargeButton;
	private ImageButton myselfButton;
	private static BottomManager bottomManager = new BottomManager();

	public static BottomManager getBottomManager() {
		return bottomManager;
	}

	public void init(Activity activity) {
		bottomMenuContainer = (RelativeLayout) activity
				.findViewById(R.id.mj_bottom);
		commonBottom = (LinearLayout) activity
				.findViewById(R.id.ii_bottom_common);
		playBottom = (LinearLayout) activity.findViewById(R.id.ii_bottom_game);

		playBottomNotice = (TextView) activity
				.findViewById(R.id.ii_bottom_game_choose_notice);
		cleanButton = (ImageButton) activity
				.findViewById(R.id.ii_bottom_game_choose_clean);
		addButton = (ImageButton) activity
				.findViewById(R.id.ii_bottom_game_choose_ok);
		setListener();

	}

	private void setListener() {
		cleanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BaseUI baseUI = MiddleManager.getMiddleManager().getCurrentUI();
				if (baseUI instanceof PlayGame) {
					((PlaySQQ) baseUI).clear();
				}
			}
		});
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BaseUI baseUI = MiddleManager.getMiddleManager().getCurrentUI();
				if (baseUI instanceof PlayGame) {
					((PlaySQQ) baseUI).done();
				}

			}
		});
	}

	public void showCommonBottom() {
		if (bottomMenuContainer.getVisibility() == View.GONE
				|| bottomMenuContainer.getVisibility() == View.INVISIBLE) {
			bottomMenuContainer.setVisibility(View.VISIBLE);
		}
		commonBottom.setVisibility(View.VISIBLE);
		playBottom.setVisibility(View.INVISIBLE);
	}

	public void showGameBottom() {
		if (bottomMenuContainer.getVisibility() == View.GONE
				|| bottomMenuContainer.getVisibility() == View.INVISIBLE) {
			bottomMenuContainer.setVisibility(View.VISIBLE);
		}
		commonBottom.setVisibility(View.INVISIBLE);
		playBottom.setVisibility(View.VISIBLE);
	}

	public void changeBottomVisiblity(int type) {
		if (bottomMenuContainer.getVisibility() != type)
			bottomMenuContainer.setVisibility(type);
	}

	public void changeGameBottomNotice(String notice) {
		playBottomNotice.setText(notice);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if (data != null && StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case constantValue.VIEW_FIRST:
			case constantValue.VIEW_HALL:
			case constantValue.VIEW_LOGIN:
				showCommonBottom();
				break;
			case constantValue.VIEW_SECOND:
			case constantValue.VIEW_SSQ:
				showGameBottom();
				break;
			case constantValue.VIEW_SHOPPING:
				changeBottomVisiblity(View.GONE);
				break;
			default:
				break;
			}
		}
	}

}
