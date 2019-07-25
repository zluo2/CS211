import java.util.ArrayList;
import java.util.List;

public abstract class TreeNode {
	
	static int count;
	private String id;//String id
	private List<TreeNode> children;//List children
	private TreeNode parent;//TreeNode parent
	
	public TreeNode(List children){
		
		this.id = Integer.toString(++count);
		this.parent = null;
		this.children = children;
	}

	public TreeNode() {
		this.id = Integer.toString(++count);
		this.parent = null;
		this.children = new ArrayList<TreeNode>();
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		TreeNode.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	public void addChild(TreeNode child){
		this.children.add(child);
		child.setParent(this);
	}
	

}
