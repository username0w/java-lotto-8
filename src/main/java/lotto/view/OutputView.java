package lotto.view;

import java.util.List;
import lotto.model.LottoResult;
import lotto.model.LottoTicket;
import lotto.model.Rank;

public class OutputView {

    public void printMoneyInputMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoTickets(List<LottoTicket> lottoTickets) {
        System.out.println();
        int count = lottoTickets.size();
        System.out.printf("%d개를 구매했습니다.%n", count);
        for (LottoTicket ticket : lottoTickets) {
            System.out.println(ticket.getNumbers());
        }
        System.out.println();
    }

    public void printLottoInputMessage() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printBonusInputMessage() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printLottoResult(LottoResult lottoResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        for (Rank rank : Rank.values()) {
            if (rank == null) {
                continue;
            }
            int count = lottoResult.getResult().getOrDefault(rank, 0);
            String bonusText = "";
            if (rank.isHasBonusNumber()) {
                bonusText = ", 보너스 볼 일치";
            }
            System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                    rank.getEqualLottoNumberCount(),
                    bonusText,
                    rank.getPrize(),
                    count);
        }

        System.out.println("---");
        System.out.printf("총 수익률은 %.1f%%입니다.%n", lottoResult.getProfitRate() * 100);
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println();
    }
}
