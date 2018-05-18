package com.projects.pedro.dummywireframe;

/**
 * Represent a row object with the attributes necessary to dynamically create or alter.
 */
public class VacanciesListRow {
    private String btn1, btn2;

    public VacanciesListRow(String btn1, String btn2) {
        this.btn1 = btn1;
        this.btn2 = btn2;
    }

    public String getBtn1() {
        return btn1;
    }

    public String getBtn2() {
        return btn2;
    }
}
