
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class TagNode extends TreeNode {
	
	private String tag;//tag
	private Map<String,String>attributes;// attributes
	
	
	public TagNode(String tag){
		
		super();
		this.attributes = new HashMap<String, String>();
		this.tag = tag;	
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		
		this.attributes = attributes;
	}
	
	public void addAttribute (String name, String value){
		
		this.attributes.put(name, value);
		
	}
	
	public String getValue (String key){
		return this.attributes.get(key);
	}
	
	public String mineCloseText(){
			
		String stringline = "";
		ListIterator it = this.getChildren().listIterator();
		while (it.hasNext()){
			Object element = it.next();
			if (element.getClass().getName() == "TextNode")
				if (it.hasNext())
					stringline += ((TextNode)element).getText() +" ";
				else stringline += ((TextNode)element).getText();
					
		}
		if (stringline.length()>1&&stringline.lastIndexOf(" ")==stringline.length()-1)
			stringline = stringline.substring(0, stringline.length()-1);
		return stringline;
	}

}
