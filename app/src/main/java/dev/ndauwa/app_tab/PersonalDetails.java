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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.personal, container, false);
        initialiseComponets(view);
        return view;
    }

    private void initialiseComponets(View view) {
        firstName = view.findViewById(R.id.editTextFirstName);
        lastName = view.findViewById(R.id.editTextLastName);
        middleName = view.findViewById(R.id.editTextMiddleName);
        idNumber = view.findViewById(R.id.editTextIdNumber);
        regNo =view.findViewById(R.id.editTextRegistrationNo);
        genderRadioGroup = view.findViewById(R.id.gender_radio_group);
        course = view.findViewById(R.id.course_spinner);
        school = view.findViewById(R.id.school_spinner);
        department = view.findViewById(R.id.department_spinner);
        submit = view.findViewById(R.id.submit_button);
        cancel = view.findViewById(R.id.cancel_button);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        submit.setOnClickListener(view -> {
            String first = String.valueOf(firstName.getText());
            String middle = middleName.getText().toString();
            String last = lastName.getText().toString();
            String id = idNumber.getText().toString();
            String reg = regNo.getText().toString();
            int selectedId = genderRadioGroup.getCheckedRadioButtonId();
            String gender;
            if (selectedId == R.id.male_radio_button) {
                // Male option is selected
                gender = "Male";
            } else if (selectedId == R.id.female_radio_button) {
                // Female option is selected
                gender = "Female";
            }
            final String[] selectedCourse = {""};
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
            final String[] selectedDepartment = {""};
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
            final String[] selectedSchool = {""};
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
        });
    }
}
