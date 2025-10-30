package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

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
        lotteryMachine.buyTickets(ticketCounts);

        // then
        assertThat(lotteryMachine.getTickets()).hasSize(ticketCounts);
    }
}