package lotto.controller;

import java.util.List;
import lotto.model.Bonus;
import lotto.model.LotteryMachine;
import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.LottoResultCalculator;
import lotto.model.Money;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LotteryMachine lotteryMachine;
    private final LottoResultCalculator lottoResultCalculator;

    public LottoController(InputView inputView, OutputView outputView,
                           LotteryMachine lotteryMachine, LottoResultCalculator lottoResultCalculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lotteryMachine = lotteryMachine;
        this.lottoResultCalculator = lottoResultCalculator;
    }

    public void run() {
        Money money = readTotalMoney();

        lotteryMachine.buyTickets(money.getTicketCount());
        outputView.printLottoTickets(lotteryMachine.getTickets());

        WinningNumbers winningNumbers = readWinningNumbers();

        LottoResult result = lottoResultCalculator.calculate(
                lotteryMachine.getTickets(), winningNumbers, money.getAmount()
        );

        outputView.printLottoResult(result);
    }

    private Money readTotalMoney() {
        while (true) {
            try {
                outputView.printMoneyInputMessage();
                String input = inputView.readMoneyInput();
                int amount = Integer.parseInt(input);
                return new Money(amount);
            } catch (NumberFormatException e) {
                outputView.printError("숫자를 입력해야 합니다.");
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinningNumbers readWinningNumbers() {
        Lotto winningLotto = readWinningLottoNumbers();
        while (true) {
            try {
                Bonus bonus = readBonusNumber();
                return new WinningNumbers(winningLotto, bonus);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Lotto readWinningLottoNumbers() {
        while (true) {
            try {
                outputView.printLottoInputMessage();
                String lottoInput = inputView.readLottoInput();
                List<Integer> lottoNumbers = parseLottoNumbers(lottoInput);
                return new Lotto(lottoNumbers);
            } catch (NumberFormatException e) {
                outputView.printError("숫자를 입력해야 합니다.");
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Bonus readBonusNumber() {
        while (true) {
            try {
                outputView.printBonusInputMessage();
                String bonusInput = inputView.readBonusInput();
                int bonusNumber = Integer.parseInt(bonusInput);
                return new Bonus(bonusNumber);
            } catch (NumberFormatException e) {
                outputView.printError("숫자를 입력해야 합니다.");
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<Integer> parseLottoNumbers(String input) {
        try {
            String[] parts = input.split(",");
            if (parts.length != 6) {
                throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
            }
            return java.util.Arrays.stream(parts)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }
}
