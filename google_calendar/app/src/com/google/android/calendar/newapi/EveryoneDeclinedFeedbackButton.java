// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi;

import android.accounts.Account;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.calendar.utils.feedback.FeedbackUtil;

public class EveryoneDeclinedFeedbackButton extends ImageButton
    implements android.view.View.OnClickListener
{

    public Account account;

    public EveryoneDeclinedFeedbackButton(Context context)
    {
        super(context);
        setOnClickListener(this);
    }

    public EveryoneDeclinedFeedbackButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setOnClickListener(this);
    }

    public void onClick(View view)
    {
        FeedbackUtil.sendFeedback(getContext(), "everyone-declined@google.com", "Internal feedback: Everyone declined", "Everyone else declined this meeting. You're giving feedback for this feature. What is working well or not working well about this?", account);
    }
}
