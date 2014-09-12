import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kyle on 9/10/2014.
 */
public class View
{
	public static JLabel score = new JLabel("0");

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
		score.setFont(new Font("Arial", 0, 14));
		score.setForeground(Color.RED);

		JPanel mainPanel = createMainPanel();
		mainPanel.setBackground(Color.black);
		JPanel scoringPanel = createScoringPanel(score);
		JPanel gamePanel = createGamePanel();

		Portfolio1.gameCards = Portfolio1.createGameCards();

		for (GameCard card : Portfolio1.gameCards)
		{
			gamePanel.add(card);
		}

		mainPanel.add(Box.createVerticalStrut(25));
		mainPanel.add(scoringPanel);
		mainPanel.add(Box.createVerticalStrut(25));
		mainPanel.add(gamePanel);

		return mainPanel;
	}

	private static JPanel createScoringPanel(final JLabel score)
	{
		JPanel scoringPanel = new JPanel();
		Box hBox = Box.createHorizontalBox();

		scoringPanel.setLayout(new BoxLayout(scoringPanel, BoxLayout.X_AXIS));
		JLabel scoreLabel = new JLabel("Score: ");
		hBox.add(scoreLabel);
		hBox.add(Box.createRigidArea(new Dimension(10, 20)));
		hBox.add(score);

		scoringPanel.add(hBox);

		return scoringPanel;
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
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		return mainPanel;
	}

	public static void winGame()
	{
		JPanel winPanel = new JPanel();
		Object[] options = new Object[1];
		options[0] = new JButton("Exit");
		((JButton) options[0]).addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						System.exit(0);
					}
				}
		);

		JOptionPane.showOptionDialog(winPanel, "Hooray for you! You won this simple game!", "Well aren't you a winner!", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
	}
}
