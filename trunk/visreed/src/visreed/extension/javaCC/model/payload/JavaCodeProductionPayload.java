/**
 * JavaCodePayload.java
 * 
 * @date: Aug 21, 2011
 * @author: Xiaoyu Guo
 * This file is part of the Teaching Machine project.
 */
package visreed.extension.javaCC.model.payload;

import higraph.view.HigraphView;
import tm.backtrack.BTTimeManager;
import visreed.extension.javaCC.view.JavaCodeNodeView;
import visreed.model.VisreedEdge;
import visreed.model.VisreedEdgeLabel;
import visreed.model.VisreedHigraph;
import visreed.model.VisreedNode;
import visreed.model.VisreedPayload;
import visreed.model.VisreedSubgraph;
import visreed.model.VisreedWholeGraph;
import visreed.view.VisreedNodeView;

/**
 * @author Xiaoyu Guo
 */
public class JavaCodeProductionPayload extends ProductionPayload {

	private String code;
    public JavaCodeProductionPayload() {
        super();
    }
    
    public JavaCodeProductionPayload(String code){
    	super();
        this.code = code;
    }
    
    /** The maximum length of the code, for display */
    public static final int MAX_DESC_CODE_DISPLAY_LENGTH = 10;

    /* (non-Javadoc)
     * @see visreed.model.VisreedPayload#format(visreed.model.VisreedNode)
     */
    @Override
    public String format(VisreedNode currentNode) {
        String result = "";
        if(this.code == null){
            result = "null";
        } else if (this.code.length() == 0){
            result = "\"\"";
        } else if (this.code.length() < MAX_DESC_CODE_DISPLAY_LENGTH){
            result = this.code;
        } else {
            result = this.code.substring(0, MAX_DESC_CODE_DISPLAY_LENGTH - 1);
            result += "...";
        }
        return result;
    }
    
    @Override
    public String getDescription(){
        return "Java Code";
    }

    /* (non-Javadoc)
     * @see visreed.model.VisreedPayload#constructView(higraph.view.HigraphView, visreed.model.VisreedNode, tm.backtrack.BTTimeManager)
     */
    @Override
    public VisreedNodeView constructView(
        HigraphView<VisreedPayload, VisreedEdgeLabel, VisreedHigraph, VisreedWholeGraph, VisreedSubgraph, VisreedNode, VisreedEdge> sgv,
        VisreedNode node,
        BTTimeManager timeman
    ) {
        return new JavaCodeNodeView(sgv, node, timeman);
    }

}