// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.ConferenceCallSpan;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.event.ConferenceCallView;
import com.google.android.calendar.tiles.view.TextTileView;

public abstract class ConferenceTileView extends TextTileView
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/note/ConferenceTileView);
    private GestureDetector gestureDetector;

    public ConferenceTileView(Context context)
    {
        super(context);
    }

    public ConferenceTileView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final View createContentView(LayoutInflater layoutinflater)
    {
        gestureDetector = new GestureDetector(getContext(), new _cls1());
        return layoutinflater.inflate(0x7f0500ed, this, false);
    }

    public Spannable createOneClickDialingText(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            return null;
        } else
        {
            charsequence = SpannableString.valueOf(charsequence);
            ConferenceCallUtils.addLinks(charsequence, ((ConferenceCallView)super.primaryLine).listener);
            class .Lambda._cls0
                implements android.view.View.OnTouchListener
            {

                private final ConferenceTileView arg$1;

                public final boolean onTouch(View view, MotionEvent motionevent)
                {
                    return arg$1.onPrimaryTextTouched(view, motionevent);
                }

            .Lambda._cls0()
            {
                arg$1 = ConferenceTileView.this;
            }
            }

            super.primaryLine.setOnTouchListener(new .Lambda._cls0());
            return charsequence;
        }
    }

    public abstract String getAnalyticsSegmentDescription();

    public void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        view = (ViewGroup)view;
        com.google.android.calendar.tiles.view.Tile.Dimensions dimensions = com.google.android.calendar.tiles.view.Tile.Dimensions.SMALL_TILE_MIN_HEIGHT;
        view.setMinimumHeight(getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId));
        super.primaryLine.setFocusable(true);
    }

    final boolean onPrimaryTextTouched(View view, MotionEvent motionevent)
    {
        int i;
        view = (TextView)view;
        i = motionevent.getAction();
        if (i != 1 && i != 0 || !(view.getText() instanceof Spanned)) goto _L2; else goto _L1
_L1:
        ClickableSpan aclickablespan[];
        int j = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        int l = view.getTotalPaddingLeft();
        int i1 = view.getTotalPaddingTop();
        int j1 = view.getScrollX();
        int k1 = view.getScrollY();
        Layout layout = view.getLayout();
        j = layout.getOffsetForHorizontal(layout.getLineForVertical((k - i1) + k1), (j - l) + j1);
        aclickablespan = (ClickableSpan[])((Spanned)view.getText()).getSpans(j, j, android/text/style/ClickableSpan);
        if (aclickablespan.length == 0) goto _L2; else goto _L3
_L3:
        if (i != 1) goto _L5; else goto _L4
_L4:
        motionevent = aclickablespan[0];
        if (motionevent instanceof ConferenceCallSpan) goto _L7; else goto _L6
_L6:
        try
        {
            motionevent.onClick(view);
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            LogUtils.e(TAG, view, "Failed to handle span click", new Object[0]);
            Toast.makeText(getContext(), 0x7f13008a, 0).show();
        }
_L5:
        return true;
_L7:
        motionevent = (ConferenceCallSpan)motionevent;
        view = (ConferenceCallView)view;
        if (((ConferenceCallView) (view)).listener != null)
        {
            ((ConferenceCallView) (view)).listener.onClick(((ConferenceCallSpan) (motionevent)).conferenceNumber);
        }
        if (true) goto _L5; else goto _L2
_L2:
        return gestureDetector.onTouchEvent(motionevent);
    }

    public final TextTileView setPrimaryText(CharSequence acharsequence[])
    {
        acharsequence = concatenate(acharsequence);
        super.setPrimaryText(new CharSequence[] {
            createOneClickDialingText(acharsequence)
        });
        if (acharsequence != null)
        {
            acharsequence = ((ConferenceCallView)super.primaryLine).getText();
            boolean flag;
            if (!TextUtils.isEmpty(acharsequence) && (acharsequence instanceof Spanned))
            {
                if (((ConferenceCallSpan[])((Spanned)acharsequence).getSpans(0, acharsequence.length(), com/google/android/calendar/event/ConferenceCallSpan)).length > 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ConferenceCallUtils.logAction(getContext(), "conference_link_shown", getAnalyticsSegmentDescription());
            }
        }
        return this;
    }


    private class _cls1 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final ConferenceTileView this$0;

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            performClick();
            return true;
        }

        _cls1()
        {
            this$0 = ConferenceTileView.this;
            super();
        }
    }

}
