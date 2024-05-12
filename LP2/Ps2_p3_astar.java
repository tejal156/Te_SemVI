import java.util.*;

class Ps2_p3_astar {
    
    static class State {
        // int evaluation;
        int g;
        int[][] grid;
        int x ;
        int y ;
        int px ;
        int py ;
        int h , f ;
        
        State( int g, int[][] grid , int px , int py ,int[][] end , int x , int y ) {
            // this.evaluation = evaluation;
            this.g = g;
            this.grid = grid;
            this.px = px ;
            this.x = x ;
            this.py = py ;
            this.y = y ;
            this.f= 0 ;
        int heuristic = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != -1 && grid[i][j] != end[i][j]) {
                    heuristic++;
                }
            }
        }
        this.h  = heuristic;
        f = g + h ;
            

        }
    }
    


 
    static boolean check(int[][] start, int[][] end) {
        // int[] temp = {-1, 1, 2, 3, 4, 5, 6, 7, 8};
        List<int[][]> all = new ArrayList<>();
        PriorityQueue< State > pq = new PriorityQueue<>( new Comparator<State>(){
            public int compare( State o1 , State o2){
                return o1.f - o2.f ;
            }
        }) ;
        State ss = new State( 0 , start , -1 , -1 , end , 0 , 0 ) ;
        pq.add(ss);
        int arr1[]= { 1, 0, -1 , 0};
        int arr2[] = { 0 , 1, 0 , -1};
        while(pq.isEmpty() == false){
            State currS = pq.peek() ;
            pq.remove() ;
            if (Arrays.deepEquals(currS.grid , end)) {
                System.out.println(currS.g);
                return true;
            }
            for(int i = 0 ; i < 4 ; i++){
                int nx = currS.x + arr1[i] ;
                int ny = currS.y + arr2[i] ;
                if(nx >= 0 && ny >= 0 && nx < start.length && ny < start.length && ( nx != currS.px || nx!= currS.py)){
                    int temp = start[currS.x][currS.y] ; 
                    start[currS.x][currS.y] = start[nx][ny] ; 
                    start[nx][ny] = temp ;
                    pq.add( new State( 1 , start , currS.x , currS.y , end , nx , ny));
                }
            }
        }

        return false ;

    }
    


    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] start = new int[3][3];
        int[][] end = new int[3][3];
        
        System.out.println("initial state");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start[i][j] = scanner.nextInt();
            }
        }
        
        System.out.println("final state");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                end[i][j] = scanner.nextInt();
            }
        }
        
        if (check(start, end)) {
            System.out.println(" Possible");
        } else {
            System.out.println("Not Possible");
        }
    }
}
