package dev.ndauwa.app_tab;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


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
    final String[] selectedDepartment = {""};
    final String[] selectedCourse = new String[1];
    final String[] selectedSchool = {""};
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase = database.getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.personal, container, false);
        initialiseComponets(view);
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

            course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Get the selected item's text
                    selectedCourse[0] = parentView.getItemAtPosition(position).toString();
                    // If the selected item is empty, display a pop-up message
                    if (selectedCourse[0].isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Please select an item from the spinner")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Get the selected item's text
                    selectedDepartment[0] = parentView.getItemAtPosition(position).toString();
                    // If the selected item is empty, display a pop-up message
                    if (selectedDepartment[0].isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Please select an item from the spinner")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Get the selected item's text
                    selectedSchool[0] = parentView.getItemAtPosition(position).toString();
                    // If the selected item is empty, display a pop-up message
                    if (selectedSchool[0].isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Please select an item from the spinner")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            Map<String, String> student = new HashMap<>();
            student.put("firstName", first);
            student.put("middleName", middle);
            student.put("lastName", last);
            student.put("idNumber",id);
            student.put("regNo", reg);
            student.put("gender",gender);
            student.put("course", selectedCourse[0]);
            student.put("department", selectedDepartment[0]);
            student.put("school", selectedSchool[0]);
            mDatabase.child("students").child("student").setValue(student);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
