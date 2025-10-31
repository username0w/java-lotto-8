package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    @DisplayName("보너스 번호는 1~45 외의 숫자면 예외가 발생한다")
    @Test
    void shouldThrowException_whenBonusOutOfRange() {
        // given
        int invalidBonus = 0;

        assertThatThrownBy(() -> new Bonus(invalidBonus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 있으면 true를 반환한다")
    @Test
    void shouldReturnTrue_whenBonusNumberExists() {
        // given
        Bonus bonus = new Bonus(3);
        List<Integer> numbers = List.of(1, 2, 3, 10, 11, 12);

        // when & then
        assertThat(bonus.hasBonusNumbers(numbers)).isTrue();
    }


    @DisplayName("보너스 번호가 없으면 false를 반환한다")
    @Test
    void shouldReturnFalse_whenBonusNumberNotExists() {
        // given
        Bonus bonus = new Bonus(8);
        List<Integer> numbers = List.of(1, 2, 3, 10, 11, 12);

        // when & then
        assertThat(bonus.hasBonusNumbers(numbers)).isFalse();
    }
}