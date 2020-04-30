package com.example.jpa.util;

import org.joda.money.CurrencyUnit;

import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import javax.money.MonetaryContext;
import javax.money.NumberValue;
import java.math.BigDecimal;

/**
 * Created by liyq on 2020/4/21.
 */
public class Money implements MonetaryAmount {
    /**
     * The currency, not null.
     */
    private final CurrencyUnit currency;

    /**
     * The amount, not null.
     */
    private final BigDecimal amount;

    public Money(BigDecimal amount, CurrencyUnit currency){
        assert currency != null : "Joda-Money bug: Currency must not be null";
        assert amount != null : "Joda-Money bug: Money must not be null";
        this.amount = (amount.scale() < 0 ? amount.setScale(0) : amount);
        this.currency = currency;
    }

    public static Money of(BigDecimal amount, CurrencyUnit currency) {
        return new Money(amount,currency);
    }

    public static MonetaryAmount zero(CurrencyUnit currency) {
        return new Money(BigDecimal.ZERO,currency);
    }

    @Override
    public MonetaryContext getContext() {
        return null;
    }

    @Override
    public MonetaryAmountFactory<? extends MonetaryAmount> getFactory() {
        return null;
    }

    @Override
    public boolean isGreaterThan(MonetaryAmount amount) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqualTo(MonetaryAmount amount) {
        return false;
    }

    @Override
    public boolean isLessThan(MonetaryAmount amount) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqualTo(MonetaryAmount amt) {
        return false;
    }

    @Override
    public boolean isEqualTo(MonetaryAmount amount) {
        return false;
    }

    @Override
    public int signum() {
        return 0;
    }

    @Override
    public MonetaryAmount add(MonetaryAmount other) {
        this.amount.add(((Money)other).getAmount());
        return this;
    }

    public static Money add(Money source,Money other) {
        source.amount.add((other).getAmount());
        return source;
    }

    @Override
    public MonetaryAmount subtract(MonetaryAmount amount) {
        return null;
    }

    @Override
    public MonetaryAmount multiply(long multiplicand) {
        return null;
    }

    @Override
    public MonetaryAmount multiply(double multiplicand) {
        return null;
    }

    @Override
    public MonetaryAmount multiply(Number multiplicand) {
        return null;
    }

    @Override
    public MonetaryAmount divide(long divisor) {
        return null;
    }

    @Override
    public MonetaryAmount divide(double divisor) {
        return null;
    }

    @Override
    public MonetaryAmount divide(Number divisor) {
        return null;
    }

    @Override
    public MonetaryAmount remainder(long divisor) {
        return null;
    }

    @Override
    public MonetaryAmount remainder(double divisor) {
        return null;
    }

    @Override
    public MonetaryAmount remainder(Number divisor) {
        return null;
    }

    @Override
    public MonetaryAmount[] divideAndRemainder(long divisor) {
        return new MonetaryAmount[0];
    }

    @Override
    public MonetaryAmount[] divideAndRemainder(double divisor) {
        return new MonetaryAmount[0];
    }

    @Override
    public MonetaryAmount[] divideAndRemainder(Number divisor) {
        return new MonetaryAmount[0];
    }

    @Override
    public MonetaryAmount divideToIntegralValue(long divisor) {
        return null;
    }

    @Override
    public MonetaryAmount divideToIntegralValue(double divisor) {
        return null;
    }

    @Override
    public MonetaryAmount divideToIntegralValue(Number divisor) {
        return null;
    }

    @Override
    public MonetaryAmount scaleByPowerOfTen(int power) {
        return null;
    }

    @Override
    public MonetaryAmount abs() {
        return null;
    }

    @Override
    public MonetaryAmount negate() {
        return null;
    }

    @Override
    public MonetaryAmount plus() {
        return null;
    }

    @Override
    public MonetaryAmount stripTrailingZeros() {
        return null;
    }

    @Override
    public int compareTo(MonetaryAmount o) {
        return 0;
    }

    @Override
    public javax.money.CurrencyUnit getCurrency() {
        return null;
    }

    @Override
    public NumberValue getNumber() {
        return null;
    }


    public BigDecimal getAmount() {
        return amount;
    }

}
