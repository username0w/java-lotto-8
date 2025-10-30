package lotto.model;

import lotto.Lotto;

public class WinningNumbers {

    private final Lotto lotto;
    private final Bonus bonus;

    public WinningNumbers(Lotto lotto, Bonus bonus) {
        this.lotto = lotto;
        this.bonus = bonus;
    }
}
