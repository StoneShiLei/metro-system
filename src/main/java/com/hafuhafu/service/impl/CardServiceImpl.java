package com.hafuhafu.service.impl;

import com.hafuhafu.dao.ICardDao;
import com.hafuhafu.model.Card;
import com.hafuhafu.model.CardTypeEnum;
import com.hafuhafu.service.ICardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-23
 * Time: 19:10
 */

@Service("cardService")
public class CardServiceImpl implements ICardService {
    private final static Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    @Resource
    private ICardDao cardDao;

    @Override
    public Card getCard(String cardId) {
        Card card = cardDao.selectCard(cardId);
        if (card == null) {
            return null;
        }
        return card;
    }

    @Override
    public String newCard(CardTypeEnum cardType) {
        Card card = new Card();
        card.setCardType(cardType);
        int insertResult = cardDao.saveCard(card);
        try {
            if (insertResult == 1) {
                return card.getCardId();
            } else {
                throw new Exception("新建公交卡插入数据库失败，insert返回值为：" + insertResult);
            }
        } catch (Exception e) {
            log.error("保存新Card到数据库失败:{}",e.getMessage(),e);
            return null;
        }
    }

    @Override
    public void destroyCard(String cardId) {
        int deleteResult = cardDao.deleteCard(cardId);
        try {
            if (deleteResult != 1) {
                throw new Exception("注销公交卡失败");
            }
        } catch (Exception e) {
            log.error("从数据库删除公交卡失败：{}", e.getMessage(), e);
        }
    }
}
