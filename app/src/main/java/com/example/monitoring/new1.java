package com.example.monitoring;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class new1 extends AppCompatActivity {

    private TextView dataTextView;
    private List<List<String>> temp1Lists = new ArrayList<>();
    private List<List<String>> temp2Lists = new ArrayList<>();
    private List<List<String>> temp3Lists = new ArrayList<>();
    private List<List<String>> temp4Lists = new ArrayList<>();
    private List<List<String>> temp5Lists = new ArrayList<>();
    private List<String> TimeRowList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new1);

        final String SCRIPT_URL = "https://script.google.com/macros/s/AKfycbycAnRX-0fNBzpLI9J1UemtaAVB5JA9prClA71q4R7UrAxyS2C5brPhaAMGn2sczlyF/exec?getapp=1";
        //final List<List<String>> temp1Lists = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a URL object for the script
                    URL url = new URL(SCRIPT_URL);

                    // Make an HTTP GET request to the script
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    // Read the response from the server
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String responseData = reader.readLine();
//                    System.out.println(responseData);



                    String[] dataList = responseData.split("\\|\\|");
                    List<List<String>> rowList = new ArrayList<>();

                    // Iterate over the dataList and split each row into a separate list
                    for (String row : dataList) {
                        List<String> colList = Arrays.asList(row.split(","));
                        rowList.add(colList);
                    }
                    // Iterate over the rowList and split each row into separate lists using "*"
                    // Iterate over the rowList and print each row
                    // Get the first rowList
                    List<String> DateRowList = rowList.get(0);
                    List <String> TimeList = rowList.get(1);
                    for(String val:TimeList){
                        if(val.contains("Time")){
                            continue;
                        }
                        else{
                            TimeRowList.add(val);
                            //System.out.println(val);
                        }
                    }
                    System.out.println(TimeRowList);
                    List<String> Hard1RowList = rowList.get(2);
                    List<String> Hard2RowList = rowList.get(3);
                    List<String> Hard3RowList = rowList.get(4);
                    List<String> Hard4RowList = rowList.get(5);
                    List<String> Hard5RowList = rowList.get(6);
//                    List<List<String>> temp1Lists = new ArrayList<>();
                    List<String> temp1Data = new ArrayList<>();
                    for (String data : Hard1RowList) {
                        if (data.contains("*")) {
                            temp1Lists.add(temp1Data);
                            temp1Data = new ArrayList<>();
                        }
                        else if(data.contains("TEMP1")){
                            continue;
                        }
                        else {
                            temp1Data.add(data);
                        }
                    }
// Add the last list of data to the list of lists
                    temp1Lists.add(temp1Data);

// Iterate over the temp1Lists and print each list
                    for (List<String> list : temp1Lists) {
                        System.out.println(list);
                    }

                    //List<List<String>> temp2Lists = new ArrayList<>();
                    List<String> temp2Data = new ArrayList<>();
                    for (String data : Hard2RowList) {
                        if (data.contains("*")) {
                            temp2Lists.add(temp2Data);
                            temp2Data = new ArrayList<>();
                        }
                        else if(data.contains("TEMP2")){
                            continue;
                        }
                        else {
                            temp2Data.add(data);
                        }
                    }

                    temp2Lists.add(temp2Data);


                    List<String> temp3Data = new ArrayList<>();
                    for (String data : Hard3RowList) {
                        if (data.contains("*")) {
                            temp3Lists.add(temp3Data);
                            temp3Data = new ArrayList<>();
                        }
                        else if(data.contains("TEMP3")){
                            continue;
                        }
                        else {
                            temp3Data.add(data);
                        }
                    }
                    temp3Lists.add(temp3Data);
                    List<String> temp4Data = new ArrayList<>();
                    for (String data : Hard4RowList) {
                        if (data.contains("*")) {
                            temp4Lists.add(temp4Data);
                            temp4Data = new ArrayList<>();
                        }
                        else if(data.contains("TEMP4")){
                            continue;
                        }else {
                            temp4Data.add(data);
                        }
                    }
                    temp4Lists.add(temp4Data);

                    List<String> temp5Data = new ArrayList<>();
                    for (String data : Hard5RowList) {
                        if (data.contains("*")) {
                            temp5Lists.add(temp5Data);
                            temp5Data = new ArrayList<>();
                        }
                        else if(data.contains("TEMP5")){
                            continue;
                        }
                        else {
                            temp5Data.add(data);
                        }
                    }
                    temp5Lists.add(temp5Data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Get reference to the TextView that will display the data
        dataTextView = findViewById(R.id.dataTextView);

        // Create an ArrayAdapter for the spinner
        String[] items = new String[]{"--Select--","Hardware 1", "Hardware 2", "Hardware 3", "Hardware 4", "Hardware 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                // Get the selected item
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                // If the selected item is "Item 1", call the readDataFromScript() method
                if(selectedItem.equals("Hardware 1")) {
                    //readDataFromScript();

                    createChart(temp1Lists,TimeRowList);
                }
                if(selectedItem.equals("Hardware 2")) {
                    //readDataFromScript();
                    createChart(temp2Lists,TimeRowList);
                }
                if(selectedItem.equals("Hardware 3")) {
                    //readDataFromScript();
                    createChart(temp3Lists,TimeRowList);
                }
                if(selectedItem.equals("Hardware 4")) {
                    //readDataFromScript();
                    createChart(temp4Lists,TimeRowList);
                }
                if(selectedItem.equals("Hardware 5")) {
                    //readDataFromScript();
                    createChart(temp5Lists,TimeRowList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }


    private void createChart(List<List<String>> data,List<String> Time){
        LineChart mLineChart = findViewById(R.id.line_chart);
        List<Entry> entries = new ArrayList<>();
        SimpleDateFormat format=new SimpleDateFormat("hh:mm", Locale.US);
        for(int i=0;i<data.size();i++) {
            List<String> values = data.get(i);

            try {
                int j=0;

                for (String val : values) {
                    String timeString = Time.get(j);
                    Date date = format.parse(timeString);
                    long timestamp = date.getTime();
                    if (Integer.parseInt(val) >= 0) {
                        entries.add(new Entry(Integer.parseInt(val), timestamp));
                    }
                    //i++;
                    j++;
                }
            }catch(ParseException e){
                throw new RuntimeException(e);
            }

            break;



        }
        LineData lineData = new LineData(new LineDataSet(entries, "Temperature"));

        // customize chart
        mLineChart.setData(lineData);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis = mLineChart.getAxisLeft();
        yAxis.setGranularity(1);

        mLineChart.getAxisRight().setEnabled(false);

        mLineChart.getDescription().setEnabled(false);
        mLineChart.invalidate(); // refresh chart

        //graph for chart2

        LineChart mLineChart2 = findViewById(R.id.line_chart);
        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(1, 10));
        entries2.add(new Entry(2, 20));
        entries2.add(new Entry(3, 30));
        entries2.add(new Entry(4, 25));
        entries2.add(new Entry(5, 15));

        //graph for chart3

        LineChart mLineChart3 = findViewById(R.id.line_chart2);
        List<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(1, 10));
        entries3.add(new Entry(2, 20));
        entries3.add(new Entry(3, 30));
        entries3.add(new Entry(4, 25));
        entries3.add(new Entry(5, 15));

        LineData lineData3 = new LineData(new LineDataSet(entries3, "H2"));

        // customize chart
        mLineChart3.setData(lineData3);
        mLineChart3.setTouchEnabled(true);
        mLineChart3.setDragEnabled(true);
        mLineChart3.setScaleEnabled(true);

        XAxis xAxis3 = mLineChart3.getXAxis();
        xAxis3.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis3.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis3 = mLineChart3.getAxisLeft();
        yAxis3.setGranularity(1);

        mLineChart3.getAxisRight().setEnabled(false);

        mLineChart3.getDescription().setEnabled(false);
        mLineChart3.invalidate();

        //graph for chart4

        LineChart mLineChart4 = findViewById(R.id.line_chart3);
        List<Entry> entries4 = new ArrayList<>();
        entries4.add(new Entry(1, 10));
        entries4.add(new Entry(2, 20));
        entries4.add(new Entry(3, 30));
        entries4.add(new Entry(4, 25));
        entries4.add(new Entry(5, 15));

        LineData lineData4 = new LineData(new LineDataSet(entries, "LPG"));

        // customize chart
        mLineChart4.setData(lineData4);
        mLineChart4.setTouchEnabled(true);
        mLineChart4.setDragEnabled(true);
        mLineChart4.setScaleEnabled(true);

        XAxis xAxis4 = mLineChart4.getXAxis();
        xAxis4.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis4.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis4 = mLineChart4.getAxisLeft();
        yAxis4.setGranularity(1);

        mLineChart4.getAxisRight().setEnabled(false);

        mLineChart4.getDescription().setEnabled(false);
        mLineChart4.invalidate();

        //graph for chart5

        LineChart mLineChart5 = findViewById(R.id.line_chart4);
        List<Entry> entries5 = new ArrayList<>();
        entries5.add(new Entry(1, 10));
        entries5.add(new Entry(2, 20));
        entries5.add(new Entry(3, 30));
        entries5.add(new Entry(4, 25));
        entries5.add(new Entry(5, 15));

        LineData lineData5 = new LineData(new LineDataSet(entries, "CH4"));

        // customize chart
        mLineChart5.setData(lineData5);
        mLineChart5.setTouchEnabled(true);
        mLineChart5.setDragEnabled(true);
        mLineChart5.setScaleEnabled(true);

        XAxis xAxis5 = mLineChart5.getXAxis();
        xAxis5.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis5.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis5 = mLineChart5.getAxisLeft();
        yAxis5.setGranularity(1);

        mLineChart5.getAxisRight().setEnabled(false);

        mLineChart5.getDescription().setEnabled(false);
        mLineChart5.invalidate();


        //graph for chart6

        LineChart mLineChart6 = findViewById(R.id.line_chart5);
        List<Entry> entries6 = new ArrayList<>();
        entries6.add(new Entry(1, 10));
        entries6.add(new Entry(2, 20));
        entries6.add(new Entry(3, 30));
        entries6.add(new Entry(4, 25));
        entries6.add(new Entry(5, 15));

        LineData lineData6= new LineData(new LineDataSet(entries, "CO"));

        // customize chart
        mLineChart6.setData(lineData6);
        mLineChart6.setTouchEnabled(true);
        mLineChart6.setDragEnabled(true);
        mLineChart6.setScaleEnabled(true);

        XAxis xAxis6= mLineChart6.getXAxis();
        xAxis6.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis6.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis6 = mLineChart6.getAxisLeft();
        yAxis6.setGranularity(1);

        mLineChart6.getAxisRight().setEnabled(false);

        mLineChart6.getDescription().setEnabled(false);
        mLineChart6.invalidate();

        //graph for chart7

        LineChart mLineChart7 = findViewById(R.id.line_chart6);
        List<Entry> entries7 = new ArrayList<>();
        entries7.add(new Entry(1, 10));
        entries7.add(new Entry(2, 20));
        entries7.add(new Entry(3, 30));
        entries7.add(new Entry(4, 25));
        entries7.add(new Entry(5, 15));

        LineData lineData7 = new LineData(new LineDataSet(entries, "ALC"));

        // customize chart
        mLineChart7.setData(lineData7);
        mLineChart7.setTouchEnabled(true);
        mLineChart7.setDragEnabled(true);
        mLineChart7.setScaleEnabled(true);

        XAxis xAxis7 = mLineChart7.getXAxis();
        xAxis7.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis7.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis7 = mLineChart7.getAxisLeft();
        yAxis7.setGranularity(1);

        mLineChart7.getAxisRight().setEnabled(false);

        mLineChart7.getDescription().setEnabled(false);
        mLineChart7.invalidate();

        //graph for chart8

        LineChart mLineChart8 = findViewById(R.id.line_chart7);
        List<Entry> entries8 = new ArrayList<>();
        entries8.add(new Entry(1, 10));
        entries8.add(new Entry(2, 20));
        entries8.add(new Entry(3, 30));
        entries8.add(new Entry(4, 25));
        entries8.add(new Entry(5, 15));

        LineData lineData8 = new LineData(new LineDataSet(entries, "PROP"));

        // customize chart
        mLineChart8.setData(lineData8);
        mLineChart8.setTouchEnabled(true);
        mLineChart8.setDragEnabled(true);
        mLineChart8.setScaleEnabled(true);

        XAxis xAxis8 = mLineChart8.getXAxis();
        xAxis8.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis8.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis8 = mLineChart8.getAxisLeft();
        yAxis8.setGranularity(1);

        mLineChart8.getAxisRight().setEnabled(false);

        mLineChart8.getDescription().setEnabled(false);
        mLineChart8.invalidate();

        //graph for chart9

        LineChart mLineChart9 = findViewById(R.id.line_chart8);
        List<Entry> entries9 = new ArrayList<>();
        entries9.add(new Entry(1, 10));
        entries9.add(new Entry(2, 20));
        entries9.add(new Entry(3, 30));
        entries9.add(new Entry(4, 25));
        entries9.add(new Entry(5, 15));

        LineData lineData9= new LineData(new LineDataSet(entries, "AIR"));

        // customize chart
        mLineChart9.setData(lineData9);
        mLineChart9.setTouchEnabled(true);
        mLineChart9.setDragEnabled(true);
        mLineChart9.setScaleEnabled(true);

        XAxis xAxis9 = mLineChart2.getXAxis();
        xAxis9.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis9.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis9 = mLineChart9.getAxisLeft();
        yAxis9.setGranularity(1);

        mLineChart9.getAxisRight().setEnabled(false);

        mLineChart9.getDescription().setEnabled(false);
        mLineChart9.invalidate();

        //graph for chart10

        LineChart mLineChart10= findViewById(R.id.line_chart9);
        List<Entry> entries10= new ArrayList<>();
        entries10.add(new Entry(1, 10));
        entries10.add(new Entry(2, 20));
        entries10.add(new Entry(3, 30));
        entries10.add(new Entry(4, 25));
        entries10.add(new Entry(5, 15));

        LineData lineData10= new LineData(new LineDataSet(entries, "NH3"));

        // customize chart
        mLineChart10.setData(lineData10);
        mLineChart10.setTouchEnabled(true);
        mLineChart10.setDragEnabled(true);
        mLineChart10.setScaleEnabled(true);

        XAxis xAxis10 = mLineChart10.getXAxis();
        xAxis10.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis10.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis10 = mLineChart10.getAxisLeft();
        yAxis10.setGranularity(1);

        mLineChart10.getAxisRight().setEnabled(false);

        mLineChart10.getDescription().setEnabled(false);
        mLineChart10.invalidate();

        //graph for chart11

        LineChart mLineChart11 = findViewById(R.id.line_chart10);
        List<Entry> entries11 = new ArrayList<>();
        entries11.add(new Entry(1, 10));
        entries11.add(new Entry(2, 20));
        entries11.add(new Entry(3, 30));
        entries11.add(new Entry(4, 25));
        entries11.add(new Entry(5, 15));

        LineData lineData11 = new LineData(new LineDataSet(entries, "TOL"));

        // customize chart
        mLineChart11.setData(lineData11);
        mLineChart11.setTouchEnabled(true);
        mLineChart11.setDragEnabled(true);
        mLineChart11.setScaleEnabled(true);

        XAxis xAxis11 = mLineChart11.getXAxis();
        xAxis11.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis11.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });

        YAxis yAxis11 = mLineChart11.getAxisLeft();
        yAxis11.setGranularity(1);

        mLineChart11.getAxisRight().setEnabled(false);

        mLineChart11.getDescription().setEnabled(false);
        mLineChart11.invalidate();



    }
}