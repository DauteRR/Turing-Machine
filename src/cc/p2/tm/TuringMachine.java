/**
 * File containing the TuringMachine entity definition. 
 */
package cc.p2.tm;

import java.util.ArrayList;
import java.util.TreeSet;


import cc.p2.tm.TMComponents.Alphabet;
import cc.p2.tm.TMComponents.State;
import cc.p2.tm.TMComponents.Symbol;
import cc.p2.tm.TMComponents.Tape;
import cc.p2.tm.TMComponents.Transition;

/**
 * Class which represents a Turing Machine. A Turing Machine is formally defined
 * as a 7-tuple: TM = (Q, Σ, Γ, δ, s, b, F)
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 29 oct. 2018
 */
public class TuringMachine
{
	/** Q: Finite set of states */
	TreeSet<State>			states;
	/** Σ: Input alphabet, compound by terminal symbols */
	Alphabet				inputAlphabet;
	/** Γ: Tape alphabet, compound by non terminal symbols */
	Alphabet				tapeAlphabet;
	/** δ: Finite set of transitions between states */
	TreeSet<Transition>		transitions;
	/** s: s ∈ Q is the initial state */
	State					initialState;
	/**
	 * b: b ∈ Γ is the blank symbol (the only symbol allowed to occur on the
	 * tape infinitely often at any step during the computation)
	 */
	public static Symbol	blankSymbol;
	/** F: F ⊆ Q is the set of accepting states */
	TreeSet<State>			acceptingStates;

	/** TM's tapes */
	ArrayList<Tape>			tapes = new ArrayList<Tape>();

	/**
	 * Constructor
	 * 
	 * @param states
	 * @param inputAlphabet
	 * @param tapeAlphabet
	 * @param transitions
	 * @param initialState
	 * @param acceptingStates
	 */
	public TuringMachine(TreeSet<State> states, Alphabet inputAlphabet,
			Alphabet tapeAlphabet, TreeSet<Transition> transitions,
			State initialState, Symbol blankSymbol,
			TreeSet<State> acceptingStates, int amountOfTapes)
	{
		super();
		this.states = states;
		this.inputAlphabet = inputAlphabet;
		this.tapeAlphabet = tapeAlphabet;
		this.transitions = transitions;
		this.initialState = initialState;
		this.acceptingStates = acceptingStates;
		TuringMachine.blankSymbol = blankSymbol;
		
		for (int i = 0; i < amountOfTapes; ++i)
			tapes.add(new Tape());
	}
	
	/**
	 * Public interface to start the simulation process
	 * 
	 * @param inputString Input string
	 * @param traceMode Specifies if the trace mode is enabled
	 */
	public void startSimulation(ArrayList<Symbol> inputString, boolean traceMode)
	{
		setInputString(inputString);
		if (simulate(traceMode)) 
		{
			System.out.println("\n\nSimulation finished in an accepting state");
		} else
		{
			System.out.println("\n\nSimulation didn't finish in an accepting state");
		}
		
		tapes.get(0).printFromHeaderToRight();
	}
	
	/**
	 * Simulates the TM's behavior
	 */
	private boolean simulate(boolean traceMode)
	{
		State currentState = initialState;
		ArrayList<Symbol> inputSymbols = new ArrayList<Symbol>();
		for (Tape tape: tapes)
			inputSymbols.add(tape.readSymbol());
		Transition transitionToApply = getTransition(currentState, inputSymbols);
		
		if (traceMode)
		{
			System.out.println("==========================");
			for (Tape tape: tapes)
				System.out.println(tape);
			System.out.println("State: " + currentState + "\nInput symbols: " + inputSymbols + "\nTransition to apply: " + transitionToApply);
			System.out.println("==========================");
		}
		
		while (transitionToApply != null)
		{
			currentState = transitionToApply.getDestinationState();
			for (int i = 0; i < tapes.size(); ++i)
			{
				tapes.get(i).writeSymbol(transitionToApply.getOutputSymbols().get(i));
				tapes.get(i).move(transitionToApply.getTapeHeadersMovements().get(i));
			}
			inputSymbols.clear();
			for (Tape tape: tapes)
				inputSymbols.add(tape.readSymbol());
			
			if (traceMode)
			{
				for (Tape tape: tapes)
					System.out.println(tape);
				System.out.println("State: " + currentState + "\nInput symbols: " + inputSymbols + "\nTransition to apply: " + transitionToApply);
				System.out.println("==========================");
			}
			
			transitionToApply = getTransition(currentState, inputSymbols);
		}
		
		return acceptingStates.contains(currentState);
	}
	
	/**
	 * Returns possible transition given a state and input symbols
	 * 
	 * @param currentState
	 * @param inputSymbol
	 * @return Transition
	 */
	private Transition getTransition(State currentState, ArrayList<Symbol> inputSymbols)
	{
		Transition possibleTransition = null;
		int matches = 0;
		
		for (Transition transition: transitions)
		{
			if (transition.getOriginState().equals(currentState) &&
				transition.getInputSymbols().equals(inputSymbols))
			{
				matches++;
				possibleTransition = transition;
			}
		}
		if (matches > 1)
		{
			throw new IllegalArgumentException("This simulator doesn't support non-deterministic Turing Machines");
		}
		
		return possibleTransition;
	}
 	
	
	/**
	 * Sets the input string in the first tape (the input tape)
	 * 
	 * @param inputString
	 */
	private void setInputString(ArrayList<Symbol> inputString)
	{
		if (tapes.size() <= 0)
			throw new IllegalArgumentException("You can't specify the input string if the Turing Machine hasn't tapes");
		
		tapes.get(0).setTapeContent(inputString);
	}

	@Override
	public String toString()
	{
		String TM = "TM=(Q, Σ, Γ, δ, s, b, F)\n\n  Q = { ";
		for (State state : states)
			TM += state + ", ";
		TM = TM.substring(0, TM.length() - 2) + "}\n  Σ = " + inputAlphabet
				+ "\n  Γ = " + tapeAlphabet + "\n  s = " + initialState
				+ "\n  b = " + blankSymbol + "\n  F = { ";

		for (State state : acceptingStates)
			TM += state + ", ";
		TM = TM.substring(0, TM.length() - 2) + "}\n  δ = {\n    ";

		for (Transition transition : transitions)
			TM += transition + ",\n    ";
		TM = TM.substring(0, TM.length() - 6) + "\n  }\n\n";

		return TM;
	}

	/**
	 * Getter method for inputAlphabet attribute.
	 * 
	 * @return inputAlphabet
	 */
	public Alphabet getInputAlphabet()
	{
		return inputAlphabet;
	}
}
