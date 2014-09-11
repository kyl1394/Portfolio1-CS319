import java.awt.*;
import javax.swing.JButton;

/**
 * Created by Kyle on 9/9/2014.
 */
public class GameCard extends JButton
{
	public int cardIndex;
	public Color cardColor;

	public int state; 
	public static final int STATE_FLIPPED = 1;
	public static final int STATE_UNFLIPPED = 2;
	public static final int STATE_MATCHED = 3;

	public GameCard(int index, Color color)
	{
		cardColor = color;
		cardIndex = index;
		state = STATE_UNFLIPPED;
	}
}
