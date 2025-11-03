package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("입력에 따라 결과가 출력된다")
    @Test
    void shouldPrintCorrectResult() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @DisplayName("구입 금액에 따라 로또 티켓 수가 출력된다")
    @Test
    void shouldPrintCorrectTicketCount() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("5000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains("5개를 구매했습니다.");
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45)
        );
    }

    @DisplayName("로또 번호와 보너스 번호가 모두 입력되면 결과가 출력된다")
    @Test
    void shouldPrintResult_whenWinningNumbersEntered() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "3개 일치 (5,000원) - 1개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @DisplayName("금액 입력이 잘못되면 재입력 처리 후 정상 동작")
    @Test
    void shouldRetryMoneyInput_whenInvalid() {
        assertSimpleTest(() -> {
            run("1000j", "5000", "1,2,3,4,5,6", "7");

            assertThat(output()).contains(ERROR_MESSAGE + " 숫자를 입력해야 합니다.");
            assertThat(output()).contains("5개를 구매했습니다.");
        });
    }

    @DisplayName("로또 번호 입력이 잘못되면 재입력 처리 후 정상 동작")
    @Test
    void shouldRetryLottoInput_whenInvalid() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5", "1,2,3,4,5,6", "7");

            assertThat(output()).contains(ERROR_MESSAGE + " 로또 번호는 6개여야 합니다.");
            assertThat(output()).contains("1개를 구매했습니다.");
        });
    }

    @DisplayName("보너스 번호가 잘못되면 재입력 처리 후 정상 동작")
    @Test
    void shouldRetryBonusInput_whenInvalid() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "3", "7");

            assertThat(output()).contains(ERROR_MESSAGE + " 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            assertThat(output()).contains("당첨 통계");
        });
    }

    @DisplayName("금액 입력이 잘못되면 예외 메시지를 출력한다")
    @Test
    void shouldThrowError_whenInvalidMoneyInput() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE + " 숫자를 입력해야 합니다.");
        });
    }

    @DisplayName("로또 번호 입력이 6개가 아니면 예외 메시지를 출력한다")
    @Test
    void shouldThrowError_whenLottoNumberCountInvalid() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5", "7");
            assertThat(output()).contains(ERROR_MESSAGE + " 로또 번호는 6개여야 합니다.");
        });
    }

    @DisplayName("로또 번호가 중복되면 예외 메시지를 출력한다")
    @Test
    void shouldThrowError_whenDuplicateLottoNumbers() {
        assertSimpleTest(() -> {
            runException("1000", "1,1,2,3,4,5", "6");
            assertThat(output()).contains(ERROR_MESSAGE + " 로또 번호는 중복될 수 없습니다.");
        });
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외 메시지를 출력한다")
    @Test
    void shouldThrowError_whenLottoNumberOutOfRange() {
        assertSimpleTest(() -> {
            runException("1000", "0,2,3,4,5,6", "7");
            assertThat(output()).contains(ERROR_MESSAGE + " 로또 번호는 1~45 사이여야 합니다.");
        });
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외 메시지를 출력한다")
    @Test
    void shouldThrowError_whenBonusNumberOutOfRange() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "50");
            assertThat(output()).contains(ERROR_MESSAGE + " 보너스 번호는 1~45 사이여야 합니다.");
        });
    }

    @DisplayName("보너스 번호가 당첨 번호와 겹치면 예외 메시지를 출력한다")
    @Test
    void shouldThrowError_whenBonusNumberOverlaps() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "3");
            assertThat(output()).contains(ERROR_MESSAGE + " 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

