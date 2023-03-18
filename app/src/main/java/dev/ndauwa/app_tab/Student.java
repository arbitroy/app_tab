package dev.ndauwa.app_tab;

public class Student {
    private String first;
    private String middle;
    private String last;
    private String id;
    private String reg;
    private String gender;
    private String[] selectedDepartment = {""};
    private String[] selectedCourse = new String[1];
    private String[] selectedSchool = {""};

    public Student(String first, String middle, String last, String id, String reg, String gender, String[] selectedDepartment, String[] selectedCourse, String[] selectedSchool) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.id = id;
        this.reg = reg;
        this.gender = gender;
        this.selectedDepartment = selectedDepartment;
        this.selectedCourse = selectedCourse;
        this.selectedSchool = selectedSchool;
    }

    public Student(String first, String middle, String last, String id, String reg, String gender, String s, String s1, String s2) {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getSelectedDepartment() {
        return selectedDepartment;
    }

    public String[] getSelectedCourse() {
        return selectedCourse;
    }

    public String[] getSelectedSchool() {
        return selectedSchool;
    }


}
