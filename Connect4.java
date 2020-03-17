import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
public class Connect4 implements Cloneable {
	int width = 7;
	int height = 6;
	char[][] grid = new char[height][width];
	/* {{'.','.','.','.','.','.','.',},
		{'.','.','.','.','.','.','.',},
		{'.','.','.','.','.','.','.',},
		{'.','.','.','.','.','.','.',},
		{'.','.','.','.','.','.','.',},
		{'.','.','.','.','.','.','.',}}
	*/
	public Connect4() {
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				grid[j][i] = '.';
			}
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Connect4 cloned = (Connect4) super.clone();
		char[][] newgrid = new char[6][7];
    	for (int i = 0; i < cloned.grid.length; i++) {
        	newgrid[i] = Arrays.copyOf(cloned.grid[i], cloned.grid[i].length);
     	}
		cloned.grid = newgrid;
		return cloned;
	}
	public static void cls() {
		/**
		* Clears the screen
		*/
		//Clear screen (Replit)
		System.out.print("\033[2J\033[;H");
		//Clear screen (BlueJ)
		System.out.print("\u000C");
	}
	public static void pause(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	public char[][] getGrid() {
		return grid;
	}
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	public StringBuilder getCol(int column) {
		/*
		* Returns a column as a series of characters (top to bottom)
		*/
		StringBuilder sb = new StringBuilder(6);
		for (int i=0;i<height;i++) {
			sb.append(grid[i][column]);
		}
		return sb;
	}
	public StringBuilder getRow(int row) {
		/*
		* Returns a row as a series of characters (left to right)
		*/
		StringBuilder sb = new StringBuilder(7);
		for (int i=0;i<width;i++) {
			sb.append(grid[row][i]);
		}
		return sb;
	}
	public void insert(char x,int row,int column) {
		grid[row][column] = x;
	}
	public void drop(char x,int column) {
		//drops into actual column value (input-1)
		StringBuilder col = getCol(column);
		for (int i=(col.length()-1);i>=0;i--) {
			if (col.charAt(i) == '.') {
				grid[i][column] = x;
				return;
			}
		}
		System.out.println("Error - column filled.");
		return;
	}
	public void clearGrid() {
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				grid[i][j] = '.';
			}
		}
	}
	public void moveGridDown() {
		StringBuilder sb;
		for (int i=4;i>0;i--) {
			sb = getRow(i);
			replaceRow(i+1,sb);
		}
		clearRow(0);
		pause(100);
	}
	public void clearRow(int row) {
		for (int i=0;i<width;i++) {
			grid[row][i] = '.';
		}
	}
	public void replaceRow(int row,StringBuilder replacement) {
		for (int i=0;i<width;i++) {
			grid[row][i] = replacement.charAt(i);
		}
	}
	@Override
	public String toString() {
		StringBuilder currentRow;
		StringBuilder str = new StringBuilder("");
		str.append(" 1 2 3 4 5 6 7\n");
		for (int i=0;i<height;i++) {
			currentRow = getRow(i);
			str.append("|");
			for (int j=0;j<width;j++) {
				if (currentRow.charAt(j)=='.') {
					str.append("_");
				} else {
					str.append(currentRow.charAt(j));
				}
				str.append("|");
			}
			str.append("\n");
		}
		return str.toString();
	}
	public void printAvailableMoves() {
		StringBuilder topRow = getRow(0);
		System.out.print("[");
		for (int i=0;i<width;i++) {
			if (topRow.charAt(i)=='.') {
				System.out.print(i+1);
				System.out.print(",");
			}
		}
		System.out.println("]");
	}
	public ArrayList getAvailableMoves() {
		//list goes from 1 to 7 (inclusive)
		StringBuilder topRow = getRow(0);
		ArrayList<Integer> moves = new ArrayList();
		for (int i=0;i<width;i++) {
			if (topRow.charAt(i)=='.') {
				moves.add(i+1);
			}
		}
		return moves;
	}
	public boolean boardFilled() {
		if (getAvailableMoves().size()==0) {
			return true;
		}
		return false;
	}
	public boolean checkWin() {
		//check rows
		StringBuilder row;
		for (int i=0;i<height;i++) {
			row = getRow(i);
			for (int j=0;j<4;j++) {
				if (row.charAt(j) != '.') {
					if (row.charAt(j)==row.charAt(j+1) && row.charAt(j+1)==row.charAt(j+2) && row.charAt(j+2)==row.charAt(j+3)){
						return true;
					}
				}
			}
		}
		//check columns
		StringBuilder column;
		//gets column from 0 to 6
		for (int i=0;i<width;i++) {
			column = getCol(i);
			for (int j=0;j<3;j++) {
				if (column.charAt(j) != '.') {
					if (column.charAt(j)==column.charAt(j+1) && column.charAt(j+1)==column.charAt(j+2) && column.charAt(j+2)==column.charAt(j+3)) {
						return true;
					}
				}
			}
		}
		//check diagonals
		//upRightDiag
		for (int i=3;i<6;i++) {
			for (int j=0;j<4;j++) {
				if (grid[i][j] != '.') {
					if (grid[i][j]==grid[i-1][j+1] && grid[i-1][j+1]==grid[i-2][j+2] && grid[i-2][j+2]==grid[i-3][j+3]) {
						return true;
					}
				}
			}
		}
		//upLeftDiag
		for (int i=3;i<6;i++) {
			for (int j=3;j<7;j++) {
				if (grid[i][j] != '.') {
					if (grid[i][j]==grid[i-1][j-1] && grid[i-1][j-1]==grid[i-2][j-2] && grid[i-2][j-2]==grid[i-3][j-3]) {
						return true;
					}
				}
			}
		}
		//not win
		return false;
	}
	public boolean checkWin(char x) {
		//check rows
		StringBuilder row;
		for (int i=0;i<height;i++) {
			row = getRow(i);
			for (int j=0;j<4;j++) {
				if (row.charAt(j) == x) {
					if (row.charAt(j)==row.charAt(j+1) && row.charAt(j+1)==row.charAt(j+2) && row.charAt(j+2)==row.charAt(j+3)){
						return true;
					}
				}
			}
		}
		//check columns
		StringBuilder column;
		//gets column from 0 to 6
		for (int i=0;i<width;i++) {
			column = getCol(i);
			for (int j=0;j<3;j++) {
				if (column.charAt(j) == x) {
					if (column.charAt(j)==column.charAt(j+1) && column.charAt(j+1)==column.charAt(j+2) && column.charAt(j+2)==column.charAt(j+3)) {
						return true;
					}
				}
			}
		}
		//check diagonals
		//upRightDiag
		for (int i=3;i<6;i++) {
			for (int j=0;j<4;j++) {
				if (grid[i][j] == x) {
					if (grid[i][j]==grid[i-1][j+1] && grid[i-1][j+1]==grid[i-2][j+2] && grid[i-2][j+2]==grid[i-3][j+3]) {
						return true;
					}
				}
			}
		}
		//upLeftDiag
		for (int i=3;i<6;i++) {
			for (int j=3;j<7;j++) {
				if (grid[i][j] == x) {
					if (grid[i][j]==grid[i-1][j-1] && grid[i-1][j-1]==grid[i-2][j-2] && grid[i-2][j-2]==grid[i-3][j-3]) {
						return true;
					}
				}
			}
		}
		//not win
		return false;
	}
	public int checkThrees(char x) {
		int sum = 0;
		//check rows
		StringBuilder row;
		for (int i=0;i<height;i++) {
			row = getRow(i);
			for (int j=0;j<5;j++) {
				if (row.charAt(j) == x) {
					if (row.charAt(j)==row.charAt(j+1) && row.charAt(j+1)==row.charAt(j+2)){
						sum += 1;
					}
				}
			}
		}
		//check columns
		StringBuilder column;
		//gets column from 0 to 6
		for (int i=0;i<width;i++) {
			column = getCol(i);
			for (int j=0;j<4;j++) {
				if (column.charAt(j) == x) {
					if (column.charAt(j)==column.charAt(j+1) && column.charAt(j+1)==column.charAt(j+2)) {
						sum += 1;
					}
				}
			}
		}
		//check diagonals
		//upRightDiag
		for (int i=2;i<6;i++) {
			for (int j=0;j<5;j++) {
				if (grid[i][j] == x) {
					if (grid[i][j]==grid[i-1][j+1] && grid[i-1][j+1]==grid[i-2][j+2]) {
						sum += 1;
					}
				}
			}
		}
		//upLeftDiag
		for (int i=2;i<6;i++) {
			for (int j=2;j<7;j++) {
				if (grid[i][j] == x) {
					if (grid[i][j]==grid[i-1][j-1] && grid[i-1][j-1]==grid[i-2][j-2]) {
						sum += 1;
					}
				}
			}
		}
		return sum;
	}
}