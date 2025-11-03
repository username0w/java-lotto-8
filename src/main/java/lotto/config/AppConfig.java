package lotto.config;

import lotto.controller.LottoController;
import lotto.model.LotteryMachine;
import lotto.model.LottoResultCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public LotteryMachine lotteryMachine() {
        return new LotteryMachine();
    }

    public LottoResultCalculator lottoResultCalculator() {
        return new LottoResultCalculator();
    }

    public LottoController lottoController() {
        return new LottoController(
                inputView(),
                outputView(),
                lotteryMachine(),
                lottoResultCalculator()
        );
    }
}
