package com.hafuhafu.controller;

import com.hafuhafu.dao.ICardDao;
import com.hafuhafu.model.Card;
import com.hafuhafu.model.ResultInfo;
import com.hafuhafu.service.ICardBalanceService;
import com.hafuhafu.service.ICardService;
import com.hafuhafu.util.BalanceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-21
 * Time: 14:12
 */

@Controller
@RequestMapping("/balance")
public class BalanceController {

    @Resource
    private ICardBalanceService cardBalanceService;
    @Resource
    private ICardService cardService;


    @RequestMapping("/home")
    public String balanceInquiry(){
        return "balance";
    }

    @ResponseBody
    @RequestMapping("/getbalance")
    public ResultInfo<String> getBalance(@RequestParam("cardId") String cardId, HttpServletRequest request) {
        Card card = cardService.getCard(cardId);
        if (card == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "公交卡id不存在", request.getRequestURL().toString(), "");
        }
        String balance = cardBalanceService.getBalance(card);
        if (balance == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "余额查询异常", request.getRequestURL().toString(), "");
        } else {
            return new ResultInfo<>(ResultInfo.OK, "查询成功", request.getRequestURL().toString(),
                    balance + "  " + BalanceUtil.number2CNMontrayUnit(card.getBalance()));
        }
    }

    @ResponseBody
    @RequestMapping("/recharge")
    public ResultInfo<String> recharge(@RequestParam("amount") Integer amount, @RequestParam("cardId") String cardId,
                                       HttpServletRequest request) {
        Card card = cardService.getCard(cardId);
        if (card == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "公交卡id不存在", request.getRequestURL().toString(), "");
        }
        String RechargeResult = cardBalanceService.recharge(card, amount);
        if (RechargeResult == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "储值失败，公交卡储值后余额不能超过1000", request.getRequestURL().toString(), "");
        } else {
            return new ResultInfo<>(ResultInfo.OK, "充值成功", request.getRequestURL().toString(), RechargeResult + " " +
                    BalanceUtil.number2CNMontrayUnit(new BigDecimal(RechargeResult)));
        }
    }
}
