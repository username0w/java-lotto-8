package lotto.controller;

import java.util.List;
import lotto.model.Bonus;
import lotto.model.LotteryMachine;
import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.LottoResultCalculator;
import lotto.model.WinningNumbers;

public class LottoController {

    public void run() {

        // 1. 구입 금액 입력
        String inputMoney = "10000";
        // 검증
        int totalMoney = 10000;

        // 장 수 계산
        int ticketCount = 10;

        LotteryMachine lotteryMachine = new LotteryMachine();
        lotteryMachine.buyTickets(ticketCount);
        // 2. 자동 발매 번호 출력

        // 3. 로또 번호 입력
        List<Integer> lottoNumber = List.of(1, 2, 3, 4, 5, 6);
        // 검증
        Lotto lotto = new Lotto(lottoNumber);

        // 4. 보너스 번호 입력
        int bonusNumber = 9;
        // 검증
        Bonus bonus = new Bonus(bonusNumber);

        // 당첨 번호 객체 만들기
        WinningNumbers winningNumbers = new WinningNumbers(lotto, bonus);

        // 5. 결과 계산
        // lotteryMachine 의 LottoTicket 리스트를 넘긴다.
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();

        // result 에서 outputview 로 값을 꺼내서 넘긴다.
        LottoResult result = lottoResultCalculator.calculate(lotteryMachine.getTickets(), winningNumbers, totalMoney);

        System.out.println("result : " + result.getResult());
        System.out.println("profitRate : " + result.getProfitRate());
        // 5. 결과 출력
    }
}
