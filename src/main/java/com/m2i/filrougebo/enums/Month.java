package com.m2i.filrougebo.enums;

public enum Month {
    JANUARY(1, "Janvier"),
    FEBRUARY(2, "Février"),
    MARCH(3, "Mars"),
    APRIL(4, "Avril"),
    MAY(5, "Mai"),
    JUNE(6, "Juin"),
    JULY(7, "Juillet"),
    AUGUST(8, "Août"),
    SEPTEMBER(9, "Septembre"),
    OCTOBER(10, "Octobre"),
    NOVEMBER(11, "Novembre"),
    DECEMBER(12, "Décembre");

    public final int id;
    public final String label;

    private Month(int id, String label) {
        this.id = id;
        this.label = label;
    }
    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
