public class FizzBuzz {
 
 public static void main(String[] args) {
  int num = 100;
  fizzBuzzSB(num);
 }
 
 private static void fizzBuzzSB(int num) {
  long t1 = System.currentTimeMillis();
   
  StringBuilder sb = new StringBuilder(num);
  for (int i = 1; i <= num; i++) {
   if (i % 15 == 0) {
    sb.append("FizzBuzz\n");
   } else if (i % 3 == 0) {
    sb.append("Fizz\n");
   } else if (i % 5 == 0) {
    sb.append("Buzz\n");
   } else {
    sb.append(i+"\n");
   }
  }
  System.out.println(sb);
 
  System.out.println("time taken with SB: "+ (System.currentTimeMillis() - t1));
 }
}