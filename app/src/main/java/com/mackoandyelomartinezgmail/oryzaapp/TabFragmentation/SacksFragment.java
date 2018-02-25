package com.mackoandyelomartinezgmail.oryzaapp.TabFragmentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.mackoandyelomartinezgmail.oryzaapp.R;

import static android.content.ContentValues.TAG;

public class SacksFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }
    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_sacks, container, false);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //update data from database
        DatabaseReference humidityRef = database.getReference("Arduino_Humidity");
        DatabaseReference temperatureRef = database.getReference("Arduino_Temperature");
//        myRef.setValue("awdadwadawbdawd");

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
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        String url = " <html>\n" +
                "<body>\n" +
                "<div id=\"chart_div\"></div>\n" +
                "</body>\n" +
                "</html>\n" +
                "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                " \n" +
                "<script> \n" +
                "\n" +
                "google.charts.load('current', {packages: ['corechart', 'line']});\n" +
                "google.charts.setOnLoadCallback(drawLogScales);\n" +
                "\n" +
                "function drawLogScales() {\n" +
                "      var data = new google.visualization.DataTable();\n" +
                "      data.addColumn('number', 'X');\n" +
                "      data.addColumn('number', 'Dogs');\n" +
                "      data.addColumn('number', 'Cats');\n" +
                "\n" +
                "      data.addRows([\n" +
                "        [0, 0, 0],    [1, 10, 5],   [2, 23, 15],  [3, 17, 9],   [4, 18, 10],  [5, 9, 5],\n" +
                "        [6, 11, 3],   [7, 27, 19],  [8, 33, 25],  [9, 40, 32],  [10, 32, 24], [11, 35, 27],\n" +
                "        [12, 30, 22], [13, 40, 32], [14, 42, 34], [15, 47, 39], [16, 44, 36], [17, 48, 40],\n" +
                "        [18, 52, 44], [19, 54, 46], [20, 42, 34], [21, 55, 47], [22, 56, 48], [23, 57, 49],\n" +
                "        [24, 60, 52], [25, 50, 42], [26, 52, 44], [27, 51, 43], [28, 49, 41], [29, 53, 45],\n" +
                "        [30, 55, 47], [31, 60, 52], [32, 61, 53], [33, 59, 51], [34, 62, 54], [35, 65, 57],\n" +
                "        [36, 62, 54], [37, 58, 50], [38, 55, 47], [39, 61, 53], [40, 64, 56], [41, 65, 57],\n" +
                "        [42, 63, 55], [43, 66, 58], [44, 67, 59], [45, 69, 61], [46, 69, 61], [47, 70, 62],\n" +
                "        [48, 72, 64], [49, 68, 60], [50, 66, 58], [51, 65, 57], [52, 67, 59], [53, 70, 62],\n" +
                "        [54, 71, 63], [55, 72, 64], [56, 73, 65], [57, 75, 67], [58, 70, 62], [59, 68, 60],\n" +
                "        [60, 64, 56], [61, 60, 52], [62, 65, 57], [63, 67, 59], [64, 68, 60], [65, 69, 61],\n" +
                "        [66, 70, 62], [67, 72, 64], [68, 75, 67], [69, 80, 72]\n" +
                "      ]);\n" +
                "\n" +
                "      var options = {\n" +
                "        hAxis: {\n" +
                "          title: 'Time',\n" +
                "          logScale: true\n" +
                "        },\n" +
                "        vAxis: {\n" +
                "          title: 'Popularity',\n" +
                "          logScale: false\n" +
                "        },\n" +
                "        colors: ['#a52714', '#097138']\n" +
                "      };\n" +
                "\n" +
                "      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));\n" +
                "      chart.draw(data, options);\n" +
                "    }\n" +
                "</script>";

        WebView wv = (WebView) view.findViewById(R.id.simpleWebView);
        wv.loadData(url, "text/html", null);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;
       // return inflater.inflate(R.layout.fragment_sacks, container, false);



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