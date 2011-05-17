
import javax.swing.JPanel;
/**
* Insansierer en "Panel" som extendar JPanel, 
* lite dåligt val av namn på klass kanske.
* 
* Gör lite magi så att allting flyter lite finare.
*/
public class Panel extends JPanel{
    public Panel(){
        // Information om varför IgnoreRepaint står här är hämtat från:
        // http://www.cokeandcode.com/info/tut2d.html
        setIgnoreRepaint(true);
        setFocusable(true);
    }
}