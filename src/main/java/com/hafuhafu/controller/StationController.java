package com.hafuhafu.controller;

import com.hafuhafu.model.Card;
import com.hafuhafu.model.ResultInfo;
import com.hafuhafu.service.ICardBalanceService;
import com.hafuhafu.service.ICardService;
import com.hafuhafu.service.ISubwayService;
import com.hafuhafu.service.impl.SubwayServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-25
 * Time: 23:34
 */

@Controller
public class StationController {
    @Resource
    private ISubwayService subwayService;
    @Resource
    private ICardService cardService;
    @Resource
    private ICardBalanceService cardBalanceService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/getsubway")
    @ResponseBody
    public ResultInfo<String> checkSubway(@RequestParam("start") String start, @RequestParam("end") String end, HttpServletRequest request) {

        Map<String,Integer> result = subwayService.getSubway(start, end);
        if (result == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "获取最短路径失败", request.getRequestURL().toString(), "");
        } else {
            String subway = "";
            for (String s : result.keySet()) {
                subway = s;
            }
            return new ResultInfo<>(ResultInfo.OK, "获取成功", request.getRequestURL().toString(), subway);
        }
    }

    @RequestMapping("/in")
    @ResponseBody
    public ResultInfo<String> in(@RequestParam("cardId") String cardId,@RequestParam("start") String start ,HttpServletRequest request) {
        Card card = cardService.getCard(cardId);
        if (card == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "公交卡id不存在", request.getRequestURL().toString(), "");
        }

        card = cardBalanceService.in(card,start);
        if (card == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "入站失败，余额不足", request.getRequestURL().toString(), "");
        } else {
            return new ResultInfo<>(ResultInfo.OK, "入站成功", request.getRequestURL().toString(), card.getBalance().toPlainString());
        }
    }

    @RequestMapping("/out")
    @ResponseBody
    public ResultInfo<String> out(@RequestParam("cardId") String cardId,@RequestParam("end") String end ,HttpServletRequest request) {
        Card card = cardService.getCard(cardId);
        if (card == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "公交卡id不存在", request.getRequestURL().toString(), "");
        }
        String result = cardBalanceService.out(card,end);
        if (result == null) {
            return new ResultInfo<>(ResultInfo.ERROR, "出站失败，余额不足", request.getRequestURL().toString(), "");
        } else {
            return new ResultInfo<>(ResultInfo.OK, "出站成功", request.getRequestURL().toString(), result);
        }
    }
}
