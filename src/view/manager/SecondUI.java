package view.manager;

import interf.constantValue;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class SecondUI extends BaseUI {
	private TextView textView;

	public SecondUI(Context context) {
		super(context);
		init();
	}

	public void init() {
		textView = new TextView(context);
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		textView.setLayoutParams(layoutParams);
		textView.setBackgroundColor(Color.RED);
		textView.setText("这是第二个界面");
	}

	public View getChild() {
		// 简单界面：

		return textView;
	}

	public int getID() {
		return constantValue.VIEW_SECOND;
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
