// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.res.Resources;
import android.widget.EditText;

// Referenced classes of package com.google.android.calendar.event:
//            CustomNotificationBaseDialog

final class tionSet extends tionSet
{

    private final CustomNotificationBaseDialog this$0;

    protected final String getViewText(int i, int j, boolean flag)
    {
        if (flag)
        {
            if (i == 0x7f10014c)
            {
                return resources.getQuantityString(0x7f120029, count);
            }
            if (i == 0x7f10014d)
            {
                return resources.getQuantityString(0x7f120023, count);
            }
            if (i == 0x7f10014e)
            {
                return resources.getQuantityString(0x7f120010, count);
            }
            if (i == 0x7f10014f)
            {
                return resources.getQuantityString(0x7f12004f, count);
            }
        } else
        {
            if (i == 0x7f10014c)
            {
                return resources.getQuantityString(0x7f120028, count);
            }
            if (i == 0x7f10014d)
            {
                return resources.getQuantityString(0x7f120022, count);
            }
            if (i == 0x7f10014e)
            {
                return resources.getQuantityString(0x7f12000f, count);
            }
            if (i == 0x7f10014f)
            {
                return resources.getQuantityString(0x7f12004e, count);
            }
        }
        return "";
    }

    protected final void onItemSelected()
    {
        validateIntervalText(editText.getText().toString());
    }

    tionSet()
    {
        this$0 = CustomNotificationBaseDialog.this;
        super(CustomNotificationBaseDialog.this, (byte)0);
    }
}
