package net.april1.ccc2017;

public class GameIterations {
    private int[] array;
    private boolean firstReady = false;
    private static final int START = 0;
    private int last_result;
 
    public GameIterations(int games, int results) {
        if (games < 1) {
            throw new IllegalArgumentException("The games must be min. 1");
        }
        if (results < 2) {
            throw new IllegalArgumentException("The results must be min. 2");
        }
        array = new int[games];
        reset();
        last_result = results - 1;
    }
 
    public void reset() {
        for (int i = 0; i < array.length; i++) {
            array[i] = START;
        }
        firstReady = false;
    }
 
    public boolean hasMore() {
        boolean end = firstReady;
        for (int i = 0; i < array.length; i++) {
            end = end && array[i] == last_result;
        }
        return !end;
    }
 
    public int[] getNext() {
 
        if (!firstReady) {
            firstReady = true;
            return array;
        }
 
        array[array.length-1]++;
        for(int i = array.length-1;i>0;i--) {
        	if (array[i]>last_result)
        	{
        		array[i]=0;
        		array[i-1]++;
        	}
        	
        }
        
        return array;
    } // getNext()
 
    // For testing of the PermutationGenerator class
    public static void main(String[] args) {
    	GameIterations pg = new GameIterations(8, 5);
 
    	int count = 0;
        while (pg.hasMore()) {
        	count++;
            int[] temp =  pg.getNext();
            for (int i = 0; i < temp.length; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();
        }
        System.out.println(count);
    }
 
}
