package lotto.model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public boolean containsNumber(int num) {
        return numbers.contains(num);
    }

    public int countMatchingNumbers(List<Integer> number) {
        return (int) number.stream()
                .filter(numbers::contains)
                .count();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        checkDuplicate(numbers);
        checkNumberRange(numbers);
    }

    private void checkDuplicate(List<Integer> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void checkNumberRange(List<Integer> numbers) {
        boolean hasOutOfRange = numbers.stream()
                .anyMatch(n -> n < 1 || n > 45);
        if (hasOutOfRange) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }
}
