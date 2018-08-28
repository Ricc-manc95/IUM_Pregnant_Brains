// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.newapi.segment.attendee:
//            ContactTileView, AttendeesUtils

public class AttendeeTileView extends ContactTileView
{

    private String a11yResponseStatusMessages[];
    private Attendee attendee;
    private boolean organizer;

    public AttendeeTileView(Context context)
    {
        super(context);
    }

    protected final int getContentEndPadding()
    {
        int i = getResources().getInteger(0x7f110021);
        com.google.android.calendar.material.Material.Dimensions dimensions = com.google.android.calendar.material.Material.Dimensions.INCREMENT;
        return i * getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        a11yResponseStatusMessages = getResources().getStringArray(0x7f0b000e);
        setHorizontalIncrement(0x7f110020);
    }

    public AttendeeTileView setData(Account account, Attendee attendee1, boolean flag, long l)
    {
        Object obj1 = null;
        attendee = attendee1;
        organizer = flag;
        String s2 = Platform.nullToEmpty(attendee1.attendeeDescriptor.email).toLowerCase();
        String s3 = AttendeesUtils.getAttendeeName(attendee1);
        Object obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).response_comments();
        if (flag)
        {
            obj = getResources().getText(0x7f1300d6);
        } else
        {
            obj = null;
        }
        setData(account.name, s2, s3, ((CharSequence) (obj)));
        setTag(attendee1);
        attendee1 = AttendeesUtils.getAttendeeName(attendee);
        if (TextUtils.isEmpty(super.primaryLine.getText()))
        {
            account = obj1;
        } else
        {
            int i = attendee.response.status.ordinal();
            String s = "";
            account = s;
            if (i >= 0)
            {
                account = s;
                if (i < a11yResponseStatusMessages.length)
                {
                    account = a11yResponseStatusMessages[i];
                }
            }
            if (organizer)
            {
                attendee1 = String.valueOf(attendee1);
                String s1 = getContext().getString(0x7f1300d6);
                attendee1 = (new StringBuilder(String.valueOf(attendee1).length() + 1 + String.valueOf(s1).length())).append(attendee1).append(" ").append(s1).toString();
            }
            account = String.format(account, new Object[] {
                attendee1
            });
            attendee1 = Features.instance;
            if (attendee1 == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)attendee1).response_comments();
        }
        setContentDescription(account);
        super.contentView.setFocusable(false);
        super.contentView.setImportantForAccessibility(2);
        return this;
    }
}
