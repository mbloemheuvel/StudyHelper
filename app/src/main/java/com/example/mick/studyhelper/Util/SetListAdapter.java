package com.example.mick.studyhelper.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mick.studyhelper.R;
import com.example.mick.studyhelper.Set;

import java.util.List;

/**
 * Created by Mick on 22-Jun-16.
 */
public class SetListAdapter extends BaseAdapter {

    private int resource;
    private List<Set> setList;
    private Context context;


    public SetListAdapter(Context context, int resource, List<Set> setList){
        this.context = context;
        this.setList = setList;
        this.resource = resource;
    }
    @Override
    public int getCount() {
        return setList.size();
    }

    @Override
    public Object getItem(int position) {
        return setList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return setList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }
        TextView title = (TextView)v.findViewById(R.id.title);
        TextView creator = (TextView)v.findViewById(R.id.creator);
        TextView termLanguage = (TextView)v.findViewById(R.id.term_language);
        TextView definitionLanguage  = (TextView)v.findViewById(R.id.definition_language);
        Set s = (Set) getItem(position);
        title.setText(s.getTitle());
        creator.setText(s.getCreatedBy());
        termLanguage.setText(s.getLangTerms());
        definitionLanguage.setText(s.getLangDefinitions());
        return v;
    }


}
