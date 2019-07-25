import java.util.ArrayList;
import java.util.List;

public class Analyzer {
	
	private Profile[] profiles = new Profile [100];//Profile [] profiles
	private int count;//int count
	
	
	public void parse(String[] fileLines) throws DataParseException{
		
		for (int i=0; i<fileLines.length; i++){
			
			if (fileLines[i].startsWith("PROFILE")){
				String [] tempString = fileLines[i].split(" ");
				profiles[count] = new Profile(tempString[1], tempString[2]);
			    count++;
			}
			else{
				String change = fileLines[i];
				Object [] line = {change};
				profiles[count-1].parseDataDump(line);
			}	
		}
	}

	public Profile[] getProfiles() {
		return profiles;
	}

	public void setProfiles(Profile[] profiles) {
		this.profiles = profiles;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public List analyzeProfiles(){
		
		List result = new ArrayList();//get usernames
		
		for (int i=0; i<count; i++){
			
			Object [] tempMessage = profiles[i].getMessages();
			for (int j=0; j<tempMessage.length; j++){
				
				String body = (String)((Message) tempMessage[j]).getBody();
				if (Message.getNegativeKeywords(body).size()>1)
					result.add((String)profiles[i].getUsername());
			}
		}
		
		return result;
		
		
	}

}
