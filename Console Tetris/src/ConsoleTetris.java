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

		char[][] gameBoard = new char[22][10]; //standard tetris grid is 22 rows by 10 columns
		boolean gameOver = false; //condition for game loop to continue running
		boolean spawnTetromino = true; //if true, we need to spawn a random tetromino on to gameBoard
		
		initializegameBoard(gameBoard); //sets multi-dimension array making up gameBoard to all zeroes
		printTitleScreen(); //:P
		
		//Thread.sleep allows intro screen to stick around for a while
		//requires try/catch block
		try {
			Thread.sleep(2500);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//main game loop
		while(!gameOver) {
			if(spawnTetromino) {
				spawnRandomTetromino(gameBoard, rand); //spawns a random tetromino on to gameBoard
				spawnTetromino = false; //tetromino has been spawned at this point
			}
			
			spawnTetromino = dropTetromino(gameBoard); //drops the tetromino down one row at a time
													   //if it can't be dropped any further, true is returned
													   //in which case another tetromino has to be spawned
			//gameBoard is printed every 100ms 
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
				/*
				else if(gameBoard[i][j] == 1)
					System.out.print("1");
				else if(gameBoard[i][j] == 2)
					System.out.print("2");
					*/
			}
			System.out.print("#\n");
		}
		for(int i = 0; i < gameBoard[0].length+2; i++) //2 accounts for first and last "#" in gird
			System.out.print("#");
		System.out.println("\n");
	}
	
	public static void spawnRandomTetromino(char[][] gameBoard, Random rand) {
		//spawnVerticalI(gameBoard);
		int tetromino = rand.nextInt(8) + 1; //generate random no. between 1 and 7
		
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
			case 8: spawnVerticalI(gameBoard);
		}
	}
	
	public static void spawnVerticalI(char[][] gameBoard) {
		gameBoard[0][4] = 1;
		gameBoard[1][4] = 1;
		gameBoard[2][4] = 1;
		gameBoard[3][4] = 1;
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
	
	public static boolean dropTetromino(char[][] gameBoard){ //returns boolean value indicating if when we have to spawn next tetromino
		
		int tetrominoRow = -1; //holds row containing current tetromino
		boolean tetrominoFound = false; //used to find which row contains current tetromino
		boolean collision = false; //used to determine if drop possible
		boolean specialCaseOne = true; //it's a special case if tetromino is a horizontal 'I' shaped one
		boolean specialCaseTwo = false; //it's also a special case if tetromino is a vertical 'I' shaped one
		
		//find tetromino location in board
		for(int i = 0; i < gameBoard.length && !tetrominoFound; i++) { //sweep through all rows until tetromino found
			for(int j = 0; j < gameBoard[i].length; j++) { //sweep through column to determine if row contains tetromino
				//we found our row
				if(gameBoard[i][j] == 1) {
					tetrominoFound = true;
					tetrominoRow = i;
				}
			}
		}	
		
		//we assume that specialCaseOne applies and so we must test to determine if it 'doesn't' apply
		if(tetrominoFound && tetrominoRow+1 != gameBoard.length) {
			//the special case is basically when we just need to check the next row for whether the tetromino can be dropped
			for(int j = 0; j < gameBoard[tetrominoRow].length; j++) {
				if(gameBoard[tetrominoRow+1][j] == 1) {
					specialCaseOne = false; //tetromino spans two rows and therefore specialCaseOne doesn't apply
				}
			}
		}

		//on the contrary, we need to determine if specialCaseTwo applies since we assume it doesn't apply initially
		if(tetrominoFound && tetrominoRow+3 < gameBoard.length) { 
			for(int j = 0; j < gameBoard[tetrominoRow].length; j++) {
				if(gameBoard[tetrominoRow][j] == 1 && gameBoard[tetrominoRow+1][j] == 1 && gameBoard[tetrominoRow+2][j] == 1 && gameBoard[tetrominoRow+3][j] == 1) {
					specialCaseTwo = true; //tetromino spans four rows and therefore specialCaseTwo applies
				}
			}
		}
	
		//check if tetromino can be dropped
		if(specialCaseOne) { //horizontal 'I'
			if(tetrominoRow + 1 == gameBoard.length)
				collision = true;
			else {
				for(int i = tetrominoRow; i < Math.min(tetrominoRow+1, gameBoard.length-1); i++) {
					for(int j = 0; j < gameBoard[i].length; j++) {
						if(gameBoard[i][j] == 1 && gameBoard[i+1][j] == 2) 
							collision = true;
					}
				}
			}
		}
		
		
		else if(specialCaseTwo) { //vertical 'I'
			if(tetrominoRow + 4 >= gameBoard.length)
				collision = true;
			else {
				for(int i = tetrominoRow; i < Math.min(tetrominoRow+1, gameBoard.length-1); i++) {
					for(int j = 0; j < gameBoard[i].length; j++) {
						if(gameBoard[i][j] == 1 && (gameBoard[i+1][j] == 2 || gameBoard[i+2][j] == 2 || gameBoard[i+3][j] == 2 || gameBoard[i+4][j] == 2))
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
						if( gameBoard[i+1][j] == 1 && gameBoard[i+2][j] == 2) 
							collision = true;
					}
				}
			}
		}
		
		//drop the tetromino if feasible
		if(!collision) {
			if(specialCaseOne) {
				for(int i = tetrominoRow, j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i][j] == 1) {
						gameBoard[i+1][j] = 1; //move part of tetromino downwards
						gameBoard[i][j] = 0; //clear part from old position
					}
				}
			}
			
			
			else if(specialCaseTwo) {
				for(int i = tetrominoRow, j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i][j] == 1) {
						gameBoard[i+4][j] = 1; //move part of tetromino downwards
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
		
		//in the event a collision occurs
		if(collision) {
			for(int i = tetrominoRow; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i][j] == 1)
						gameBoard[i][j] = 2;
				}
			}
		}
		
		return collision; //true means new tetromino needs to be spawned, not the case otherwise
	}
	
	public static void moveTetrominoLeft(char[][] gameBoard) {
		
		int tetrominoRow = -1; //holds the row of the tetromino
		boolean tetrominoFound = false; //used to determine if row contains a tetromino
		boolean collision = false; //used to determine if shift to left possible
		
		//find tetromino location in board
		for(int i = 0; i < gameBoard.length && !tetrominoFound; i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 1) {
					tetrominoFound = true;
					tetrominoRow = i;
				}
			}
		}
		
		//check if tetromino can be moved to the left
		
		//move tetromino to the left if feasible
	}
	
	public static void moveTetrominoRight(char[][] gameBoard) {
		int tetrominoRow = -1; //holds the row of the tetromino
		boolean tetrominoFound = false; //used to determine if row contains a tetromino
		boolean collision = false; //used to determine if shift to right possible
		boolean specialCaseOne = true; //it's a special case if tetromino is a horizontal 'I' shaped one
		
		//find tetromino location in board
		for(int i = 0; i < gameBoard.length && !tetrominoFound; i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 1) {
					tetrominoFound = true;
					tetrominoRow = i;
				}
			}
		}
	}
	
	public static void moveTetrominoDown(char[][] gameBoard) {
		
	}
}
