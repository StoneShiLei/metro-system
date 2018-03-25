package com.hafuhafu.service;

import java.util.Map;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-20
 * Time: 19:45
 */
public interface ISubwayService {

    Map<String,Integer> getSubway(String start, String end);
}
