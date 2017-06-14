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
		
		Player p1 = new Player(3, "Corey Sanders", 0);
		roster[0] = p1;
		Player p2 = new Player(2, "Shaquille Doorson", 0);
		roster[1] = p2;
		Player p3 = new Player(5, "Mike Williams", 0);
		roster[2] = p3;
		Player p4 = new Player(21, "Candido Sa", 0);
		roster[3] = p4;
		Player p5 = new Player(33, "Deshawn Freeman", 0);
		roster[4] = p5;
		Player p6 = new Player(11, "Eugene Omoruyi", 0);
		roster[5] = p6;
		Player p7 = new Player(35, "Issa Thiam", 0);
		roster[6] = p7;
		Player p8 = new Player (13, "Matt Bullock", 0);
		roster[7] = p8;
		Player p9 = new Player (0, "Souf Mensah", 0);
		roster[8] = p9;
		Player p10 = new Player (15, "Jake Dadika", 0);
		roster[9] = p10;
		
		
		
		//Step 2 -> Create starting lineup
		Player[] startingLineup = new Player[5];
		boolean input = true;
		for (int i = 0; i<5; i++) {
			while (input) {
				System.out.println("Enter number of starter: ");
				int num = scanner.nextInt();
				for (int j = 0; j < sizeOfRoster; j++) {
					if (num == roster[j].number) {
						startingLineup[i] = roster[j];
						input = false; 
						break;
					}
				}
				if (startingLineup[i] == null) {
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
					for (int k = 0; k < 13; k++) {
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
					
					Player[] currentLineup = new Player[5];
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
				calculatePlusMinuses(starters);
				gameInProgress = false;
			} else {
				//Improper input
				System.out.println("ERROR: Incorrect input. Try again");
			}
		}
		
	}

	private static void calculatePlusMinuses(Lineup starters) {
		// TODO Auto-generated method stub
		
	}

}
