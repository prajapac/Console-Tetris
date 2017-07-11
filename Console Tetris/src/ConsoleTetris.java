/**
 * @author Crckr Hckr
 * 
 * The main .java file for the Console Tetris project intended for making
 * a Tetris Clone that uses a console video for graphics. 
 *
 */

import java.util.Random; //allows for use of random numbers

public class ConsoleTetris {
	
	public static void main(String[] args) {
		
		Random rand = new Random(); //used to generate random tetrominos

		char[][] gameBoard = new char[22][10]; //standard tetris grid
		boolean gameOver = false; //condition for game loop
		boolean spawnTetromino = true;
		
		initializegameBoard(gameBoard);
		printTitleScreen();
		
		try {
			Thread.sleep(2500);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		while(!gameOver) {
			if(spawnTetromino) {
				spawnRandomTetromino(gameBoard, rand);
				spawnTetromino = false;
			}
			
			spawnTetromino = dropTetromino(gameBoard);
			
			try {
				Thread.sleep(100);
				printgameBoard(gameBoard);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void printTitleScreen() {
		System.out.println(" _____ _____ _   _  _____  _____ _      _____");
		System.out.println("/  __ \\  _  | \\ | |/  ___||  _  | |    |  ___|");
		System.out.println("| /  \\/ | | |  \\| |\\ `--. | | | | |    | |__  ");
		System.out.println("| |   | | | | . ` | `--. \\| | | | |    |  __|");
		System.out.println("| \\__/\\ \\_/ / |\\  |/\\__/ /\\ \\_/ / |____| |___");
		System.out.println(" \\____/\\___/\\_| \\_/\\____/  \\___/\\_____/\\____/");
		System.out.println("\n\n");
		System.out.println(" _____ _____ ___________ _____ _____");
		System.out.println("|_   _|  ___|_   _| ___ \\_   _/  ___|");
		System.out.println("  | | | |__   | | | |_/ / | | \\ `--.");
		System.out.println("  | | |  __|  | | |    /  | |  `--. \\");
		System.out.println("  | | | |___  | | | |\\ \\ _| |_/\\__/ /");
		System.out.println("  \\_/ \\____/  \\_/ \\_| \\_|\\___/\\____/");
		System.out.println("");
	}
	
	public static void initializegameBoard(char[][] gameBoard) {
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = 0;
			}
		}
	}
	
	public static void printgameBoard(char[][] gameBoard) throws Exception {
		for(int i = 0; i < 20; i++)
			System.out.println();
		for(int i = 2; i < gameBoard.length; i++) {
			System.out.print("#");
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print("*");
			}
			System.out.print("#\n");
		}
		for(int i = 0; i < gameBoard[0].length+2; i++) //2 accounts for first and last "#" in gird
			System.out.print("#");
		System.out.println("\n");
	}
	
	public static void spawnTetrominoI(char[][] gameBoard){
		for (int j = 0 + 3; j < gameBoard[0].length - 3; j++)
			gameBoard[0][j] = 1; //****
 	}
	
	public static void spawnTetrominoO(char[][] gameBoard){
		gameBoard[0][4] = 1; gameBoard[0][5] = 1; //**
		gameBoard[1][4] = 1; gameBoard[1][5] = 1; //**
 	}
	
	public static void spawnTetrominoT(char[][] gameBoard){
		gameBoard[0][3] = 1; gameBoard[0][4] = 1; gameBoard[0][5] = 1;  //***
		gameBoard[1][4] = 1;											// *
	}
	
	public static void spawnTetrominoS(char[][] gameBoard){
		gameBoard[0][4] = 1; gameBoard[0][5] = 1;  //**
		gameBoard[1][3] = 1; gameBoard[1][4] = 1; //**
	}
	
	public static void spawnTetrominoZ(char[][] gameBoard){
		gameBoard[0][3] = 1; gameBoard[0][4] = 1; //**
		gameBoard[1][4] = 1; gameBoard[1][5] = 1;  //**
	}
	
	public static void spawnTetrominoJ(char[][] gameBoard){
		gameBoard[0][5] = 1;											//  *
		gameBoard[1][3] = 1; gameBoard[1][4] = 1; gameBoard[1][5] = 1;  //***
	}
	
	public static void spawnTetrominoL(char[][] gameBoard){
		gameBoard[0][3] = 1;											//*
		gameBoard[1][3] = 1; gameBoard[1][4] = 1; gameBoard[1][5] = 1;  //***
	}
	
	public static void spawnRandomTetromino(char[][] gameBoard, Random rand) {
		int tetromino = rand.nextInt(7) + 1; //generate random no. between 1 and 7
		
		switch (tetromino) {
			case 1: spawnTetrominoI(gameBoard);
			        break;
			case 2: spawnTetrominoO(gameBoard);
	        	break;
			case 3: spawnTetrominoT(gameBoard);
	        	break;
			case 4: spawnTetrominoS(gameBoard);
	        	break;
			case 5: spawnTetrominoZ(gameBoard);
	        	break;
			case 6: spawnTetrominoJ(gameBoard);
	        	break;
			case 7: spawnTetrominoL(gameBoard);
	        	break;
		}
	}
	
	public static boolean dropTetromino(char[][] gameBoard){ //change to return boolean so we can know when to spawn next tetromino
		
		int tetrominoRow = -1; //holds the row of the tetromino
		boolean tetrominoFound = false; //used to determine if row contains a tetromino
		boolean collision = false; //used to determine if drop possible
		boolean specialCase = true; //it's a special case if tetromino is a horizontal 'I' shaped one
		
		//find tetromino location in board
		for(int i = 0; i < gameBoard.length && !tetrominoFound; i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 1) {
					tetrominoFound = true;
					tetrominoRow = i;
				}
			}
			if(tetrominoFound && i+1 != gameBoard.length) {
				for(int j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i+1][j] == 1) {
						specialCase = false; //the special case is basically when we just need to check the next row if tetromino can be dropped
					}
				}
			}
		}
		
		//check if tetromino can be dropped
		if(specialCase) { //horizontal I
			if(tetrominoRow + 1 == gameBoard.length)
				collision = true;
			else {
				for(int i = tetrominoRow; i < Math.min(tetrominoRow+1, gameBoard.length-1); i++) {
					for(int j = 0; j < gameBoard[i].length; j++) {
						if(gameBoard[i][j] == 1 && gameBoard[i+1][j] == 1)
							collision = true;
					}
				}
			}
		}
		
		else { //every other tetromino
			if(tetrominoRow + 1 >= gameBoard.length || tetrominoRow + 2 >= gameBoard.length) //all other tetrominoes occupy at least two rows
				collision = true;
			else {
				for(int i = tetrominoRow; i < Math.min(tetrominoRow+1, gameBoard.length-1); i++) {
					for(int j = 0; j < gameBoard[i].length; j++) {
						if( gameBoard[i+1][j] == 1 && gameBoard[i+2][j] == 1)
							collision = true;
					}
				}
			}
		}
		
		//drop the tetromino if feasible
		if(!collision) {
			if(specialCase) {
				for(int i = tetrominoRow, j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i][j] == 1) {
						gameBoard[i+1][j] = 1; //move part of tetromino downwards
						gameBoard[i][j] = 0; //clear part from old position
					}
				}
			}
			else {
				for(int i = tetrominoRow, j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i+1][j] == 1) {
						gameBoard[i+2][j] = 1; //move part of tetromino downwards
						gameBoard[i+1][j] = 0; //clear part from old position
					}
				}
				
				for(int i = tetrominoRow, j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i][j] == 1) {
						gameBoard[i+1][j] = 1; //move part of tetromino downwards
						gameBoard[i][j] = 0; //clear part from old position
					}
				}
			}
		}
		/*
		for(int i = tetrominoRow; i < Math.min(tetrominoRow + 2, gameBoard.length); i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i+1][j] == 1 && gameBoard[i+2][j] == 1 )
					collision = true;
			}
			if(!collision) {
				for(int j = 0; j < gameBoard[i].length; j++) {
					gameBoard[i+1][j] = gameBoard[i][j];
					gameBoard[i][j] = 0;
				}
			}
		}*/
		return collision;
	}
}
