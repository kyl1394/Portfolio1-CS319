import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Kyle on 9/9/2014.
 * Simple memory match game 4 x 4 grid. Just to show logic of panels and etc.
 */



public class Portfolio1
{
	private static int gameState;
	private static final int NO_CARDS_FLIPPED = 1;
	private static final int ONE_CARD_FLIPPED = 2;
	private static final int TWO_CARDS_FLIPPED = 3;

	public static GameCard[] gameCards = new GameCard[16];
	public static Color[] colors = new Color[16];

	public static void main(String[] args)
	{

		JFrame frame = new JFrame("The awesomest memory game");
		frame.setSize(new Dimension(600, 480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setContentPane(createContainers());
		gameState = NO_CARDS_FLIPPED;

		//frame.pack();
		frame.setVisible(true);
	}

	static JPanel createContainers()
	{
		JPanel mainPanel = createMainPanel();
		JPanel gamePanel = createGamePanel();

		GameCard[] gameCards = createGameButtons();

		for (GameCard card : gameCards)
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

	private static GameCard[] createGameButtons()
	{
		colors = generateGameColors(gameCards.length);

		for (int index = 0; index < gameCards.length; index++)
		{
			gameCards[index] = createCard(index, colors[index]);
			assignAction(gameCards[index], index);
		}

		return gameCards;
	}

	private static void assignAction(JButton gameCard, final int index)
	{
		gameCard.addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						flipCard(index);
						checkMatch();
					}
				}
		);
	}

	private static Color[] generateGameColors(int numCards)
	{
		int numMatches = 0;

		if (numCards % 2 != 0)
			System.err.println("Cannot have odd amount of cards!");
		else
			numMatches = numCards / 2;

		Color[] cardColors = new Color[numCards];
		int[] cardValues = initializeValues(numMatches);
		randomizeArray(cardValues);

		int index = 0;

		for (int i : cardValues)
		{
			switch (i)
			{
				case 0:
					cardColors[index] = Color.GREEN;
					index++;
					break;
				case 1:
					cardColors[index] = Color.YELLOW;
					index++;
					break;
				case 2:
					cardColors[index] = Color.ORANGE;
					index++;
					break;
				case 3:
					cardColors[index] = Color.RED;
					index++;
					break;
				case 4:
					cardColors[index] = Color.CYAN;
					index++;
					break;
				case 5:
					cardColors[index] = Color.GRAY;
					index++;
					break;
				case 6:
					cardColors[index] = Color.MAGENTA;
					index++;
					break;
				case 7:
					cardColors[index] = Color.PINK;
					index++;
					break;
				case 8:
					cardColors[index] = Color.DARK_GRAY;
					index++;
					break;
				default:
					System.err.println("Game cannot be played with this amount of cards");
					break;
			}
		}
		return cardColors;
	}

	private static int[] randomizeArray(int[] arr)
	{
		Random rand = new Random();
		for (int i = arr.length - 1; i > 0; i--)
		{
			int index = rand.nextInt(i + 1);

			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
		}
		return new int[0];
	}

	private static int[] initializeValues(int maxValue)
	{
		int[] values = new int[maxValue * 2];
		int j = 0;

		for (int i = 0; i < maxValue; i++)
		{
			values[j] = i;
			values[j+1] = i;

			j += 2;
		}

		return values;
	}

	private static GameCard createCard(int index, Color color)
	{
		GameCard newCard = new GameCard(index, color);
		newCard.setSize(new Dimension(10, 10));
		newCard.setBackground(Color.blue);

		return newCard;
	}

	private static void flipCard(int index)
	{
		if (gameCards[index].state == GameCard.STATE_UNFLIPPED)
		{
			gameCards[index].setBackground(colors[index]);
			gameCards[index].state = GameCard.STATE_FLIPPED;
		}
		else
		{
			gameCards[index].setBackground(Color.blue);
			gameCards[index].state = GameCard.STATE_UNFLIPPED;
		}

		gameState++;
	}

	private static void checkMatch()
	{
		if (gameState != TWO_CARDS_FLIPPED) //Only need to check if two cards are flipped
			return;

		ArrayList<GameCard> flippedCards = new ArrayList<GameCard>();

		for (GameCard card : gameCards)
		{
			if (card.state == GameCard.STATE_FLIPPED)
				flippedCards.add(card);
		}

		if (flippedCards.get(0).cardColor == flippedCards.get(1).cardColor)
		{
			flippedCards.get(0).state = GameCard.STATE_MATCHED;
			flippedCards.get(1).state = GameCard.STATE_MATCHED;
		}
		else
		{
			flipCard(flippedCards.get(0).cardIndex);
			flipCard(flippedCards.get(1).cardIndex);
		}

		gameState = 1;
	}
}
