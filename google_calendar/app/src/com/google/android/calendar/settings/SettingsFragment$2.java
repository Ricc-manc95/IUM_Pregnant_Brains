// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceGroupAdapter;
import android.support.v7.preference.PreferenceViewHolder;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

final class it> extends PreferenceGroupAdapter
{

    public final void onBindViewHolder(android.support.v7.widget.lder lder, int i, List list)
    {
        lder = (PreferenceViewHolder)lder;
        View view = lder.findViewById(0x1020006);
        if (view != null && view.getClass().getSimpleName().equals("PreferenceImageView") && (view.getParent() instanceof ViewGroup))
        {
            ((ViewGroup)view.getParent()).setId(0x102003e);
        }
        super.onBindViewHolder(lder, i, list);
    }

    (PreferenceGroup preferencegroup)
    {
        super(preferencegroup);
    }
}
