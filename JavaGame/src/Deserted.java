
public class Deserted extends Locale {

	public Deserted(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	 public int getheatIndex() {
	        return heatIndex;
	    }
	    public void setheatIndex(int heatIndex) {
	        this.heatIndex = heatIndex;
	    }


	    @Override
	    public String toString() {
	        return super.toString() + " Temperature =" + this.heatIndex;
	    }
	    public String getText() {
	    	return super.getText() + " Current temperature = " + this.heatIndex;
	    }

	    //
	    // Private
	    //
	    private int heatIndex;
	
}
