/**
 * File containing the Transition entity definition. 
 */
package cc.p2.tm.TMComponents;

import java.util.ArrayList;

import cc.p2.tm.TMComponents.Tape.HeaderMovement;

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
	/** Movement of each tape header */
	ArrayList<HeaderMovement> tapeHeadersMovements;
	/** Input symbols required in each tape */
	ArrayList<Symbol> inputSymbols;
	/** Symbols to write in each tape */
	ArrayList<Symbol> outputSymbols;
	/** Identifies each transition */
	int	transitionID;
	
	/**
	 * Constructor.
	 * 
	 * @param originState
	 * @param destinationState
	 * @param tapeHeadersMovements
	 * @param inputSymbols
	 * @param outputSymbols
	 * @param transitionID
	 */
	public Transition(State originState, State destinationState,
			ArrayList<HeaderMovement> tapeHeadersMovements, ArrayList<Symbol> inputSymbols,
			ArrayList<Symbol> outputSymbols, int transitionID)
	{
		this.originState = originState;
		this.destinationState = destinationState;
		this.tapeHeadersMovements = tapeHeadersMovements;
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
		return "(" + originState + ", " + inputSymbols + ", " + destinationState + ", " + outputSymbols  + ", " + tapeHeadersMovements + ")";
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
	 * Getter method for tapeHeadersMovements attribute.
	 * @return tapeHeadersMovements
	 */
	public ArrayList<HeaderMovement> getTapeHeadersMovements()
	{
		return tapeHeadersMovements;
	}

	/**
	 * Getter method for inputSymbols attribute.
	 * @return inputSymbols
	 */
	public ArrayList<Symbol> getInputSymbols()
	{
		return inputSymbols;
	}

	/**
	 * Getter method for outputSymbols attribute.
	 * @return outputSymbols
	 */
	public ArrayList<Symbol> getOutputSymbols()
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
