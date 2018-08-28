// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.view.View;
import android.widget.EditText;

// Referenced classes of package com.google.android.calendar.groove:
//            CustomGrooveFragment

final class val.suggestion
    implements android.view.ustomGrooveFragment._cls5
{

    private final CustomGrooveFragment this$0;
    private final String val$suggestion;

    public final void onClick(View view)
    {
        editText.setText(val$suggestion);
        saveSelection(type, editText.getText().toString());
    }

    ()
    {
        this$0 = final_customgroovefragment;
        val$suggestion = String.this;
        super();
    }
}
