package sg.edu.rp.c346.id20025835.petsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etType, etDescription, etYear;
    Button btnCancel, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etType = findViewById(R.id.etType);
        etDescription = findViewById(R.id.etDescription);
        etYear = findViewById(R.id.etYear);

        Intent i = getIntent();
        final Pet currentPet = (Pet) i.getSerializableExtra("Pet");

        etID.setText(currentPet.getId()+"");
        etName.setText(currentPet.getName());
        etType.setText(currentPet.getType());
        etDescription.setText(currentPet.getDescription());
        etYear.setText(currentPet.getYearReleased()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentPet.setName(etName.getText().toString().trim());
                currentPet.setType(etType.getText().toString().trim());
                currentPet.setDescription(etDescription.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentPet.setYearReleased(year);

                int result = dbh.updatePet(currentPet);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deletePet(currentPet.getId());
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}