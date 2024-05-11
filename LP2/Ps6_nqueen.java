import java.util.* ;

class ps6_nqueen{
    public static void main( String args[]){


        ArrayList<ArrayList<ArrayList<Integer>>> soln1= BnBoundMethode( 5);
        System.out.println("No of Solns exists : "+ soln1.size()) ;
        printSolns(soln1) ;

        ArrayList<ArrayList<ArrayList<Integer>>> soln2= BacktrackingMethode( 4);
        System.out.println("No of Solns exists : "+ soln2.size()) ;
        printSolns(soln2) ;
        
    }

    public static void printSolns(ArrayList<ArrayList<ArrayList<Integer>>> soln){
        int tl = soln.size() ;
        if( tl == 0){
            System.out.println("Soln doesnt exists") ;
            return ;
        }
        
        int ml = soln.get(0).size() ;
        for( int i = 0 ; i < tl ; i++){
            System.out.println("soln no "+ (i + 1)+ " : ");
            for(int r = 0 ; r < ml ; r++ ){
                for(int c = 0 ; c < ml ; c++){
                    System.out.print( soln.get(i).get(r).get(c) == 0 ? ". " : "Q ") ;
                }
                System.out.println() ;
            }
            System.out.println();
            System.out.println();
        }
    }


    public static ArrayList<ArrayList<ArrayList<Integer>>> BacktrackingMethode( int n){
        ArrayList<ArrayList<Integer>>currM = new ArrayList<>(n) ;
        for(int i = 0 ; i < n ; i++){
            ArrayList<Integer> temp = new ArrayList<>(n) ;
            for(int j = 0 ; j < n ; j++){
                temp.add(0);
            }    
            currM.add(temp) ;
        }
        ArrayList<ArrayList<ArrayList<Integer>>> ans = new ArrayList<>() ;
        
        helperOfBacktracking( 0 , ans , currM ) ;

    
        return ans;
    }

    public static void helperOfBacktracking( int currRow , ArrayList<ArrayList<ArrayList<Integer>>> ans , ArrayList<ArrayList<Integer>> currM )
    {
        int n = currM.size() ;
        if (currRow == n){
            ArrayList<ArrayList<Integer>> copyCurrM = new ArrayList<>();
            for (ArrayList<Integer> row : currM) {
                copyCurrM.add(new ArrayList<>(row));
            }
            ans.add(copyCurrM);
        }
        for(int i = 0 ; i < n ; i++){
            if( isSafe( currRow , i , currM) == true){
                currM.get( currRow).set( i , 1) ;
                helperOfBacktracking( currRow+1 , ans , currM ) ;
                currM.get(currRow).set( i , 0 );
            }
        }
        return ;
    }

    public static boolean isSafe( int curri , int currj , ArrayList<ArrayList<Integer>> currM){

        int n = currM.size() ;
        // check for column
        for(int i = 0 ; i < n ; i++){
            if( i != curri ){
                if( currM.get( i ).get( currj) == 1){
                    return false ;
                }
            }
        }

        // check got pdig
        int i = curri -1 ;
        int j = currj -1 ;
        while( i >= 0 && j >= 0 ){
            if(currM.get( i ).get( j) == 1){
                return false ;
            }
            i-- ;
            j-- ;
        }

        i = curri -1 ;
        j = currj + 1 ;
        while( i >= 0 && j < n ){
            if(currM.get( i ).get( j) == 1){
                return false ;
            }
            i-- ;
            j ++ ;
        }

        return true ;

    }


    public static ArrayList<ArrayList<ArrayList<Integer>>> BnBoundMethode( int n){
        ArrayList<ArrayList<Integer>>currM = new ArrayList<>(n) ;
        for(int i = 0 ; i < n ; i++){
            ArrayList<Integer> temp = new ArrayList<>(n) ;
            for(int j = 0 ; j < n ; j++){
                temp.add(0);
            }    
            currM.add(temp) ;
        }
        ArrayList<ArrayList<ArrayList<Integer>>> ans = new ArrayList<>() ;
        ArrayList<Integer> pdarr = new ArrayList<>(2*n+1) ;
        ArrayList<Integer> ndarr = new ArrayList<>( (2* n) +1 ) ;
        ArrayList<Integer> carr = new ArrayList<>(n) ;
        for(int i = 0 ; i < (n*2 + 1) ; i++){
            pdarr.add(0);
            ndarr.add(0);
        }
        for(int i = 0 ; i < n ; i++){
            carr.add(0);
        }
        
        helperOfBnBound( 0 , ans , currM  , pdarr , ndarr , carr) ;

    
        return ans;
    }

    public static void helperOfBnBound( int currRow , ArrayList<ArrayList<ArrayList<Integer>>> ans , ArrayList<ArrayList<Integer>> currM  , ArrayList<Integer>  pdarr , ArrayList<Integer>  ndarr ,  ArrayList<Integer> carr)
    {
        int n = currM.size() ;
        if (currRow == n){
            ArrayList<ArrayList<Integer>> copyCurrM = new ArrayList<>();
            for (ArrayList<Integer> row : currM) {
                copyCurrM.add(new ArrayList<>(row));
            }
            ans.add(copyCurrM);
        }
        for(int i = 0 ; i < n ; i++){
            if( isSafe2( currRow , i , pdarr, ndarr , carr ) == true){
                currM.get( currRow).set( i , 1) ;
                carr.set( i , 1 );
                pdarr.set((currRow + i) , 1 );
                ndarr.set((currRow - i+n) , 1 );
                helperOfBnBound( currRow+1 , ans , currM ,  pdarr, ndarr , carr) ;
                currM.get(currRow).set( i , 0 );
                carr.set( i , 0 );
                pdarr.set((currRow + i) , 0 );
                ndarr.set((currRow - i+n) , 0 );
            }
        }
        return ;
    }

    public static boolean isSafe2( int curri , int currj , ArrayList<Integer>  pdarr , ArrayList<Integer>  ndarr ,  ArrayList<Integer> carr ){
        int n = carr.size() ;
        if( pdarr.get(curri + currj) == 1 ||  ndarr.get(curri - currj + n) == 1 || carr.get(currj) == 1   ){
            return false ;
        }
        return true ;
    }

}

