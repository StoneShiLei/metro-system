package com.hafuhafu.util;

import com.hafuhafu.conf.MetroDataBuilder;
import com.hafuhafu.model.Station;

import java.util.*;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-20
 * Time: 19:45
 */

public class SubwayCalculate {
    public static HashMap<Station,List<List<Station>>> subWayMap = new HashMap<>();
    private List<Station> outList = new ArrayList<>();

    static {
        for (Station start : MetroDataBuilder.stationSet) {
            List<List<Station>> list = new LinkedList<>();
            for (Station end : MetroDataBuilder.stationSet) {
                if (start.equals(end)) {
                    continue;
                }
                SubwayCalculate sw = new SubwayCalculate();
                sw.calculate(start, end);
                List<Station> tempList = new ArrayList<>();
                tempList.addAll(start.getAllPassedStations(end));

                for (Station s1 : tempList) {
                    for (Station s2 : MetroDataBuilder.stationSet) {
                        if (s1.getName().equals(s2.getName())) {
                            tempList.set(tempList.indexOf(s1), s2);
                        }
                    }
                }

                list.add(tempList);
            }
            subWayMap.put(start, list);
        }
    }

    private void calculate(Station s1, Station s2) {

        if (outList.size() == MetroDataBuilder.totalStation){
            //System.out.printf("起点：%s  到  终点：%s  共经过%s站\n", s1.getName(), s2.getName(), s1.getAllPassedStations(s2).size() - 1);
            //for (Station station : s1.getAllPassedStations(s2)) {
            //    System.out.print(station.getName() + "->");
            //}
            //System.out.println();
            return;
        }

        if (!outList.contains(s1)) {
            outList.add(s1);
        }

        if (s1.getOrderSetMap().isEmpty()) {
            List<Station> Stations = getAllLinkedStations(s1);
            for (Station s : Stations) {
                s1.getAllPassedStations(s).add(s);
            }
        }

        Station parent = getShortestPath(s1);

        if (parent.equals(s2)) {
            //System.out.printf("起点：%s  到  终点：%s  共经过%s站\n", s1.getName(), s2.getName(), s1.getAllPassedStations(s2).size() - 1);
            //for (Station station : s1.getAllPassedStations(s2)) {
            //    System.out.print(station.getName() + "->");
            //}
            //System.out.println();
            return;
        }
        for (Station child : getAllLinkedStations(parent)) {
            if (outList.contains(child)) {
                continue;
            }
            int shortestPath = (s1.getAllPassedStations(parent).size() - 1) + 1;
            if (s1.getAllPassedStations(child).contains(child)) {
                if ((s1.getAllPassedStations(child).size() - 1) > shortestPath) {
                    s1.getAllPassedStations(child).clear();
                    s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                    s1.getAllPassedStations(child).add(child);
                }
            } else {
                s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                s1.getAllPassedStations(child).add(child);
            }
        }
        outList.add(parent);
        calculate(s1,s2);
    }

    private Station getShortestPath(Station station) {
        int minPath = Integer.MAX_VALUE;
        Station rets = null;
        for (Station s : station.getOrderSetMap().keySet()) {
            if (outList.contains(s)) {
                continue;
            }
            LinkedHashSet<Station> set = station.getAllPassedStations(s);
            if (set.size() < minPath) {
                minPath = set.size();
                rets = s;
            }
        }
        return rets;
    }

    private List<Station> getAllLinkedStations(Station station) {
        List<Station> linkedStations = new ArrayList<>();
        for (List<Station> line : MetroDataBuilder.lineSet) {
            if (line.contains(station)) {
                Station s = line.get(line.indexOf(station));
                if (s.getPrev() != null) {
                    linkedStations.add(s.getPrev());
                }
                if (s.getNext() != null) {
                    linkedStations.add(s.getNext());
                }
            }
        }
        return linkedStations;
    }

}
