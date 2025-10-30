package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

}