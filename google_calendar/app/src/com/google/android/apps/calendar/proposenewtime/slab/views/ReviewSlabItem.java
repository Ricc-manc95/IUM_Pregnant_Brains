// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.apps.calendar.proposenewtime.state.Attendee;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.calendar.Utils;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.avatar.ContactPhotoRequestKey;
import com.google.android.calendar.common.drawable.DefaultableBitmapDrawable;
import com.google.android.calendar.event.image.cache.contactphoto.ContactPhotoCacheHolder;
import com.google.android.calendar.time.clock.Clock;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.slab.views:
//            SlabItem, ShowMoreLabelTextView

public final class ReviewSlabItem extends SlabItem
{

    private final ShowMoreLabelTextView commentView = (ShowMoreLabelTextView)findViewById(0x7f100307);
    private final TextView proposalDate = (TextView)findViewById(0x7f100309);
    private final TextView proposalTime = (TextView)findViewById(0x7f10030a);
    private final ImageView proposerAvatar = (ImageView)findViewById(0x7f100308);
    private final TextView proposerName = (TextView)findViewById(0x7f10030b);

    public ReviewSlabItem(Context context)
    {
        super(context);
        inflate(context, 0x7f050132, this);
    }

    private final void updateContentDescription()
    {
        Resources resources = getResources();
        setContentDescription(resources.getString(0x7f13006f, new Object[] {
            resources.getString(0x7f130051, new Object[] {
                proposerName.getText()
            }), proposalDate.getText(), proposalTime.getText(), commentView.fullText
        }));
    }

    public final void setAttendee(Attendee attendee)
    {
        Object obj = ContactInfo.newBuilder();
        obj.primaryEmail = attendee.getEmail();
        obj.sourceAccountName = attendee.getSourceAccount();
        obj.name = attendee.getDisplayName();
        obj = new ContactInfo(((com.google.android.calendar.avatar.ContactInfo.Builder) (obj)));
        obj = new ContactPhotoRequestKey(getContext().getApplicationContext(), ((ContactInfo) (obj)));
        DefaultableBitmapDrawable defaultablebitmapdrawable = new DefaultableBitmapDrawable(getResources(), ContactPhotoCacheHolder.getContactPhotoCache(), false, null, 0x7f020132);
        int i = getResources().getDimensionPixelSize(0x7f0e0063);
        defaultablebitmapdrawable.setDecodeDimensions(i, i);
        defaultablebitmapdrawable.setBounds(0, 0, i, i);
        defaultablebitmapdrawable.bind(((com.android.bitmap.RequestKey) (obj)));
        proposerAvatar.setImageDrawable(defaultablebitmapdrawable);
        obj = proposerName;
        if (TextUtils.isEmpty(attendee.getDisplayName()))
        {
            attendee = attendee.getEmail();
        } else
        {
            attendee = attendee.getDisplayName();
        }
        ((TextView) (obj)).setText(attendee);
        updateContentDescription();
    }

    public final void setTimeProposal(TimeProposal timeproposal)
    {
        byte byte0 = 0;
        Object obj = Utils.getTimeZoneId(getContext(), null);
        StringBuilder stringbuilder = new StringBuilder();
        StringBuilder stringbuilder1 = new StringBuilder();
        long l1 = timeproposal.getStartTimeMillis();
        long l2 = timeproposal.getEndTimeMillis();
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        Utils.getDisplayedDateTimesInTimezone(l1, l2, l, ((String) (obj)), false, 0, getContext(), stringbuilder, stringbuilder1);
        proposalDate.setText(stringbuilder.toString());
        proposalTime.setText(stringbuilder1.toString());
        timeproposal = timeproposal.getComment().replaceAll("[\r\n]+", " ");
        commentView.setFullText(timeproposal);
        obj = commentView;
        if (TextUtils.isEmpty(timeproposal))
        {
            byte0 = 8;
        }
        ((ShowMoreLabelTextView) (obj)).setVisibility(byte0);
        updateContentDescription();
    }
}
