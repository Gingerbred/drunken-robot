public class List0 {

    //
    // Public
    //

    // Constructor
    public List0() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ListItem getHead() {
        return head;
    }
    public void setHead(ListItem head) {
        this.head = head;
    }
    public void add(ListItem item) {
        // System.out.println("adding " + item.toString());
        if (this.head == null) {
            // The list is empty.
            this.head = item;
            this.last = item;
        } else {
            // The list is NOT empty.
            this.last.setNext(item);
            this.last = item;

            // Before:
            // 1. Move to the end of the list.
            /*
            ListItem lastItem = this.head;
            while (lastItem.getNext() != null) {
                lastItem = lastItem.getNext();
            }
            // 2. Attach the passed-in item to the last item in the list.
            lastItem.setNext(item);
            */
        }
    }


    // Other methods
    @Override
    public String toString() {
        String retVal = new String();
        retVal = " name=" + this.name + " desc=" + this.desc + "\n";
        ListItem currentItem = this.head;
        while (currentItem != null) {
            retVal = retVal + currentItem.toString() + "\n";
            currentItem = currentItem.getNext();
        }
        return retVal;
    }


    //
    // Private
    //
    private String name;
    private String desc;
    private ListItem head;
    private ListItem last;
}
