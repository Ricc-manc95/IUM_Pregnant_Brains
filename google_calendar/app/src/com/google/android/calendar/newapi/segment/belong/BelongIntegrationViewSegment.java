// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.belong;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.newapi.model.GrooveHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

public final class BelongIntegrationViewSegment extends TextTileView
    implements android.view.View.OnClickListener, ViewSegment
{

    private final GrooveHolder model;

    public BelongIntegrationViewSegment(Context context, GrooveHolder grooveholder)
    {
        super(context);
        model = grooveholder;
    }

    public final void onClick(View view)
    {
        view = getContext().getPackageManager().getLaunchIntentForPackage("com.google.android.apps.fitness");
        Context context;
        if (view != null)
        {
            getContext().startActivity(view);
        } else
        {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=com.google.android.apps.fitness"));
            getContext().startActivity(intent);
        }
        context = getContext();
        if (view != null)
        {
            view = "opened_fit";
        } else
        {
            view = "opened_play_store";
        }
        BelongUtils.log(context, "view_screen", view);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f0201bb);
        super.denseMode = false;
        setOnClickListener(this);
        treatAsButton(true);
        setFocusable(true);
    }

    public final void updateUi()
    {
        int i;
        if (model.getHabit() != null && model.getHabit().isFitIntegrationEnabled())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (this != null)
        {
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        setPrimaryText(new CharSequence[] {
            getResources().getString(0x7f130223, new Object[0])
        });
        setSecondaryText(new CharSequence[] {
            getResources().getString(0x7f130224, new Object[0])
        });
        useTextAsContentDescription(0x7f13016e);
    }
}
