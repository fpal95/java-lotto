package lotto.domain.lotto;

import java.util.Objects;

public class LottoNumber {

    private final int number;

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final String EXCEPTION_NUMBER_RANGE = "[ERROR] 숫자의 범위가 올바르지 않습니다.";

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public int value() {
        return number;
    }

    private void validateNumber(int number) {
        if (number < LOTTO_MIN_NUMBER || LOTTO_MAX_NUMBER < number) {
            throw new IllegalArgumentException(EXCEPTION_NUMBER_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
