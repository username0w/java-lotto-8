package lotto.model;

import java.util.Collections;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> result;
    private final double profitRate;

    public LottoResult(Map<Rank, Integer> result, double profitRate) {
        this.result = Collections.unmodifiableMap(result);
        this.profitRate = profitRate;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
