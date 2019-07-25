
public class Profile {
	
	private Object[] photos = new Object [0];//photos
	private Object[] contacts = new Object [0];//contacts
	private Object[] posts = new Object [0];//posts
	private Object[] messages = new Object [0];//messages
	private String username;//username
	private String gender;//gender
	
	public Profile(String username, String gender){
		
		this.username = username;
		this.gender = gender;
		
	}
	
	public void parseDataDump(Object[] lines) throws DataParseException{
		
		for (int i=0; i<lines.length; i++){
			
				if (minePhoto((String)lines[i])){
				
					String tempString = (String)lines[i];
					String location = tempString.substring(0, tempString.lastIndexOf(" "));
					String name = location.substring(tempString.lastIndexOf("/")+1, location.length());
					String sizeString = tempString.substring(tempString.lastIndexOf(" ")+1, tempString.length());
					int size = Integer.parseInt(sizeString);
				
					Photo photoTemp = new Photo(name, size, location);
					this.inefficientAndDangerousAddObject(photoTemp);	
				
						
				}
			
				if (mineContact((String)lines[i])){
				
					String newName = ((String)lines[i]).substring(8, ((String)lines[i]).length());
					this.inefficientAndDangerousAddObject(newName);
				
				}
			
				if (minePost((String)lines[i])){
					
					String [] tempString = ((String)lines[i]).split("#");
					
					String tempphoto = tempString[3];
					String location = tempphoto.substring(0, tempphoto.lastIndexOf(" "));
					String name = location.substring(tempphoto.lastIndexOf("/")+1, location.length());
					String sizeString = tempphoto.substring(tempphoto.lastIndexOf(" ")+1, tempphoto.length());
					int size = Integer.parseInt(sizeString);
					
					Photo photoTemp = new Photo(name, size, location);
					Post postTemp = new Post(tempString[1], tempString[2]);
					postTemp.addPhoto(photoTemp);
					this.inefficientAndDangerousAddObject(postTemp);
					this.inefficientAndDangerousAddObject(photoTemp);
					
				
				}
				
				if (mineMessage((String)lines[i])){
					
					String [] tempString = ((String)lines[i]).split("#");
					Message tempMessage = new Message(tempString[1], tempString[2], tempString[3]);
					this.inefficientAndDangerousAddObject(tempMessage);
					this.inefficientAndDangerousAddObject(tempString[1]);
					
				}
			
			if (!(minePhoto((String)lines[i])||mineContact((String)lines[i])||minePost((String)lines[i])||mineMessage((String)lines[i])))
					throw new DataParseException("Wrong");
		
		}
		
	}


	private boolean minePhoto(String line){
		
		if (line.startsWith("http://")&&line.contains(" "))
			return true;
		return false;
	}
	
	private boolean mineContact (String line){
		
		if (line.startsWith("CONTACT"))
			return true;
		else return false;
		
	}
	
	private boolean minePost (String line){
		
		if (line.startsWith("POST#"))//this.minePhoto()
			return true;
		else return false;
		
	}
	
	private boolean mineMessage (String line){
		
		if (line.startsWith("MESSAGE#"))//this.mineContact()
			return true;
		else return false;
		
	}
	
	public void inefficientAndDangerousAddObject (Object object){
		
		if (object instanceof Photo){
			
			boolean notEqual = true;
			for (int j = 0; j<photos.length; j++)
				if (((Photo)photos[j]).equals(object)){
					notEqual = false;
					break;
				}
			
			if (notEqual){
			
				Object [] newPhotos = new Object [this.photos.length+1];
				for (int i=0; i<this.photos.length; i++)
					newPhotos[i] = photos[i];
				newPhotos[newPhotos.length-1] = object;
				this.photos = newPhotos;
			}
		}
		
		if (object instanceof String){
			Object [] newContacts = new Object [this.contacts.length+1];
			for (int i=0; i<this.contacts.length; i++)
				newContacts[i] = contacts[i];
			newContacts[newContacts.length-1] = object;
			this.contacts = newContacts;
		}
		
		if (object instanceof Post){
			Object [] newPosts = new Object [this.posts.length+1];
			for (int i=0; i<this.posts.length; i++)
				newPosts[i] = posts[i];
			newPosts[newPosts.length-1] = object;
			this.posts = newPosts;
		}
		
		if (object instanceof Message){
			Object [] newMessages = new Object [this.messages.length+1];
			for (int i=0; i<this.messages.length; i++)
				newMessages[i] = messages[i];
			newMessages[newMessages.length-1] = object;
			this.messages = newMessages;
		}
		
		
		
	}
	
	public Object[] getPhotos() {
		return photos;
	}

	public void setPhotos(Object[] photos) {
		this.photos = photos;
	}

	public Object[] getContacts() {
		return contacts;
	}

	public void setContacts(Object[] contacts) {
		this.contacts = contacts;
	}

	public Object[] getPosts() {
		return posts;
	}

	public void setPosts(Object[] posts) {
		this.posts = posts;
	}

	public Object[] getMessages() {
		return messages;
	}

	public void setMessages(Object[] messages) {
		this.messages = messages;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
