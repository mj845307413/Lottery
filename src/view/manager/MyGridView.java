package view.manager;

import utils.DensityUtil;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mj.lotterymj.R;

public class MyGridView extends GridView {
	private OnActionUPListener actionUPListener;

	public void setActionUPListener(OnActionUPListener actionUPListener) {
		this.actionUPListener = actionUPListener;
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		View contentView = View.inflate(context, R.layout.il_gridview_item_pop,
				null);
		textView = (TextView) contentView.findViewById(R.id.ii_pretextView);
		popupWindow = new PopupWindow(context);
		popupWindow.setContentView(contentView);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		popupWindow.setAnimationStyle(0);
		popupWindow.setHeight(DensityUtil.dip2px(context, 53));
		popupWindow.setWidth(DensityUtil.dip2px(context, 55));

	}

	private PopupWindow popupWindow;
	private TextView textView;

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		System.out.println("ev");
		int x = (int) ev.getX();
		int y = (int) ev.getY();
		int position = pointToPosition(x, y);
		if (position == INVALID_POSITION) {
			return false;
		}
		TextView childTextView = (TextView) this.getChildAt(position);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("down");
			this.getParent().getParent().requestDisallowInterceptTouchEvent(true);
			showpop(childTextView);
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("up");
			if (actionUPListener != null) {
				actionUPListener.actionUp(childTextView,position);
			}
			this.getParent().getParent().requestDisallowInterceptTouchEvent(false);
			dismisspop();
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("move");
			updatepop(childTextView);
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	private void dismisspop() {
		// TODO Auto-generated method stub
		popupWindow.dismiss();
	}

	private void updatepop(TextView childTextView) {
		// TODO Auto-generated method stub
		int yOffset = -(popupWindow.getHeight() + childTextView.getHeight());
		int xOffset = -(popupWindow.getWidth() - childTextView.getWidth()) / 2;
		textView.setText(childTextView.getText());
		popupWindow.update(childTextView, xOffset, yOffset, -1, -1);
	}

	private void showpop(TextView childTextView) {
		// TODO Auto-generated method stub
		int yOffset = -(popupWindow.getHeight() + childTextView.getHeight());
		int xOffset = -(popupWindow.getWidth() - childTextView.getWidth()) / 2;
		textView.setText(childTextView.getText());
		popupWindow.showAsDropDown(childTextView, xOffset, yOffset);
	}

	public interface OnActionUPListener {
		void actionUp(View view,int position);
		
	}
}
