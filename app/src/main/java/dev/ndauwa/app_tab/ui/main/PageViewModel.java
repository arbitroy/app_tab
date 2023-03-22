package dev.ndauwa.app_tab.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ndauwa.app_tab.Student;

public class PageViewModel extends ViewModel {

    private final MutableLiveData<String> courses = new MutableLiveData<>();



    private final MutableLiveData<Student> students = new MutableLiveData<>();

    @Override
    public String toString() {
        return courses.getValue();
    }
    public Student getStudents() {
        return students.getValue();
    }
    public LiveData getCourse() {
        return courses;
    }

    public void addCourse(String course) {
        courses.setValue(course);
    }
    public void addStudent(Student student) {students.setValue(student);}
    public void removeStudent() {
        students.setValue(new Student());
    }
}