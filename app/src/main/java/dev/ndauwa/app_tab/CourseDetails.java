package dev.ndauwa.app_tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import dev.ndauwa.app_tab.ui.main.PageViewModel;

public class CourseDetails extends Fragment {

    Spinner courseSpinner;
    TableLayout courseTable;
    Button save;
    Button next;
    ViewPager pager;

    Spinner year;
    Spinner semester;
    String yr = "";
    String sem = "";
    private PageViewModel viewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_details, container, false);
        year = view.findViewById(R.id.year_spinner);
        semester = view.findViewById(R.id.semester_spinner);
        yr = year.getSelectedItem().toString();
        sem =  semester.getSelectedItem().toString();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        courseSpinner = view.findViewById(R.id.course_spinner);
        courseTable = view.findViewById(R.id.course_table);
        save = view.findViewById(R.id.save_button);
        next = view.findViewById(R.id.next_button);

        List<String>data = new ArrayList<>();
        viewModel.getCourse().observe(getViewLifecycleOwner(), course ->{
            data.clear();
            data.add(viewModel.toString());
            // Create an ArrayAdapter and set it as the adapter for the spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            courseSpinner.setAdapter(adapter);

            semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    sem = adapterView.getItemAtPosition(i).toString();
                    populateTable(viewModel.toString(),yr,sem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    yr = adapterView.getItemAtPosition(i).toString();
                    populateTable(viewModel.toString(),yr,sem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            populateTable(viewModel.toString(),yr,sem);


       });

        save.setOnClickListener(view1 -> {
            Student student = viewModel.getStudents();
            student.setYear("Year "+yr);
            student.setSemester("Semester "+sem);
            String reg = student.getReg();
            mDatabase.child("students").child(reg).setValue(student);
            Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
            semester.setSelection(0);
            year.setSelection(0);
        });


        next.setOnClickListener(view1 -> {
            pager = view.getRootView().findViewById(R.id.view_pager);
            pager.setCurrentItem(2);
        });
    }
    public void populateTable(String course, String yr, String sem){
        mDatabase.child("courses").child(course).child("Year "+yr).child("Semester " + sem)
                .get().addOnCompleteListener(task -> {
                    courseTable.removeAllViews();
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
                        DataSnapshot dataSnapshot = task.getResult();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                                String courseCode = courseSnapshot.getValue(String.class);
                                // Add the course data to your table
                                TableRow row = new TableRow(getContext());
                                TextView courseView = new TextView(getContext());
                                courseView.setText(courseCode);
                                row.addView(courseView);
                                courseTable.addView(row);
                            }
                        }else {
                            Toast.makeText(getContext(),"Unavailable",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
