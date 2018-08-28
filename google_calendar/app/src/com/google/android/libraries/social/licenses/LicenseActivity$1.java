// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.ScrollView;
import android.widget.TextView;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            LicenseActivity

final class val.scrollView
    implements Runnable
{

    private final LicenseActivity this$0;
    private final int val$firstVisibleChar;
    private final ScrollView val$scrollView;

    public final void run()
    {
        TextView textview = (TextView)findViewById(0x7f100229);
        int i = textview.getLayout().getLineForOffset(val$firstVisibleChar);
        i = textview.getLayout().getLineTop(i);
        val$scrollView.scrollTo(0, i);
    }

    ()
    {
        this$0 = final_licenseactivity;
        val$firstVisibleChar = i;
        val$scrollView = ScrollView.this;
        super();
    }
}
