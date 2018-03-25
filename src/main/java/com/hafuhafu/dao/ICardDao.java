package com.hafuhafu.dao;

import com.hafuhafu.model.Card;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-21
 * Time: 14:37
 */

@Mapper
public interface ICardDao {

    int saveCard(Card card);

    int deleteCard(String cardId);

    int updateCard(Card card);

    Card selectCard(String cardId);
}
