package bikesim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BikeSim {
	
	/**********************************************************/

	static Bike bike = new Bike();
	
	static final int PLACE=0, FORWARD=1, TURN_LEFT=2, TURN_RIGHT=3, GPS_REPORT=4, NA = -1;
	static final String[] COMMANDS = {"PLACE", "FORWARD", "TURN_LEFT", "TURN_RIGHT", "GPS_REPORT"};
	
	static final int RIGHT=1, LEFT=-1;
	
	String outputStr = null;
	
	/**********************************************************/
	// Optional parameter: <input file name>
	/**********************************************************/

	public static void main(String[] args) {
		
		String inputFileName = (args.length == 1 ? args[0] : null);
		
		BikeSim sim = new BikeSim();
		
		sim.processInput(inputFileName);
		
		String ret = sim.getOutputString();
		
	}

	/**********************************************************/
	// Process a stream of commands from a file or the console	
	/**********************************************************/
	
	public void processInput(String fileName) {

		try {
			
			InputStreamReader reader = (fileName == null ? new InputStreamReader(System.in) : new FileReader(fileName));

			BufferedReader br = new BufferedReader(reader);
			
			boolean looping = true;
			
			while(looping) {
				
				try {
										
					String command = br.readLine();
					
					if(command == null || command.equals("QUIT")) {
						looping = false;
					} else {
						outputStr = processCommand(command);
						if(outputStr != null) {
							message(outputStr);
						}
					}

				} catch (IOException e) {
					message("Oops, error reading input ... ");
					e.printStackTrace();
					looping = false;
				}
			
			}			
			
		} catch (FileNotFoundException e) {
			message("Oops, something happened :(");
			e.printStackTrace();
		}
	
	}

	/**********************************************************/
	// Process a single command	
	/**********************************************************/
	
	public String processCommand(String command) {
		
		String ret = null;
		
		String words[] = command.split(" ");

		switch(getIndex(words[0], COMMANDS)) {
		
			case PLACE:
				
				if(words.length == 2) {
					
					String params[] = words[1].split(",");
					if(params.length == 3) {
						
						int x = getInt(params[0]);
						int y = getInt(params[1]);
						int dir = getIndex(params[2], Bike.DIRS);
						if(x != NA && y != NA && dir != NA) {
							bike.place(x, y, dir);
						}
						
					}
					
				}
				break;
				
			case FORWARD:
				bike.forward();
				break;
				
			case TURN_LEFT:
				bike.turn(LEFT);
				break;
				
			case TURN_RIGHT:
				bike.turn(RIGHT);
				break;
				
			case GPS_REPORT:
				ret = bike.getGPSReport();
				break;
				
		}
		
		return ret;

	}

	/**********************************************************/
	// return the index of the input string in the input array	
	/**********************************************************/
	
	private static int getIndex(String input, String[] array) {
		
		int ret = NA;
		
		for(int nEntry=0, maxEntries = array.length; nEntry < maxEntries; nEntry++) {
			if(input.equals(array[nEntry])) {
				ret = nEntry;
				break;
			}
		}
		
		return ret;
		
	}

	/**********************************************************/
	// return an integer from a string	
	/**********************************************************/
	
	private static int getInt(String input) {
		
		int ret = NA;
		
		try {
			ret = Integer.parseInt(input);
		} catch(NumberFormatException ex) {
		}
		
		return ret;
		
	}

	/***************************************************/
	
	private static void message(String msg) {
		System.out.println(msg);
	}
	
	/***************************************************/
	
	public String getOutputString() {
		return outputStr;
	}
	
}
