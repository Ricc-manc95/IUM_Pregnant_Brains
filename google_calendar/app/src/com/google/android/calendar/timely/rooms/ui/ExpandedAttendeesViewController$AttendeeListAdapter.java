// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.calendar.avatar.BadgeViewFactory;
import com.google.android.calendar.tiles.view.BadgedIconTile;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.rooms.data.Attendee;
import com.google.common.collect.ImmutableList;

public final class sourceAccount extends android.support.v7.widget.tendeeListAdapter
{

    public ImmutableList attendees;
    private final String sourceAccount;

    public final int getItemCount()
    {
        return attendees.size();
    }

    public final void onBindViewHolder(android.support.v7.widget.tendeeListAdapter tendeelistadapter, int i)
    {
        Object obj;
        Object obj1;
        BadgedIconTile badgedicontile;
        obj1 = (.Lambda._cls0.arg._cls1)tendeelistadapter;
        badgedicontile = ((.Lambda._cls0.arg._cls1) (obj1))._fld1;
        obj = (Attendee)attendees.get(i);
        tendeelistadapter = badgedicontile.getContext();
        badgedicontile.getClass();
        class .Lambda._cls0
            implements Consumer
        {

            private final BadgedIconTile arg$1;

            public final void accept(Object obj2)
            {
                arg$1.setIconDrawable((Drawable)obj2);
            }

            .Lambda._cls0(BadgedIconTile badgedicontile)
            {
                arg$1 = badgedicontile;
            }
        }

        BadgeViewFactory.setupAttendeeBadge(tendeelistadapter, new .Lambda._cls0(badgedicontile), ((TileView) (badgedicontile)).iconSize, sourceAccount, ((Attendee) (obj)).getDisplayName(), ((Attendee) (obj)).getEmail());
        ((Attendee) (obj)).getResponseStatus().rceAccount();
        JVM INSTR tableswitch 1 3: default 100
    //                   1 140
    //                   2 100
    //                   3 149;
           goto _L1 _L2 _L1 _L3
_L1:
        tendeelistadapter = null;
_L8:
        badgedicontile.setBadge(tendeelistadapter);
        obj1 = ((sourceAccount) (obj1)).sourceAccount;
        tendeelistadapter = ((Attendee) (obj)).getDisplayName();
        obj = ((Attendee) (obj)).getEmail();
        if (tendeelistadapter == null) goto _L5; else goto _L4
_L4:
        ((TextView) (obj1)).setText((CharSequence)tendeelistadapter);
        return;
_L2:
        tendeelistadapter = Integer.valueOf(0x7f020112);
        continue; /* Loop/switch isn't completed */
_L3:
        tendeelistadapter = Integer.valueOf(0x7f020258);
        continue; /* Loop/switch isn't completed */
_L5:
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        tendeelistadapter = ((android.support.v7.widget.leHolder.name) (obj));
        if (true) goto _L4; else goto _L6
_L6:
        throw new NullPointerException("Both parameters are null");
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final android.support.v7.widget.tendeeListAdapter onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        return sourceAccount(viewgroup);
    }

    public .Lambda._cls0(String s)
    {
        sourceAccount = s;
    }
}
