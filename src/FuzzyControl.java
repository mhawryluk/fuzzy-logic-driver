import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class FuzzyControl {

    private final FIS fis = FIS.load("fuzzy_velocity2.fcl",false);
    FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();

    public FuzzyControl(){
//        fuzzyRuleSet.chart();
    }

    public double getVelocityChange(double coinDistH, double obstacleDistH, double coinDist, double obstacleDist){
//        System.out.println("coin dist: " + coinDist + " obstacle dist: " + obstacleDist);
        fuzzyRuleSet.setVariable("coinDistH", coinDistH);
        fuzzyRuleSet.setVariable("obstacleDistH", obstacleDistH);

        fuzzyRuleSet.setVariable("coinDist", coinDist);
        fuzzyRuleSet.setVariable("obstacleDist", obstacleDist);

        fuzzyRuleSet.evaluate();

        double change = fuzzyRuleSet.getVariable("velocityChange").defuzzify();
//        System.out.println("coinDistH: " + coinDistH + " coinDist: " + coinDist +" obstacleDistH: " + obstacleDistH + " obstacleDist: "+  obstacleDist + " change: " + change);
        return change;
    }
}
