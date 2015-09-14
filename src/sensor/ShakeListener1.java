package sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

//

/**
 * 处理传感器监�?
 * 
 * @author Administrator
 * 
 */
// public abstract class ShakeListener1 implements SensorEventListener {
// private Context context;
// private Vibrator vibrator;
//
// public ShakeListener1(Context context) {
// super();
// this.context = context;
// vibrator = (Vibrator) context
// .getSystemService(Context.VIBRATOR_SERVICE );
// }
//
// private float lastX;
// private float lastY;
// private float lastZ;
// private long lasttime;
//
// private long duration = 100;
// private float total;
// private float switchValue = 200; @Override
// public void onSensorChanged(SensorEvent event) {
// if (lasttime == 0) {
// lastX = event.values[SensorManager.DATA_X];
// lastY = event.values[SensorManager.DATA_Y];
// lastZ = event.values[SensorManager.DATA_Z];
//
// lasttime = System.currentTimeMillis();
// } else {
// long currenttime = System.currentTimeMillis();
// if ((currenttime - lasttime) >= duration) {
// float x = event.values[SensorManager.DATA_X];
// float y = event.values[SensorManager.DATA_Y];
// float z = event.values[SensorManager.DATA_Z];
//
//
// float dx = Math.abs(x - lastX);
// float dy = Math.abs(y - lastY);
// float dz = Math.abs(z - lastZ);
//
// if (dx < 1) {
// dx = 0;
// }
// if (dy < 1) {
// dy = 0;
// }
// if (dz < 1) {
// dz = 0;
// }
// float shake = dx + dy + dz;
//
// if (shake == 0) {
// init();
// }
//
// total += shake;
//
// if (total >= switchValue) {
// randomCure();
// vibrator.vibrate(100);
// init();
//
// } else {
// lastX = event.values[SensorManager.DATA_X];
// lastY = event.values[SensorManager.DATA_Y];
// lastZ = event.values[SensorManager.DATA_Z];
//
// lasttime = System.currentTimeMillis();
// }
// }
//
// }
// }
//
// public abstract void randomCure();
//
// private void init() {
// lastX = 0;
// lastY = 0;
// lastZ = 0;
// lasttime = 0;
//
// total = 0;// 累加
//
// }
//
// @Override
// public void onAccuracyChanged(Sensor sensor, int accuracy) {
// // TODO Auto-generated method stub
//
// }
//
// }

public abstract class ShakeListener1 implements SensorEventListener {

	private Vibrator vibrator;
	private Context context;

	public ShakeListener1(Context context) {
		super();
		this.context = context;
		vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
	}

	private float lastX;
	private float lastY;
	private float lastZ;
	private long lasttime;
	private float total;
	private float switchValue = 200;
	private long duration = 100;

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (lasttime == 0) {
			lastX = event.values[SensorManager.DATA_X];
			lastY = event.values[SensorManager.DATA_Y];
			lastZ = event.values[SensorManager.DATA_Z];
			lasttime = System.currentTimeMillis();
		} else {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - lasttime) >= duration) {
				float x = event.values[SensorManager.DATA_X];
				float y = event.values[SensorManager.DATA_Y];
				float z = event.values[SensorManager.DATA_Z];
				float dx = Math.abs(x - lastX);
				float dy = Math.abs(y - lastY);
				float dz = Math.abs(z - lastZ);
				if (dx < 1) {
					dx = 0;
				}
				if (dy < 1) {
					dy = 0;
				}
				if (dz < 1) {
					dz = 0;
				}
				float shake = dx + dy + dz;
				if (shake == 0) {
					init();
				}
				total += shake;

				if (total >= switchValue) {

					randomCure();
					vibrator.vibrate(100);
					init();

				} else {
					lastX = event.values[SensorManager.DATA_X];
					lastY = event.values[SensorManager.DATA_Y];
					lastZ = event.values[SensorManager.DATA_Z];

					lasttime = System.currentTimeMillis();
				}
			}
		}
	}

	public abstract void randomCure();

	private void init() {
		// TODO Auto-generated method stub
		lastX = 0;
		lastY = 0;
		lastZ = 0;
		lasttime = 0;
		total = 0;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
