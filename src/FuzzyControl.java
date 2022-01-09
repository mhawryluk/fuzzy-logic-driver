import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class FuzzyControl {

    private final FIS fis = FIS.load("fuzzy_velocity2.fcl",false);
    FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();

    public FuzzyControl(){
        fuzzyRuleSet.chart();
    }

    public double getVelocityChange(double coinDistH, double chaserDistH, double coinDist, double chaserDist){
//        System.out.println("coin dist: " + coinDist + " chaser dist: " + chaserDist);
        fuzzyRuleSet.setVariable("coinDistH", coinDistH);
        fuzzyRuleSet.setVariable("chaserDistH", chaserDistH);

        fuzzyRuleSet.setVariable("coinDist", coinDist);
        fuzzyRuleSet.setVariable("chaserDist", chaserDist);

        fuzzyRuleSet.evaluate();

        double change = fuzzyRuleSet.getVariable("velocityChange").defuzzify();
//        System.out.println("coin dist: " + coinDist + " chaser dist: " + chaserDist + " change: " + change);
        return change;
    }
}
