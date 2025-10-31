package lotto.model;

import java.util.Arrays;

public enum Rank {

    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private int equalLottoNumberCount;
    private boolean hasBonusNumber;
    private int prize;

    Rank(int equalLottoNumberCount, boolean hasBonusNumber, int prize) {
        this.equalLottoNumberCount = equalLottoNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static Rank findRankByEqualLottoCountAndBonus(int equalLottoNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(values())
                .filter(value -> value.equalLottoNumberCount == equalLottoNumberCount)
                .filter(rank -> rank.hasBonusNumber == hasBonusNumber)
                .findFirst()
                .orElse(null);
    }
}
