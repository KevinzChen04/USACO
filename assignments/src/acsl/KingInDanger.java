package acsl;

public class KingInDanger {
    static int[] rotatex = {0, 0, 1, -1, 1, -1, 1, -1};
    static int[] rotatey = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[][] grid;
    static int maxMoves = 8;
	public static void main(String[] args) {
		String pieces = "Rc1 Kd8 Qb6 Re5 Bh3";
		grid = new int[8][8];
        String[] temporary = pieces.split(" ");
        Triple[] betterPieces = new Triple[temporary.length];
        for(int i = 0; i < temporary.length; i++){
            String piece = temporary[i].substring(0, 1);
            String position = temporary[i].substring(1);
            String[] temp = position.split("");
            int x = alphabetToNumber(temp[0]);
            int y = Integer.parseInt(temp[1]) - 1;
            betterPieces[i] = new Triple(piece, x, y);
            if(piece.equals("K")){
                grid[y][x] = 5;
                continue;
            }
            grid[y][x] = 2;
        }
        int sol = 0;
        boolean inCheck = false;
        int kingPositionx = 0;
        int kingPositiony = 0;
        for(Triple i : betterPieces){
            if(i.piece.equals("P")){
                pawn(i.positionx, i.positiony);
                continue;
            }
            if(i.piece.equals("R")){
                rook(i.positionx, i.positiony);
                continue;
            }
            if(i.piece.equals("B")){
                bishop(i.positionx, i.positiony);
            }
            if(i.piece.equals("Q")){
                bishop(i.positionx, i.positiony);
                rook(i.positionx, i.positiony);
            }
            if(i.piece.equals("N")){
                knight(i.positionx, i.positiony);
            }
            if(i.piece.equals("K")){
            	kingPositionx = i.positionx;
                kingPositiony = i.positiony;
            }
        }
        sol = king(kingPositionx, kingPositiony);
        if(grid[kingPositiony][kingPositionx] == 6){
            inCheck = true;
        }
        if(sol == maxMoves && inCheck){
            System.out.println("CheckMate");
        }
        if(sol == maxMoves && !inCheck){
            System.out.println("Stalemate");
        }
        if(sol != maxMoves && inCheck){
            System.out.println("Check");
        }
        if(sol != maxMoves && !inCheck){
            System.out.println("Safe");
        }
	}
    public static class Triple{
        String piece;
        int positionx;
        int positiony;
        public Triple(String x, int y, int z){
            piece = x;
            positionx = y;
            positiony = z;
        }
    }
    public static int alphabetToNumber(String x){
        if(x.equals("a")){
            return 0;
        }
        if(x.equals("b")){
            return 1;
        }
        if(x.equals("c")){
            return 2;
        }
        if(x.equals("d")){
            return 3;
        }
        if(x.equals("e")){
            return 4;
        }
        if(x.equals("f")){
            return 5;
        }
        if(x.equals("g")){
            return 6;
        }
        if(x.equals("h")){
            return 7;
        }
        return -1;
    }
    public static boolean inRange(int x){
        if(x > -1 && x < 8){
            return true;
        }
        return false;
    }
    public static void fillGrid(int x, int y){
        if(grid[y][x] == 0){
            grid[y][x] = 1;
            return;
        }
        if(grid[y][x] == 2){
            grid[y][x] = 3;
            return;
        }
        if(grid[y][x] == 5){
            grid[y][x] = 6;
            return;
        }
    }
    public static int king(int x, int y){
        int sol = 0;
        for(int i = 0; i < 8; i ++){
            int newx = rotatex[i] + x;
            int newy = rotatey[i] + y;
            if(inRange(newx) && inRange(newy)){
                if(grid[newy][newx] == 1 || grid[newy][newx] == 3){
                    sol++;
                }
            }
            else {
            	maxMoves--;
            }
        }
        return sol;
    }
    public static void knight(int x, int y){
        if(inRange(x + 2) && inRange(y + 1)){
            fillGrid(x + 2, y + 1);
        }
        if(inRange(x + 2) && inRange(y - 1)){
            fillGrid(x + 2, y - 1);
        }
        if(inRange(x - 2) && inRange(y + 1)){
            fillGrid(x - 2, y + 1);
        }
        if(inRange(x - 2) && inRange(y - 1)){
            fillGrid(x - 2, y + 1);
        }
        if(inRange(x + 1) && inRange(y + 2)){
            fillGrid(x + 1, y + 2);
        }
        if(inRange(x + 1) && inRange(y - 2)){
            fillGrid(x + 1, y - 2);
        }
        if(inRange(x - 1) && inRange(y + 2)){
            fillGrid(x - 1, y + 2);
        }
        if(inRange(x - 1) && inRange(y - 2)){
            fillGrid(x - 1, y + 2);
        }
    }
    public static void bishop(int x, int y){
        for(int i = 1; i < 8; i++){
            if(inRange(x + i) && inRange(y + i)){
                fillGrid(x + i, y + i);
                if(grid[y + i][x + i] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            if(inRange(x - i) && inRange(y + i)){
                fillGrid(x - i, y + i);
                if(grid[y + i][x - i] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            if(inRange(x + i) && inRange(y - i)){
                fillGrid(x + i, y - i);
                if(grid[y - i][x + i] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            if(inRange(x - i) && inRange(y - i)){
                fillGrid(x - i, y - i);
                if(grid[y - i][x - i] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
    }
    public static void rook(int x, int y){
        for(int i = 1; i < 8; i++){
            if(inRange(x + i)){
                fillGrid(x + i, y);
                if(grid[y][x + i] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            if(inRange(x - i)){
                fillGrid(x - i, y);
                if(grid[y][x - i] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            if(inRange(y - i)){
                fillGrid(x, y - i);
                if(grid[y - i][x] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            if(inRange(y + i)){
                fillGrid(x, y + i);
                if(grid[y + i][x] == 3){
                    break;
                }
            }
            else{
                break;
            }
        }
    }
    public static void pawn(int x, int y){
        if(inRange(x + 1)){
            fillGrid(x + 1, y + 1);
        }
        if(inRange(x - 1)){
            fillGrid(x + 1, y + 1);
        }
        fillGrid(x, y + 1);
    }
}
