// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.event:
//            CustomNotificationBaseDialog

final class check
{

    public ImageView check;
    public TextView textView;

    public (CustomNotificationBaseDialog customnotificationbasedialog, View view)
    {
        textView = (TextView)view.findViewById(0x7f100042);
        textView.setText(" ");
        textView.setTypeface(customnotificationbasedialog.robotoMedium);
        check = (ImageView)view.findViewById(0x7f100148);
    }
}
