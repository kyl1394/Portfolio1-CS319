import javax.swing.*;
import java.awt.*;

/**
 * Created by Kyle on 9/10/2014.
 */
public class View
{
	public static void main(String[] args)
	{

		JFrame frame = new JFrame("The awesomest memory game");
		frame.setSize(new Dimension(600, 480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setContentPane(createContainers());
		Portfolio1.gameState = Portfolio1.NO_CARDS_FLIPPED;

		//frame.pack();
		frame.setVisible(true);
	}

	static JPanel createContainers()
	{
		JPanel mainPanel = createMainPanel();
		JPanel gamePanel = createGamePanel();

		Portfolio1.gameCards = Portfolio1.createGameCards();

		for (GameCard card : Portfolio1.gameCards)
		{
			gamePanel.add(card);
		}

		mainPanel.add(gamePanel);

		return mainPanel;

	}

	private static JPanel createGamePanel()
	{
		JPanel gamePanel = new JPanel();

		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setHgap(15);
		gridLayout.setVgap(15);

		gamePanel.setLayout(gridLayout);
		gamePanel.setBackground(Color.black);
		return gamePanel;
	}

	static JPanel createMainPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		return mainPanel;
	}
}
