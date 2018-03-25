package com.hafuhafu.conf;

import com.hafuhafu.model.Station;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-20
 * Time: 19:47
 */
public class MetroDataBuilder {
    public static List<Station> line1 = new ArrayList<>();
    public static List<Station> line2 = new ArrayList<>();
    public static List<Station> line3 = new ArrayList<>();
    public static List<Station> totalLine = new ArrayList<>();
    public static Set<List<Station>> lineSet = new HashSet<>();
    public static Set<Station> stationSet = new HashSet<>();
    public static int totalStation = 0;

    static {
        String line1str = "后卫寨、三桥、皂河、枣园、汉城路、开远门、劳动路、玉祥门、洒金桥、北大街、五路口、朝阳门、康复路、通化门、" +
                "万寿路、长乐坡、浐河、半坡、纺织城";
        String line2str = "北客站、北苑、运动公园、行政中心、凤城五路、市图书馆、大明宫西、龙首原、安远门、北大街、钟楼、永宁门、南稍门、" +
                "体育场、小寨、纬一街、会展中心、三爻、凤栖原、航天城、韦曲南";
        String line3str = "鱼化寨、丈八北路、延平门、科技路、太白南路、吉祥村、小寨、大雁塔、北池头、青龙寺、延兴门、咸宁路、长乐公园、" +
                "通化门、胡家庙、石家街、辛家庙、广泰门、桃花潭、浐灞中心、香湖湾、务庄、国际港务区、双寨、新筑、保税区";

        buildStation(line1str, line1);
        buildStation(line2str, line2);
        buildStation(line3str, line3);




        totalLine.addAll(line1);
        totalLine.addAll(line2);
        totalLine.addAll(line3);

        stationSet.addAll(totalLine);
        setLineNumber(line1, 1);
        setLineNumber(line2, 2);
        setLineNumber(line3, 3);

        lineSet.add(line1);
        lineSet.add(line2);
        lineSet.add(line3);

        totalStation = line1.size() + line2.size() + line3.size();

    }


    private static void buildStation(String lineStr,List<Station> line) {
        String[] lineArr = lineStr.split("、");
        for (String s : lineArr) {
            line.add(new Station(s));
        }
        for (int i=0;i<line.size();i++) {
            if (i < line.size() - 1) {
                line.get(i).setNext(line.get(i + 1));
                line.get(i + 1).setPrev(line.get(i));
            }
        }
    }

    private static void setLineNumber(List<Station> line,Integer number){
        for (Station station : stationSet) {
            if (line.contains(station)) {
                station.setLineNumber(number);
            }
        }
        //for (Station s : line) {
        //    s.setLineNumber(number);
        //}
    }
}
