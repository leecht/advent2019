import java.io.*;
import java.util.ArrayList;

public class MyClass {
    public static void main(String args[]) {
        int nPasswordStart = 264360;
        int nPasswordEnd = 746325;
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int nCount = 0;
        
        for (int nTest = nPasswordStart; nTest <= nPasswordEnd; nTest++) {
            String strTest = String.valueOf(nTest);
            boolean bContinue = false;
            // Check for adjacent digits
            String strPair1 = strTest.substring(0, 2);
            String strPair2 = strTest.substring(1, 3);
            String strPair3 = strTest.substring(2, 4);
            String strPair4 = strTest.substring(3, 5);
            String strPair5 = strTest.substring(4, 6);
            if (strPair1.startsWith(strPair1.substring(1))) {
                bContinue = true;
            }
            if (strPair2.startsWith(strPair2.substring(1))) {
                bContinue = true;
            }
            if (strPair3.startsWith(strPair3.substring(1))) {
                bContinue = true;
            }
            if (strPair4.startsWith(strPair4.substring(1))) {
                bContinue = true;
            }
            if (strPair5.startsWith(strPair5.substring(1))) {
                bContinue = true;
            }
            if (bContinue) {
                try {
                    n1 = Integer.parseInt(strTest.substring(0,1));
                    n2 = Integer.parseInt(strTest.substring(1,2));
                    n3 = Integer.parseInt(strTest.substring(2,3));
                    n4 = Integer.parseInt(strTest.substring(3,4));
                    n5 = Integer.parseInt(strTest.substring(4,5));
                    n6 = Integer.parseInt(strTest.substring(5,6));
                } catch (NumberFormatException e) {
                    System.out.println("Caught NumberFormatException");
                    bContinue = false;
                }
                if (bContinue && n2 < n1) {
                    bContinue = false;
                }
                if (bContinue && n3 < n2) {
                    bContinue = false;
                }
                if (bContinue && n4 < n3) {
                    bContinue = false;
                }
                if (bContinue && n5 < n4) {
                    bContinue = false;
                }
                if (bContinue && n6 < n5) {
                    bContinue = false;
                }
                if (bContinue) {
                    nCount++;
                }
            }
        }
        System.out.println("Counted " + nCount + " possibilities.");
    }
}
