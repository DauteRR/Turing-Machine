/**
 * File containing the Simulator entity definition. 
 */
package cc.p2.tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		String inputString = "";
		if (args.length < 1)
		{
			System.err.println("You have to specify the configuration file of the Turing Machine at least:");
			System.err.println("java -jar TMSimulator.jar Samples/TM1.txt [input.txt]");
			return;
		} else if (args.length == 1)
		{
			System.out.print("Enter input string(separe symbols with whitespaces): ");
			
			Scanner scanner = new Scanner(System.in);
		    inputString = scanner.nextLine();
		    scanner.close();
		} else
		{
			BufferedReader reader = new BufferedReader(new FileReader(args[1]));
			inputString = reader.readLine();
			reader.close();
			System.out.println("Input string: " + inputString);
		}
		
		TuringMachine TM = TMParser.constructTM(args[0]);
		
//		Symbol[] inputStringSymbols = new Symbol[inputString.split("\\s").length];
//		for (int i = 0; i < inputStringSymbols.length; ++i)
//			inputStringSymbols[i] = TM.getInputAlphabet().getSymbol(inputString.split("\\s")[i]);
		//TODO Update "input tape"
		
		System.out.println(TM);
	}

}
