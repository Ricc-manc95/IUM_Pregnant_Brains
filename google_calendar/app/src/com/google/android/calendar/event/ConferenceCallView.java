// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;
import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

// Referenced classes of package com.google.android.calendar.event:
//            ConferenceCallSpan

public class ConferenceCallView extends TextView
{
    public static final class ConferenceCallLinkMovementMethod extends LinkMovementMethod
    {

        public static ConferenceCallLinkMovementMethod instance;

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
                        ((ConferenceCallView) (textview)).listener.onClick(aconferencecallspan[0].conferenceNumber);
                    }
                    return true;
                }
            }
            return super.onTouchEvent(textview, spannable, motionevent);
        }

        public ConferenceCallLinkMovementMethod()
        {
        }
    }


    public ConferenceCallSpan.OnConferenceNumberClickListener listener;

    public ConferenceCallView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if (android.os.Build.VERSION.SDK_INT == 23 && getSelectionStart() != getSelectionEnd() && motionevent.getActionMasked() == 0)
        {
            CharSequence charsequence = getText();
            setText(null);
            setText(charsequence);
        }
        return super.dispatchTouchEvent(motionevent);
    }

    public int getSelectionEnd()
    {
        boolean flag1 = true;
        int ai[] = new int[2];
        ai[0] = super.getSelectionStart();
        ai[1] = super.getSelectionEnd();
        boolean flag;
        if (ai.length > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        int j = ai[0];
        for (int i = ((flag1) ? 1 : 0); i < ai.length;)
        {
            int k = j;
            if (ai[i] > j)
            {
                k = ai[i];
            }
            i++;
            j = k;
        }

        return j;
    }

    public int getSelectionStart()
    {
        boolean flag1 = true;
        int ai[] = new int[2];
        ai[0] = super.getSelectionStart();
        ai[1] = super.getSelectionEnd();
        boolean flag;
        if (ai.length > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        int j = ai[0];
        for (int i = ((flag1) ? 1 : 0); i < ai.length;)
        {
            int k = j;
            if (ai[i] < j)
            {
                k = ai[i];
            }
            i++;
            j = k;
        }

        return j;
    }

    public void setTextIsSelectable(boolean flag)
    {
        super.setTextIsSelectable(flag);
        if (ConferenceCallLinkMovementMethod.instance == null)
        {
            ConferenceCallLinkMovementMethod.instance = new ConferenceCallLinkMovementMethod();
        }
        setMovementMethod(ConferenceCallLinkMovementMethod.instance);
    }
}
