// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.view.View;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl

public final class NowLineViewHolder extends TimelineAdapterViewHolderImpl
{

    NowLineViewHolder(Context context, NowLineDrawable nowlinedrawable)
    {
        super(new View(context));
        itemView.setBackground(nowlinedrawable);
    }
}
