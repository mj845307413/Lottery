package adapter;

import java.text.DecimalFormat;
import java.util.List;

import com.mj.lotterymj.R;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PoolAdapter extends BaseAdapter {
	public PoolAdapter(Context context, int endNum, List<Integer> slectedNums,
			int slectedBgResId) {
		super();
		this.context = context;
		this.endNum = endNum;
		this.slectedNums = slectedNums;
		this.slectedBgResId = slectedBgResId;
	}

	private Context context;

	private int endNum;

	private List<Integer> slectedNums;

	private int slectedBgResId;// 选中的背景图片的资源id

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return endNum;
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

	private String positionString;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView = new TextView(context);
		textView.setGravity(Gravity.CENTER);
		DecimalFormat decimalFormat = new DecimalFormat("00");
		textView.setText(decimalFormat.format(position + 1));
		/*
		 * position = position + 1; if (position < 10) { positionString = "0" +
		 * position; } else { positionString = String.valueOf(position); }
		 * textView.setText(positionString);
		 */
		if (slectedNums.contains(position + 1)) {
			textView.setBackgroundResource(slectedBgResId);
		} else {
			textView.setBackgroundResource(R.drawable.id_defalut_ball);
		}
		return textView;
	}

}
