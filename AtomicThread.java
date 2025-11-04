// class Main{
//     public static void main(String[] args){
//         int temp = 0;
//         for(int i =0;i<4;i++){
//               Thread th = new Thread(()->{
//                 temp=i;
//                 System.out.println(temp);
                
//             });
//             th.start();  
//         }
//     }
// }

// ERROR!
// /tmp/EEkmNE6SnJ/Main.java:7: error: local variables referenced from a lambda expression must be final or effectively final
// ERROR!
//                 temp=i;
//                      ^
// /tmp/EEkmNE6SnJ/Main.java:7: error: local variables referenced from a lambda expression must be final or effectively final
//                 temp=i;
//                 ^
// ERROR!
// /tmp/EEkmNE6SnJ/Main.java:8: error: local variables referenced from a lambda expression must be final or effectively final
//                 System.out.println(temp);
//                                   ^
// 3 errors
// ERROR!
// error: compilation failed




// class Main{
//     public static void main(String[] args){
//         int temp = 0;
//         for(int i =0;i<4;i++){
//             final int copy=i ;
//               Thread th = new Thread(()->{
//                 temp=copy;
//                 System.out.println(temp);
                
//             });
//             th.start();  
//         }
//     }
// }

// ERROR!
// /tmp/9C4J1ypP3d/Main.java:40: error: local variables referenced from a lambda expression must be final or effectively final
//                 temp=copy;
//                 ^
// ERROR!
// /tmp/9C4J1ypP3d/Main.java:41: error: local variables referenced from a lambda expression must be final or effectively final
//                 System.out.println(temp);
//                                   ^
// 2 errors
// ERROR!
// error: compilation failed


// import java.util.concurrent.atomic.*;
// class Main{
//     public static void main(String[] args){
//         AtomicInteger temp = new AtomicInteger(0);
//         for(int i =0;i<4;i++){
//             final int copy=i ;
//               Thread th = new Thread(()->{
//                 temp=copy;
//                 System.out.println(temp);
                
//             });
//             th.start();  
//         }
//     }
// }



// ERROR!
// /tmp/Yk83rQXJlQ/Main.java:69: error: incompatible types: int cannot be converted to AtomicInteger
//                 temp=copy;
//                      ^
// 1 error
// ERROR!
// error: compilation failed

// === Code Exited With Errors ===



// import java.util.concurrent.atomic.*;
// class Main{
//     public static void main(String[] args){
//         AtomicInteger temp = new AtomicInteger(0);
//         for(int i =0;i<4;i++){
//             final int copy=i ;
//               Thread th = new Thread(()->{
//                 temp=new AtomicInteger(copy);
//                 System.out.println(temp);
                
//             });
//             th.start();  
//         }
//     }
// }


// ERROR!
// /tmp/7mQEatQZUT/Main.java:99: error: local variables referenced from a lambda expression must be final or effectively final
//                 temp=new AtomicInteger(copy);
//                 ^
// ERROR!
// /tmp/7mQEatQZUT/Main.java:100: error: local variables referenced from a lambda expression must be final or effectively final
//                 System.out.println(temp);
//                                   ^
// 2 errors
// ERROR!
// error: compilation failed




// import java.util.concurrent.atomic.AtomicInteger;
// import java.util.concurrent.atomic.AtomicReference;
// class Main{
//     public static void main(String[] args){
//         AtomicReference<AtomicInteger> temp = new AtomicReference<>(new AtomicInteger(0));
//         for(int i =0;i<4;i++){
//             final int copy=i ;
//               Thread th = new Thread(()->{
//                 temp.set(new AtomicInteger(copy));
//                 System.out.println(temp);
                
//             });
//             th.start();  
//         }
//     }
// }










import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
class Main{
    static{
        
    }
    public static void om(int it){
              AtomicReference<AtomicInteger> temp = new AtomicReference<>(new AtomicInteger(0));
        for(int i =0;i<4;i++){
            final int copy=i ;
              Thread th = new Thread(()->{
                temp.set(new AtomicInteger(copy));
                System.out.println(temp);
                
            });
            th.start();  
        }
    }
    public static void main(String[] args){
        om(4);
    }
}
