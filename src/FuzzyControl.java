import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class FuzzyControl {

    private final FIS fis = FIS.load("fuzzy_velocity.fcl",false);
    FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();

    public FuzzyControl(){
        fuzzyRuleSet.chart();
    }

    public double getVelocityChange(double coinDist, double chaserDist){
        fuzzyRuleSet.setVariable("coinDist", coinDist);
        fuzzyRuleSet.setVariable("chaserDist", chaserDist);

        fuzzyRuleSet.evaluate();

        double change = fuzzyRuleSet.getVariable("velocity_change").defuzzify();
        System.out.println("coin dist: " + coinDist + " chaser dist: " + chaserDist + " change: " + change);
        return change;
    }
}
