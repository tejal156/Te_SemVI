import java.util.* ;
class Ps2_Astar{

    public static void main( String args[]){
        // System.out.println("Hii");
        int m[][] = {
            {0 , 0 , 0},
            {0 , 0 , 1},
            {0 , 0 , 0}
        };
        Astar obj = new Astar() ;
        obj.executeAstar( m , 0 , 0 , 2 , 2) ;
    }

    public static class Astar{
        class Path{
            int x , y , g , h, f;
            Path par ;
            Path(int x , int y , int g , int ex ,int ey , Path par ){
                this.x = x ;
                this.y = y ;
                this.g = g ;
                this.h = h ;
                this.par = par ;
                h =(int) Math.sqrt( Math.pow(ex - x , 2 ) + Math.pow(ey-y , 2 )) ;
                f = g + h ;
            }
            Path(){
                x = y = g = h = f =-1 ;
            }
        }

        public void executeAstar( int[][] gMatrix ,int sx , int sy , int ex , int ey ){
            int nr = gMatrix.length ;
            int nc = gMatrix[0].length ;
            Path isVisited[][] = new Path[gMatrix.length][gMatrix[0].length];
            // for(int i = 0 ; i < nr ; i++){
            //     for( int j = 0 ; j < nc ; j++){
            //         isV
            //     }
            // }

            PriorityQueue<Path>pq = new PriorityQueue<>( new Comparator<Path>(){
                public int compare(Path o1 , Path o2){
                    if(o1.f != o2.f){
                        return o1.f - o2.f ;
                    }
                    else if(o1.g != o2.g){
                        return o1.g - o2.g ;
                    }
                    else if(o1.x != o2.x){
                        return o1.x - o2.x ;
                    }
                    else{
                        return o1.y - o2.y ;
                    }

                }
            } );
            

            pq.add( new Path( sx , sy , 0 , ex , ey, new Path())   ) ;
            isVisited[sx][sy] = new Path() ;
            
            
            while(pq.isEmpty() == false){
                Path currp = pq.peek() ;
                // System.out.println(currp.x + " "+ currp.y) ;
                pq.remove() ;
                if(currp.x == ex && currp.y == ey){
                    // backtracking logic
                    System.out.println("reached to end");
                    Path bp = currp ;
                    System.out.println( "x y f g h") ;
                    while(bp.x != -1){
                        
                        System.out.println(bp.x + " " + bp.y+ " "+ bp.f + " " + bp.g + " "+ bp.h) ;
                        bp = bp.par ;
                    }
                    return ;
                }
                int nx[] = {1 , 0 , -1 , 0} ;
                int ny[] = {0 , 1 , 0 , -1} ;
                for(int i = 0 ; i < 4 ; i++){
                    int ni = currp.x + nx[i] ;
                    int nj = currp.y + ny[i] ;
                    if( ni < nr && ni >= 0 && nj < nc && nj >= 0 && gMatrix[ni][nj]!= 1){
                        if( isVisited[ni][nj] == null ){
                            Path temp1 = new Path(ni , nj , currp.g+1 , ex , ey , currp ) ;
                            isVisited[ni][nj] = temp1 ;
                            pq.add(temp1) ;
                        }
                        else if(isVisited[ni][nj].g > currp.g+1){
                            Path temp1 = new Path(ni , nj , currp.g+1 , ex , ey , currp ) ;
                            isVisited[ni][nj] = temp1 ;
                            pq.add(temp1) ;
                        }

                    }
                }

            }

        }
    }

    
}

