
public class Item {
	// items array!
	/* These are all of the items in the game
	 * 
	 * If the boolean == true, the item has been found.
	 * 0= The map found in the Pub (loc0)
	 * 1= The Master Found near the Waterfall (loc4)
	 * 2= First Key Part Found in the Sensual Stream (loc7)
	 * 3= Second Key Part Found in the Devoid Desert (loc9)
	 * 4= Third Key Part Found in the Friendly Forest (loc8)
	 *	
	 */
	private int id;
	private String name; //name of the item
	private String desc; //desc of the item
	private boolean found=false; // has the item been found/purchased
	
	
	public Item(){
		this.id=0;
	}
	public Item(int id){
		this.id=id;
	}
	public Item(int id, String name, String desc, Boolean found){
		this.id=id;
		this.name=name;
		this.desc=desc;
		this.found=found;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return desc;
	}
	public void setDescription(String description) {
		this.desc = description;
	}
	public String toString(){
		return "name: "+ this.name +" desc: "+ this.desc;
	}
	public boolean isFound() {
		return found;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	
}
