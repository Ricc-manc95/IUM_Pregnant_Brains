// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch;

import android.content.DialogInterface;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

// Referenced classes of package com.google.android.calendar.launch:
//            LaunchInfoActivity

final class val.availability
    implements android.content.celListener
{

    private final LaunchInfoActivity this$0;
    private final GoogleApiAvailability val$availability;

    public final void onCancel(DialogInterface dialoginterface)
    {
        if (val$availability.isGooglePlayServicesAvailable(LaunchInfoActivity.this) != 0)
        {
            finish();
            return;
        } else
        {
            tryStartup();
            return;
        }
    }

    ()
    {
        this$0 = final_launchinfoactivity;
        val$availability = GoogleApiAvailability.this;
        super();
    }
}
