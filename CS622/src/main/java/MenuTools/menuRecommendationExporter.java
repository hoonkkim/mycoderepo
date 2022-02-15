package MenuTools;

import Menus.Menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class menuRecommendationExporter {
    // the menuRecommendationExportFormatting() method aims to export an array of
    // Menus.Menu objects to a text file. To do so it does 2 things:
    // 1. menuRecommendationExportFormatting():
    // Takes an array of Menus.Menu objects and converts returns an array of strings
    // that consist of the menu objects key attributes: Name, Cost, Frequency, Recency
    // 2. menuRecommenderExport():
    // Takes the array of strings and File I/O's the strings into a text file.
    public String[] menuToString(ArrayList<Menu> menuArrayList) {
        String[] exportStringArray = new String[menuArrayList.size()+1];
        exportStringArray[0] = "Recommendation #, Menus.Menu Name, Cost, Frequency, Recency, Score";
        for (int i = 0; i < menuArrayList.size(); i++) {

            exportStringArray[i+1] =
                    "#"+(i+1)+". "
                    +menuArrayList.get(i).getName()+", "
                    +menuArrayList.get(i).getCost()+", "
                    +menuArrayList.get(i).getFrequency()+", "
                    +menuArrayList.get(i).getRecency()+", "
                    +menuArrayList.get(i).getScore();
        }

//        for (int j =0 ; j < exportStringArray.length; j++ )
//        {
//            System.out.println(exportStringArray[j]);
//        }

        return exportStringArray;
    }

    public void exportMenus(String[] menuStringArray) {
        try {
            FileWriter fr = new FileWriter("MenuRecommendationExport.txt");
            BufferedWriter br = new BufferedWriter(fr);
            for (int k = 0; k < menuStringArray.length; k++) {
                if(k>0) {br.newLine();}
                br.write(menuStringArray[k]);
            }
            System.out.println("Recommendations Exported to MenuRecommendationExport.txt");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
