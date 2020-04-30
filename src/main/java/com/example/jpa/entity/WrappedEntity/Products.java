package com.example.jpa.entity.WrappedEntity;

import com.example.jpa.entity.Product;
import com.example.jpa.util.Money;
import lombok.RequiredArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.springframework.data.util.Streamable;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.Iterator;

@RequiredArgsConstructor(staticName = "of")
public
class Products implements Streamable<Product> {

  private Streamable<Product> streamable;

  public BigDecimal getTotal() {
    return streamable.stream() //
      .map(product->product.getPriceAmount())
      .reduce(((money1, money2) -> money1.add(money2))).get();
  }

  @Override
  public Iterator<Product> iterator() {
    return streamable.iterator();
  }
}