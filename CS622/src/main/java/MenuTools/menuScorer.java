package MenuTools;
import Menus.Menu;

public class menuScorer {
    public <T extends Menu> void menuScorer(T scoredMenu) {

        double recommendationScore;
        // 10 is max score and 0 is min score. Anything beyond are scored as 0/10
        double FScore = (scoredMenu.getFrequency());
        if(FScore > 10.0D) {FScore = 10.0D;}
        if(FScore < 0.0D) {FScore = 0.0D;}

        // 14 Day Recency. Recency 14 or over is scored as a 10.
        // 0 Day Recency gets a 0 Score.
        double RScore = (scoredMenu.getRecency()*10.0D/14.0D);
        if(scoredMenu.getRecency() > 14.0D) {RScore = 10.0D;}
        if(scoredMenu.getRecency() < 0)  {RScore = 0.0D;}

        // Total Score is the sum of scores.
        double totalScore = FScore+RScore;

        scoredMenu.setScore(totalScore);
    }
}
