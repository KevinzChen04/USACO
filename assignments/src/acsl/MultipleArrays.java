package acsl;
import java.util.*;
import java.io.*;
public class MultipleArrays {
	 public static class Pair{
	        int r;
	        int c;
	        public Pair(int x, int y){
	            r = x;
	            c = y;
	        }
	        @Override
	        public int hashCode() {
	            final int prime = 31;
	            int result = 1;
	            result = prime * result + c;
	            result = prime * result + r;
	            return result;
	        }
	        @Override
	        public boolean equals(Object obj) {
	            Pair other = (Pair) obj;
	            if (r != other.r)
	                return false;
	            if (c != other.c)
	                return false;
	            return true;
	        }
	    }
	public static void main(String[] args) {
		String dim = "5 3";
		ArrayList<String> arrays = new ArrayList<String>();
		arrays.add("9 9 9 8 8 8 7 7 7 6 6 6 5 5 5");
		arrays.add("5 6 7 8 5 5 6 7 8 9 5 6 7 8 9");
		arrays.add("5 6 3 2 1 9 4 3 2 1 5 4 3 2 1");
		arrays.add("5 5 5 6 6 6 7 7 7 8 8 8 9 9 9");
		arrays.add("1 2 3 4 5 6 7 8 9 8 7 6 5 4 3");
		int sol = 0;
        int indexOfSpace = dim.indexOf(" ");
        int rows = Integer.parseInt(dim.substring(0, indexOfSpace));
        int cols = Integer.parseInt(dim.substring(indexOfSpace + 1));
        int numOfArrays = arrays.size();
        HashSet<Pair> visited = new HashSet<Pair>();
        int[][][] grid = new int[numOfArrays][rows][cols];
        for(int i = 0; i < numOfArrays; i++){
            String[] temp = arrays.get(i).split(" ");
            for(int j = 0; j < rows; j++){
                for(int k = 0; k < cols; k++){
                    grid[i][j][k] = Integer.parseInt(temp[j * cols + k]);
                }
            }
        }
        int[] rotationr = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] rotationc = {-1, -1, -1, 0, 0, 1, 1, 1};
        int currentr = 0;
        int currentc = 0;
        int lowest = Integer.MAX_VALUE;
        for(int i = 0; i < numOfArrays; i++){
            if(lowest > grid[i][currentr][currentc]){
                lowest = grid[i][currentr][currentc];
            }
        }
        sol += lowest;
        while(true){
            TreeMap<Integer, Pair> unique = new TreeMap<Integer, Pair>();
            HashSet<Integer> removed = new HashSet<Integer>();
            visited.add(new Pair(currentr, currentc));
            for(int i = 0; i < 8; i++){
                int futurer = currentr + rotationr[i];
                int futurec = currentc + rotationc[i];
                for(int j = 0; j < numOfArrays; j++){
                    if(futurer >= rows || futurer < 0 || futurec >= cols || futurec < 0){
                        continue;
                    }
                    if(removed.contains(grid[j][futurer][futurec])){
                        continue;
                    }
                    if(unique.containsKey(grid[j][futurer][futurec])){
                        unique.remove(grid[j][futurer][futurec]);
                        removed.add(grid[j][futurer][futurec]);
                        continue;
                    }
                    unique.put(grid[j][futurer][futurec], new Pair(futurer, futurec));
                }
            }
            Pair temp = unique.get(unique.lastKey());
            currentc = temp.c;
            currentr = temp.r;
            if(visited.contains(new Pair(currentr, currentc))){
                break;
            }
            lowest = Integer.MAX_VALUE;
            for(int i = 0; i < numOfArrays; i++){
                if(lowest > grid[i][currentr][currentc]){
                    lowest = grid[i][currentr][currentc];
                }
            }
            sol += lowest;
        }
        System.out.println(sol);
	}

}
