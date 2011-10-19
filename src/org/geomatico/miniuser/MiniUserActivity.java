package org.geomatico.miniuser;

import org.anddev.android.weatherforecast.weather.WeatherSet;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnCancelListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import es.prodevelop.gvsig.mini.common.IContext;
import es.prodevelop.gvsig.mini.common.IEvent;
import es.prodevelop.gvsig.mini.context.ItemContext;
import es.prodevelop.gvsig.mini.exceptions.BaseException;
import es.prodevelop.gvsig.mini.geom.android.GPSPoint;
import es.prodevelop.gvsig.mini.location.LocationHandler;
import es.prodevelop.gvsig.mini.map.IMapActivity;
import es.prodevelop.gvsig.mini.map.IMapView;
import es.prodevelop.gvsig.mini.map.MapView;
import es.prodevelop.tilecache.IDownloadWaiter;
import es.prodevelop.tilecache.renderer.MapRenderer;

public class MiniUserActivity extends Activity implements IMapActivity{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		IMapActivity mapActivity;
		IContext context;
		MapRenderer renderer;
		MapView view = new MapView(mapActivity, context, renderer,
				metrics.widthPixels, metrics.heightPixels);
		setContentView(view);
	}

	@Override
	public void initializeSensor() {
	}

	@Override
	public void initializeSensor(boolean forceNavigationMode) {
	}

	@Override
	public void initLocation() {
	}

	@Override
	public void showLocationSourceDialog() {
	}

	@Override
	public GPSPoint getLastLocation() {
		return null;
	}

	@Override
	public void enableGPS() {
		
	}

	@Override
	public void disableGPS() {
		
	}

	@Override
	public void setLocationHandlerEnabled(boolean isLocationHandlerEnabled) {
		
	}

	@Override
	public boolean isLocationHandlerEnabled() {
		return false;
	}

	@Override
	public void stopSensor() {
		
	}

	@Override
	public void startGPS() {
		
	}

	@Override
	public void stopGPS() {
		
	}

	@Override
	public LocationHandler getLocationHandler() {
		return null;
	}

	@Override
	public LocationManager getLocationManager() {
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
	}

	@Override
	public void onLocationChanged(Location location) {
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	@Override
	public void showIndeterminateDialog(int title, int message, int icon,
			OnCancelListener cancelListener) {
	}

	@Override
	public void hideIndeterminateDialog() {
	}

	@Override
	public void showOKDialog(String textBody, int title, boolean editView) {
	}

	@Override
	public void showToast(int resId) {
	}

	@Override
	public void showDialogFromFile(String assetsFile, int id) {
	}

	@Override
	public void enableMenuItem(MenuItem item, boolean enable) {
	}

	@Override
	public ActionBar getActionbar() {
		return null;
	}

	@Override
	public void setActionbar(ActionBar actionbar) {
	}

	@Override
	public void addActionToActionbar(AbstractAction action) {
	}

	@Override
	public void addActionBar(LayoutInflater inflater) {
	}

	@Override
	public void reloadLayerAfterDownload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AlertDialog getDownloadTilesDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showDownloadTilesDialog(MapRenderer currentRenderer,
			IMapView mapView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void instantiateTileDownloaderTask(LinearLayout l, int progress,
			MapRenderer currentRenderer, int mapWidth, int mapHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDownloadCanceled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailDownload(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFatalError(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinishDownload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartDownload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTileDeleted(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTileDownloaded(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTileLoadedFromFileSystem(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTileNotFound(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTileSkipped(IEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTotalNumTilesRetrieved(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDataTransfer(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDownloadWaiter getDownloadWaiter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onNewMessage(int arg0, IEvent arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onSettingChange(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(Bundle savedInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Handler getMapHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMapView getMapView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNavigationMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNavigationMode(boolean navigation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRecenterOnGPSLocationActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRecenterOnGPSLocationActive(boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGPSStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setOverlayContext(ItemContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemContext getOverlayContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContext(ItemContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemContext getItemContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void obtainCellLocation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearContext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOverlayContext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showCircularView(ItemContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showContext(ItemContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCenter(Bundle outState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadCenter(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadUI(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLocationOverlay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateZoomControl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMap(Bundle outState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadSettings(Bundle outState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSettings(Bundle outState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadMap(Bundle outState) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewLayers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSearchDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWeather(WeatherSet ws) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContext(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateRoute() {
		// TODO Auto-generated method stub
		
	}
}