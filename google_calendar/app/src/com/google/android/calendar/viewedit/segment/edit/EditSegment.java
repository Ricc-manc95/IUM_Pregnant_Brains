// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.viewedit.segment.edit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class EditSegment extends FrameLayout
{

    public Object mListener;

    public EditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setLayoutDirection(3);
    }
}
