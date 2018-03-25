package com.hafuhafu.model;

import java.util.*;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-20
 * Time: 19:46
 */
public class Station {
    private String name;
    private Station prev;
    private Station next;
    private List<Integer> lineNumber;
    private Map<Station, LinkedHashSet<Station>> orderSetMap = new HashMap<>();

    public Station(String name) {
        this.name = name;
        this.lineNumber = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getPrev() {
        return prev;
    }

    public void setPrev(Station prev) {
        this.prev = prev;
    }

    public Station getNext() {
        return next;
    }

    public void setNext(Station next) {
        this.next = next;
    }

    public List<Integer> getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer number) {
        this.lineNumber.add(number);
    }

    public Map<Station, LinkedHashSet<Station>> getOrderSetMap() {
        return orderSetMap;
    }

    public LinkedHashSet<Station> getAllPassedStations(Station station) {
        if (orderSetMap.get(station) == null) {
            LinkedHashSet<Station> set = new LinkedHashSet<>();
            set.add(this);
            orderSetMap.put(station, set);
        }
        return orderSetMap.get(station);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Station) {
            Station s = (Station) obj;
            return s.getName().equals(this.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
