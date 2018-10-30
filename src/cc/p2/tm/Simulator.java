/**
 * File containing the Simulator entity definition. 
 */
package cc.p2.tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cc.p2.tm.AuxiliaryTools.TMParser;
import cc.p2.tm.TMComponents.Symbol;

/**
 * Class which constructs and starts Turing Machine simulation.
 * It contains a main method to start the program aswell.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 29 oct. 2018
 */
public class Simulator
{

	/**
	 * Main method
	 *
	 * @param args Arguments given to the program
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String inputStringRepresentation = "";
		if (args.length < 2)
		{
			System.err.println("You have to specify the configuration file of the Turing Machine at least:");
			System.err.println("java -jar TMSimulator.jar Samples/TM1.txt {0 | 1} [input.txt]");
			return;
		} else if (args.length == 2)
		{
			System.out.print("Enter input string(separe symbols with whitespaces): ");
			
			Scanner scanner = new Scanner(System.in);
		    inputStringRepresentation = scanner.nextLine();
		    scanner.close();
		} else
		{
			BufferedReader reader = new BufferedReader(new FileReader(args[2]));
			inputStringRepresentation = reader.readLine();
			reader.close();
			System.out.println("Input string: " + inputStringRepresentation);
		}
		
		TuringMachine TM = TMParser.constructTM(args[0]);
		
		String[] inputStringSymbols = inputStringRepresentation.split("\\s");
		ArrayList<Symbol> inputString = new ArrayList<Symbol>();
		if (!inputStringRepresentation.isEmpty())
			for (int i = 0; i < inputStringSymbols.length; ++i)
				inputString.add(TM.getInputAlphabet().getSymbol(inputStringSymbols[i]));
		
		System.out.println("\n" + TM);
		
		TM.startSimulation(inputString, Integer.parseInt(args[1]) == 1);
	}

}
