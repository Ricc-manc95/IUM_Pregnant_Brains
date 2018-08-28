// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.widget.TextView;
import com.google.android.apps.calendar.proposenewtime.utils.ProposeNewTimeUtils;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.common.base.CharMatcher;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee:
//            AttendeeTileView

public final class ProposalAttendeeTileView extends AttendeeTileView
{

    public final Callback callback;
    private final boolean clickableProposals;
    private AppCompatButton timeProposalButton;
    private TextView timeProposalView;

    public ProposalAttendeeTileView(Context context, boolean flag, Callback callback1)
    {
        super(context);
        clickableProposals = flag;
        callback = callback1;
    }

    protected final int adjustTileHeight(int i)
    {
        int j = super.adjustTileHeight(i);
        if (timeProposalButton != null)
        {
            i = getResources().getDimensionPixelSize(0x7f0e0327);
        } else
        {
            i = 0;
        }
        return i + j;
    }

    protected final int getActualLineCount()
    {
        int j = super.getActualLineCount();
        int i;
        if (timeProposalView != null)
        {
            i = timeProposalView.getLineCount();
        } else
        {
            i = 0;
        }
        return i + j;
    }

    protected final int getExpectedLineCount()
    {
        boolean flag = false;
        int j = super.getExpectedLineCount();
        int i = ((flag) ? 1 : 0);
        if (timeProposalView != null)
        {
            CharSequence charsequence = timeProposalView.getText();
            if (TextUtils.isEmpty(charsequence))
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = CharMatcher.is('\n').countIn(charsequence) + 1;
            }
        }
        return i + j;
    }

    public final AttendeeTileView setData(Account account, Attendee attendee, boolean flag, long l)
    {
        byte byte0 = 8;
        super.setData(account, attendee, flag, l);
        account = ProposeNewTimeUtils.getProposalString(attendee.response, l, getContext());
        if (!TextUtils.isEmpty(account) || timeProposalView != null || timeProposalButton != null)
        {
            if (clickableProposals)
            {
                if (timeProposalButton == null)
                {
                    inflate(getContext(), 0x7f050172, container);
                    timeProposalButton = (AppCompatButton)findViewById(0x7f100378);
                }
            } else
            if (timeProposalView == null)
            {
                inflate(getContext(), 0x7f050173, container);
                timeProposalView = (TextView)findViewById(0x7f100379);
            }
            if (clickableProposals)
            {
                AppCompatButton appcompatbutton = timeProposalButton;
                class .Lambda._cls0
                    implements android.view.View.OnClickListener
                {

                    private final ProposalAttendeeTileView arg$1;
                    private final Attendee arg$2;

                    public final void onClick(View view)
                    {
                        view = arg$1;
                        Attendee attendee1 = arg$2;
                        ((ProposalAttendeeTileView) (view)).callback.onGuestProposalClicked(attendee1);
                    }

            .Lambda._cls0(Attendee attendee)
            {
                arg$1 = ProposalAttendeeTileView.this;
                arg$2 = attendee;
            }

                    private class Callback
                    {

                        public abstract void onGuestProposalClicked(Attendee attendee);
                    }

                }

                if (TextUtils.isEmpty(account))
                {
                    byte0 = 8;
                } else
                {
                    byte0 = 0;
                }
                appcompatbutton.setVisibility(byte0);
                timeProposalButton.setText(account);
                timeProposalButton.setContentDescription(getResources().getString(0x7f1300d8, new Object[] {
                    account
                }));
            } else
            {
                TextView textview = timeProposalView;
                if (!TextUtils.isEmpty(account))
                {
                    byte0 = 0;
                }
                textview.setVisibility(byte0);
                timeProposalView.setText(account);
                timeProposalView.setContentDescription(getResources().getString(0x7f1300d8, new Object[] {
                    account
                }));
                timeProposalView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(0x7f0e039c));
                timeProposalView.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), 0x7f020223), null, null, null);
            }
        }
        if (timeProposalButton != null && callback != null)
        {
            timeProposalButton.setOnClickListener(new .Lambda._cls0(attendee));
        }
        return this;
    }
}
