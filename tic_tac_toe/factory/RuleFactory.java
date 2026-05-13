package tic_tac_toe.factory;

import tic_tac_toe.rule.Rule;
import tic_tac_toe.rule.StandardRule;

public class RuleFactory {
    public static Rule getRule (RuleType type) {
        if (type == RuleType.STANDARD) return new StandardRule();
        else throw new UnsupportedOperationException("No such type supported.");
    }
}
