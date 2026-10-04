public class ThreeIntegers {
    
    public static void checkEquality(String arg1 , String arg2, String arg3) { 
        int integer1 = Integer.parseInt(arg1);
        int integer2 = Integer.parseInt(arg2);
        int integer3 = Integer.parseInt(arg3);

        if (integer1 == integer2 && integer2 == integer3) {
            StdOut.println("Equal");
        } else {
            StdOut.println("Not Equal");
        }
    }


    public static void main(String[] args) {
        checkEquality(args[0], args[1], args[2]);
    }
}