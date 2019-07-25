
public class TextNode extends TreeNode{
	
	private String text;//text
	
	public TextNode(String text){
		
		super();
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
