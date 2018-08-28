// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.DialogInterface;

// Referenced classes of package com.google.android.calendar.event:
//            AccessCodePickerDialog

final class this._cls0
    implements android.content.stener
{

    private final AccessCodePickerDialog this$0;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = adapter;
        dialoginterface.selectedItemIndex = i;
        dialoginterface.notifyDataSetChanged();
    }

    cessCodeAdapter()
    {
        this$0 = AccessCodePickerDialog.this;
        super();
    }
}
