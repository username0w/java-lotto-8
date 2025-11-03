package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @DisplayName("생성 시 숫자를 오름차순으로 정렬한다")
    @Test
    void shouldSortNumbersAscending_whenCreated() {
        // given
        List<Integer> input = List.of(12, 3, 7, 1, 10, 5);

        // when
        LottoTicket ticket = new LottoTicket(input);

        // then
        assertThat(ticket.getNumbers()).containsExactly(1, 3, 5, 7, 10, 12);
    }
}