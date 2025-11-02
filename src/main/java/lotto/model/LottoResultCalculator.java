package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultCalculator {

    public LottoResult calculate(List<LottoTicket> tickets, WinningNumbers winningNumbers, int totalMoney) {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);

        for (LottoTicket ticket : tickets) {
            int equalNumbers = winningNumbers.countMatchingNumbers(ticket.getNumbers());
            boolean bonus = winningNumbers.hasBonusNumber(ticket.getNumbers());
            Rank rank = Rank.findRankByEqualLottoCountAndBonus(equalNumbers, bonus);
            if (rank != null) {
                result.put(rank, result.getOrDefault(rank, 0) + 1);
            }
        }

        double profitRate = calculateProfitRate(result, totalMoney);
        return new LottoResult(result, profitRate);
    }


    private double calculateProfitRate(Map<Rank, Integer> result, int totalMoney) {
        long totalPrize = result.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
        return (double) totalPrize / totalMoney;
    }
}
