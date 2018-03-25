package com.hafuhafu.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-21
 * Time: 13:09
 */
public class Card {
    private CardTypeEnum cardType;
    private BigDecimal balance;
    private String cardId;
    private CardStatusEnum cardStatus;
    private String cardStart;
    private String cardEnd;

    public Card() {
        this.cardType = CardTypeEnum.NORMAL;
        this.balance = new BigDecimal(0.00);
        this.cardId = idCreater();
        this.cardStatus = CardStatusEnum.OUT;
        this.cardStart = null;
        this.cardEnd = null;
    }

    public Card(String cardId, BigDecimal balance, CardTypeEnum cardType, CardStatusEnum cardStatus,
                String cardStart, String cardEnd) {
        this.cardId = cardId;
        this.balance = balance;
        this.cardType = cardType;
        this.cardStatus = cardStatus;
        this.cardStart = cardStart;
        this.cardEnd = cardEnd;
    }

    private String idCreater() {
        String date = String.valueOf(new Date().getTime());
        String idStr = date.substring(date.length() - 6);
        String randomStr = String.valueOf((int)(Math.random()*100));
        if (randomStr.length() < 2) {
            randomStr = "0" + randomStr;
        }
        return idStr + randomStr;

    }

    public CardTypeEnum getCardType() {
        return cardType;
    }

    public void setCardType(CardTypeEnum cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public CardStatusEnum getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatusEnum cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardStart() {
        return cardStart;
    }

    public void setCardStart(String cardStart) {
        this.cardStart = cardStart;
    }

    public String getCardEnd() {
        return cardEnd;
    }

    public void setCardEnd(String cardEnd) {
        this.cardEnd = cardEnd;
    }
}
