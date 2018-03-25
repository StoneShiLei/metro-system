package com.hafuhafu.service;

import com.hafuhafu.model.Card;
import com.hafuhafu.model.CardTypeEnum;

import java.util.Calendar;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-23
 * Time: 19:04
 */
public interface ICardService {

    Card getCard(String cardId);

    String newCard(CardTypeEnum cardType);

    void destroyCard(String cardId);
}
