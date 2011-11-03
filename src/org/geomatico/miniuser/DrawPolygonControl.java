package org.geomatico.miniuser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

import es.prodevelop.gvsig.mini.control.AbstractMapControl;
import es.prodevelop.gvsig.mini.control.IMapControl;
import es.prodevelop.gvsig.mini.exceptions.BaseException;
import es.prodevelop.gvsig.mini.geom.Point;
import es.prodevelop.gvsig.mini.geom.impl.jts.JTSFeature;
import es.prodevelop.gvsig.mini.geom.impl.jts.JTSGeometry;
import es.prodevelop.gvsig.mini.legend.PointLegend;
import es.prodevelop.gvsig.mini.legend.PolygonLegend;
import es.prodevelop.gvsig.mini.map.IMapView;
import es.prodevelop.gvsig.mini.symbol.PointSymbol;
import es.prodevelop.gvsig.mini.symbol.PolygonSymbol;
import es.prodevelop.gvsig.mini.symbol.Symbol;
import es.prodevelop.gvsig.mini.symbol.SymbolComposite;

/*
 * Draws a cross in the middle of the screen and adds a point there whenever addPoint is called
 */
public class DrawPolygonControl extends AbstractMapControl implements
		IMapControl {

	private static final GeometryFactory gf = new GeometryFactory();
	private DrawingOverlay drawingOverlay;
	private Context context;

	public DrawPolygonControl(Context context) {
		super("Draw polygon");
		this.context = context;
	}

	/**
	 * Add the drawing overlay to the map. Clear it from previous drawings
	 * 
	 * @see es.prodevelop.gvsig.mini.control.AbstractMapControl#activate()
	 */
	public void activate() {
		DrawingOverlay drawingOverlay = getDrawingOverlay();
		drawingOverlay.clear();
		getMapView().addOverlay(drawingOverlay);
	}

	/**
	 * Remove the drawing overlay
	 * 
	 * @see es.prodevelop.gvsig.mini.control.AbstractMapControl#deactivate()
	 */
	public void deactivate() {
		getMapView().removeOverlay(getDrawingOverlay());
	}

	/**
	 * Lazy drawing overlay getter
	 * 
	 * @return
	 */
	private DrawingOverlay getDrawingOverlay() {
		if (drawingOverlay == null) {
			drawingOverlay = new DrawingOverlay(context, getMapView());
		}

		return drawingOverlay;
	}

	/**
	 * Add the center of the map as point in the drawing overlay
	 */
	public void addPointAtCenter() {
		Point p = getMapView().getMRendererInfo().center;
		getDrawingOverlay().addPoint(new Coordinate(p.getX(), p.getY()));
		getMapView().invalidate();
	}

	/**
	 * Draws the cross
	 * 
	 * @author fergonco
	 */
	private class DrawingOverlay extends AbstractDrawingOverlay {
		private ArrayList<Coordinate> points = new ArrayList<Coordinate>();

		DrawingOverlay(Context context, IMapView mapView) {
			super(context, mapView, "Drawing overlay");
		}

		public void clear() {
			points.clear();
		}

		private void addPoint(Coordinate coordinate) {
			points.add(coordinate);
		}

		@Override
		protected void onDraw(Canvas c, IMapView map) {
			// draw cross
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(1);
			paint.setStyle(Style.STROKE);

			int centerX = map.getMapWidth() / 2;
			int centerY = map.getMapHeight() / 2;
			c.drawLine(centerX - 5, centerY, centerX + 5, centerY, paint);
			c.drawLine(centerX, centerY - 5, centerX, centerY + 5, paint);

			/*
			 * Draw geometry
			 */
			String srs = getMapView().getMRendererInfo().getSRS();
			if (points.size() < 3) {
				// Build point feature collection
				ArrayList<JTSFeature> features = new ArrayList<JTSFeature>();
				for (Coordinate coordinate : points) {
					JTSFeature jtsFeature = new JTSFeature(new JTSGeometry(
							gf.createPoint(coordinate)));
					// ugly hack
					try {
						jtsFeature.setSRS(srs);
						jtsFeature.reprojectGeometry(srs);
					} catch (BaseException e) {
						throw new RuntimeException(e);
					}

					features.add(jtsFeature);
				}

				// draw
				PointLegend legend = new PointLegend();
				PointSymbol pointSymbol = new PointSymbol(Color.BLUE, 5);
				legend.draw(c, map, features.iterator(), pointSymbol);
			} else {
				// Build singleton feature collection with the polygon
				@SuppressWarnings("unchecked")
				ArrayList<Coordinate> closedRing = (ArrayList<Coordinate>) points
						.clone();
				closedRing.add(closedRing.get(0));
				LinearRing shell = gf.createLinearRing(closedRing
						.toArray(new Coordinate[0]));
				Polygon pol = gf.createPolygon(shell, null);
				JTSFeature feature = new JTSFeature(new JTSGeometry(pol));
				// Ugly hack
				try {
					feature.setSRS(srs);
					feature.reprojectGeometry(srs);
				} catch (BaseException e) {
					throw new RuntimeException(e);
				}
				List<JTSFeature> featureCollection = Collections
						.singletonList(feature);

				/*
				 * Build a composite symbol to draw the polygon. Transparent
				 * fill and opaque outline
				 */
				PolygonSymbol fillSymbol = new PolygonSymbol(0x4040ff, 2);
				fillSymbol.setAlpha(128);
				PolygonSymbol outlineSymbol = new PolygonSymbol(Color.BLUE, 2);
				outlineSymbol.setStyle(Style.STROKE);
				Symbol s = new SymbolComposite(fillSymbol, outlineSymbol);

				// draw
				PolygonLegend legend = new PolygonLegend();
				legend.draw(c, map, featureCollection.iterator(), s);
			}
		}
	}
}
