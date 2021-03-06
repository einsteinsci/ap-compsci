/*
 * Author: Ryan Peacock
 * Project: PrintRange
 * Filename: P03_PrintRange.java
 * Purpose: Print a range of integers bounded by an upper and lower value, surrounded by square brackets
 * Pseudocode:
 * 
 * 	void printRange(a, b)
 * 	{
 * 		print("[");
 * 		if (a < b)
 * 		{
 * 			for (int i = a; i <= b; i++)
 * 			{
 * 				print(i);
 * 				printDelimiter();
 * 			}
 * 		}
 * 		else if (a > b)
 * 		{
 * 			for (int i = b; i <= a; i++)
 * 			{
 * 				print(i);
 * 				printDelimiter();
 * 			}
 * 		}
 * 		print("]");
 * 	}
 */

package net.einsteinsci.apcompsci.p03_strings;

import net.einsteinsci.apcompsci.ConsoleUtils;

public class P03_PrintRange
{
	public static void main(int a, int b)
	{
		printRange(a, b);
	}
	
	public static void printRange(int a, int b)
	{
		ConsoleUtils.print("[");
		if (a == b)
		{
			ConsoleUtils.print(a);
		}
		else if (a < b)
		{
			for (int i = a; i <= b; ++i)
			{
				ConsoleUtils.print(i);
				
				if (i < b)
				{
					ConsoleUtils.print(", ");
				}
			}
		}
		else if (b < a)
		{
			for (int i = a; i >= b; --i)
			{
				ConsoleUtils.print(i);
				
				if (i > b)
				{
					ConsoleUtils.print(", ");
				}
			}
		}
		else
		{
			throw new IllegalStateException("WARNING: Mathematical impossibility in progress...");
		}
		ConsoleUtils.println("]");
	}
}
