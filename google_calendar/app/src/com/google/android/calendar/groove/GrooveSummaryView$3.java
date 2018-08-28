// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.calendar.common.view.fab.FloatingActionButton;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveSummaryView

final class this._cls0
    implements android.view.
{

    private final GrooveSummaryView this$0;

    public final void onClick(View view)
    {
        if (getContext() instanceof stener)
        {
            fab.setClickable(false);
            titleView.setVisibility(4);
            contractFrameView.setVisibility(8);
            progressBar.setVisibility(0);
            waitingTextView.setVisibility(0);
            divider.setVisibility(8);
            fab.setIcon(0);
            GrooveSummaryView.waitingTextIndex = 0;
            handler = new Handler();
            handler.post(waitingTextUpdateTask);
            view = GrooveSummaryView.this;
            view.announceForAccessibility(TextUtils.join(" ", view.getResources().getStringArray(0x7f0b0030)));
            ((stener)getContext()).onConfirmContract();
        }
    }

    ionButton()
    {
        this$0 = GrooveSummaryView.this;
        super();
    }
}
