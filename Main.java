import java.util.Scanner;
import java.util.ArrayList;
class Main {
	
    public static void main(String[] args) throws CloneNotSupportedException{
    	Scanner in = new Scanner(System.in);
		//human is always X, computer is always O
		boolean quit = false;
		while (!quit) {
			System.out.print("Would you like to play against another human or a computer? \n1: Human\n2: Computer\n3: Quit\n>>");
			int input = in.nextInt();
			in.nextLine();
			switch (input) {
				case 1:
					humanVsHumanGame();
					break;
				case 2:
					humanVsComputerGame();
					break;
				case 3:
				System.out.println("Goodbye!");
					quit = true;
					break;
				default:
					System.out.println("Invalid option.");
					break;
			}
		}
    }
	public static void humanVsComputerGame() throws CloneNotSupportedException {
		//human vs human loop
		Scanner in = new Scanner(System.in);
		Connect4 game = new Connect4();
		boolean game_running = true;
		int input;
		AiPlayer computer = new AiPlayer(6);
		while (game_running) {
			//player 1
			game.cls();
			System.out.println("Player 1:\n");
			System.out.println(game);
			System.out.print("Available moves: ");
			game.printAvailableMoves();
			input = intIn(game.getAvailableMoves());
			game.drop('X',input-1);
			if (game.checkWin()) {
				game.cls();
				System.out.println("Player 1:\n");
				System.out.println(game);
				System.out.println("Player 1 wins!");
				break;
			}
			//player 2
			game.cls();
			System.out.println("Player 2:\n");
			System.out.println(game);
			System.out.print("Available moves: ");
			game.printAvailableMoves();
			input = computer.randomMove(game);
			game.drop('O',input);
			if (game.checkWin()) {
				game.cls();
				System.out.println("Player 2:\n");
				System.out.println(game);
				System.out.println("The computer wins!");
				break;
			}
		}
		in.nextLine();
		game.cls();
		System.out.print(game);
		for (int i=0;i<6;i++) {
			game.pause(250);
			game.cls();
			game.moveGridDown();
			System.out.print(game);
			game.pause(500);
			game.cls();
			
		}
	}
	public static void humanVsHumanGame() {
		//human vs human loop
		Scanner in = new Scanner(System.in);
		Connect4 game = new Connect4();
		boolean game_running = true;
		int input;
		while (game_running) {
			//player 1
			game.cls();
			System.out.println("Player 1:\n");
			System.out.println(game);
			System.out.print("Available moves: ");
			game.printAvailableMoves();
			input = intIn(game.getAvailableMoves());
			game.drop('X',input-1);
			if (game.checkWin()) {
				game.cls();
				System.out.println("Player 1:\n");
				System.out.println(game);
				System.out.println("Player 1 wins!");
				break;
			}
			//player 2
			game.cls();
			System.out.println("Player 2:\n");
			System.out.println(game);
			System.out.print("Available moves: ");
			game.printAvailableMoves();
			input = intIn(game.getAvailableMoves());
			game.drop('O',input-1);
			if (game.checkWin()) {
				game.cls();
				System.out.println("Player 2:\n");
				System.out.println(game);
				System.out.println("Player 2 wins!");
				break;
			}
		}
		in.nextLine();
		game.cls();
		System.out.print(game);
		for (int i=0;i<6;i++) {
			game.pause(250);
			game.cls();
			game.moveGridDown();
			System.out.print(game);
			game.pause(500);
			game.cls();
		}
	}
	public static int intIn(ArrayList moves) {
		Scanner in = new Scanner(System.in);
		int input = -1;
		while (true) {
			try {
				System.out.print("Make a move: ");
				input = in.nextInt();
				if (moves.contains(input)) {
					return input;
				} else {
					System.out.println("Move not available");
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
				in.nextLine();
			}
			
		}
	}
}