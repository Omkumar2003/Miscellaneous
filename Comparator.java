import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.ArrayList;


class Main{
    public static void main(String[] args){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        for(int i=0;i<10;i++){
            hm.put(i*i,(int)(Math.random()*100));
        }
        
        for(Map.Entry<Integer,Integer> m: hm.entrySet()){
            System.out.println(m.getKey()+"----->"+m.getValue());
        }
        
        
        
        ArrayList<Map.Entry<Integer,Integer>> ar= new ArrayList<>(hm.entrySet());
        ar.sort(new B());
        System.out.println("=============");
        for(Map.Entry<Integer,Integer> m: ar){
            System.out.println(m.getKey()+"----->"+m.getValue());
        }
    }
}


class B implements Comparator<Map.Entry<Integer,Integer>>{
    public int compare(Map.Entry<Integer,Integer> a,Map.Entry<Integer,Integer> b){
        Integer temp = a.getValue()-b.getValue();
        if(temp==0){
            return a.getKey()-b.getKey();
        }
        return temp;
    }
}
