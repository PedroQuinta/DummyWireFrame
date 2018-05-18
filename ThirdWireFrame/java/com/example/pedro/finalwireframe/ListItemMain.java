package com.example.pedro.finalwireframe;

import android.widget.Button;

public class ListItemMain {
    private String title;
    private String btn1, btn2, btn3;

    public ListItemMain(String title, String btn1, String btn2, String btn3) {
        this.title = title;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
    }

    public String getTitle() {
        return title;
    }

    public String getBtn1() {
        return btn1;
    }

    public String getBtn2() {
        return btn2;
    }

    public String getBtn3() {
        return btn3;
    }
}
