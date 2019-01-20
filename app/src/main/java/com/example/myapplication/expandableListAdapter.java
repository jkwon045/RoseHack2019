package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

public class expandableListAdapter extends BaseExpandableListAdapter{

    private HashMap<String, List<String>> mSubgoalHashMap;
    private String[] mGoals;
    private String[] mDates;

    //String is the identifier that holds the List ( the group/goal )
    //So maybe change that into a List<String> ?
    public expandableListAdapter(HashMap<String, List<String>> subgoalHashMap, List<String> dates) {
        mSubgoalHashMap = subgoalHashMap;
        mGoals = mSubgoalHashMap.keySet().toArray(new String[0]); //Sets mGoals to be a list of identifiers for the hashmap
        mDates = dates.toArray( new String[0] );
    }

    @Override
    public int getGroupCount() {
        return mSubgoalHashMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mSubgoalHashMap.get(mGoals[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGoals[groupPosition];
    }

    public Object getDate(int groupPosition){
        return mDates[groupPosition];
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mSubgoalHashMap.get(mGoals[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_group, parent, false);
        }

        TextView mainGoalText = convertView.findViewById(R.id.mainGoalText);
        TextView remainingDaysLeftText = convertView.findViewById(R.id.countdownText);
        ImageView check = convertView.findViewById(R.id.imageView);

   /*     mainGoalText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final ImageView check = v.findViewById(R.id.imageView);
                check.setVisibility(View.VISIBLE);
            }
        });*/
        mainGoalText.setText(String.valueOf(getGroup(groupPosition)));
        remainingDaysLeftText.setText(String.valueOf(getDate(groupPosition)));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_subgroup, parent, false);
        }

        TextView subGoalText = convertView.findViewById(R.id.subGoalText);
        subGoalText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ImageView check = v.findViewById(R.id.imageView);
                check.setVisibility(View.VISIBLE);
            }
        });
        subGoalText.setText(String.valueOf(getChild(groupPosition,childPosition)));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
