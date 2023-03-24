package dev.ndauwa.app_tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.ndauwa.app_tab.ui.main.PageViewModel;

public class Summary extends Fragment {
    private PageViewModel viewModel;

    TableLayout courseTable;
    TextView name;
    TextView regNo;
    TextView idNo;
    TextView department;
    TextView cours;
    TextView year;
    TextView semester;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        courseTable = view.findViewById(R.id.courseTable);
        viewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        name = view.findViewById(R.id.name_textview);
        regNo = view.findViewById(R.id.reg_number_textview);
        department = view.findViewById(R.id.department_textview);
        idNo = view.findViewById(R.id.id_number_textview);
        cours = view.findViewById(R.id.course_textview);
        year = view.findViewById(R.id.year);
        semester = view.findViewById(R.id.sem_textview);
        if(viewModel.getStudents().getValue() != null) {
            viewModel.getStudents().observe(getViewLifecycleOwner(), stud -> {
                        name.setText(stud.getFirst()+" "+stud.getMiddle()+" "+stud.getLast());
                        regNo.setText(stud.getReg());
                        idNo.setText(stud.getId());
                        department.setText(stud.getSelectedDepartment());
                        cours.setText(stud.getSelectedCourse());
                        year.setText(stud.getYear());
                        semester.setText(stud.getSemester());
                        populateTable(stud.getSelectedCourse(), stud.getYear(), stud.getSemester());
                    }
            );
        }else {
            Toast.makeText(getContext(), "Input your credentials", Toast.LENGTH_LONG).show();
        }

    }
    public void populateTable(String course, String yr, String sem){
        mDatabase.child("courses").child(course).child(yr).child(sem)
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
