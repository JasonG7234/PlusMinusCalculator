package defaultpackage;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlusMinusCalculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Step 1 -> Define all players 
		//Step 2 -> Insert starting lineup
		//Step 3 -> Enter while loop and monitor all lineups and subs
		//PlusMinusCalculatorObjects o = new PlusMinusCalculatorObjects();
		
		//STEP 1 -> Define roster
		System.out.println("Enter number of eligible players: ");
		int sizeOfRoster = scanner.nextInt();
		Player[] roster = new Player[sizeOfRoster];
		
		Player p1 = new Player(3, "Corey Sanders", 0, false);
		roster[0] = p1;
		Player p2 = new Player(2, "Shaquille Doorson", 0, false);
		roster[1] = p2;
		Player p3 = new Player(5, "Mike Williams", 0, false);
		roster[2] = p3;
		Player p4 = new Player(21, "Candido Sa", 0, false);
		roster[3] = p4;
		Player p5 = new Player(33, "Deshawn Freeman", 0, false);
		roster[4] = p5;
		Player p6 = new Player(11, "Eugene Omoruyi", 0, false);
		roster[5] = p6;
		Player p7 = new Player(35, "Issa Thiam", 0, false);
		roster[6] = p7;
		Player p8 = new Player (13, "Matt Bullock", 0, false);
		roster[7] = p8;
		Player p9 = new Player (0, "Souf Mensah", 0, false);
		roster[8] = p9;
		Player p10 = new Player (15, "Jake Dadika", 0, false);
		roster[9] = p10;
		
		
		
		//Step 2 -> Create starting lineup
		Player[] startingLineup = new Player[5];
		//inproperInput means the user did not enter a correct starter,
		//numberInLineup means tells the console to output the correct ERROR statement depending on criteria
		boolean inproperInput;
		boolean numberInLineup;
		for (int i = 0; i<5; i++) {
		    inproperInput = true;
		    numberInLineup = true;
			while (input) {
				System.out.println("Enter number of starter: ");
				int num = scanner.nextInt();
				//Check if player is on roster
				for (int j = 0; j < sizeOfRoster; j++) {
					if (num == roster[j].number) {
					    startingLineup[i] = roster[j];
					    inproperInput = false; 
					    //Check if player is already in starting lineup
					    for (int k = i-1; k >= 0; k--) {
					        if (startingLineup[k].number == num) {
					            //If player is already in starting lineup...
					            startingLineup[i] = null;
					            //This boolean is responsible for outputting the right ERROR Statement
					            numberInLineup = false;
					            System.out.println("ERROR: Number already in starting lineup. Try again.");
					            inproperInput = true;
					            break;
					        }
					    }
						break;
					}
				}
				if (startingLineup[i] == null && numberInLineup) {
				    System.out.println("ERROR: Number not found on roster. Try again.");
			    }
			}
		}
		System.out.println("Starters entered successfully.");
		System.out.println();
		
		Lineup starters = new Lineup(startingLineup, 0, null);
		Lineup cur = starters;
		boolean gameInProgress = true;
		input = true;
		while (gameInProgress) {
			System.out.println("Type s for substitution, p for points scored, or q for quit.");
			String action = scanner.next();
			
			if (action.equalsIgnoreCase("s")) {
				//Substitution
				int i = 0;
				int k = 0;
				while (input) {
					//Sub out
					System.out.println("Enter the number of the player being subbed out: ");
					int subout = scanner.nextInt();
					for (i = 0; i<5; i++) {
						if (cur.unit[i].number == subout) {
							input = false;
							break;
						}
					}
					if (input) {
						System.out.println("ERROR: Player is not currently in the game. Try again");
					}
				}
				
				boolean input2 = true;
				input = true;
				//Sub in
				while (input) {
					System.out.println("Enter the number of the player being subbed in: ");
					int subin = scanner.nextInt();
					//Check to make sure player is on roster
					for (k = 0; k < 13; k++) {
						if (roster[k].number == subin) {
							input2 = false;
							break;
						}
					}
					if (input2) {
						System.out.println("ERROR: Player is not on the roster. Try again");
					} else {
						input = false;
						//Check to make sure player is not currently on court
						for (int j = 0; j < 5; j++) {
							if (cur.unit[j].number == subin && j != i) {
								//Currently allowing for a player to 'sub out' for themselves as a way of cancelling a substitution
								System.out.println("ERROR: Player is currently on the court. Try again");
								input = true;
								break;
							}
						}
					}
					
					Player[] currentPlayers = new Player[5];
					currentPlayers = cur.unit;
					// Represent substitution
					currentPlayers[i] = roster[k];
					roster[k].hasPlayed = true;
					Lineup newLineup = new Lineup(currentPlayers, 0, null);
					cur.next = newLineup;
					cur = cur.next;
					
					/* Future updates -> I would like to give the user the
					 * option to make multiple substitutions at once, if
					 * let's say a coach subs out all five guys. 
					 * OR
					 * Save the starters as a lineup, and not create a new
					 * lineup at the start of the second half or end of game. 
					 */
				}
			} else if (action.equalsIgnoreCase("p")) {
				//Points scored
				System.out.println("Enter how many points were scored. (If they were scored for the other team, input a negative number.)");
				int score = scanner.nextInt();
				cur.plusminus += score; /* Update plus minus for unit */
				for (int i = 0; i<5; i++) {
					cur.unit[i].plusminus += score;
				}
			} else if (action.equalsIgnoreCase("q")) {
				//Exit loop
				System.out.println("Plus minuses are being calculated.");
				calculatePlusMinuses(roster, sizeOfRoster, starters);
				gameInProgress = false;
			} else {
				//Improper input
				System.out.println("ERROR: Incorrect input. Try again");
			}
		}
		
	}

	private static void calculatePlusMinuses(Player[] roster, int sizeOfRoster, Lineup starters) {
		// TODO Auto-generated method stub
		System.out.println("Calculating best lineups...");
		try {
		TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {}
		System.out.println("Overall plus minuses: ");
         	for (int a = 0; a < sizeOfRoster; a++) {
             	    if (roster[a].plusminus > 0) {
                 	System.out.println(roster[a].name + " -> +" + roster[a].plusminus);
		    } else {
                	System.out.println(roster[a].name + " -> " + roster[a].plusminus);
             	    }
         	}
	}

}
