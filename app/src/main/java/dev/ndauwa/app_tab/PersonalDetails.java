package dev.ndauwa.app_tab;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

import dev.ndauwa.app_tab.ui.main.PageViewModel;


public class PersonalDetails extends Fragment {
    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText idNumber;
    private EditText regNo;
    private RadioGroup genderRadioGroup;
    private Spinner course;
    private Spinner department;
    private Spinner school;
    private Button submit;
    private Button cancel;
    private String first;
    private String middle;
    private String last;
    private String id;
    private String reg;
    private String gender;
    private String selectedDepartment;
    private String selectedCourse;
    private String selectedSchool;
    private PageViewModel viewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.personal, container, false);
        initialiseComponets(view);
        viewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item's text
                String selectedItemText = parentView.getItemAtPosition(position).toString();
                selectedCourse = selectedItemText;
                viewModel.addCourse(selectedCourse);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item's text
                String selectedItemText = parentView.getItemAtPosition(position).toString();
                selectedDepartment = selectedItemText;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(),"nothing" , Toast.LENGTH_LONG).show();
            }
        });

        school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item's text
                String selectedItemText = parentView.getItemAtPosition(position).toString();
                selectedSchool = selectedItemText;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cancel.setOnClickListener(veiw2 ->{
            firstName.setText("");
            middleName.setText("");
            lastName.setText("");
            idNumber.setText("");
            regNo.setText("");
            genderRadioGroup.clearCheck();
            course.setSelection(0);
            school.setSelection(0);
            department.setSelection(0);
            viewModel.removeStudent();
        });
        submit.setOnClickListener(view1 -> {
            first = String.valueOf(firstName.getText());
            middle = middleName.getText().toString();
            last = lastName.getText().toString();
            id = idNumber.getText().toString();
            reg = regNo.getText().toString();
            int selectedId = genderRadioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.male_radio_button) {
                // Male option is selected
                gender = "Male";
            } else if (selectedId == R.id.female_radio_button) {
                // Female option is selected
                gender = "Female";
            }
            Student student = new Student(first,middle,last,id,reg,gender,selectedDepartment,selectedCourse,selectedSchool);
            viewModel.addStudent(student);
            firstName.setText("");
            middleName.setText("");
            lastName.setText("");
            idNumber.setText("");
            regNo.setText("");
            genderRadioGroup.clearCheck();
            school.setSelection(0);
            department.setSelection(0);
            Toast.makeText(getContext(), "Proceed to enter the course details", Toast.LENGTH_LONG).show();
        });
        return view;
    }

    private void initialiseComponets(View view) {
        submit = view.findViewById(R.id.submit_button);
        firstName = view.findViewById(R.id.editTextFirstName);
        lastName = view.findViewById(R.id.editTextLastName);
        middleName = view.findViewById(R.id.editTextMiddleName);
        idNumber = view.findViewById(R.id.editTextIdNumber);
        regNo =view.findViewById(R.id.editTextRegistrationNo);
        genderRadioGroup = view.findViewById(R.id.gender_radio_group);
        course = view.findViewById(R.id.course_spinner);
        school = view.findViewById(R.id.school_spinner);
        department = view.findViewById(R.id.department_spinner);
        cancel = view.findViewById(R.id.cancel_button);

    }

}
