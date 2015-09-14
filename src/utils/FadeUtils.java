package utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

public class FadeUtils {
	public static void fadeOut(final View view, long duration) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
		alphaAnimation.setDuration(duration);
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
//				ViewGroup Parent=(ViewGroup) view.getParent();
//				Parent.removeView(view);
//				//Parent.removeAllViews();
//				System.out.println("clean view");
			}
		});
		view.startAnimation(alphaAnimation);
	}

	public static void fadeIn(View view, long duration, long delay) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setStartOffset(delay);
		alphaAnimation.setDuration(duration);
		view.startAnimation(alphaAnimation);
	}
}
