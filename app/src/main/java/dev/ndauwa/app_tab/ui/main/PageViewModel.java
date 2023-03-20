package dev.ndauwa.app_tab.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private final MutableLiveData<String> courses = new MutableLiveData<>();

    @Override
    public String toString() {
        return courses.getValue();
    }

    public LiveData getCourse() {
        return courses;
    }
    public void addCourse(String course) {
        courses.setValue(course);
    }
}