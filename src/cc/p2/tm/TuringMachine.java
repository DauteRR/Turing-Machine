/**
 * File containing the TuringMachine entity definition. 
 */
package cc.p2.tm;

import java.util.TreeSet;

import cc.p2.tm.tmelements.Alphabet;
import cc.p2.tm.tmelements.State;
import cc.p2.tm.tmelements.Symbol;
import cc.p2.tm.tmelements.Transition;

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
	/** Γ: Stack alphabet, compound by non terminal symbols */
	Alphabet				stackAlphabet;
	/** δ: Finite set of transitions between states */
	TreeSet<Transition>		transitions;
	/** s: s ∈ Q is the initial state */
	State					initialState;
	/** b: b ∈ Γ is the blank symbol (the only symbol allowed to occur on the tape infinitely often at any step during the computation) */
	public static Symbol	whiteSymbol;
	/** F: F ⊆ Q is the set of accepting states */
	TreeSet<State>			acceptingStates;
}
