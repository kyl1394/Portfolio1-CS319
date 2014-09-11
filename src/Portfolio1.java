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
	public static int gameState;
	public static final int NO_CARDS_FLIPPED = 1;
	public static final int ONE_CARD_FLIPPED = 2;
	public static final int TWO_CARDS_FLIPPED = 3;

	public static GameCard[] gameCards = new GameCard[16];
	public static Color[] colors = new Color[16];

	private static GameCard[] flippedCards = new GameCard[2];

	protected static GameCard[] createGameCards()
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
						if (gameState == TWO_CARDS_FLIPPED)
						{
							flipCard(flippedCards[0].cardIndex);
							flipCard(flippedCards[1].cardIndex);

							gameState = NO_CARDS_FLIPPED;
						}

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

			if (gameState == NO_CARDS_FLIPPED)
				flippedCards[0] = gameCards[index];
			else
				flippedCards[1] = gameCards[index];

			gameState++;
		}
		else
		{
			gameCards[index].setBackground(Color.blue);
			gameCards[index].state = GameCard.STATE_UNFLIPPED;

			gameState--;
		}
	}

	private static void checkMatch()
	{
		if (gameState != TWO_CARDS_FLIPPED) //Only need to check if two cards are flipped
			return;

		if (flippedCards[0].cardColor == flippedCards[1].cardColor)
		{
			flippedCards[0].state = GameCard.STATE_MATCHED;
			flippedCards[1].state = GameCard.STATE_MATCHED;

			matchCards();
		}
	}

	private static void matchCards()
	{
		flippedCards[0].setEnabled(false);
		flippedCards[1].setEnabled(false);

		flippedCards[0] = null;
		flippedCards[1] = null;

		gameState = NO_CARDS_FLIPPED;
	}
}
