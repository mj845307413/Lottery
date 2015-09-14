package view.manager;

import interf.constantValue;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class FirstUI extends BaseUI {

	public FirstUI(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public View getChild() {
		// 简单界面：
		TextView textView = new TextView(context);
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		textView.setLayoutParams(layoutParams);
		textView.setBackgroundColor(Color.BLUE);
		textView.setText("这是第一个界面");
		return textView;
	}

	public int getID() {
		return constantValue.VIEW_FIRST;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	}
