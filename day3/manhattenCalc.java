import java.io.*;

public class MyClass {
    public static void main(String args[]) {
        String strInput = "";
        String strWires[] = null;
        String strWire1[] = null;
        String strWire2[] = null;
        String fileInput = "myfiles/day3input.txt";
        String fileOutput = "myfiles/day3output.txt";
        String strProgValues[] = null;

        System.out.println("Start reading....");
        strInput = readFile(fileInput);
        System.out.println(strInput);

        if (strInput != null) {
            strWires = strInput.split("\n");
        }
 //       System.out.println(strWires[0]);
 //       System.out.println(strWires[1]);
        
        if (strWires != null) {
            strWire1 = strWires[0].split(",");
            strWire2 = strWires[0].split(",");
        }
/*        if (strWire1 != null && strWire2 != null) {
            int nMapX = strProgValues.length;
            int nMapY = strProgValues.length;
            int nProgValues[] = new int[nProgSize];
            try {
                for (int i = 0; i < strProgValues.length; i++) {
//                    System.out.println(strProgValues[i]);
                    nProgValues[i] = Integer.parseInt(strProgValues[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Caught NumberFormatException");
                    
            }
            
            System.out.println("Running opcode machine");
            for (int i = 0; i < nProgValues.length; i += 4) {
                int nOpCode = nProgValues[i];
                switch (nOpCode) {
                    case 1:
                        System.out.println("Adding");
                        int nSum = nProgValues[nProgValues[i+1]] + nProgValues[nProgValues[i+2]];
                        nProgValues[nProgValues[i+3]] = nSum;
                        break;
                    case 2:
                        System.out.println("Multiplying");
                        int nMultiply = nProgValues[nProgValues[i+1]] * nProgValues[nProgValues[i+2]];
                        nProgValues[nProgValues[i+3]] = nMultiply;
                        break;
                    case 99:
                        System.out.println("Ending...");
                        i = nProgValues.length;
                        break;
                    default:
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nProgValues.length; j++) {
                sb.append(String.valueOf(nProgValues[j]));
                if (j < nProgValues.length) {
                   sb.append(","); 
                }
            }
            System.out.println("Writing Output File");
            writeFile(fileOutput, sb.toString());
            System.out.println("Output: " + sb.toString());
        }
*/
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
