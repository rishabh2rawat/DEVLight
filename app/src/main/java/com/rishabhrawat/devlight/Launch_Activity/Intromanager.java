package com.rishabhrawat.devlight.Launch_Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;

/**
 * Created by rishabh on 10-Jul-17.
 */

public class Intromanager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Intromanager(Context context)
    {
        this.context=context;
        pref=context.getSharedPreferences("first",Context.MODE_PRIVATE);
        editor=pref.edit();

    }

    public void setFirst(boolean isFirst)
    {
        editor.putBoolean("check",isFirst);
        editor.commit();
    }

    public boolean Check()
    {

        return pref.getBoolean("check",true);
    }
}
