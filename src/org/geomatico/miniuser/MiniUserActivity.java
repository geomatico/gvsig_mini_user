package org.geomatico.miniuser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ZoomControls;

import com.markupartist.android.widget.ActionBar;

import es.prodevelop.gvsig.mini.R;
import es.prodevelop.gvsig.mini.action.MapControlAction;
import es.prodevelop.gvsig.mini.app.Initializer;
import es.prodevelop.gvsig.mini.common.CompatManager;
import es.prodevelop.gvsig.mini.common.IContext;
import es.prodevelop.gvsig.mini.context.ItemContext;
import es.prodevelop.gvsig.mini.control.DoubleTapZoom;
import es.prodevelop.gvsig.mini.control.PanControl;
import es.prodevelop.gvsig.mini.exceptions.BaseException;
import es.prodevelop.gvsig.mini.map.MapView;
import es.prodevelop.gvsig.mini.map.MapViewListener;
import es.prodevelop.tilecache.renderer.MapRenderer;
import es.prodevelop.tilecache.renderer.OSMMercatorRenderer;

public class MiniUserActivity extends Activity implements MapViewListener {

	private MapView mapView;
	private ZoomControls zoomControls;
	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mapView = createMapView();

		if (mapView != null) {
			/*
			 * Add zoom controls
			 */
			RelativeLayout relativeLayout = new RelativeLayout(this);
			relativeLayout.addView(mapView, new RelativeLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

			zoomControls = new ZoomControls(this);
			final RelativeLayout.LayoutParams zzParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			zzParams.addRule(RelativeLayout.ALIGN_BOTTOM);
			zzParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			relativeLayout.addView(zoomControls, zzParams);
			zoomControls.setId(107);
			zoomControls.setVisibility(View.VISIBLE);

			zoomControls.setOnZoomInClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mapView.getMapViewController().zoomIn();
				}
			});
			zoomControls.setOnZoomOutClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mapView.getMapViewController().zoomOut();
				}
			});

			setContentView(relativeLayout);

			/*
			 * Add actionbar
			 */
			getWindow().addContentView(
					LayoutInflater.from(this)
							.inflate(R.layout.actionbars, null),
					new ViewGroup.LayoutParams(
							ViewGroup.LayoutParams.FILL_PARENT,
							ViewGroup.LayoutParams.WRAP_CONTENT));
			actionBar = (ActionBar) findViewById(com.markupartist.android.widget.actionbar.R.id.actionbar);

			/*
			 * Create polygon control and add it to the bar
			 */
			final DrawPolygonControl polygonControl = new DrawPolygonControl(
					this);
			actionBar.addAction(new MapControlAction(mapView,
					R.drawable.arrow_on, polygonControl));
			
			/*
			 * Add a button to actually draw a point
			 */
			actionBar.addAction(new ActionBar.Action() {

				@Override
				public void performAction(View view) {
					polygonControl.addPointAtCenter();
				}

				@Override
				public int getDrawable() {
					return R.drawable.arrowdown;
				}
			});
		}
	}

	private MapView createMapView() {
		MapView view = null;
		try {
			// Initialize
			try {
				Initializer.getInstance().initialize(getApplicationContext());
			} catch (Exception e) {
				throw new BaseException(e);
			}

			// Instantiate MapView
			IContext context = CompatManager.getInstance()
					.getRegisteredContext();
			Handler mapHandler = new Handler();
			MapRenderer renderer = OSMMercatorRenderer.getMapnikRenderer();
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			view = new MapView(this, mapHandler, context, renderer,
					metrics.widthPixels, metrics.heightPixels);

			// Configure controls
			view.addControl(new PanControl());
			view.addControl(new DoubleTapZoom());

			view.addMapViewListener(this);
		} catch (BaseException e) {
			Log.e("MiniUser", "Cannot instantiate Mini activity", e);
			// TODO Tell the user something is really wrong here
		}
		return view;
	}

	@Override
	public void zoomLevelChanged() {
		try {
			MapRenderer r = mapView.getMRendererInfo();

			if (r.getZoomLevel() > r.getZoomMinLevel())
				zoomControls.setIsZoomOutEnabled(true);
			else
				zoomControls.setIsZoomOutEnabled(false);

			if (r.getZOOM_MAXLEVEL() > r.getZoomLevel()) {
				zoomControls.setIsZoomInEnabled(true);
			} else {
				zoomControls.setIsZoomInEnabled(false);
			}
		} catch (Exception e) {
			Log.e("MiniUser", "updateZoomControl: ", e);
		}
	}

	@Override
	public void overlayContextToShow(ItemContext itemContext, double x, double y) {
	}

	@Override
	public void defaultContextToShow(double x, double y) {
	}

	@Override
	public void exclusiveControlChanged() {
		/*
		 * Tell the action bar to update the "selected" status of its buttons.
		 */
		actionBar.refreshSelectableActions();
	}
}