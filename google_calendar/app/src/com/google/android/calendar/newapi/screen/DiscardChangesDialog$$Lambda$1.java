// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.DialogInterface;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            DiscardChangesDialog

final class arg._cls1
    implements android.content.
{

    private final DiscardChangesDialog arg$1;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        arg$1.dismissDialog();
    }

    (DiscardChangesDialog discardchangesdialog)
    {
        arg$1 = discardchangesdialog;
    }
}
