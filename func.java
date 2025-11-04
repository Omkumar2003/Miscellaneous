// Thread start() method is like a condom ..........can only be use once ..if u try to make that i throws exception
//to use thread multiple time ....u can to create it inside the loop then start it ...in every iteration it will generate the new thread



class Main{
    public static void main(String[] args){
        A a= new A(()->{
            System.out.println("vvvvvvv");
        });
        
        a.start();
    }
}

interface B{
    void om();
}
class A{
    B b;
    A(B b){
        this.b=b;
    }
    public void start(){
        b.om();
    }
}
