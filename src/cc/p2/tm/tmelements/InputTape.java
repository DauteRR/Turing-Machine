/**
 * File containing the InputTape entity definition. 
 */
package cc.p2.tm.tmelements;

import java.util.ArrayList;

import cc.p2.tm.TuringMachine;

/**
 * Class which represents an input tape of a multitape Turing Machine.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 29 oct. 2018
 */
public class InputTape
{
	/**
	 * Enumeration containing the available movements of an input tape header
	 */
	public static enum HeaderMovement
	{
		LEFT, RIGHT, STAY
	}

	/** Representation of the input tape, each element is a cell */
	ArrayList<Symbol>	inputTape;
	/** Position of the read/write header */
	int					headerPosition	= 0;

	/**
	 * Constructor.
	 * 
	 * @param inputTape
	 */
	public InputTape(ArrayList<Symbol> inputTape)
	{
		this.inputTape = inputTape;
	}

	/**
	 * Moves the input tape header.
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
			inputTape.add(0, TuringMachine.whiteSymbol);
		} else if (headerPosition >= inputTape.size())
		{
			inputTape.add(TuringMachine.whiteSymbol);
		}
	}

	/**
	 * Returns the symbol pointed by the header.
	 * 
	 * @return Symbol
	 */
	public Symbol readSymbol()
	{
		return inputTape.get(headerPosition);
	}

	/**
	 * Writes a symbol in the cell pointed by the header.
	 * 
	 * @param symbolToWrite
	 */
	public void writeSymbol(Symbol symbolToWrite)
	{
		inputTape.set(headerPosition, symbolToWrite);
	}
}
