package view.manager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import utils.FadeUtils;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MiddleManager extends Observable {
	public static MiddleManager middleManager = new MiddleManager();

	public static MiddleManager getMiddleManager() {
		return middleManager;
	}

	private static View childBeforeView;

	public static View getChildBeforeView() {
		return childBeforeView;
	}

	public static void setChildBeforeView(View childBeforeView) {
		MiddleManager.childBeforeView = childBeforeView;
	}

	private static RelativeLayout mj_middle;

	public RelativeLayout getMj_middle() {
		return mj_middle;
	}

	public void setMj_middle(RelativeLayout mj_middle) {
		MiddleManager.mj_middle = mj_middle;
	}

	private static Map<String, BaseUI> VIEWCASE = new HashMap<String, BaseUI>();

	public static Map<String, BaseUI> getVIEWCASE() {
		return VIEWCASE;
	}

	private BaseUI currentUI;

	public BaseUI getCurrentUI() {
		return currentUI;
	}

	public void setCurrentUI(BaseUI currentUI) {
		this.currentUI = currentUI;
	}

	public void showChangeUI(Class<? extends BaseUI> targetClazz) {
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}
		BaseUI targetUI = null;
		String key = targetClazz.getSimpleName();
		if (VIEWCASE.containsKey(key)) {
			targetUI = VIEWCASE.get(key);
		} else {
			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);
				targetUI = constructor.newInstance(getLocalContext());
				VIEWCASE.put(key, targetUI);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("constructor new instance error");
			}
		}
		View child = targetUI.getChild();
		if (childBeforeView == null) {
			TranslateAnimation translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF,
					0, Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0);
			translateAnimation.setDuration(2000);
			mj_middle.addView(child);
			child.startAnimation(translateAnimation);
		} else {
			FadeUtils.fadeOut(getChildBeforeView(), 2000);
			currentUI.onPause();
			mj_middle.removeAllViews();
			mj_middle.addView(child);

			FadeUtils.fadeIn(child, 2000, 2000);
		}
		setChildBeforeView(child);
		targetUI.onResume();
		currentUI = targetUI;
		HISTORY.addFirst(key);
		changeTitleAndBottom();
	}

	public void changeTitleAndBottom() {
		// TODO Auto-generated method stub
		/*
		 * switch (currentUI.getID()) { case constantValue.VIEW_FIRST:
		 * TitleManager.getTitleManager().showUnloginTitle();
		 * BottomManager.getBottomManager().showCommonBottom(); break; case
		 * constantValue.VIEW_SECOND:
		 * TitleManager.getTitleManager().showLoginCommonTitle();
		 * BottomManager.getBottomManager().showGameBottom(); break; default:
		 * break; }
		 */
		setChanged();
		notifyObservers(currentUI.getID());
	}

	public Context getLocalContext() {
		return mj_middle.getContext();

	}

	private static LinkedList<String> HISTORY = new LinkedList<String>();

	public static LinkedList<String> getHISTORY() {
		return HISTORY;
	}

	public boolean goBack() {
		if (HISTORY.size() > 0) {
			if (HISTORY.size() == 1) {
				return false;
			}
			HISTORY.removeFirst();
			if (HISTORY.size() > 0) {
				String key = HISTORY.getFirst();
				BaseUI targetUI = VIEWCASE.get(key);
				View child = targetUI.getChild();
				FadeUtils.fadeOut(getChildBeforeView(), 2000);
				currentUI.onPause();
				mj_middle.removeAllViews();
				mj_middle.addView(child);
				FadeUtils.fadeIn(child, 2000, 2000);
				setChildBeforeView(child);
				targetUI.onResume();
				currentUI = targetUI;
				changeTitleAndBottom();
				return true;
			}
		}
		return false;

	}

	public void showChangeUI(Class<? extends BaseUI> targetClazz, Bundle bundle) {
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}
		BaseUI targetUI = null;
		String key = targetClazz.getSimpleName();
		if (VIEWCASE.containsKey(key)) {
			targetUI = VIEWCASE.get(key);
		} else {
			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);
				targetUI = constructor.newInstance(getLocalContext());
				VIEWCASE.put(key, targetUI);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("constructor new instance error");
			}
		}
		if (targetUI != null) {
			targetUI.setBundle(bundle);
		}
		View child = targetUI.getChild();
		if (childBeforeView == null) {
			TranslateAnimation translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF,
					0, Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0);
			translateAnimation.setDuration(2000);
			mj_middle.addView(child);
			child.startAnimation(translateAnimation);
		} else {
			FadeUtils.fadeOut(getChildBeforeView(), 2000);
			currentUI.onPause();
			mj_middle.removeAllViews();
			mj_middle.addView(child);

			FadeUtils.fadeIn(child, 2000, 2000);
		}
		setChildBeforeView(child);
		targetUI.onResume();
		currentUI = targetUI;
		HISTORY.addFirst(key);
		changeTitleAndBottom();
	}

}
