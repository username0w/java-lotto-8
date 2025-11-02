package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @DisplayName("로또 번호에 1보다 작은 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_1보다_작은_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 45보다 큰 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_45보다_큰_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(46, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 번호가 일치하면 6을 반환한다")
    @Test
    void shouldReturnSix_whenAllNumbersMatch() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        List<Integer> numbers = List.of(1, 2, 3, 10, 11, 12);

        // when & then
        assertThat(lotto.countMatchingNumbers(numbers)).isEqualTo(6);
    }

    @DisplayName("일부 번호만 일치하면 일치 개수를 반환한다")
    @Test
    void shouldReturnCorrectCount_whenSomeNumbersMatch() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> numbers = List.of(1, 2, 7, 8, 9, 10);

        // when & then
        assertThat(lotto.countMatchingNumbers(numbers)).isEqualTo(2);
    }

    @DisplayName("전혀 일치하지 않으면 0을 반환한다")
    @Test
    void shouldReturnZero_whenNoNumbersMatch() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> numbers = List.of(7, 8, 9, 10, 11, 12);

        // when & then
        assertThat(lotto.countMatchingNumbers(numbers)).isEqualTo(0);
    }

}
