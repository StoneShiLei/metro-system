package com.hafuhafu.service;

import com.hafuhafu.model.Card;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-22
 * Time: 17:59
 */
public interface ICardBalanceService {

    String getBalance(Card card);

    String recharge(Card card,Integer amount);

    Card in(Card card, String start);

    String out(Card card, String end);

}
