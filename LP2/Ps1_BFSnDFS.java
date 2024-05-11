import java.util.* ;
class Ps1_BFSnDFS{
    public static void main( String args[]){

        CityMap obj = new CityMap();

        LinkedHashMap<String , ArrayList<String>>hm = new LinkedHashMap<>() ;
        // Replace alphabates by city name in exam
        hm.put("A" , new ArrayList<>(List.of( "B" , "C" , "D" )) );
        hm.put("B" , new ArrayList<>(List.of( "A")) );
        hm.put("C" , new ArrayList<>(List.of("E" , "D","A" )) );
        hm.put("D" , new ArrayList<>(List.of("E" , "C" , "A","F" )) );
        hm.put("E" , new ArrayList<>(List.of(  "D" , "G" , "C")) );
        hm.put("F" , new ArrayList<>(List.of(  "G" , "D" )) );
        hm.put("G" , new ArrayList<>(List.of(  "F" , "E" , "H" )) );
        hm.put("H" , new ArrayList<>(List.of(  "G" )) );

        obj.createGraph(hm) ;
        obj.printGraph();

        obj.bfs("F") ;
        obj.bfs("Z") ;
        obj.dfs("F") ;
        obj.dfs("Z") ;

        // dfs : perform searching using dfs
        // dfsTraversal : perform traversal using dfs : do not implement in exam
        // bfs : perform searching using bfs
        // bfsTraversal : perform traversal using bfs : do not implement in exam
        // print graph
        

    }
}


class CityMap{
    ArrayList<ArrayList<Integer>> edges ;
    HashMap<String , Integer> cityMapping ;
    HashMap< Integer , String> numMapping ;
    
    CityMap(){
        edges = new ArrayList() ;
        cityMapping = new HashMap<>() ;
        numMapping = new HashMap<>() ;
    }

    public void printGraph(){
        System.out.println("Graph : ");
        for(int i = 0 ; i < edges.size() ; i++){
            System.out.print( i + ": ") ;
            for(int j = 0 ; j < edges.get(i).size() ; j++ ){
                System.out.print(edges.get(i).get(j) + " ") ;
            }
            System.out.println() ;
        }
        System.out.println();
    }

    public void createGraph(LinkedHashMap< String , ArrayList<String>> cityNames){
        Set<String>keys = cityNames.keySet() ;
        int no = 0 ; 
        for(String key : keys){
            cityMapping.put(key , no) ;
            numMapping.put(no , key) ;

            no ++ ;
        }
        for(String key : keys){
            ArrayList<String> values = cityNames.get(key);
            ArrayList<Integer> temp = new ArrayList<>() ;
            for(String val : values){
                temp.add( cityMapping.get(val) ) ;
            }
            edges.add(temp) ;
        }

    }

    public void dfsTraversal(){
        int isVisited[] = new int[edges.size()] ;
        dfsTraversalUtil(0 , isVisited , 0 ) ;
    }
    public void dfsTraversalUtil( int n ,int isVisited[] , int level){
        isVisited[n] = 1 ;
        System.out.println(" city no: " + n +" city name: " + numMapping.get(n)  );
        for(int i = 0 ; i < edges.get(n).size() ; i++){
            if( isVisited[ edges.get(n).get(i)] == 0 ){
                DFSutil(edges.get(n).get(i) , isVisited , level + 1 ) ;
            }
        }
    }

    public void dfs(String target ){
        int isVisited[] = new int[edges.size()] ;
        System.out.println( "Target  : "+ target  );
        System.out.println( "DFS Path :  "  );
        int target1 = cityMapping.containsKey(target) == false ? -1 : cityMapping.get(target); 
        boolean ans = DFSutil(0 , isVisited , target1) ;
        System.out.println( "end  "  );
        if(ans == true){
            System.out.println("target is present ");
        }
        else{
            System.out.println("target is not present ");
        }
        System.out.println();

    }
    public boolean DFSutil( int n ,int isVisited[]  ,int target){
        isVisited[n] = 1 ;
        boolean ans = false ;
        if( n == target){
            System.out.print( numMapping.get(n) + " => "  );
            return true ; 
        }
        System.out.print( numMapping.get(n) + " => "  );
        for(int i = 0 ; i < edges.get(n).size() ; i++){
            if( isVisited[ edges.get(n).get(i)] == 0 ){
                ans = ans || DFSutil(edges.get(n).get(i) , isVisited , target) ;
                
                if(ans == true){
                    // System.out.print( numMapping.get(n) + " => "  );
                    return true ;
                }
                System.out.print( numMapping.get(n) + " => "  );
            }
        }
        return false ;
    }

    public  void bfsTraversal(){
        int isVisited[] = new int[edges.size()] ;
        Queue<Integer> q = new LinkedList<>() ;
        q.add(0) ;
        bfsTraversalutil(0 , isVisited , 0 , q ) ;
    }
    public void bfsTraversalutil( int n ,int isVisited[] , int level , Queue<Integer> q){
        for(int i = 0 ; i < edges.get(n).size() ; i++){
            if( isVisited[edges.get(n).get(i) ] == 0){
                q.add(edges.get(n).get(i)) ;
            }
        }
        if( q.isEmpty() == false){
            int temp = q.remove() ;
            if(isVisited[temp] == 0){
                isVisited[temp] = 1 ;
                System.out.println(" city no: " + temp +" city name: " + numMapping.get(temp));

                
            }
            bfsTraversalutil( temp , isVisited , level+1 , q) ;
 
        }
    }

    public  void bfs(String target){
        int isVisited[] = new int[edges.size()] ;
        Queue<Integer> q = new LinkedList<>() ;
        q.add(0) ;
        q.add(-2);

        int target1 = cityMapping.containsKey(target) == false ? -1 : cityMapping.get(target); 
        System.out.println( "Target  : "+ target  );
        System.out.println( "BFS Path :  "  );
        // System.out.println();
        System.out.print("level " + 0 + " : "); 
        boolean ans = bfsutil(0 , isVisited ,1 , q ,  target1) ;
        System.out.println();
        if(ans == true){
            System.out.println("target is present ");
        }
        else{
            System.out.println("target is not present ");
        }
        System.out.println();
    }
    public boolean bfsutil( int n ,int isVisited[] , int level , Queue<Integer> q, int target){
        if( n == target){
            return true ;
        }
        if(n != -2){
        for(int i = 0 ; i < edges.get(n).size() ; i++){
            if( isVisited[edges.get(n).get(i) ] == 0){
                q.add(edges.get(n).get(i)) ;
            }
        }
        }
        if(q.size() == 1 && q.peek() == -2){
            return false ;
        }
        else if( q.isEmpty() == false){
            int temp = q.remove() ;
            if(temp == -2){
                q.add(-2);
                System.out.println();
                System.out.print("level " + level + " : ");                 
                return bfsutil( temp , isVisited , level+1 , q , target) ; 

            }
            else{
                if(isVisited[temp] == 0){
                    isVisited[temp] = 1 ;
                    System.out.print(" no: " + temp +" name: " + numMapping.get(temp) );   
                }   ;
                return bfsutil( temp , isVisited , level , q , target) ; 
                             
            }
                 
        }
        else{
            return false ;
        }
    }
}

        // tc 2
        // ArrayList<Integer>temp1 = new ArrayList<>(List.of( 1,2 ));
        // ArrayList<Integer>temp2 = new ArrayList<>(List.of( 0 , 2));
        // ArrayList<Integer>temp3 = new ArrayList<>(List.of( 0 , 3));
        // ArrayList<Integer>temp4 = new ArrayList<>(List.of( 1 ,2 ));
        // obj.edges.add(temp1) ;
        // obj.edges.add(temp2) ;
        // obj.edges.add(temp3) ;
        // obj.edges.add(temp4) ;
        // obj.dfs() ;
        // tc 1
        // hm.put("Dapoli" , new ArrayList<>(List.of( "Khed" , "Mahad" )) );
        // hm.put("Khed" , new ArrayList<>(List.of( "Pune" , "Mahad" , "Dapoli")) );
        // hm.put("Mahad" , new ArrayList<>(List.of( "Pune" , "Khed" , "Dapoli" )) );
        // hm.put("Pune" , new ArrayList<>(List.of( "Khed" , "Mahad" )) );