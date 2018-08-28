// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common.fullscreen;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.utils.SystemWindowInsetApplier;

public final class SearchFullScreenViewHolder
{

    public final View clearButton;
    public final LinearLayout container;
    public final View doneButton;
    public final NinjaEditText searchBox;
    public final RecyclerView suggestionList;
    public final Toolbar toolbar;
    public final View view;

    public SearchFullScreenViewHolder(Context context)
    {
        view = LayoutInflater.from(context).inflate(0x7f0500e4, null);
        container = (LinearLayout)view.findViewById(0x7f100136);
        toolbar = (Toolbar)view.findViewById(0x7f100113);
        searchBox = (NinjaEditText)view.findViewById(0x7f100293);
        clearButton = view.findViewById(0x7f10018d);
        doneButton = view.findViewById(0x7f100294);
        suggestionList = (RecyclerView)view.findViewById(0x7f100295);
        context = new SystemWindowInsetApplier();
        context.addView(container, 8, 1);
        ViewCompat.setOnApplyWindowInsetsListener(view, context);
    }
}
