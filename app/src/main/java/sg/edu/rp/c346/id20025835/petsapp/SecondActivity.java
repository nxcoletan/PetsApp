package sg.edu.rp.c346.id20025835.petsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lv;
    ArrayList<Pet> petList;
	//ArrayAdapter<Song> adapter;
    Button btnCats;

    ArrayList<String> years;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    CustomAdapter adapter;
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        petList.clear();
        petList.addAll(dbh.getAllPets());
        adapter.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getYears());
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) this.findViewById(R.id.lv);
        btnCats = (Button) this.findViewById(R.id.btnShowCat);
        spinner = (Spinner) this.findViewById(R.id.spinnerYear);

        DBHelper dbh = new DBHelper(this);
        petList = dbh.getAllPets();
        years = dbh.getYears();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, petList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Pet", petList.get(position));
                startActivity(i);
            }
        });

        btnCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                petList.clear();
                petList.addAll(dbh.getAllSongsByType("Cat"));
                adapter.notifyDataSetChanged();
            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                petList.clear();
                petList.addAll(dbh.getAllSongsByYear(Integer.valueOf(years.get(position))));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
