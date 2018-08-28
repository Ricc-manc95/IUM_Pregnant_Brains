// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.contract;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.newapi.model.GrooveHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;

public final class ContractViewSegment extends LinearLayout
    implements ViewSegment
{

    private final GrooveHolder model;
    private final TextTileView tile = (TextTileView)findViewById(0x7f100262);

    public ContractViewSegment(Context context, GrooveHolder grooveholder)
    {
        super(context);
        setOrientation(1);
        inflate(context, 0x7f0500cc, this);
        model = grooveholder;
        tile.setFocusable(true);
    }

    public final CharSequence getContentDescription()
    {
        TextTileView texttileview = (TextTileView)findViewById(0x7f100262);
        StringBuilder stringbuilder = new StringBuilder();
        CharSequence charsequence = super.getContentDescription();
        if (charsequence != null)
        {
            stringbuilder.append(charsequence).append("\n ");
        }
        stringbuilder.append(texttileview.getContentDescription());
        return stringbuilder.toString();
    }

    public final void updateUi()
    {
        HabitContract habitcontract = model.getHabit().getContract();
        int i = habitcontract.getInterval();
        if (i == 2 || i == 3)
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
        i = habitcontract.getInterval();
        if (i == 2 || i == 3)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            return;
        } else
        {
            tile.setPrimaryText(new CharSequence[] {
                GrooveUtils.getFrequencyString(getResources(), habitcontract)
            });
            tile.setSecondaryText(new CharSequence[] {
                GrooveUtils.getDurationAndPreferredTimesString(getResources(), habitcontract)
            });
            tile.getSecondaryTextView().setContentDescription(GrooveUtils.getDurationAndPreferredTimesAccessibilityString(getResources(), habitcontract));
            tile.useTextAsContentDescription(0x7f130170);
            return;
        }
    }
}
