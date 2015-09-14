package com.mj.lotterymj;

import view.manager.BottomManager;
import view.manager.FirstUI;
import view.manager.Hall;
import view.manager.Hall1;
import view.manager.Hall2;
import view.manager.MiddleManager;
import view.manager.SecondUI;
import view.manager.TitleManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private RelativeLayout mj_middle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DisplayMetrics outMetrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		GlobalParams.WIN_WIDTH=outMetrics.widthPixels;
		init();
	}

	private void init() {
		TitleManager titleManager = TitleManager.getTitleManager();
		titleManager.init(this);
		titleManager.showUnloginTitle();

		BottomManager.getBottomManager().init(this);
		BottomManager.getBottomManager().showCommonBottom();

		mj_middle = (RelativeLayout) findViewById(R.id.mj_middle);
		MiddleManager.getMiddleManager().setMj_middle(mj_middle);
		
		//建立观察者与被观察者之间的关系
		MiddleManager.getMiddleManager().addObserver(TitleManager.getTitleManager());
		MiddleManager.getMiddleManager().addObserver(BottomManager.getBottomManager());
		
		
		showFirstUI();

	}


	private void showFirstUI() {
		MiddleManager.getMiddleManager().showChangeUI(Hall2.class);
	}

	protected void loadSecondUI() {
		// TODO Auto-generated method stub

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean result = MiddleManager.getMiddleManager().goBack();
			if (!result) {
				Toast.makeText(MainActivity.this, "是否要退出", 1).show();
			}
			return false;
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
}
