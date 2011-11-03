package org.geomatico.miniuser;

import android.content.Context;
import android.graphics.Canvas;
import es.prodevelop.gvsig.mini.context.ItemContext;
import es.prodevelop.gvsig.mini.geom.Extent;
import es.prodevelop.gvsig.mini.geom.Feature;
import es.prodevelop.gvsig.mini.geom.Pixel;
import es.prodevelop.gvsig.mini.map.IMapView;
import es.prodevelop.gvsig.mini.overlay.MapOverlay;

/**
 * Class used to hide event methods, which are not used when the overlay is used
 * just to draw
 * 
 * @author fergonco
 * 
 */
public abstract class AbstractDrawingOverlay extends MapOverlay {

	public AbstractDrawingOverlay(Context context, IMapView mapView, String name) {
		super(context, mapView, name);
	}

	@Override
	public ItemContext getItemContext() {
		return null;
	}

	@Override
	public void onExtentChanged(Extent newExtent, int zoomLevel,
			double resolution) {
	}

	@Override
	public void onLayerChanged(String layerName) {
	}

	@Override
	protected void onDrawFinished(Canvas c, IMapView maps) {
	}

	@Override
	public Feature getNearestFeature(Pixel pixel) {
		return null;
	}

}
