// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.SuggestionRow;
import java.util.TimeZone;

public class FindTime2UiSuggestionItemView extends LinearLayout
{
    public static interface OnMoreInformationRequestListener
    {

        public abstract void onMoreInformationRequest(FindTime2UiSuggestionItemView findtime2uisuggestionitemview, SuggestionRow suggestionrow);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/ui/FindTime2UiSuggestionItemView);
    public TextView dateView;
    public TextView descriptionView;
    private View moreIcon;
    public OnMoreInformationRequestListener onMoreInformationRequestListener;
    public String suggestionDescription;
    public SuggestionRow suggestionRow;
    public TextView timeView;
    public TimeZone timezone;

    public FindTime2UiSuggestionItemView(Context context, TimeZone timezone1)
    {
        super(context);
        LayoutInflater.from(context).inflate(0x7f050067, this);
        dateView = (TextView)findViewById(0x7f10019a);
        timeView = (TextView)findViewById(0x7f100151);
        timezone = timezone1;
        descriptionView = (TextView)findViewById(0x7f10010b);
        moreIcon = findViewById(0x7f10019b);
        moreIcon.setOnClickListener(new _cls1());
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final FindTime2UiSuggestionItemView this$0;

        public final void onClick(View view)
        {
            if (onMoreInformationRequestListener != null)
            {
                onMoreInformationRequestListener.onMoreInformationRequest(FindTime2UiSuggestionItemView.this, suggestionRow);
            }
        }

        _cls1()
        {
            this$0 = FindTime2UiSuggestionItemView.this;
            super();
        }
    }

}
