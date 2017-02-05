package DB;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by tzeyangng on 4/2/17.
 */
public class CSVReader {
    public static String[][] extractSkills(){
        String csvFile = "/Users/tzeyangng/Desktop/GovTech/Occupations2.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[][] output = new String[11][13];
        int rows = 0;
        int count =0;

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                // use comma as separator
                output[count] = line.split(cvsSplitBy);
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }

    public static ArrayList extractSkillsForOccupation(String[][] skills){
        String csvFile = "/Users/tzeyangng/Desktop/GovTech/Occupations.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> list = new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] StArray = line.split(cvsSplitBy);
                System.out.println(StArray[0]);
                for (int i = 0; i< Array.getLength(skills);i++){
                    breakpoint:
                    for (int j = 0; j< Array.getLength(skills[i]);j++){
                        for (int k = 0; k< Array.getLength(StArray);k++){
                            if (StArray[k].contains(skills[i][j])){
                                list.add(StArray[0]);
                                list.add(skills[i][0]);
                                break breakpoint;
                            }
                        }
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
