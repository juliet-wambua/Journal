package com.nea.myjournal;

import android.widget.ArrayAdapter;
import android.content.Context;

public class JournalsArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mJournals;
    private String[] mAbout;

    public JournalsArrayAdapter(Context mContext, int resource, String[] mJournals, String[] mAbout) {
        super(mContext, resource);
        this.mContext = mContext;
        this. mJournals = mJournals;
        this.mAbout = mAbout;
    }

    @Override
    public Object getItem(int position) {
        String journal = mJournals[position];
        String about = mAbout[position];
        return String.format("%s \nServes great: %s", journal, about);
    }

    @Override
    public int getCount() {
        return mJournals.length;
    }
}
