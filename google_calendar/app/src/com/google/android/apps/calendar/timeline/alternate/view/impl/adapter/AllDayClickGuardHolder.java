// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.view.View;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl

public final class AllDayClickGuardHolder extends TimelineAdapterViewHolderImpl
{

    public AllDayClickGuardHolder(Context context)
    {
        context = new View(context);
        context.setBackgroundColor(0);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            public static final android.view.View.OnClickListener $instance = new .Lambda._cls0();

            public final void onClick(View view)
            {
                AllDayClickGuardHolder.lambda$createView$0$AllDayClickGuardHolder$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
            }


            private .Lambda._cls0()
            {
            }
        }

        context.setOnClickListener(.Lambda._cls0..instance);
        context.setImportantForAccessibility(2);
        super(context);
    }

    static final void lambda$createView$0$AllDayClickGuardHolder$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
    {
    }
}
