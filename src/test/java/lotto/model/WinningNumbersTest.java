package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("로또 티켓과 당첨 번호가 일치하는 개수를 반환한다")
    @Test
    void shouldReturnCountOfMatchingNumbers_whenTicketCompared() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonus);

        List<Integer> ticketNumbers = List.of(1, 2, 3, 10, 11, 12);

        // when
        int matchCount = winningNumbers.countMatchingNumbers(ticketNumbers);

        // then
        assertThat(matchCount).isEqualTo(3);
    }

    @DisplayName("로또 티켓에 보너스 번호가 포함되어 있으면 true 를 반환한다")
    @Test
    void shouldReturnTrue_whenTicketHasBonusNumber() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonus);

        List<Integer> ticketNumbers = List.of(7, 8, 9, 10, 11, 12);

        // when
        boolean hasBonus = winningNumbers.hasBonusNumber(ticketNumbers);

        // then
        assertThat(hasBonus).isTrue();
    }

    @DisplayName("로또 티켓에 보너스 번호가 포함되어 있지 않으면 false 를 반환한다")
    @Test
    void shouldReturnFalse_whenTicketDoesNotHaveBonusNumber() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonus);

        List<Integer> ticketNumbers = List.of(8, 9, 10, 11, 12, 13);

        // when
        boolean hasBonus = winningNumbers.hasBonusNumber(ticketNumbers);

        // then
        assertThat(hasBonus).isFalse();
    }
}