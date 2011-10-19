package org.geomatico.miniuser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import es.prodevelop.gvsig.mini.app.Initializer;
import es.prodevelop.gvsig.mini.common.CompatManager;
import es.prodevelop.gvsig.mini.common.IContext;
import es.prodevelop.gvsig.mini.exceptions.BaseException;
import es.prodevelop.gvsig.mini.map.MapView;
import es.prodevelop.tilecache.renderer.MapRenderer;
import es.prodevelop.tilecache.renderer.OSMMercatorRenderer;

public class MiniUserActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		try {
			try {
				Initializer.getInstance().initialize(
						getApplicationContext());
			} catch (Exception e) {
				throw new BaseException(e);
			}
			IContext context = CompatManager.getInstance()
					.getRegisteredContext();
			Handler mapHandler = new Handler();
			MapRenderer renderer = OSMMercatorRenderer.getMapnikRenderer();
			MapView view = new MapView(this, mapHandler, context, renderer,
					metrics.widthPixels, metrics.heightPixels);
			setContentView(view);
		} catch (BaseException e) {
			Log.e("MiniUser", "Cannot instantiate Mini activity", e);
			// TODO Tell the user something is really wrong here
		}
	}
}