/**
 * @author Crckr Hckr
 * 
 * The main .java file for the Console Tetris project intended for making
 * a Tetris Clone that uses a console video for graphics. 
 *
 */
public class ConsoleTetris {
	
	public static void main(String[] args) {
		char[][] gameBoard = new char[22][10]; //standard tetris grid
		boolean gameOver = false; //condition for game loop
		
		initializegameBoard(gameBoard);
		printTitleScreen();
		
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		spawnTetrominoI(gameBoard);
		
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		dropTetromino(gameBoard);
		
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		dropTetromino(gameBoard);
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		dropTetromino(gameBoard);
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		dropTetromino(gameBoard);
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		dropTetromino(gameBoard);
		try {
			printgameBoard(gameBoard);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
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
		for(int i = 2; i < gameBoard.length; i++) {
			System.out.print("#");
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print("*");
			}
			System.out.print("#\n");
			Thread.sleep(100);
		}
		for(int i = 0; i < gameBoard[0].length+2; i++) //2 accounts for first and last "#" in gird
			System.out.print("#");
		System.out.println("\n");
	}
	
	public static void spawnTetrominoI(char[][] gameBoard){
		for (int i = 0 + 3; i < gameBoard[0].length - 3; i++)
			gameBoard[0][i] = 1;
	}
	
	public static void spawnTetrominoO(char[][] gameBoard){
			
	}
	
	public static void spawnTetrominoT(char[][] gameBoard){
		
	}
	
	public static void spawnTetrominoS(char[][] gameBoard){
		
	}
	
	public static void spawnTetrominoZ(char[][] gameBoard){
		
	}
	
	public static void spawnTetrominoJ(char[][] gameBoard){
		
	}
	
	public static void spawnTetrominoL(char[][] gameBoard){
		
	}
	
	public static void dropTetromino(char[][] gameBoard){ //change to return boolean so we can know when to spawn next tetromino
		
		int tetrominoRow = -1; //holds the row of the tetromino
		boolean tetrominoFound = false; //used to determine if row contains a tetromino
		boolean collision = false; //used to determine if drop possible
		boolean specialCase = true; //it's a special case if tetromino is a horizontal 'I' shaped one
		
		//find tetromino location in board
		for(int i = 0; i < gameBoard.length && !tetrominoFound; i++) {
			for(int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 1) {
					tetrominoFound = !tetrominoFound;
					tetrominoRow = i;
				}
			}
			if(tetrominoFound) {
				for(int j = 0; j < gameBoard[i].length; j++) {
					if(gameBoard[i+1][j] == 1) {
						specialCase = false; //the special case is basically when we just need to check the next row if tetromino can be dropped
						System.out.println(tetrominoRow);
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
		
		else { //any other tetromino
			
		}
		
		//drop the tetromino if feasible
		if(!collision) {
			for(int i = tetrominoRow, j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == 1) {
					gameBoard[i+1][j] = 1; //move part of tetromino downwards
					gameBoard[i][j] = 0; //clear part from old position
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
	}
}
