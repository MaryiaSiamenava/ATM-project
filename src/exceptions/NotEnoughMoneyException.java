package exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    private final double atmBalance;

    public NotEnoughMoneyException(double atmBalance) {
        this.atmBalance = atmBalance;
    }

    public double getAtmBalance() {
        return atmBalance;
    }

}
