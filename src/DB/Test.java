package DB;
import code4goal.antony.resumeparser.Annie;
import code4goal.antony.resumeparser.ResumeParserProgram;

import java.time.Period;
import java.util.ArrayList;

/**
 * Created by tzeyangng on 4/2/17.
 */
public class Test {
    public static void main(String[] args) {
        String[] arguments = {"Resume.pdf","output.txt"};
        //String[] arguments = {};
        ResumeParserProgram.main(arguments);

        /*Annie a = new Annie();
        try{
            a.initAnnie();
            a.execute();
        }catch(Exception e){
            e.printStackTrace();
        }*/

        /*String[] hobby = {"eating","sleeping"};
        String[] job = {"eating professionally","sleeping pro"};
        DB db = new DB();
        Period p = Period.ofDays(25);
        db.saveService("carpenting","For my new floor","description",12,24,p);*/

        //db.DBQuery();
        //System.out.print(db.getLastID("occupationskill"));

    }

    public static void seedDB(){
        String[][] skills = CSVReader.extractSkills();
        ArrayList<String> list = CSVReader.extractSkillsForOccupation(skills);
        /*while(!list.isEmpty()){
            System.out.print(list.remove(0) + "\n");
        }*/
        //DB.DBPut(list);
    }

}
