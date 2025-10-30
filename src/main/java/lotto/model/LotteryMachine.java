package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryMachine {

    private final List<LottoTicket> tickets;

    public LotteryMachine() {
        this.tickets = new ArrayList<>();
    }

    public void buyTickets(int count) {
        for (int i = 0; i < count; i++) {
            tickets.add(createLottoTicket());
        }
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    private LottoTicket createLottoTicket() {
        return new LottoTicket(generateRandomNumbers());
    }

    private List<Integer> generateRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}


