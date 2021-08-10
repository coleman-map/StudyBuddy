package com.example.studybudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;


//Got from in class activity for display report for user
public class SimpleChart extends AppCompatActivity {

    private Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_chart);
        try {
            String correct = getIntent().getStringExtra("EXTRA_CORRECT");
            System.out.println("Correct " + correct);
            Pie pie = AnyChart.pie();
            List<DataEntry> data = new ArrayList<>();
            int val = Integer.parseInt(correct);
            int rest = 10 - val;
            data.add(new ValueDataEntry("Correct", val));
            data.add(new ValueDataEntry("wrong", rest));
            pie.data(data);
            AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
            anyChartView.setChart(pie);

            homeButton = findViewById(R.id.homeButton);
            homeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}