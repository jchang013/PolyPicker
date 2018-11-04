package com.example.tccl9.polypicker;
import java.io.Serializable;

public class Course implements Serializable {
    private String code;
    private String name;
    private String category;
    private String school;
    private String polytechnic;
    private String description;
    private int cutoff;
    private String link;
    private int bookmark;

    public Course() { }

    public Course(String code, String name, String category, String school, String polytechnic, String description,
                  int cutoff, String link, int bookmark) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.school = school;
        this.polytechnic = polytechnic;
        this.description = description;
        this.cutoff = cutoff;
        this.link = link;
        this.bookmark = bookmark;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool() {
        this.school = school;
    }

    public String getPolytechnic() {
        return this.polytechnic;
    }

    public void setPolytechnic(String polytechnic) {
        this.polytechnic = polytechnic;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCutoff() {
        return this.cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink() {
        this.link = link;
    }

    public int getBookmark() {
        return this.bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }
}
