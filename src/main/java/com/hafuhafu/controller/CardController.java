package com.hafuhafu.controller;

import com.hafuhafu.model.Card;
import com.hafuhafu.model.CardTypeEnum;
import com.hafuhafu.model.ResultInfo;
import com.hafuhafu.service.ICardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-23
 * Time: 18:42
 */

@Controller
@RequestMapping("/card")
public class CardController {

    @Resource
    private ICardService cardService;

    @RequestMapping("/manage")
    public String card() {
        return "card";
    }

    @RequestMapping("/newcard")
    @ResponseBody
    public ResultInfo<String> newCard(@RequestParam("cardType")CardTypeEnum cardType, HttpServletRequest request){
        String cardId = cardService.newCard(cardType);
        if (cardId == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "公交卡创建失败", request.getRequestURL().toString(), "");
        } else {
            return new ResultInfo<>(ResultInfo.OK, "创建成功", request.getRequestURL().toString(), cardId);
        }

    }

    @RequestMapping("/destroycard")
    @ResponseBody
    public ResultInfo<String> destoryCard(@RequestParam("cardId") String cardId, HttpServletRequest request) {
        Card card = cardService.getCard(cardId);
        if (card == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "公交卡id不存在", request.getRequestURL().toString(), "");
        } else {
            cardService.destroyCard(cardId);
            return new ResultInfo<>(ResultInfo.OK, "注销成功", request.getRequestURL().toString(), "");
        }

    }
}
