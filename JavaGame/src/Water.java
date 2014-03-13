public class Water extends Locale {       // Space IS-A Locale.

    //
    // Public
    //

    // Constructor
    public Water(int id){
        super(id);
    }

    // Getters and Setters
    public int getWaterSpeed() {
        return waterSpeed;
    }
    public void setWaterSpeed(int waterSpeed) {
        this.waterSpeed = waterSpeed;
    }


    @Override
    public String toString() {
        return super.toString() + " Speed of the water =" + this.waterSpeed;
    }
    public String getText() {
    	return super.getText() + "Current water speed = " + this.waterSpeed;
    }

    //
    // Private
    //
    private int waterSpeed;
}