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
	ArrayList<Symbol>	tape;
	/** Position of the read/write header */
	int					headerPosition	= 0;

	/**
	 * Constructor.
	 * 
	 * @param tape
	 */
	public Tape(ArrayList<Symbol> tape)
	{
		this.tape = tape;
	}

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
		return tape.get(headerPosition);
	}

	/**
	 * Writes a symbol in the cell pointed by the header.
	 * 
	 * @param symbolToWrite
	 */
	public void writeSymbol(Symbol symbolToWrite)
	{
		tape.set(headerPosition, symbolToWrite);
	}
}
