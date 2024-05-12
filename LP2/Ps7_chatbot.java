
import java.util.* ; 
class Ps7_chatbot{

    public static void main(String args[]){
        System.out.println("Hii");
        Chatbot obj = new Chatbot() ;
        Scanner sc = new Scanner(System.in) ;
        while(true){
            System.out.print("user    : ");
            String q = sc.nextLine();
            
            if( q.trim().toLowerCase().contains("bye")){
                System.out.println("chatbot : "+obj.endChatting());
                break ;
            }
            else{
                System.out.println("chatbot : "+obj.doChating(q) );
            }
        }
    };

    public static class Chatbot{
        HashMap< String , ArrayList<String>> content;
        int n ;
        Chatbot(){
            content = new HashMap<>();
            n = 3 ;
            content.put( "screen" , new ArrayList( Arrays.asList("6 inches" , "6$")));
            content.put( "battery" , new ArrayList( Arrays.asList("4000 mAh" , "8$")));
            content.put( "ram" , new ArrayList( Arrays.asList("4 gb" , "9$")));
            content.put( "stoarage" , new ArrayList( Arrays.asList("8 gb" , "10$")));
            content.put( "cover" , new ArrayList( Arrays.asList("6 inches" , "2$")));
            content.put( "sim" , new ArrayList( Arrays.asList("nano" , "3$")));
            content.put( "context" , new ArrayList( Arrays.asList("None" )));
        }

        public String doChating(String q){
            boolean isf = false ;
            boolean isD = false ;
            String currD = "" ;
            String currF = "" ;

            String ans ;
            
            q = q.trim().toLowerCase();
            if(q.contains("context")){
                ans = "do not understand";
                return ans ;
            }
            if(q.contains("hii")){
                ans = "Hello" ;
                return ans ;
            }

            if(q.contains("size")){
                isf = true ;
                currF = "size" ;
            }
            if(q.contains("price")){
                isf = true ;
                currF = "price" ;
            }

            for( String key : content.keySet()){
                if( q.contains(key)){
                    isD = true ;
                    currD = key.toLowerCase() ;
                    content.put("context" , new ArrayList<String>( Arrays.asList(key)) );
                    break ;
                }
            }
            
            if(! content.get("context").get(0).contains("None")){
                currD = content.get("context").get(0) ;
                isD = true ;
            }
            if(isD){
                if(isf && isD){
                    if(currF.equals("size")){
                        ans = currD+ "'s size is " +content.get(currD).get(0) ;
                    }
                    else{
                        ans = currD+ "'s price is "+ content.get(currD).get(1) ;
                    }
                }
                else{
                    ans = currD+ "'s price is "+ content.get(currD).get(1)+ " and size is " +content.get(currD).get(0) ;
                }
            }
            else{
                ans = "Sorry I do not understand your Question. Could you please rephrase it?" ;

            }
            return ans ;
        }

        public String endChatting(){
            content.put("context" ,new ArrayList<String>( Arrays.asList("None")) );
            return  "Bye" ;
        }
    };
}