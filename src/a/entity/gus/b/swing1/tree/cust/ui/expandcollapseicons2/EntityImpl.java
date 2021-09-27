package a.entity.gus.b.swing1.tree.cust.ui.expandcollapseicons2;

import a.framework.*;
import javax.swing.JTree;
import javax.swing.Icon;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.tree.TreePath;
import java.awt.Insets;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210927";}
	
	private Icon icon_col1;
	private Icon icon_exp1;
	private Icon icon_col2;
	private Icon icon_exp2;
	

	public EntityImpl() throws Exception
	{
		icon_col1 =  (Icon) Outside.resource(this,"icon#TREE_collapsedState1");
		icon_exp1 =  (Icon) Outside.resource(this,"icon#TREE_expandedState1");
		icon_col2 =  (Icon) Outside.resource(this,"icon#TREE_collapsedState2");
		icon_exp2 =  (Icon) Outside.resource(this,"icon#TREE_expandedState2");
	}
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		tree.setUI(new BasicTreeUI1());
	}
	
	private class BasicTreeUI1 extends BasicTreeUI
	{
		public Icon getCollapsedIcon(){return icon_col1;}
		public Icon getExpandedIcon(){return icon_exp1;}
		
		
		protected void paintExpandControl(Graphics g,
					Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path,
					int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf)
		{
			if(isLeaf) return;
			
			Object value = path.getLastPathComponent();
			int childNumber = treeModel.getChildCount(value);
			
			if(hasBeenExpanded && childNumber == 0) return;
			
			int middleXOfKnob = findMiddleXOfKnob(bounds);
			int middleYOfKnob = findMiddleYOfKnob(bounds);
			
			boolean isDeep = false;
			
			int n = Math.min(childNumber,100);
			for(int i=0;i<n;i++)
			{
				Object child = treeModel.getChild(value,i);
				if(!treeModel.isLeaf(child)) {isDeep = true;break;}
			}

			if(isExpanded)
			{
				Icon icon = isDeep ? icon_exp2 : icon_exp1;
				if(icon != null) drawCentered(tree,g,icon,middleXOfKnob,middleYOfKnob);
			}
			else
			{
				Icon icon = isDeep ? icon_col2 : icon_col1;
				if(icon != null) drawCentered(tree,g,icon,middleXOfKnob,middleYOfKnob);
			}
		}
		
		private int findMiddleXOfKnob(Rectangle bounds)
		{
			if(tree.getComponentOrientation().isLeftToRight())
				return bounds.x - getRightChildIndent() + 1;
			return bounds.x + bounds.width + getRightChildIndent() - 1;
		}
		
		private int findMiddleYOfKnob(Rectangle bounds)
		{
			return bounds.y + (bounds.height / 2);
		}
	}
}
