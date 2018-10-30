/**
 * File containing the Tape entity definition. 
 */
package cc.p2.tm.TMComponents;

import java.util.ArrayList;

import cc.p2.tm.TuringMachine;

/**
 * Class which represents a tape of a multitape Turing Machine.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 29 oct. 2018
 */
public class Tape
{
	/**
	 * Enumeration containing the available movements of the tape header
	 */
	public static enum HeaderMovement
	{
		LEFT, RIGHT, STAY
	}

	/** Representation of the tape, each element is a cell */
	ArrayList<Symbol>	tape = new ArrayList<Symbol>();
	/** Position of the read/write header */
	int					headerPosition	= 0;

	/**
	 * Moves the tape header.
	 * 
	 * @param movement
	 */
	public void move(HeaderMovement movement)
	{
		if (movement.equals(HeaderMovement.STAY))
			return;
		else if (movement.equals(HeaderMovement.LEFT))
			headerPosition--;
		else if (movement.equals(HeaderMovement.RIGHT))
			headerPosition++;

		if (headerPosition < 0)
		{
			headerPosition = 0;
			tape.add(0, TuringMachine.blankSymbol);
		} else if (headerPosition >= tape.size())
		{
			tape.add(TuringMachine.blankSymbol);
		}
	}

	/**
	 * Returns the symbol pointed by the header.
	 * 
	 * @return Symbol
	 */
	public Symbol readSymbol()
	{
		if (tape.isEmpty())
			return TuringMachine.blankSymbol;
		
		return tape.get(headerPosition);
	}

	/**
	 * Writes a symbol in the cell pointed by the header.
	 * 
	 * @param symbolToWrite
	 */
	public void writeSymbol(Symbol symbolToWrite)
	{
		if (headerPosition >= tape.size())
			tape.add(symbolToWrite);
		
		tape.set(headerPosition, symbolToWrite);
	}

	@Override
	public String toString()
	{
		String tapeRepresentation = "...| ";
		
		if (tape.size() > 0)
		{
			for (int i = 0; i < tape.size(); ++i)
			{
				if (i == headerPosition)
					tapeRepresentation += ">";
				tapeRepresentation += tape.get(i) + " | ";
			}
		} else
			tapeRepresentation += ">. |";
		
		return tapeRepresentation.substring(0, tapeRepresentation.length() - 1) + "...";
	}
	
	/**
	 * Prints the content of the tape from header to right
	 */
	public void printFromHeaderToRight()
	{
		System.out.print("...| >");
		if (headerPosition >= tape.size())
			System.out.print(". |");
		else
			for (int i = headerPosition; i < tape.size(); ++i)
				System.out.print(tape.get(i) + " | ");
		System.out.println("...");
	}

	/**
	 * Setter method for tape attribute.
	 * @param tape 
	 */
	public void setTapeContent(ArrayList<Symbol> tape)
	{
		this.tape = tape;
	}
}
