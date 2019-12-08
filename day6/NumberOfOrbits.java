import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MyClass {
    public static void main(String args[]) {
        String strInput = "";
        String strData[] = null;
        String strBody[] = null;
        String strOrbital[] = null;
        int nOrbits[] = null;
        ArrayList<String> arrFind = new ArrayList<String>();
        ArrayList<String> arrInOrbit = new ArrayList<String>();
//        String fileInput = "myfiles/day6inputtest.txt";
        String fileInput = "myfiles/day6input.txt";
        String fileOutput = "myfiles/day6output.txt";

        System.out.println("Start reading....");
        strInput = readFile(fileInput);
//        System.out.println(strInput);

        if (strInput != null) {
            strData = strInput.split("\n");
        }

        if (strData != null) {
            strBody = new String[strData.length];
            strOrbital= new String[strData.length];
            nOrbits= new int[strData.length];
            for (int x = 0; x < strData.length; x++) {
                String strSplit[] = null;
                strSplit = strData[x].split("\\)");
                strBody[x] = strSplit[0];
                strOrbital[x] = strSplit[1];
//                System.out.println("Bodies: " + strBody[x] + "  Orbitals: " + strOrbital[x]);
            }
        }
        
        arrFind.add("COM");
        int nOrbitLevel = 0;
        Iterator iFind = arrFind.iterator();
        while (iFind.hasNext()) {
            arrInOrbit = findOrbitals(strBody, strOrbital, arrFind);
            nOrbitLevel++;
//			System.out.print("\nRecording...");
//            for (String strRecording : arrInOrbit) {
//    			System.out.print( strRecording + "..." );
//            }
            Iterator iInOrbit = arrInOrbit.iterator();
            while (iInOrbit.hasNext()) {
                for (int x = 0; x < strOrbital.length; x++) {
                    if (strOrbital[x] == arrInOrbit.get(0)) {
                        nOrbits[x] = nOrbitLevel;
                    }
                }
                arrFind.add(arrInOrbit.get(0));
                arrInOrbit.remove(0);
            }
        }
        
        int nTotalOrbits = 0;
		System.out.print("\nNumber of Orbits...");
        for (int x = 0; x < strOrbital.length; x++) {
//		    System.out.print("\nOrbital: " + strOrbital[x] + "  " + nOrbits[x]);
		    nTotalOrbits += nOrbits[x];
        }
		System.out.print("\nTotal: " + nTotalOrbits);
        
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

    private static ArrayList<String> findOrbitals(String[] arrBodySource, String[] arrOrbitalsSource, ArrayList<String> strFindList) {
        ArrayList<String> arrFoundOrbitals = new ArrayList<String>();
        
//		System.out.print("\nFinding... ");
//        for (String strFind : strFindList) {
//			System.out.print( strFind + " .. " );
//        }
        Iterator iFind = strFindList.iterator();
        while (iFind.hasNext()) {
//			System.out.print("\nAdding...");
            for (int x = 0; x < arrBodySource.length; x++) {
                if (arrBodySource[x].equals(strFindList.get(0))) {
//        			System.out.print( arrOrbitalsSource[x] + "..." );
                    arrFoundOrbitals.add(arrOrbitalsSource[x]);
                }
            }
            strFindList.remove(0);
        }
        return arrFoundOrbitals;
    }
}
