package com.mackoandyelomartinezgmail.oryzaapp.TabFragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mackoandyelomartinezgmail.oryzaapp.R;

import static android.content.ContentValues.TAG;

public class SacksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //update data from database
        DatabaseReference humidityRef = database.getReference("Arduino_Humidity");
        DatabaseReference temperatureRef = database.getReference("Arduino_Humidity");
//        myRef.setValue("awdadwadawbdawd");

        // Read from the database
        humidityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                setHumidityText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        temperatureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                setTemperatureText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return inflater.inflate(R.layout.fragment_sacks, container, false);
    }
    public interface OnFragmentInteractionListener {
        // NOTE : We changed the Uri to String.
        void onFragmentInteraction(String title);
    }
    public void setTemperatureText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.humidityValue);
        textView.setText(text);
    }
    public void setHumidityText(String text)
    {
        TextView textView = (TextView) getView().findViewById(R.id.temperatureValue);
        textView.setText(text);
    }
}