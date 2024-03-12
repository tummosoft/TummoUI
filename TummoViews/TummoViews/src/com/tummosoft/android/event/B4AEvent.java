package com.tummosoft.android.event;

import android.view.View;
import anywheresoftware.b4a.BA;

public class B4AEvent {
    
    static public void setNewEvent(BA ba, String BAevent, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ba.raiseEventFromUI(view, BAevent + "_click", null);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ba.raiseEventFromUI(v, BAevent + "_onlongclick", v);
                return true;
            }
        });
    }
    
}
 