import java.io.*;

public class MyClass {
    public static void main(String args[]) {
        String strInput = "";
        String fileInput = "myfiles/input.txt";
        String fileOutput = "myfiles/output.txt";
        String strProgValues[] = null;
        int nNoun = 84;
        int nVerb = 44;

        System.out.println("Start reading....");
        strInput = readFile(fileInput);
        System.out.println(strInput);
        
        if (strInput != null) {
            strProgValues = strInput.split(",");
        }
        if (strProgValues != null) {
            int nProgSize = strProgValues.length;
            int nLiveProgValues[] = new int[nProgSize];
            int nOriginalProgValues[] = new int[nProgSize];
            try {
                for (int i = 0; i < strProgValues.length; i++) {
//                    System.out.println(strProgValues[i]);
                    nOriginalProgValues[i] = Integer.parseInt(strProgValues[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Caught NumberFormatException");
                    
            }
            // Iterate through Noun and Verb
            nNoun = 0;
            nVerb = 0;
            while (nLiveProgValues[0] != 19690720 && nNoun <= 99) {
                nVerb = 0;
                while (nLiveProgValues[0] != 19690720 && nVerb <= 99) {
                    // Reload original program and run through machine again
                    for (int k = 0; k < nOriginalProgValues.length; k++) {
                        nLiveProgValues[k] = nOriginalProgValues[k];
                    }
                    nLiveProgValues[1] = nNoun;
                    nLiveProgValues[2] = nVerb;
//                    System.out.println("Running opcode machine");
                    for (int i = 0; i < nLiveProgValues.length; i += 4) {
                        int nOpCode = nLiveProgValues[i];
                        switch (nOpCode) {
                            case 1:
        //                        System.out.println("Adding");
                                int nSum = nLiveProgValues[nLiveProgValues[i+1]] + nLiveProgValues[nLiveProgValues[i+2]];
                                nLiveProgValues[nLiveProgValues[i+3]] = nSum;
                                break;
                            case 2:
        //                        System.out.println("Multiplying");
                                int nMultiply = nLiveProgValues[nLiveProgValues[i+1]] * nLiveProgValues[nLiveProgValues[i+2]];
                                nLiveProgValues[nLiveProgValues[i+3]] = nMultiply;
                                break;
                            case 99:
//                                System.out.println("Ending...");
                                i = nLiveProgValues.length;
                                break;
                            default:
                                break;
                        }
                    }
                    nVerb++;
                }
                nNoun++;
            }
            System.out.println("Search finished... Noun = " + (nNoun - 1) + " and Verb = " + (nVerb - 1) );
            
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nLiveProgValues.length; j++) {
                sb.append(String.valueOf(nLiveProgValues[j]));
                if (j < nLiveProgValues.length) {
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
            
            BufferedReader bufferedReader = 
                new BufferedReader(new FileReader(fileName));

            while((txt = bufferedReader.readLine()) != null) {
//                System.out.println(txt);
                sb.append(txt);
                sb.append("\n");
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to read...");                
        }
        catch(IOException ex) {
            System.out.println("Unable to read...");
        }
        return sb.toString();
	}

	private static void writeFile(String fileName, String strContents) {
	        try {
	
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(new FileWriter(fileName));
	
	            bufferedWriter.write(strContents);
	            bufferedWriter.newLine();

	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println("Error writing....");
	        }
	}

}