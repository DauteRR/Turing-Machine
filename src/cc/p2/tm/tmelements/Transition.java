/**
 * File containing the Transition entity definition. 
 */
package cc.p2.tm.tmelements;

import cc.p2.tm.tmelements.InputTape.HeaderMovement;

/**
 * Class which represents a transition of a multitape Turing Machine.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 29 oct. 2018
 */
public class Transition implements Comparable<Transition>
{
	/** Origin state of the transition */
	State originState;
	/** Destination state of the transition */
	State destinationState;
	/** Movement of each input tape header */
	HeaderMovement[] inputTapeHeadersMovements;
	/** Input symbols required in each tape */
	Symbol[] inputSymbols;
	/** Symbols to write in each tape */
	Symbol[] outputSymbols;
	/** Identifies each transition */
	int	transitionID;
	
	/**
	 * Constructor.
	 * 
	 * @param originState
	 * @param destinationState
	 * @param inputTapeHeadersMovements
	 * @param inputSymbols
	 * @param outputSymbols
	 * @param transitionID
	 */
	public Transition(State originState, State destinationState,
			HeaderMovement[] inputTapeHeadersMovements, Symbol[] inputSymbols,
			Symbol[] outputSymbols, int transitionID)
	{
		this.originState = originState;
		this.destinationState = destinationState;
		this.inputTapeHeadersMovements = inputTapeHeadersMovements;
		this.inputSymbols = inputSymbols;
		this.outputSymbols = outputSymbols;
		this.transitionID = transitionID;
	}
	
	@Override
	public int compareTo(Transition otherTransition)
	{
		Integer firstID = Integer.valueOf(transitionID);
		Integer secondID = Integer.valueOf(otherTransition.getTransitionID());

		return firstID.compareTo(secondID);
	}
	
	@Override
	public String toString()
	{
		return "(" + originState + ", " + inputSymbols + ", " + outputSymbols + ", " + destinationState + ", " + inputTapeHeadersMovements + ")";
	}

	/**
	 * Getter method for originState attribute.
	 * @return originState
	 */
	public State getOriginState()
	{
		return originState;
	}

	/**
	 * Getter method for destinationState attribute.
	 * @return destinationState
	 */
	public State getDestinationState()
	{
		return destinationState;
	}

	/**
	 * Getter method for inputTapeHeadersMovements attribute.
	 * @return inputTapeHeadersMovements
	 */
	public HeaderMovement[] getInputTapeHeadersMovements()
	{
		return inputTapeHeadersMovements;
	}

	/**
	 * Getter method for inputSymbols attribute.
	 * @return inputSymbols
	 */
	public Symbol[] getInputSymbols()
	{
		return inputSymbols;
	}

	/**
	 * Getter method for outputSymbols attribute.
	 * @return outputSymbols
	 */
	public Symbol[] getOutputSymbols()
	{
		return outputSymbols;
	}

	/**
	 * Getter method for transitionID attribute.
	 * @return transitionID
	 */
	public int getTransitionID()
	{
		return transitionID;
	}
}
