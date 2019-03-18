import java.util.Random;

public class client {


    
    public static int count(int n){
        // for each sites count n, do the weighted quick-union by height 1000 times, 
        // and obtain the average pairs count to be more accurate
        int totalPairsCount = 0;
        for(int i = 0; i<1000; i++){
            UF_HWQUPC hwqupc = new UF_HWQUPC(n, true);
            int pairsCount = 0;
            while(hwqupc.components() != 1){
                int[] pair = randomPair(n);
                pairsCount++;
                if(!hwqupc.connected(pair[0], pair[1]))
                    hwqupc.union(pair[0], pair[1]);
            }
            totalPairsCount += pairsCount;
        }
        return (int)(totalPairsCount/1000);
    }
    
    private static int[] randomPair(int n){
        Random r = new Random();
        int a = r.nextInt(n);
        int b = r.nextInt(n);
        while(a == b)
            b = r.nextInt(n);
        int[] pair = {a,b};
        return pair;
    }
    
    /**
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int[] sitesCount = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200, 102400};
        for(int n : sitesCount){
            int pairs = count(n);
            System.out.println("Initial sites: " + n + ", Pairs generated: " + pairs);
        }
    }
    
}

