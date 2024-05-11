import java.util.* ;
class Ps4_primsAlgo{
    public static void main( String args[]){
        CityMap obj = new Network();
        int Nodes[][] = new int[][]{
            { 0 , 2 , 5 , 1},
            {  2 , 0 , 0 , 3},
            { 5 , 0 , 0 , 4} ,
            { 1 , 3, 4, 0}
        };
        // int Nodes[][] = new int[][]{
        //     { 0 , 3 , 1 },
        //     { 3 ,0 ,2 },
        //     { 1 ,2, 0 } ,
        // };
        obj.primsAlgo( Nodes);
    }
}


class Network{
    public ArrayList<Edge> primsAlgo(int graph[][]){
        int isVisited[] = new int[ graph.length] ;
        PriorityQueue<Edge> pq = new PriorityQueue<>(new ComparatorEdge());
        ArrayList<Edge> ans = new ArrayList<>() ;
        pq.add( new Edge());
        while(pq.isEmpty() == false ){
            Edge currNode = pq.peek() ;
            int flag = 0 ;
            
            while( isVisited[currNode.currn] == 1){
                pq.remove() ;
                if( pq.isEmpty() == false){
                    currNode = pq.peek() ;
                }
                else{
                    flag = 1 ;
                    break ;
                }
                
            }
            
            if(flag == 1){
                break ;
            }
            pq.remove() ;

            int r = currNode.currn ;
            isVisited[r] = 1 ;
            ans.add( new Edge( currNode.currn , currNode.par , currNode.cost) ) ;
            for(int c = 0  ;c < graph.length ; c++){
                if(isVisited[c] == 0 && graph[r][c]!= 0 ){
                    pq.add( new Edge(c , r , graph[r][c] )) ;
                }
            }

        }
        for(Edge ele : ans){
            System.out.println( ele.currn + " " + ele.par + " " + ele.cost) ;
        }

        System.out.println( ans.size());
        return ans ;

    }

    class Edge{
        int par ;
        int currn ;
        int cost ;
        Edge(){
            par = -1 ;
            currn = 0 ;
            cost = 0 ; 
        }
        Edge(  int currn1 , int par1 , int cost1){
            par = par1 ;
            currn = currn1 ;
            cost = cost1 ; 
        }
    }

    class ComparatorEdge implements Comparator<Edge>{
        public int compare(Edge obj1 , Edge obj2){
            return obj1.cost - obj2.cost ;
        }
    }
}

