package com.hafuhafu.service.impl;

import com.hafuhafu.model.Station;
import com.hafuhafu.service.ISubwayService;
import com.hafuhafu.conf.MetroDataBuilder;
import com.hafuhafu.util.SubwayCalculate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-25
 * Time: 18:17
 */

@Service("subwayService")
public class SubwayServiceImpl implements ISubwayService {
    private final static Logger log = LoggerFactory.getLogger(SubwayServiceImpl.class);

    @Override
    public Map<String,Integer> getSubway(String startStation, String endStation) {
        Station start = null;
        Station end = null;

        try {
            for (Station station : MetroDataBuilder.stationSet) {
                if (station.getName().equals(startStation)) {
                    start = station;
                } else if (station.getName().equals(endStation)) {
                    end = station;
                }
            }
            if (start == null || end == null) {
                throw new Exception("起始点或终点不存在");
            }
            List<Station> subway = serchSubway(start, end);
            String result = changeStation(subway);
            Map<String,Integer> resultMap = new HashMap<>();
            resultMap.put(result, subway.size());

            return resultMap;

        } catch (Exception e) {
            log.error("获取最短路径失败:{}", e.getMessage(), e);
            return null;
        }
    }

    private List<Station> serchSubway(Station start,Station end){
        List<Station> subway = null;
        List<List<Station>> list = SubwayCalculate.subWayMap.get(start);
        for (List<Station> l : list) {
            if (l.get(l.size() - 1).equals(end)) {
                subway = l;
            }
        }

        return subway;
    }

    private String changeStation(List<Station> subway) {
        StringBuilder result = new StringBuilder();

        for (int i=0;i<subway.size();i++) {
            Station station = subway.get(i);

            if (i == 0) {
                result.append(station.getName()).append(" 到 ");
            } else if (i > 1) {
                if (!checkLine(station, subway.get(i - 2))) {
                    result.append(subway.get(i - 1).getName()).append(",").append("换乘")
                            .append(station.getLineNumber().get(0)).append("号线,")
                            .append(subway.get(i - 1).getName()).append(" 到 ");
                }
            }

            if (i == subway.size()-1){
                result.append(subway.get(i).getName());
            }


        }

        return result.toString();
    }

    //检查s1与s2是否为同一条线
    private boolean checkLine(Station s1, Station s2) {
        if (s1.getLineNumber().size() > 1 || s2.getLineNumber().size() >1) {
            //如果s1 or s2是换乘站  直接返回true
            return true;
        }
        return Objects.equals(s1.getLineNumber().get(0), s2.getLineNumber().get(0));


    }


    //public static void main(String[] args) {
    //    SubwayServiceImpl sb = new SubwayServiceImpl();
    //    System.out.println(sb.getSubway("后卫寨", "韦曲南"));
    //
    //}
}
