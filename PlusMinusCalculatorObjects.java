package defaultpackage;

	class Player {
		
		int number;
		String name;
		int plusminus;
		boolean hasPlayed;
		
		public Player(int number, String name, int plusminus, boolean hasPlayed) {
			this.number = number;
			this.name = name;
			this.plusminus = plusminus;
			this.hasPlayed = hasPlayed;
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



