package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotteryMachineTest {

    @DisplayName("입력된 장수 만큼 티켓을 발행한다")
    @Test
    void buyTickets() {
        // given
        int ticketCounts = 8;

        // when
        LotteryMachine lotteryMachine = new LotteryMachine();
        List<LottoTicket> tickets = lotteryMachine.buyTickets(ticketCounts);

        // then
        assertThat(tickets).hasSize(ticketCounts);
    }
}