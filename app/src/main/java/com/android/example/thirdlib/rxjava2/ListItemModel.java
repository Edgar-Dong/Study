package com.android.example.thirdlib.rxjava2;

/**
 * @author:無忌
 * @date:2020/12/4
 * @description:
 */
public class ListItemModel {
    private int position;
    private String title;
    private String description;

    public ListItemModel(int position, String title, String description) {
        this.position = position;
        this.title = title;
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ListItemModel{" +
                "position=" + position +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
