package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final Lotto lotto;
    private final Bonus bonus;

    public WinningNumbers(Lotto lotto, Bonus bonus) {
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public int countMatchingNumbers(List<Integer> ticketNumbers) {
        return lotto.countMatchingNumbers(ticketNumbers);
    }

    public boolean hasBonusNumber(List<Integer> ticketNumbers) {
        return bonus.hasBonusNumbers(ticketNumbers);
    }
}
