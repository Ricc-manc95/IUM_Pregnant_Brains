// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.DialogInterface;

// Referenced classes of package com.google.android.calendar:
//            PrivacyPolicyActivity

final class arg._cls1
    implements android.content.r
{

    private final PrivacyPolicyActivity arg$1;

    public final void onDismiss(DialogInterface dialoginterface)
    {
        arg$1.finish();
    }

    A(PrivacyPolicyActivity privacypolicyactivity)
    {
        arg$1 = privacypolicyactivity;
    }
}
