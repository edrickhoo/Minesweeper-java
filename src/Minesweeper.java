import java.util.Arrays;
import java.util.Scanner;

public class Minesweeper {
	
	public static int[][] generateBoard(int size, int bombs) {
		int[][] board = new int[size][size];
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[i].length; j ++) {
					board[i][j] = 0;
			}
		}
		return placeMines(board, bombs, size);	
	}
	
//	Recreating the board but hiding the mines as unchecked.
	public static void generateAndPrintPlayerBoard(int[][] board) {
		int[][] playerBoard = new int[board.length][board.length];
		for(int i = 0; i < playerBoard.length; i ++) {
			for(int j = 0; j < playerBoard[i].length; j ++) {
					if(board[i][j] == 1) {
						playerBoard[i][j] = 0;
					}
					if(board[i][j] == 0) {
						playerBoard[i][j] = 0;
					}
					if(board[i][j] == 2) {
						playerBoard[i][j] = 2;
					}
			}
		}
		printBoard(playerBoard);

	}
	
	public static void printBoard(int[][] board) {
		for(int i = 0; i < board.length; i ++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
//	Selects random coordinates and places bombs until there is a certain amount placed
	public static int[][] placeMines(int[][] board,int bombs, int size) {
		
		int count = 0;
		while(count < bombs) {
			int cordX = (int) Math.floor(Math.random() * (size));
			int cordY = (int) Math.floor(Math.random() * (size));
			if(board[cordX][cordY] != 1) {
				board[cordX][cordY] = 1;
				count++;
			}
		}
		
		return board;
		
		
	}
	
	public static int getYInput(Scanner myObj, int size) {
	        System.out.println("Please enter Y Cord");
	        boolean validInput = true;

	        while(validInput) {
	            int x = myObj.nextInt();
	            if(x >= 0 && x <= size) {
	                return x;
	            } else {
	                System.out.println("Please valid Y coordinate");
	            }
	        }
	        return 0;
	    
	}
	
	public static int getXInput(Scanner myObj, int size) {
        System.out.println("Please enter X Cord");
        boolean validInput = true;

        while(validInput) {
            int y = myObj.nextInt();
            if(y >= 0 && y <= size) {
                return y;
            } else {
                System.out.println("Please valid X coordinate");
            }
        }
        return 0;
    
}
//	Checks if given index will be in bounds
	public static boolean checkInBounds(int x, int y, int boardHeight) {
		if (x >= 0 && x < boardHeight && y >= 0 && y < boardHeight) {
			   return true;
		}
		
		return false;
	}
	
	public static int checkSurroundingForBombs(int[][]board, int x, int y) {
		int bombs = 0;
		
		int[][] surroundingCoords = {{x + 1, y}, {x + 1, y + 1}, {x, y + 1}, {x -1, y + 1}, {x -1, y}, {x -1, y -1}, {x, y -1}, {x + 1, y -1},};
		
		for(int i = 0; i < surroundingCoords.length; i ++) {
			if (checkInBounds(surroundingCoords[i][0], surroundingCoords[i][1], board.length) && board[surroundingCoords[i][1]][surroundingCoords[i][0]] == 1) {
				bombs++;
			} 
		}
		
////		Check top
//		if (checkInBounds(x + 1, y, board.length) && board[y][x + 1] == 1) {
//			bombs++;
//		}
////		Check top right
//		if (checkInBounds(x + 1, y + 1, board.length) && board[y + 1][x + 1] == 1) {
//			bombs++;
//		}
////		Check right
//		if (checkInBounds(x, y + 1, board.length) && board[y + 1][x] == 1) {
//			bombs++;
//		}
////		Check bottom right
//		if (checkInBounds(x - 1, y + 1, board.length) && board[y + 1][x - 1] == 1) {
//			bombs++;
//		}
////		Check bottom
//		if (checkInBounds(x - 1, y, board.length) && board[y][x - 1] == 1) {
//			bombs++;
//		}
////		Check bottom left
//		if (checkInBounds(x - 1, y - 1, board.length) && board[y - 1][x - 1] == 1) {
//			bombs++;
//		}
////		Check left
//		if (checkInBounds(x, y - 1, board.length) && board[y - 1][x] == 1) {
//			bombs++;
//		}
////		Check top left
//		if (checkInBounds(x + 1, y - 1, board.length) && board[y - 1][x + 1] == 1) {
//			bombs++;
//		}
		return bombs;
	}
	
	public static char checkPlayAgain(Scanner myObj) {
	        String letter = myObj.nextLine();

	        if(letter.length() > 0) {
	            return letter.toLowerCase().charAt(0);
	        } else {
	            return 'x';
	        }

	    }
	
	public static boolean checkGameWon(int[][]board) {
		for(int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[i].length; j ++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void greetPlayer() {
		System.out.println("WELCOME TO MINESWEEPER");
		System.out.println("Please enter coordinates to play");
		System.out.println("X: 0, Y: 0 is top left of the board");
		System.out.println("0 = Unchecked");
		System.out.println("1 = Mine");
		System.out.println("2 = Checked \n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		greetPlayer();
		
		boolean gameIsRunning = true;
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		
				while (gameIsRunning) {
					int size = 10;
					int bombs = 10;
					int[][] board = generateBoard(size, bombs);
					
				while(true) {
//					Print board
					generateAndPrintPlayerBoard(board);
					
//					Enter two inputs
					int y = getYInput(myObj, size);
					int x = getXInput(myObj, size);
					printBoard(board);
//					Check if lost or clear or win
					
//					If coords is a mine
					if(board[y][x] == 1) {
						System.out.printf("X: %d, Y: %d%n", x, y);
						System.out.println("Boom You've Hit A Mine");
						System.out.println("You Lose");
						break;
					}
					
//					If coords is unchecked
					if(board[y][x] == 0) {
						board[y][x] = 2;
						int amountOfBombsNearBy = checkSurroundingForBombs(board, x, y);
						if(checkGameWon(board)) {
							
							System.out.println("You have sweeped all the mines safely");
							System.out.println("You Win");
							break;
						}
						System.out.printf("X: %d, Y: %d is... safe there are %d bombs near by.%n%n%n", x, y, amountOfBombsNearBy);
						continue;
					}
					
//					If coords is already checked
					if(board[y][x] == 2) {
						System.out.printf("X: %d Y: %d has already been cleared please select another coordinates%n%n%n", y, x);
						continue;
					}
				}
				
				 System.out.println("Please enter Y to play again, anything else to exit");
				 char playAgain = checkPlayAgain(myObj);
				 if(playAgain == 'y') {
		                System.out.println("New game loading...");
		            } else {
		                System.out.println("Thank you for playing, see you next time!");
		                gameIsRunning = false;
		                break;
		            }
		}
	}
}
