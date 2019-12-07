import java.io.*;
import java.util.ArrayList;

public class Wire {
    private int nXmax = 0;
    private int nXmin = 0;
    private int nYmax = 0;
    private int nYmin = 0;
    private int nXnow = 0;
    private int nYnow = 0;
    private Range range;
    private ArrayList<Xwire> Xwires = new ArrayList<Xwire>();
    private ArrayList<Ywire> Ywires = new ArrayList<Ywire>();

    public Wire () {
        
    }
    
    public ArrayList<Xwire> getXwires() {
        return this.Xwires;
    }

    public ArrayList<Ywire> getYwires() {
        return this.Ywires;
    }
    
    public Range getLimits(String[] strWire, int nOffsetX, int nOffsetY) {
        range = new Range();
        try {
            for (int i = 0; i < strWire.length; i++) {
//                    System.out.println(strWire1[i]);
                if (strWire[i].startsWith("R")) {
                    // x increasing
                    nXnow = nXnow + Integer.parseInt(strWire[i].substring(1));
                } else if (strWire[i].startsWith("L")) {
                    // x decreasing
                    nXnow = nXnow - Integer.parseInt(strWire[i].substring(1));
                } else if (strWire[i].startsWith("U")) {
                    // y increasing
                    nYnow = nYnow + Integer.parseInt(strWire[i].substring(1));
                } else if (strWire[i].startsWith("D")) {
                    // y decreasing
                    nYnow = nYnow - Integer.parseInt(strWire[i].substring(1));
                } 
                if (nXnow > nXmax) {
                    nXmax = nXnow;
                }
                if (nXnow < nXmin) {
                    nXmin = nXnow;
                }
                if (nYnow > nYmax) {
                    nYmax = nYnow;
                }
                if (nYnow < nYmin) {
                    nYmin = nYnow;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException");
            return null;    
        }
//            System.out.println("X range: " + nXmin + " to " + nXmax);
//            System.out.println("Y range: " + nYmin + " to " + nYmax);
        range.setRange(nXmin, nXmax, nYmin, nYmax);
        return range;
    }
    
    public boolean plotRoute(String[] strWire, int nOffsetX, int nOffsetY, int[][] map) {
        nXnow = nOffsetX;
        nYnow = nOffsetY;
        try {
            for (int i = 0; i < strWire.length; i++) {
//                System.out.println(strWire1[i]);
//                System.out.println("Map point: [" + nXnow + "][" + nYnow + "]");
                if (strWire[i].startsWith("R")) {
                    // x increasing
                    for (int nDistance = 1; nDistance <= Integer.parseInt(strWire[i].substring(1)); nDistance++) {
                        map[++nXnow][nYnow] = 1;
                    }
                } else if (strWire[i].startsWith("L")) {
                    // x decreasing
                    for (int nDistance = 1; nDistance <= Integer.parseInt(strWire[i].substring(1)); nDistance++) {
                        map[--nXnow][nYnow] = 1;
                    }
                } else if (strWire[i].startsWith("U")) {
                    // y increasing
                    for (int nDistance = 1; nDistance <= Integer.parseInt(strWire[i].substring(1)); nDistance++) {
                        map[nXnow][++nYnow] = 1;
                    }
                } else if (strWire[i].startsWith("D")) {
                    // y decreasing
                    for (int nDistance = 1; nDistance <= Integer.parseInt(strWire[i].substring(1)); nDistance++) {
                        map[nXnow][--nYnow] = 1;
                    }
                } 
            }
//            System.out.println("Map ends: [" + nXnow + "][" + nYnow + "]");
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException");
            return false;
        }
        return true;
    }
    
    public boolean collectWires(String[] strWire, int nOffsetX, int nOffsetY) {
        nXnow = nOffsetX;
        nYnow = nOffsetY;
        for (int i = 0; i < strWire.length; i++) {
//                    System.out.println(strWire1[i]);
            int nDistance = 0;
//            System.out.println("Wire starts: [" + nXnow + "][" + nYnow + "]");
            try {
                nDistance = Integer.parseInt(strWire[i].substring(1));
            } catch (NumberFormatException e) {
                System.out.println("Caught NumberFormatException");
                return false;
            }
            if (strWire[i].startsWith("R")) {
                // x increasing
                Xwire xwire = new Xwire(nYnow, nXnow, nXnow + nDistance);
                Xwires.add(xwire);
                nXnow = nXnow + nDistance;
            } else if (strWire[i].startsWith("L")) {
                // x decreasing
                Xwire xwire = new Xwire(nYnow, nXnow - nDistance, nXnow);
                Xwires.add(xwire);
                nXnow = nXnow - nDistance;
            } else if (strWire[i].startsWith("U")) {
                // y increasing
                Ywire ywire = new Ywire(nXnow, nYnow, nYnow + nDistance);
                Ywires.add(ywire);
                nYnow = nYnow + nDistance;
            } else if (strWire[i].startsWith("D")) {
                // y decreasing
                Ywire ywire = new Ywire(nXnow, nYnow - nDistance, nYnow);
                Ywires.add(ywire);
                nYnow = nYnow - nDistance;
            } 
        }
//        System.out.println("Wire ends: [" + nXnow + "][" + nYnow + "]");
//        for (Xwire xw : Xwires) {
//            System.out.println("Wire collection: y = " + xw.y + ". Start/Finish: " + xw.nXstart + "/" + xw.nXfinish);
//        }

        return true;
    }
}
