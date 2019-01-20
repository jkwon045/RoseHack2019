package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g = findViewById(R.id.mainGoalText);

        ExpandableListView e = (findViewById(R.id.goalList));

        HashMap<String, List<String>> item = new HashMap();

        ArrayList<String> goals = new ArrayList<>();
        goals.add("Run a mile");
        goals.add("Run 2 miles");
        goals.add("Run a 5K");

        item.put("Marathon", goals); //List name then sublist

        ArrayList<String> daysLeft = new ArrayList<>();
        daysLeft.add(" 1 day ");
        daysLeft.add(" 2 days ");
        daysLeft.add(" 3 days ");

        ArrayList<String> subGoals = new ArrayList<>();
        subGoals.add("Finish 24 oz water bottle");
        subGoals.add("Drink 2 cups");
        subGoals.add("Drink 1 cup");

        item.put("Water", subGoals);

        expandableListAdapter adapter = new expandableListAdapter(item, daysLeft);
        e.setAdapter(adapter);
    }

}
