import java.util.ArrayList;
import java.util.ListIterator;

public class FileParser {
	
	private TagNode root = null; //TagNode root
	TagNode Temproot = null;
	TreeNode targe =null;
	
	
	public void createTree(ArrayList<String> lines){
			
		ListIterator<String> its = lines.listIterator();
		while (its.hasNext()){
			String element = its.next();
			
			if(element.contains("<")&&element.contains(">")&&(!element.contains("/"))){
				String tag = element.substring(1, element.length()-1);
				TagNode temp = new TagNode(tag);
				
				if (root == null){
					root = temp;
					Temproot = temp;
				}
				else root.addChild(temp);
				
				root = temp;
			}
			
			if (element.startsWith("</")&&its.hasNext()){
				root = (TagNode) root.getParent();
			}
			
			if (!(element.contains("<")&&element.contains(">"))){
				TextNode temp = new TextNode(element);
				root.addChild(temp);
			}
			
			if (element.contains("img")){
				TagNode temp = new TagNode("img");
				String value = element.substring(element.indexOf('.'), element.lastIndexOf('.')+1);
				value += "jpg";
				temp.addAttribute("src", value);
				if (element.contains("alt")){
					String value2 = element.substring(element.indexOf("alt=")+5,element.lastIndexOf('\"'));
					temp.addAttribute("alt", value2);
				}
				root.addChild(temp);
			}		
		}
		root = Temproot;
		
	}

	public TagNode getRoot() {
		return root;
	}

	public void setRoot(TagNode root) {
		this.root = root;
	}
	
	public void mineImages(ArrayList images, TreeNode node){
		
		if (node.getChildren()!= null){
			ListIterator<TreeNode> its = node.getChildren().listIterator();
			while (its.hasNext()){
				TreeNode element = its.next();
				if (element instanceof TagNode)
					if (((TagNode) element).getTag().equals("img"))
						images.add((TagNode)element);
				mineImages(images,element);
			}
		}

	}
	
	public String getKeywordsForImage(String filename){
		
		String keyWord ="";
		filename = "./"+filename;
		getKeywordsForImageHelper(this.root,filename);
		TreeNode tempParent = targe.getParent();
		keyWord += ((TagNode)tempParent).mineCloseText();
		if (tempParent.getChildren()!= null){
			ListIterator<TreeNode> its = tempParent.getChildren().listIterator();
			while (its.hasNext()){
				TreeNode element = its.next();
				if (element instanceof TagNode)
					if (((TagNode)element).getValue("alt")!=null)
						keyWord +=" "+((TagNode)element).getValue("alt");		
			}
		}
			
		return keyWord;
		
	}
		
	public void getKeywordsForImageHelper(TreeNode root, String filename){
		if (root.getChildren()!= null){
			ListIterator<TreeNode> its = root.getChildren().listIterator();
			while (its.hasNext()){
				TreeNode element = its.next();
				if (element instanceof TagNode)
					if (((TagNode)element).getTag().equals("img")){
						String temp = ((TagNode)element).getValue("src");
						System.out.println(temp+" "+filename);
						if(temp.equals(filename)){
							targe = element;
							break;
						}
					}
				
						
				getKeywordsForImageHelper(element,filename);
				}
			}
	}
	
	
	

}
