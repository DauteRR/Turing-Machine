/**
 * File containing the PDAParser entity definition. 
 */
package cc.p2.tm.AuxiliaryTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import cc.p2.tm.TuringMachine;
import cc.p2.tm.TMComponents.Alphabet;
import cc.p2.tm.TMComponents.State;
import cc.p2.tm.TMComponents.Symbol;
import cc.p2.tm.TMComponents.Tape.HeaderMovement;
import cc.p2.tm.TMComponents.Transition;

/**
 * Class which parses a configuration file and creates a Turing Machine object.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class TMParser
{
	/**
	 * Constructs a Turing Machine from a configuration file.
	 * 
	 * @param inputFile
	 * @throws IOException
	 */
	public static TuringMachine constructTM(String inputFile) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		String line = "";
		ArrayList<String> statesIdentifiers = new ArrayList<String>();

		// Skip comments and empty lines
		// States
		while (reader.ready())
		{
			line = reader.readLine();
			if (line.length() == 0 || line.charAt(0) == '#')
				continue;
			else
			{
				String[] tokens = line.split("\\s");
				for (String token : tokens)
				{
					if (token.charAt(0) == '#')
						break;
					statesIdentifiers.add(token);
				}
				break;
			}
		}

		// Input alphabet
		line = reader.readLine();
		String[] inputAlphabetSymbolsTokens = line.split("\\s");
		ArrayList<String> inputAlphabetSymbols = new ArrayList<String>();
		for (String symbol : inputAlphabetSymbolsTokens)
		{
			if (symbol.charAt(0) == '#')
				break;
			else
				inputAlphabetSymbols.add(symbol);
		}

		// Tape alphabet
		line = reader.readLine();
		String[] tapeAlphabetSymbolsTokens = line.split("\\s");
		ArrayList<String> tapeAlphabetSymbols = new ArrayList<String>();
		for (String symbol : tapeAlphabetSymbolsTokens)
		{
			if (symbol.charAt(0) == '#')
				break;
			else
				tapeAlphabetSymbols.add(symbol);
		}

		// Initial state
		line = reader.readLine();
		String[] initialStateLineTokens = line.split("\\s");
		String initialStateIdentifier = initialStateLineTokens[0];

		if (!statesIdentifiers.contains(initialStateIdentifier))
		{
			reader.close();
			throw new IllegalArgumentException("The initial state must be included in Q");
		}

		// Blank symbol
		line = reader.readLine();
		String[] blankSymbolLineTokens = line.split("\\s");
		String blankSymbolRepresentation = blankSymbolLineTokens[0];

		if (!tapeAlphabetSymbols.contains(blankSymbolRepresentation))
		{
			reader.close();
			throw new IllegalArgumentException("The blank symbol must be included in Γ");
		}

		// Accepting states
		line = reader.readLine();
		String[] acceptingStatesTokens = line.split("\\s");
		ArrayList<String> acceptingStatesIdentifiers = new ArrayList<String>();
		for (String acceptingStateIdentifier : acceptingStatesTokens)
		{
			if (acceptingStateIdentifier.charAt(0) == '#')
				break;
			else
				acceptingStatesIdentifiers.add(acceptingStateIdentifier);
		}

		if (!statesIdentifiers.containsAll(acceptingStatesIdentifiers))
		{
			reader.close();
			throw new IllegalArgumentException("Some state of F is not included in Q");
		}

		// Creation of some of the Turing Machine components
		TreeSet<State> states = new TreeSet<State>();
		for (String state : statesIdentifiers)
			states.add(new State(state));

		Alphabet inputAlphabet = new Alphabet(false);
		for (String symbol : inputAlphabetSymbols)
			inputAlphabet.addSymbol(new Symbol(symbol, Symbol.SymbolType.TERMINAL));

		Alphabet tapeAlphabet = new Alphabet(true);
		for (String symbol : tapeAlphabetSymbols)
			tapeAlphabet.addSymbol(new Symbol(symbol, Symbol.SymbolType.NON_TERMINAL));

		State initialState = null;
		for (State state : states)
			if (state.getStateID().equals(initialStateIdentifier))
			{
				initialState = state;
				break;
			}

		Symbol blankSymbol = null;
		blankSymbol = tapeAlphabet.getSymbol(blankSymbolRepresentation);
		

		TreeSet<State> acceptingStates = new TreeSet<State>();
		for (String stateID : acceptingStatesIdentifiers)
			for (State state : states)
				if (state.getStateID().equals(stateID))
				{
					acceptingStates.add(state);
					break;
				}

		// Transitions
		TreeSet<Transition> transitions = new TreeSet<Transition>();
		int amountOfTapes = 0;
		if (reader.ready())
		{
			line = reader.readLine();
			amountOfTapes = (line.split("\\s").length - 2) / 3;
			transitions.add(constructTransition(
					line, amountOfTapes, states, inputAlphabet, tapeAlphabet, 0, blankSymbol));
		}
		while (reader.ready())
		{
			transitions.add(constructTransition(reader.readLine(),
					amountOfTapes, states, inputAlphabet, tapeAlphabet, transitions.size(), blankSymbol));
		}

		reader.close();

		return new TuringMachine(states, inputAlphabet, tapeAlphabet,
				transitions, initialState, blankSymbol, acceptingStates, amountOfTapes);
	}

	/**
	 * Constructs a transition from a configuration file line.
	 * 
	 * @param line
	 * @param amountOfTapes
	 * @param states
	 * @param inputAlphabet
	 * @param tapeAlphabet
	 * @param transitionID
	 * @param blankSymbol
	 * @return transition
	 */
	private static Transition constructTransition(String line,
			int amountOfTapes, TreeSet<State> states, Alphabet inputAlphabet,
			Alphabet tapeAlphabet, int transitionID, Symbol blankSymbol)
	{
		String[] transitionTokens = line.split("\\s");
		if (transitionTokens.length < 2 + 3 * amountOfTapes)
			throw new IllegalArgumentException("Strange transition: " + line);

		String originStateID = transitionTokens[0], destinationStateID = transitionTokens[amountOfTapes + 1];
		State originState = null, destinationState = null;
		for (State state : states)
		{
			if (state.getStateID().equals(originStateID))
				originState = state;
			if (state.getStateID().equals(destinationStateID))
				destinationState = state;
		}
		
		if (originState == null || destinationState == null)
			throw new IllegalArgumentException("Unknow state in transition: " + line);

		ArrayList<Symbol> inputSymbols = new ArrayList<Symbol>();
		for (int i = 1; i < 1 + amountOfTapes; ++i)
		{
			if (transitionTokens[i].equals(blankSymbol.getSymbolRepresentation()))
				inputSymbols.add(blankSymbol);
			else
				inputSymbols.add(inputAlphabet.getSymbol(transitionTokens[i]));
		}

		ArrayList<Symbol> outputSymbols = new ArrayList<Symbol>();
		for (int i = amountOfTapes + 2; i < 2 + amountOfTapes * 2; ++i)
			outputSymbols.add(tapeAlphabet.getSymbol(transitionTokens[i]));

		ArrayList<HeaderMovement> headersMovements = new ArrayList<HeaderMovement>();
		for (int i = 2 + amountOfTapes * 2; i < 2 + amountOfTapes * 3; ++i)
		{
			switch (transitionTokens[i])
			{
			case "L":
			case "Left":
			case "LEFT":
				headersMovements.add(HeaderMovement.LEFT);
				break;
			case "R":
			case "Right":
			case "RIGHT":
				headersMovements.add(HeaderMovement.RIGHT);
				break;
			case "S":
			case "Stay":
			case "STAY":
				headersMovements.add(HeaderMovement.STAY);
				break;
			default:
				throw new IllegalArgumentException(
						"Unknow tape header movement in transition: " + line);
			}
		}

		return new Transition(originState, destinationState, headersMovements,
				inputSymbols, outputSymbols, transitionID);
	}
}
