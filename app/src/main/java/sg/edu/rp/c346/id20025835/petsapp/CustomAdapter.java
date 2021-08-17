package sg.edu.rp.c346.id20025835.petsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Pet> pets;

    public CustomAdapter(Context context, int resource, ArrayList<Pet> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.pets = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvType = rowView.findViewById(R.id.tvType);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        TextView tvYears = rowView.findViewById(R.id.tvYears);

        // Obtain the Android Version information based on the position
        Pet currentPet = pets.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentPet.getName());
        tvType.setText(currentPet.getType());
        tvDescription.setText(currentPet.getDescription());
        tvYears.setText(currentPet.getYearReleased() + "");

        if(currentPet.getYearReleased() >= 2019){

        }
        else {

        }

        return rowView;
    }

}
