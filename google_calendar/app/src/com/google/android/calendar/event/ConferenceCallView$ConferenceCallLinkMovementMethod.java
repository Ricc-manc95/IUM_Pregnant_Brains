// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.event:
//            ConferenceCallView, ConferenceCallSpan

public static final class  extends LinkMovementMethod
{

    public static nClick instance;

    public final boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        if (motionevent.getAction() == 1)
        {
            int i = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            int k = textview.getTotalPaddingLeft();
            int l = textview.getTotalPaddingTop();
            int i1 = textview.getScrollX();
            int j1 = textview.getScrollY();
            Layout layout = textview.getLayout();
            i = layout.getOffsetForHorizontal(layout.getLineForVertical((j - l) + j1), (i - k) + i1);
            ConferenceCallSpan aconferencecallspan[] = (ConferenceCallSpan[])spannable.getSpans(i, i, com/google/android/calendar/event/ConferenceCallSpan);
            if (aconferencecallspan.length != 0)
            {
                textview = (ConferenceCallView)textview;
                if (((ConferenceCallView) (textview)).listener != null)
                {
                    ((ConferenceCallView) (textview)).listener.nClick(aconferencecallspan[0].conferenceNumber);
                }
                return true;
            }
        }
        return super.onTouchEvent(textview, spannable, motionevent);
    }

    public ()
    {
    }
}
