package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultCalculatorTest {

    @DisplayName("1등과 2등 결과가 올바르게 계산된다")
    @Test
    void shouldCalculateCorrectResult_whenMethodCalled() {
        // given
        LottoTicket ticket1 = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoTicket ticket2 = new LottoTicket(List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Bonus(7)
        );
        int totalMoney = 2000;

        // when
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();
        LottoResult lottoResult = lottoResultCalculator.calculate(List.of(ticket1, ticket2), winningNumbers,
                totalMoney);

        // then
        Map<Rank, Integer> result = lottoResult.getResult();
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
    }

    @DisplayName("등수에 해당하지 않는 티켓은 결과 맵에 포함되지 않는다")
    @Test
    void shouldIgnore_whenNonWinningTickets() {
        // given
        LottoTicket ticket = new LottoTicket(List.of(10, 11, 12, 13, 14, 15));
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Bonus(7)
        );
        int totalMoney = 1000;

        // when
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();
        LottoResult lottoResult = lottoResultCalculator.calculate(List.of(ticket), winningNumbers, totalMoney);

        // then
        Map<Rank, Integer> result = lottoResult.getResult();
        assertThat(result).doesNotContainKeys(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);
    }

    @DisplayName("빈 티켓 리스트일 경우 결과 맵도 빈 상태이다")
    @Test
    void shouldReturnEmptyMap_whenTicketsEmpty() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Bonus(7)
        );
        int totalMoney = 0;

        // when
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();
        LottoResult lottoResult = lottoResultCalculator.calculate(List.of(), winningNumbers, totalMoney);

        // then
        Map<Rank, Integer> result = lottoResult.getResult();
        assertThat(result).isEmpty();
    }

    @DisplayName("동일 티켓 여러 장일 경우 올바르게 카운트된다")
    @Test
    void shouldCountCorrectly_whenDuplicateTickets() {
        // given
        LottoTicket ticket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Bonus(7)
        );
        int totalMoney = 1000;

        // when
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();
        LottoResult lottoResult = lottoResultCalculator.calculate(List.of(ticket, ticket, ticket), winningNumbers,
                totalMoney);

        // then
        Map<Rank, Integer> result = lottoResult.getResult();
        assertThat(result.get(Rank.FIRST)).isEqualTo(3);
    }
}