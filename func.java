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
