/**
 * Created to be able to pass ints by refs
 */
public class IntWrapper {
    private int value;
    public IntWrapper(int value) {this.value = value;}
    public IntWrapper() {this(-1);}
    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}
}
