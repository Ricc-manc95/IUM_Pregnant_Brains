// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            License, LicenseMenuFragment

final class arg._cls1
    implements android.widget.r
{

    private final LicenseMenuFragment arg$1;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        view = arg$1;
        adapterview = (License)adapterview.getItemAtPosition(i);
        if (((LicenseMenuFragment) (view)).licenseSelectionListener != null)
        {
            ((LicenseMenuFragment) (view)).licenseSelectionListener.onLicenseSelected(adapterview);
        }
    }

    ectionListener(LicenseMenuFragment licensemenufragment)
    {
        arg$1 = licensemenufragment;
    }
}
