import java.io.*;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class MyClass {
    public static void main(String args[]) {
        String strTest1 = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        String strTest2 = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        String strTest3 = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0";
        String strTest4 = "3,9,8,9,10,9,4,9,99,-1,8";
        String strTest5 = "3,9,7,9,10,9,4,9,99,-1,8";
        String strTest6 = "3,3,1108,-1,8,3,4,3,99";
        String strTest7 = "3,3,1107,-1,8,3,4,3,99";
        String strTest8 = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
        String strTest9 = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
        String strTest10 = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        String strInput = "";
        String fileInput = "myfiles/day7input.txt";
        String fileOutput = "myfiles/output.txt";
        String strProgValues[] = null;
        int nInput[] = new int[2];
        int nMaxThrust = 0;

        System.out.println("Start reading....");
        strInput = readFile(fileInput);
        
//        strInput = strTest3;       
        
//        System.out.println(strInput);
        if (strInput != null) {
            strProgValues = strInput.split(",");
        }
        if (strProgValues != null) {
            int nProgSize = strProgValues.length;
            int nProgValues[] = new int[nProgSize];
            try {
                for (int i = 0; i < strProgValues.length; i++) {
                    nProgValues[i] = Integer.parseInt(strProgValues[i]);
//                    System.out.print(nProgValues[i] + ",");
                }
            } catch (NumberFormatException e) {
                System.out.println("Caught NumberFormatException");
                    
            }
            System.out.println("Last value: " + nProgValues[nProgSize-2]);
            
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    if (b != a) {
                        for (int c = 0; c < 5; c++) {
                            if (c != b && c != a) {
                                for (int d = 0; d < 5; d++) {
                                    if (d != c && d != b && d != a) {
                                        for (int e = 0; e < 5; e++) {
                                            if (e != d && e != c && e != b && e != a) {
                                                System.out.println("Phase: " + a + b + c + d + e);
                                                int nOutput = -99;
                                                nInput[0] = a;  // phase
                                                nInput[1] = 0;  // input
                                                nOutput = RunOpCodeMachine(nInput, nProgValues);
                                                nInput[0] = b;  // phase
                                                nInput[1] = nOutput;  // input
                                                nOutput = RunOpCodeMachine(nInput, nProgValues);
                                                nInput[0] = c;  // phase
                                                nInput[1] = nOutput;  // input
                                                nOutput = RunOpCodeMachine(nInput, nProgValues);
                                                nInput[0] = d;  // phase
                                                nInput[1] = nOutput;  // input
                                                nOutput = RunOpCodeMachine(nInput, nProgValues);
                                                nInput[0] = e;  // phase
                                                nInput[1] = nOutput;  // input
                                                nOutput = RunOpCodeMachine(nInput, nProgValues);
                                    
                                                System.out.println("Final Output: " + nOutput);
                                                if (nOutput > nMaxThrust) {
                                                    nMaxThrust = nOutput;
                                                }
            
                                            }
                                        }  
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("Max Thrust Phase: " + nMaxThrust);
            
/*            
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < nProgValues.length; j++) {
                sb.append(String.valueOf(nProgValues[j]));
                if (j < nProgValues.length) {
                   sb.append(","); 
                }
            }
            System.out.println("Writing Output File");
//            writeFile(fileOutput, sb.toString());
//            System.out.println("Output: " + sb.toString());
*/
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
	
	private static int RunOpCodeMachine(int[] nInput, int[] nProgValues) {
		Scanner scanner = new Scanner(System.in);
		int nInputSeq = 0;
        int nParam1 = 0;
        int nParam2 = 0;
        int nParam3 = 0;
	    int nOutput = -1;
//        System.out.println("Running opcode machine");
        for (int i = 0; i < nProgValues.length;) {
            int nOpCode = nProgValues[i] % 100;
            int nModes = nProgValues[i] / 100;
            int nMode1 = nModes % 10;
            int nMode2 = (nModes / 10) % 10;
            int nMode3 = nModes / 100;
//                System.out.println("Modes: " + nMode3 + " " + nMode2 + " " + nMode1);
            
            switch (nOpCode) {
                case 1:
//                    System.out.println("Adding");
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
//                        System.out.println("Params: " + nParam2 + " " + nParam1);
                    int nSum = nParam1 + nParam2;
//                        System.out.println("Overwriting location: " + nProgValues[i+3] + " - Was value: " + nProgValues[nProgValues[i+3]]);
                    nProgValues[nProgValues[i+3]] = nSum;
//                        System.out.println("New value:: " + nProgValues[nProgValues[i+3]]);
                    i += 4;
                    break;
                case 2:
//                    System.out.println("Multiplying");
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
//                        System.out.println("Overwriting location: " + nProgValues[i+3] + " - Was value: " + nProgValues[nProgValues[i+3]]);
                    nProgValues[nProgValues[i+3]] = nMultiply;
//                        System.out.println("New value:: " + nProgValues[nProgValues[i+3]]);
                    i += 4;
                    break;
                case 3:
//                    System.out.println("Input");
                    // For now Hardcode input "1" until we find out how to perfom user input in the IDE console...
                    nProgValues[nProgValues[i+1]] = nInput[nInputSeq];
/*                    String strTxt = "";
            		try {
                		System.out.println("Input a number....");
                		strTxt = scanner.nextLine();
                		System.out.println("You have typed...");
                		System.out.println(strTxt);
            		} catch (NoSuchElementException e) {
            		    System.out.println("Type something in the Stdin box above....");
            		}
	                try {
                        nProgValues[nProgValues[i+1]] = Integer.parseInt(strTxt);
                    } catch (NumberFormatException e) {
                        System.out.println("Caught NumberFormatException...default to input 5");
                        nProgValues[nProgValues[i+1]] = 5;    
                    }
*/                  
                    nInputSeq++;
                    i += 2;
                    break;
                case 4:
                    if (nMode1 == 1) {
                        nParam1 = nProgValues[i+1];
                    } else {
                        nParam1 = nProgValues[nProgValues[i+1]];
                    }
//                    System.out.println("Output");
//                    System.out.println("Output: " + nParam1);
                    i += 2;
                    nOutput = nParam1;
//                    return nParam1;
                    break;
                case 5:
//                    System.out.println("Jump if True");
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
//                        System.out.println("Params: " + nParam2 + " " + nParam1);
                    if (nParam1 != 0) {
                        i = nParam2;
//                            System.out.println("Jumping to: " + nParam2);
                    } else {
                        i += 3;
                    }
                    break;
                case 6:
//                    System.out.println("Jump if False");
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
//                        System.out.println("Params: " + nParam2 + " " + nParam1);
                    if (nParam1 == 0) {
                        i = nParam2;
//                            System.out.println("Jumping to: " + nParam2);
                    } else {
                        i += 3;
                    }
                    break;
                case 7:
//                    System.out.println("Is Less Than");
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
                    if (nMode3 == 1) {
                        nParam3 = nProgValues[nProgValues[i+3]];
                    } else {
                        nParam3 = nProgValues[i+3];
                    }
//                        System.out.println("Params: " + nParam3 + " " + nParam2 + " " + nParam1);
                    if (nParam1 < nParam2) {
                        nProgValues[nParam3] = 1;
//                            System.out.println("Store 1 at loc: " + nParam3);
                    } else {
                        nProgValues[nParam3] = 0;
//                            System.out.println("Store 0 at loc: " + nParam3);
                    }
                    i += 4;
                    break;
                case 8:
//                    System.out.println("Is Equal");
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
                    if (nMode3 == 1) {
                        nParam3 = nProgValues[nProgValues[i+3]];
                    } else {
                        nParam3 = nProgValues[i+3];
                    }
//                        System.out.println("Params: " + nParam3 + " " + nParam2 + " " + nParam1);
                    if (nParam1 == nParam2) {
                        nProgValues[nParam3] = 1;
//                            System.out.println("Store 1 at loc: " + nParam3);
                    } else {
                        nProgValues[nParam3] = 0;
//                            System.out.println("Store 0 at loc: " + nParam3);
                    }
                    i += 4;
                    break;
                case 99:
//                    System.out.println("Ending...");
                    i = nProgValues.length;
                    break;
                default:
                    break;
            }
        }
	    return nOutput;

	}

}
