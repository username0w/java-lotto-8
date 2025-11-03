package lotto.model;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0보다 커야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위로 입력해야 합니다.");
        }
    }
}
