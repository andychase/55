import java.util.*;
public class Solver {
    public static HashSet<Integer> getPossibilities(Grid grid, int col, int row) {
        HashSet<Integer> possible = new HashSet<>(Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9}));
        for(Integer[] section: Arrays.asList(grid.getCol(col), grid.getRow(row), grid.getBox(col, row)))
            possible.removeAll(Arrays.asList(section));
        return possible;
    }
    public static Grid solveWithTransformation(Grid grid, int col, int row, int val) {grid.set(col, row, val);return solve(grid);}
    public static Grid solve(Grid grid) {
        for(int col = 0; col < 9; col++)
            for(int row = 0; row < 9; row++)
                if (grid.get(col, row) == 0) {
                    for(Object possibility: getPossibilities(grid, col, row))
                        if (solveWithTransformation(grid, col, row, (int) possibility) != null)
                            return grid;
                    grid.set(col, row, 0);
                    return null;             }
        return grid;
    }
}