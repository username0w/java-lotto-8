package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final Lotto lotto;
    private final Bonus bonus;

    public WinningNumbers(Lotto lotto, Bonus bonus) {
        validateNoOverlap(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public int countMatchingNumbers(List<Integer> ticketNumbers) {
        return lotto.countMatchingNumbers(ticketNumbers);
    }

    public boolean hasBonusNumber(List<Integer> ticketNumbers) {
        return bonus.hasBonusNumbers(ticketNumbers);
    }

    private void validateNoOverlap(Lotto lotto, Bonus bonus) {
        if (lotto.containsNumber(bonus.bonus())) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
