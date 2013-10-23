/* 55 - Andy Chase - MIT LICENSE */  import java.util.*;
public class Grid {
    public Integer[][] grid = new Integer[9][9];
    public Grid () { for(Integer[] row : grid) Arrays.fill(row, 0);}
    // Getters and Setters
    public Integer get(int col, int row) { if (col < 9 && row < 9) return grid[col][row]; else return 0; }
    public Integer[]  getCol(int col) { if (col < 10) return grid[col]; return new Integer[]{}; }
    public Integer[]  getRow(int row) { Integer[] all_rows = new Integer[9];
           for(int col = 0; col < 9; col++) all_rows[col] = get(col, row);
           return all_rows; }
    public Integer[]  getBox(int col, int row) {return getBox(new Integer[9], 0, (col / 3), (row / 3));}
        private Integer[] getBox(Integer[] all_box, int i, int col, int row) {
           for(int x = 0; x < 3; x++)
               for(int y = 0; y < 3; y++)
                  all_box[i++] = get(x+(col*3), y+(row*3));
           return all_box;
    } // Set values on the Sudoku board
    public boolean set(int col, int row, int value) {
        return (Solver.getPossibilities(this, col, row).contains(value) || value == 0) && (grid[col][row] = value) >= 0;
    } // Display Sudoku board
    public String toString() {return toString("", 0, 0);}
        private String toString(String s, int col, int row) {
            if(col == 8 && row == 8) return s + " " + get(col, row) + "\n";
            if(col == 8) return toString(s + " " + get(col, row) + "\n", 0, row+1);
            if((col % 3) == 0)  return toString(s + " | " + get(col, row), col+1, row);
            return toString(s + " " + get(col, row), col+1, row);
        } // Textual Interface
    public static void main(String [] args) {(new Grid()).ui(new Scanner(System.in));}
    public void print(String s) {System.out.println(s);}
    public int read(Scanner in) {try{return Math.abs(in.nextInt());}catch(Exception e){}ui(new Scanner(System.in));return 0;}
    public void ui(Scanner in) { print(toString()+"(col) (row) (val). 0 0 0 to solve.");
        while (true) print(ui_set(read(in) - 1, read(in) - 1, read(in))); }
    public String ui_set(int col, int row, int val) {if (col == -1) Solver.solve(this); else set(col, row, val); return toString();}
}
