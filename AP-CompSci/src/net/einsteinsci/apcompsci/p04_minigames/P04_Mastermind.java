/*
 * NEEDS WORK
 *
 * Author: Ryan Peacock
 * Project: Mastermind
 * Filename: P04_Mastermind.java
 * Purpose: Create a simple Mastermind game with numbers and the console.
 * Pseudocode:
 * 
 * 	void main()
 * 	{
 * 		MastermindGame game();
 * 		game.run();
 * 	}
 * 
 * 	class MastermindGame
 * 	{
 * 		Guess[] guesses;
 * 		Guess last;
 * 		Guess pattern;
 * 		int count;
 * 		
 * 		__ctor()
 * 		{
 * 			count = 0;
 * 			pattern = new Guess.random();
 * 		}
 * 
 * 		void run()
 * 		{
 * 			while (true)
 * 			{
 * 				guess(getLine("Guess: "));
 * 				if (hasWon())
 * 				{
 * 					break;
 * 				}
 * 
 * 				println("right number: " + numbersRight());
 * 				println("right spot: " + spotsRight());
 * 			}
 * 			
 * 			println("Correct! Guesses taken: " + count);
 * 		}
 * 
 * 		void guess(string guess)
 * 		{
 * 			guesses.add(new Guess(guess))
 * 			count++;
 * 			last = guesses.last();
 * 		}
 * 
 * 		void hasWon()
 * 		{
 * 			return spotsRight() == 4;
 * 		}
 * 
 * 		int numbersRight()
 * 		{
 * 			int total = 0;
 * 			foreach (char c in guess)
 * 			{
 * 				if (pattern.contains(c))
 * 				{
 * 					total++;
 * 				}
 * 			}
 * 			return total;
 * 		}
 * 
 * 		int spotsRight()
 * 		{
 * 			int total = 0;
 * 			foreach (char c in guess; id i)
 * 			{
 * 				if (pattern[i] == c)
 * 				{
 * 					total++;
 * 				}
 * 			}
 * 			return total;
 * 		}
 * 	}
 * 
 * 	class Guess implements IEnumerable<char>
 * 	{
 * 		char[] guess;
 * 		__ctor(string pegs)
 * 		{
 * 			guess = pegs.toArray();
 * 		}
 * 
 * 		char IEnumerable.operator[](int id)
 * 		{
 * 			return guess[id];
 * 		}
 * 
 * 		bool IEnumerable.contains(char toMatch)
 * 		{
 * 			foreach (char c : guess)
 * 			{
 * 				if (c = toMatch)
 * 				{
 * 					return true;
 * 				}
 * 			}
 * 			return false;
 * 		}
 * 
 * 		static Guess random()
 * 		{
 * 			char[4] guess;
 * 			foreach (char c in guess)
 * 			{
 * 				char rand = Random.nextInt((int)'0', (int)'9');
 * 				c = rand;
 * 			}
 * 			return Guess(guess.toString());
 * 		}
 * 	}
 */

package net.einsteinsci.apcompsci.p04_minigames;

import net.einsteinsci.apcompsci.ConsoleUtils;
import net.einsteinsci.apcompsci.start.IStartable;

import java.util.*;

public class P04_Mastermind implements IStartable
{
	public static boolean DEBUG = true;
	
	public void main()
	{
		MastermindGame game = new MastermindGame();
		game.runGame();
	}
	
	public static class MastermindGame
	{
		List<Guess> guesses = new ArrayList<>();
		Guess lastGuess;
		Guess pattern;
		int guessCount;
		
		public static final int DIFFICULTY = 4;
		
		public MastermindGame()
		{
			guessCount = 0;
			
			char[] answer = new char[DIFFICULTY];
			for (int i = 0; i < DIFFICULTY; ++i)
			{
				answer[i] = randomDigit();
			}
			pattern = new Guess(answer);
		}
		
		public void runGame()
		{
			if (DEBUG)
			{
				ConsoleUtils.println("Number loaded: " + pattern.getString());
			}
			else
			{
				ConsoleUtils.println("Number loaded.");
			}
			ConsoleUtils.println();
			
			while (true)
			{
				String guess = ConsoleUtils.getLine("Guess: ");
				if (guess.equalsIgnoreCase("cheat"))
				{
					ConsoleUtils.println("Answer: " + pattern.getString());
					ConsoleUtils.println();
					continue;
				}
				
				guess(guess);
				
				if (hasWon())
				{
					ConsoleUtils.println("Correct!");
					break;
				}
			
				ConsoleUtils.println("Incorrect.");
				ConsoleUtils.println("Numbers correct: " + getSameNumCount());
				ConsoleUtils.println("Exact matches: " + getExactMatchCount());
				ConsoleUtils.println();
			}
			
			ConsoleUtils.println("Guesses taken: " + guessCount);
		}
		
		public void guess(String guess)
		{
			guesses.add(new Guess(guess));
			guessCount++;
			lastGuess = guesses.get(guesses.size() - 1);
		}
		
		public int getSameNumCount()
		{
			int result = 0;

			List<Character> lpat = new ArrayList<>();
			for (char c : pattern.getPegs())
			{
				lpat.add(c);
			}

			for (char c : lastGuess.getPegs())
			{
				if (lpat.contains(c))
				{
					result++;
					lpat.remove(new Character(c));
				}
			}
			
			return result;
		}
		
		public int getExactMatchCount()
		{
			int result = 0;
			for (int i = 0; i < pattern.getPegs().length; ++i)
			{
				if (pattern.getPeg(i) == lastGuess.getPeg(i))
				{
					result++;
				}
			}
			
			return result;
		}
		
		public boolean hasWon()
		{
			return getExactMatchCount() >= DIFFICULTY;
		}
	}
	
	public static class Guess
	{
		char[] guess;
		public Guess(String input)
		{
			guess = input.toCharArray();
		}
		public Guess(char[] input)
		{
			guess = input;
		}
		
		public char getPeg(int id)
		{
			return guess[id];
		}
		
		public char[] getPegs()
		{
			return guess.clone();
		}
		
		public String getString()
		{
			return new String(guess);
		}
		
		public boolean contains(char test)
		{
			for (char c : guess)
			{
				if (c == test)
				{
					return true;
				}
			}
			
			return false;
		}
	}
	
	public static char randomDigit()
	{
		char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random rand = new Random();
		int n = rand.nextInt(10);
		return nums[n];
	}
}
