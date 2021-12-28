import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class FuzzyControl {

    private final FIS fis = FIS.load("fuzzy_velocity.fcl",false);
    FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();

    public double getVelocityChange(int position, int chaserDist){
        fuzzyRuleSet.setVariable("position", position);
        fuzzyRuleSet.setVariable("chaser_dist", chaserDist);

        fuzzyRuleSet.evaluate();

        return fuzzyRuleSet.getVariable("velocity_change").defuzzify();
    }
}
