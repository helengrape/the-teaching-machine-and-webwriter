/**
 * PLAYNodeView.java - play.higraph.view - PLAY
 * 
 * Created on Feb 14, 2012 by Kai Zhu
 */
package play.higraph.view;

import higraph.view.HigraphView;
import higraph.view.Label;
import higraph.view.NodeView;
import higraph.view.ZoneView;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import play.higraph.model.PLAYEdge;
import play.higraph.model.PLAYEdgeLabel;
import play.higraph.model.PLAYHigraph;
import play.higraph.model.PLAYNode;
import play.higraph.model.PLAYPayload;
import play.higraph.model.PLAYSubgraph;
import play.higraph.model.PLAYWholeGraph;
import tm.backtrack.BTTimeManager;

/**
 * @author Kai Zhu
 * 
 */
public class PLAYNodeView
	extends
	NodeView<PLAYPayload, PLAYEdgeLabel, PLAYHigraph, PLAYWholeGraph, PLAYSubgraph, PLAYNode, PLAYEdge> {

    public PLAYNodeView(
	    HigraphView<PLAYPayload, PLAYEdgeLabel, PLAYHigraph, PLAYWholeGraph, PLAYSubgraph, PLAYNode, PLAYEdge> v,
	    PLAYNode node, BTTimeManager timeMan) {
	super(v, node, timeMan);
	super.setNodeSize(150, 50);
	super.setColor(Color.BLACK);
	super.setFillColor(null);
	super.setNodeShapeType(NodeView.ROUND_RECTANGLE);
	PLAYLabel label = new PLAYLabel(node.getPayload().toString(),
		ZoneView.NORTHWEST, this, timeMan);
	label.setTheLabel(label.getId());
	super.addLabel(label);
    }

    @Override
    public void moveLabel(
	    Label<PLAYPayload, PLAYEdgeLabel, PLAYHigraph, PLAYWholeGraph, PLAYSubgraph, PLAYNode, PLAYEdge> label) {
	Rectangle2D cr = getNextShapeExtent();
	Rectangle2D lr = label.getLabelMetrics();
	// Into x and y we compute the position for the centre of the label.
	double x;
	double y;
	double halfW = lr.getWidth() / 2.0;
	double halfH = lr.getHeight() / 2.0;

	switch (label.getPlacement()) {
	case Label.EAST:
	case Label.NORTHEAST:
	case Label.SOUTHEAST:
	    x = cr.getMaxX() - halfW;
	    break;
	case Label.WEST:
	case Label.NORTHWEST:
	case Label.SOUTHWEST:
	    x = cr.getMinX() + halfW;
	    break;
	default:
	    x = cr.getCenterX();
	}
	switch (label.getPlacement()) {
	case Label.NORTH:
	case Label.NORTHEAST:
	case Label.NORTHWEST:
	    y = cr.getMinY() + halfH;
	    break;
	case Label.SOUTH:
	case Label.SOUTHEAST:
	case Label.SOUTHWEST:
	    y = cr.getMaxY() - halfH;
	    break;
	default:
	    y = cr.getCenterY();
	}
	Point2D.Double p = label.getNudge();
	// System.out.println("moved label " + getId() + " to (" + (x+p.x) +
	// ", " + (y+p.y) + ")");
	x += p.x;
	y += p.y;
	// So much for the centre, but we want the upper left corner
	label.placeNext(x - halfW, y - halfH);
    }

}
