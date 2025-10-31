package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> result = new HashMap<>();

    public LottoResult() {
    }

    public Map<Rank, Integer> calculate(List<LottoTicket> tickets, WinningNumbers winningNumbers) {
        for (LottoTicket ticket : tickets) {
            int equalNumbers = winningNumbers.countMatchingNumbers(ticket.getNumbers());
            boolean bonus = winningNumbers.hasBonusNumber(ticket.getNumbers());
            Rank rank = Rank.findRankByEqualLottoCountAndBonus(equalNumbers, bonus);
            if (rank != null) {
                result.put(rank, result.getOrDefault(rank, 0) + 1);
            }
        }
        return Map.copyOf(result);
    }
}
