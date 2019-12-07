import java.io.*;
import java.util.ArrayList;

public class MyClass {
    public static void main(String args[]) {
        String strTestWire1 = "R8,U5,L5,D3";
        String strTestWire2 = "U7,R6,D4,L4";
        String strTestWire3 = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
        String strTestWire4 = "U62,R66,U55,R34,D71,R55,D58,R83";
        String strTestWire5 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
        String strTestWire6 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
        String strInput = "";
        String strWires[] = null;
        String strWire1[] = null;
        String strWire2[] = null;
        String fileInput = "myfiles/day3input.txt";
        String fileOutput = "myfiles/day3output.txt";
        Wire wire1 = new Wire();
        Wire wire2 = new Wire();
        int nXmanhatten = 0;
        int nYmanhatten = 0;
        int nManhattenDistance = -1;
        ArrayList<Xwire> xWire1 = new ArrayList<Xwire>();
        ArrayList<Ywire> yWire1 = new ArrayList<Ywire>();
        ArrayList<Xwire> xWire2 = new ArrayList<Xwire>();
        ArrayList<Ywire> yWire2 = new ArrayList<Ywire>();
        

        System.out.println("Start reading....");
        strInput = readFile(fileInput);
        System.out.println(strInput);

        if (strInput != null) {
            strWires = strInput.split("\n");
        }
 //       System.out.println(strWires[0]);
 //       System.out.println(strWires[1]);
        
        if (strWires != null) {
//            strWire1 = strTestWire5.split(",");
//            strWire2 = strTestWire6.split(",");
            strWire1 = strWires[0].split(",");
            strWire2 = strWires[1].split(",");
            

            wire1.collectWires(strWire1, 0, 0);
            wire2.collectWires(strWire2, 0, 0);
            
//            for (Xwire xw : wire1.getXwires()) {
//                System.out.println("Wire1 at y = " + xw.y + ". Start/Finish: " + xw.nXstart + "/" + xw.nXfinish);
//            }
            
            // Find intersections
            for (Xwire xWire : wire1.getXwires()) {
                // For all Wire1 X wires compare with Wire2 Y wires.  Cross where Wire2 X between  Wire1 start&finish and Wire1 Y between Wire2 start&finish 
//                System.out.println("Xwire: y=" + xWire.y + ". Start= " + xWire.nXstart + " Finish=" + xWire.nXfinish);
                for (Ywire yWire : wire2.getYwires()) {
//                    System.out.println("Ywire: x=" + yWire.x + ". Start= " + yWire.nYstart + " Finish=" + yWire.nYfinish);
                    if (yWire.x > xWire.nXstart && yWire.x < xWire.nXfinish && xWire.y > yWire.nYstart && xWire.y < yWire.nYfinish) {
                        System.out.println("Crossed: [" + yWire.x + "][" + xWire.y + "]");
                        int nXtestManhatten = Math.abs(yWire.x);
                        int nYtestManhatten = Math.abs(xWire.y);
                        if (nManhattenDistance > 0) {
                            if (nXtestManhatten + nYtestManhatten < nManhattenDistance) {
                                nXmanhatten = nXtestManhatten;
                                nYmanhatten = nYtestManhatten;
                                nManhattenDistance = nXmanhatten + nYmanhatten;
                            }
                                
                        } else {
                            nXmanhatten = nXtestManhatten;
                            nYmanhatten = nYtestManhatten;
                            nManhattenDistance = nXmanhatten + nYmanhatten;
                        }
                        
                    }
                }
            }            
            for (Ywire yWire : wire1.getYwires()) {
                // For all Wire1 Y wires compare with Wire2 X wires.  Cross where Wire2 Y between  Wire1 start&finish and Wire1 X between Wire2 start&finish 
//                System.out.println("Ywire: x=" + yWire.x + ". Start= " + yWire.nYstart + " Finish=" + yWire.nYfinish);
                for (Xwire xWire : wire2.getXwires()) {
//                    System.out.println("Xwire: y=" + xWire.y + ". Start= " + xWire.nXstart + " Finish=" + xWire.nXfinish);
                    if (xWire.y > yWire.nYstart && xWire.y < yWire.nYfinish && yWire.x > xWire.nXstart && yWire.x < xWire.nXfinish) {
                        System.out.println("Crossed: [" + yWire.x + "][" + xWire.y + "]");
                        int nXtestManhatten = Math.abs(yWire.x);
                        int nYtestManhatten = Math.abs(xWire.y);
                        if (nManhattenDistance > 0) {
                            if (nXtestManhatten + nYtestManhatten < nManhattenDistance) {
                                nXmanhatten = nXtestManhatten;
                                nYmanhatten = nYtestManhatten;
                                nManhattenDistance = nXmanhatten + nYmanhatten;
                            }
                                
                        } else {
                            nXmanhatten = nXtestManhatten;
                            nYmanhatten = nYtestManhatten;
                            nManhattenDistance = nXmanhatten + nYmanhatten;
                        }
                        
                    }
                }
            }            
            System.out.println("Manhatten Distance = " + nManhattenDistance);
        }    
    }

	private static String readFile(String fileName) {
        String txt = null;
        StringBuilder sb = new StringBuilder();

        try {
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while((txt = bufferedReader.readLine()) != null) {
//                System.out.println(txt);
                sb.append(txt);
                sb.append("\n");
            }   

            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to read...");                
        } catch(IOException ex) {
            System.out.println("Unable to read...");
        }
        return sb.toString();
	}

	private static void writeFile(String fileName, String strContents) {
		try {

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

			bufferedWriter.write(strContents);
			bufferedWriter.newLine();

			bufferedWriter.close();
		} catch(IOException ex) {
			System.out.println("Error writing....");
		}
	}

}
