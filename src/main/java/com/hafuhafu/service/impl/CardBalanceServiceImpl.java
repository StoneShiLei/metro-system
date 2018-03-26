package com.hafuhafu.service.impl;

import com.hafuhafu.dao.ICardDao;
import com.hafuhafu.model.BalanceException;
import com.hafuhafu.model.Card;
import com.hafuhafu.model.CardStatusEnum;
import com.hafuhafu.model.CardTypeEnum;
import com.hafuhafu.service.ICardBalanceService;
import com.hafuhafu.service.ISubwayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-22
 * Time: 18:00
 */
@Service("cardBalanceService")
public class CardBalanceServiceImpl implements ICardBalanceService {
    private final static Logger log = LoggerFactory.getLogger(CardBalanceServiceImpl.class);

    @Resource
    ICardDao cardDao;
    @Resource
    ISubwayService subwayService;

    @Override
    public String getBalance(Card card) {
        try {
            BigDecimal balance = card.getBalance();
            if (balance.compareTo(new BigDecimal(1000)) == 1 || balance.compareTo(new BigDecimal(0)) == -1) {
                throw new BalanceException("卡内余额异常");
            }
            return balance.toPlainString();
        } catch (BalanceException e) {
            log.error("余额异常：balance-{},{}",card.getBalance(),e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error("余额查询异常:balance-{},{}",card.getBalance(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String recharge(Card card ,Integer amount) {
        try {
            if (amount <= 0 || amount%10 != 0){
                throw new BalanceException("充值数量为0或不是10的倍数");
            }

            BigDecimal balance = card.getBalance().add(new BigDecimal(amount));
            if (balance.compareTo(new BigDecimal(1000))  == 1 || balance.compareTo(new BigDecimal(0)) == -1){
                throw new BalanceException("充值后卡内余额大于1000或小于0");
            }

            card.setBalance(balance);
            int updateResult = cardDao.updateCard(card);

            if (updateResult == 0) {
                throw new Exception("update操作matched结果为0");
            }
            return card.getBalance().toPlainString();

        } catch (BalanceException e) {
            log.error("充值余额异常：balance-{},amount-{},{}",card.getBalance(),amount, e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error("update异常：balance-{},amount-{},{}",card.getBalance(),amount, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Card in(Card card,String start) {
        try {

            if (card.getCardStatus() == CardStatusEnum.IN) {
                card.setBalance(card.getBalance().subtract(new BigDecimal(5)));
            }

            if (card.getBalance().compareTo(new BigDecimal(1)) != 1) {
                throw new BalanceException("余额不足，无法入站");
            }

            card.setCardStatus(CardStatusEnum.IN);
            card.setCardStart(start);
            int resultUpdate = cardDao.updateCard(card);
            if (resultUpdate == 0) {
                throw new Exception("update失败");
            } else {
                return card;
            }
        } catch (BalanceException e) {
            log.error("余额不足:balance-{},{}", card.getBalance(), e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error("更改状态失败：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String out(Card card,String end) {
        try {
            if (card.getCardStatus() == CardStatusEnum.OUT) {
                card.setBalance(card.getBalance().subtract(new BigDecimal(5)));
            }
            card.setCardEnd(end);
            Map<String, Integer> subway = subwayService.getSubway(card.getCardStart(), end);
            Integer stations = 0;
            for (String s : subway.keySet()) {
                stations = subway.get(s);
            }
            BigDecimal sub = calculate(card, stations);
            card.setBalance(card.getBalance().subtract(sub));
            if (card.getBalance().compareTo(new BigDecimal(0)) == -1) {
                throw new BalanceException("出站余额不足，请先充值");
            } else {
                card.setCardStatus(CardStatusEnum.OUT);
                Integer updateResult = cardDao.updateCard(card);
                if (updateResult == 0) {
                    throw new Exception("update异常");
                }
                return card.getBalance().toPlainString();
            }
        } catch (BalanceException e) {
            log.error("出站余额异常:balance-{},{}", e.getMessage(), e);
            return null;
        } catch (Exception e) {
            log.error("出站异常:{}", e.getMessage(), e);
            return null;
        }
    }

    private BigDecimal calculate(Card card, Integer stations) {
        BigDecimal result = new BigDecimal(0);
        if (stations <= 5) {
            result = result.add(new BigDecimal(1));
        } else if (stations > 5 && stations <= 10) {
            System.out.println(new BigDecimal(2));
            result = result.add(new BigDecimal(2));
        } else if (stations > 10) {
            double temp = 2 + Math.floor(stations / 5);
            result = new BigDecimal(temp);
        }


        if (card.getCardType() == CardTypeEnum.NORMAL) {
            System.out.println(result);
            return result;
        }

        if (card.getCardType() == CardTypeEnum.STUDENT) {
            return result.divide(new BigDecimal(2));
        }

        if (card.getCardType() == CardTypeEnum.SENIOR) {
            //TODO
            return new BigDecimal(0);
        }

        if (card.getCardType() == CardTypeEnum.DISABILITY) {
            //TODO
            return new BigDecimal(0);
        }

        return result;
    }
}
