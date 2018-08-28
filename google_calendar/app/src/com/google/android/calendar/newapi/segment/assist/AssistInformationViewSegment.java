// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.assist;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.calendar.newapi.model.AssistInformationHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.task.assist.TaskAssistHolder;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

public final class AssistInformationViewSegment extends TextTileView
    implements ViewSegment
{

    public final AssistInformationHolder model;

    public AssistInformationViewSegment(Context context, AssistInformationHolder assistinformationholder)
    {
        super(context);
        model = assistinformationholder;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        super.denseMode = false;
        setFocusable(true);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final AssistInformationViewSegment arg$1;

            public final void onClick(View view1)
            {
                view1 = arg$1;
                TaskAssistHolder taskassistholder = ((AssistInformationViewSegment) (view1)).model.getAssistInformation();
                taskassistholder.gotoAssist(view1.getContext());
                boolean flag = ExperimentalOptions.isTaskAssistStagingEnabled(view1.getContext());
                taskassistholder.logClick(view1.getContext(), "event_action", flag);
            }

            .Lambda._cls0()
            {
                arg$1 = AssistInformationViewSegment.this;
            }
        }

        setOnClickListener(new .Lambda._cls0());
    }

    public final void updateUi()
    {
        boolean flag = false;
        TaskAssistHolder taskassistholder = model.getAssistInformation();
        if (taskassistholder == null)
        {
            if (this != null)
            {
                setVisibility(8);
            }
            return;
        }
        String s = taskassistholder.getDisplayText(getContext());
        android.text.SpannableString spannablestring = taskassistholder.getSecondaryDisplayText(getContext());
        int i;
        if (!TextUtils.isEmpty(s) || !TextUtils.isEmpty(spannablestring))
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
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        treatAsButton(taskassistholder.isClickable(getContext()));
        setIconDrawable(taskassistholder.getIconId());
        setContentDescription(taskassistholder.getDescription(getContext()));
        setTextAdaptively(s, spannablestring);
        useTextAsContentDescription(0x7f130167);
    }
}
