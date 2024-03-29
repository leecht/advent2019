import java.io.*;

public class MyClass {
    public static void main(String args[]) {
        String strTest1 = "1002,4,3,4,33";
        String strTest2 = "3,0,4,0,99";
        String strTest3 = "1101,100,-1,4,0";
        String strInput = "";
        String fileInput = "myfiles/day5input.txt";
        String fileOutput = "myfiles/output.txt";
        String strProgValues[] = null;
        int nParam1 = 0;
        int nParam2 = 0;

        System.out.println("Start reading....");
        strInput = readFile(fileInput);
        System.out.println(strInput);
        
//        strInput = strTest3;       
        
        if (strInput != null) {
            strProgValues = strInput.split(",");
        }
        if (strProgValues != null) {
            int nProgSize = strProgValues.length;
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
            for (int i = 0; i < nProgValues.length;) {
                int nOpCode = nProgValues[i] % 100;
                int nModes = nProgValues[i] / 100;
                int nMode1 = nModes % 10;
                int nMode2 = (nModes / 10) % 10;
                int nMode3 = nModes / 100;
                
                switch (nOpCode) {
                    case 1:
                        System.out.println("Adding");
                        if (nMode1 == 1) {
                            nParam1 = nProgValues[i+1];
                        } else {
                            nParam1 = nProgValues[nProgValues[i+1]];
                        }
                        if (nMode2 == 1) {
                            nParam2 = nProgValues[i+2];
                        } else {
                            nParam2 = nProgValues[nProgValues[i+2]];
                        }
//                        int nSum = nProgValues[nProgValues[i+1]] + nProgValues[nProgValues[i+2]];
                        int nSum = nParam1 + nParam2;
                        nProgValues[nProgValues[i+3]] = nSum;
                        i += 4;
                        break;
                    case 2:
                        System.out.println("Multiplying");
                        if (nMode1 == 1) {
                            nParam1 = nProgValues[i+1];
                        } else {
                            nParam1 = nProgValues[nProgValues[i+1]];
                        }
                        if (nMode2 == 1) {
                            nParam2 = nProgValues[i+2];
                        } else {
                            nParam2 = nProgValues[nProgValues[i+2]];
                        }
//                        int nMultiply = nProgValues[nProgValues[i+1]] * nProgValues[nProgValues[i+2]];
                        int nMultiply = nParam1 * nParam2;
                        nProgValues[nProgValues[i+3]] = nMultiply;
                        i += 4;
                        break;
                    case 3:
                        System.out.println("Input");
                        // For now Hardcode input "1" until we find out how to perfom user input in the IDE console...
                        nProgValues[nProgValues[i+1]] = 1;
                        i += 2;
                        break;
                    case 4:
                        if (nMode1 == 1) {
                            nParam1 = nProgValues[i+1];
                        } else {
                            nParam1 = nProgValues[nProgValues[i+1]];
                        }
                        System.out.println("Output");
                        System.out.println("Output: " + nParam1);
                        i += 2;
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
