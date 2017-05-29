package defaultpackage;

	class Player {
		
		int number;
		String name;
		int plusminus;
		
		public Player(int number, String name, int plusminus) {
			this.number = number;
			this.name = name;
			this.plusminus = plusminus;
		}
		
	} 
	
	class Lineup {
		
		Player[] unit = new Player[5];
		int plusminus;
		Lineup next;
		
		public Lineup(Player unit[], int plusminus, Lineup next) {
			this.unit = unit;
			this.plusminus = plusminus;
			this.next = next;
		}
		
		
		
	}



