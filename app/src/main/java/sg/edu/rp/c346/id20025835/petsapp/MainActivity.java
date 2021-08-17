package sg.edu.rp.c346.id20025835.petsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etType, etDescription, etYear;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = findViewById(R.id.etName);
        etType = findViewById(R.id.etType);
        etDescription = findViewById(R.id.etDescription);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsertSong);
        btnShowList = findViewById(R.id.btnShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String type = etType.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (name.length() == 0 || type.length() == 0|| description.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String year_str = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(year_str);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                dbh.insertPet(name, type, description, year);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etName.setText("");
                etType.setText("");
                etDescription.setText("");
                etYear.setText("");

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }
}
