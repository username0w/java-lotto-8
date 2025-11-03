package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또 번호가 6개가 넘어가면 예외가 발생한다")
    @Test
    void shouldThrowException_whenMoreThanSixNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야 합니다");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void shouldThrowException_whenNumbersAreDuplicated() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다");
    }

    @DisplayName("로또 번호에 1보다 작은 숫자가 있으면 예외가 발생한다")
    @Test
    void shouldThrowException_whenNumberIsLessThanOne() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45 사이");
    }

    @DisplayName("로또 번호에 45보다 큰 숫자가 있으면 예외가 발생한다")
    @Test
    void shouldThrowException_whenNumberIsGreaterThanFortyFive() {
        assertThatThrownBy(() -> new Lotto(List.of(46, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45 사이");
    }

    @DisplayName("모든 번호가 일치하면 6을 반환한다")
    @Test
    void shouldReturnSix_whenAllNumbersMatch() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        List<Integer> numbers = List.of(1, 2, 3, 10, 11, 12);

        assertThat(lotto.countMatchingNumbers(numbers)).isEqualTo(6);
    }

    @DisplayName("일부 번호만 일치하면 일치 개수를 반환한다")
    @Test
    void shouldReturnCorrectCount_whenSomeNumbersMatch() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> numbers = List.of(1, 2, 7, 8, 9, 10);

        assertThat(lotto.countMatchingNumbers(numbers)).isEqualTo(2);
    }

    @DisplayName("전혀 일치하지 않으면 0을 반환한다")
    @Test
    void shouldReturnZero_whenNoNumbersMatch() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> numbers = List.of(7, 8, 9, 10, 11, 12);

        assertThat(lotto.countMatchingNumbers(numbers)).isEqualTo(0);
    }
}
