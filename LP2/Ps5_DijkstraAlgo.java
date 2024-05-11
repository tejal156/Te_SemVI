import java.util.* ;
class Ps5_DijkstraAlgo{
    public static void main(String args[]){
        System.out.println("Hii");
        ArrayList< ArrayList<Node>> graph= new ArrayList<>() ;
        ArrayList<Node>temp1 = new ArrayList() ;

        int v = 4 ;
        for(int i = 0 ; i < v ; i++ ){
            graph.add( new ArrayList<Node>()) ;
        }
        graph.get(0).add( new Node( 1 , 1 )) ;
        graph.get(0).add( new Node( 3 , 2 )) ;
        graph.get(1).add( new Node( 1 , 0 )) ;
        graph.get(1).add( new Node( 1 , 2 )) ;
        graph.get(1).add( new Node( 3 , 3 )) ;
        graph.get(2).add( new Node( 3 , 0 )) ;
        graph.get(2).add( new Node( 1 , 1 )) ;
        graph.get(2).add( new Node( 6 , 3 )) ;
        graph.get(3).add( new Node( 3 , 1 )) ;
        graph.get(3).add( new Node( 6 , 2 )) ;
        DijkstrasAlgo obj = new DijkstrasAlgo() ;
        obj.executeAlgo( graph ) ;
        // store node then  d
    }
    // 8 min : logic
    static class Node{
            int d ;
            int node ;
            Node(int d , int node){
                this.d = d ;
                this.node = node ;
            }
        }

    static class DijkstrasAlgo{



        public void executeAlgo(ArrayList< ArrayList<Node>> graph){
            PriorityQueue<Node> pq = new PriorityQueue<>( new Comparator<Node>(){
                public int compare(Node o1 , Node o2){
                    if( o1.d != o2.d){
                        return o1.d-o2.d ;
                    }
                    else{
                        return o1.node-o2.node ;
                    }
                }
            });


            int dtrackingArr[] = new int[graph.size()] ;
            for(int i = 0 ; i < graph.size() ; i++){
                dtrackingArr[i]= Integer.MAX_VALUE ;
            }
            pq.add( new Node(0 , 0 ));
            dtrackingArr[0] = 0 ;
            while(pq.isEmpty()== false ){
                Node currN = pq.peek() ;
                pq.remove() ;
                if( currN.d <= dtrackingArr[currN.node] ){
                    for(int i = 0 ; i < graph.get(currN.node).size() ;i++){
                        if(graph.get(currN.node).get(i).d + currN.d < dtrackingArr[graph.get(currN.node).get(i).node] ){
                            dtrackingArr[graph.get(currN.node).get(i).node] = graph.get(currN.node).get(i).d+currN.d ;
                            pq.add(new Node( graph.get(currN.node).get(i).d+currN.d , graph.get(currN.node).get(i).node ));
                        }
                    }
                }
            }

            for(int i = 0 ; i < graph.size() ; i++){
                // dtrackingArr[i]= Integer.MAX_VALUE ;
                System.out.println(i + " " +  dtrackingArr[i] + " ");
            }

        }
    }
}