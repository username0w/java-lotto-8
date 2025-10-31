package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("로또 번호와 일치하는 개수와 보너스 번호 유무에 따라 등수를 반환한다")
    @Test
    void shouldReturnRank_whenMethodCalled() {
        // given & when & then
        assertThat(Rank.findRankByEqualLottoCountAndBonus(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.findRankByEqualLottoCountAndBonus(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.findRankByEqualLottoCountAndBonus(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.findRankByEqualLottoCountAndBonus(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.findRankByEqualLottoCountAndBonus(3, false)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("로또 번호와 일치하는 개수가 없으면 null 을 반환한다")
    @Test
    void shouldReturnNull_whenMethodCalled() {
        // given
        int equalLottoNumbers = 6;
        boolean hasBonusNumber = true;

        // when
        Rank rank = Rank.findRankByEqualLottoCountAndBonus(equalLottoNumbers, hasBonusNumber);

        // then
        assertThat(rank).isNull();
    }
}