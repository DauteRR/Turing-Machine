/**
 * File containing the TuringMachine entity definition. 
 */
package cc.p2.tm;

import java.util.TreeSet;

import cc.p2.tm.TMComponents.Alphabet;
import cc.p2.tm.TMComponents.State;
import cc.p2.tm.TMComponents.Symbol;
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
	/** b: b ∈ Γ is the blank symbol (the only symbol allowed to occur on the tape infinitely often at any step during the computation) */
	public static Symbol	blankSymbol;
	/** F: F ⊆ Q is the set of accepting states */
	TreeSet<State>			acceptingStates;
	
	/**
	 * @param states
	 * @param inputAlphabet
	 * @param tapeAlphabet
	 * @param transitions
	 * @param initialState
	 * @param acceptingStates
	 */
	public TuringMachine(TreeSet<State> states, Alphabet inputAlphabet,
			Alphabet tapeAlphabet, TreeSet<Transition> transitions,
			State initialState, Symbol blankSymbol, TreeSet<State> acceptingStates,
			int amountOfTapes)
	{
		super();
		this.states = states;
		this.inputAlphabet = inputAlphabet;
		this.tapeAlphabet = tapeAlphabet;
		this.transitions = transitions;
		this.initialState = initialState;
		this.acceptingStates = acceptingStates;
		TuringMachine.blankSymbol = blankSymbol;
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
		TM = TM.substring(0, TM.length() - 6) + "\n  }";

		return TM;
	}

	/**
	 * Getter method for inputAlphabet attribute.
	 * @return inputAlphabet
	 */
	public Alphabet getInputAlphabet()
	{
		return inputAlphabet;
	}
	
	
	
}
