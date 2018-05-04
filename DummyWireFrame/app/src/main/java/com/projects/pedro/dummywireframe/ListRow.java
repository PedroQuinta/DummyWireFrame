package com.projects.pedro.dummywireframe;

/**
 * Class adapted from: https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 * Represents the Text View row to be inserted in the recycler view.
 */
public class ListRow {

    private String title;

    public ListRow() {
    }

    /**
     * Constructor that initializes the title.
     * @param title String as title.
     */
    public ListRow(String title){
        this.title = title;
    }

    /**
     * Getter for the field title.
     * @return String as title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the field title.
     * @param name String to serve as title.
     */
    public void setTitle(String name) {
        this.title = name;
    }

}
