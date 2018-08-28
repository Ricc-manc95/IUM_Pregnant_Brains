// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class ExpandedAttendeesViewController
{

    public final ViewGroup container;
    public final View contentView;
    public final AttendeeListAdapter listAdapter;

    public ExpandedAttendeesViewController(ViewGroup viewgroup, String s)
    {
        container = viewgroup;
        contentView = LayoutInflater.from(viewgroup.getContext()).inflate(0x7f0500ab, container, false);
        listAdapter = new AttendeeListAdapter(s);
        ((RecyclerView)contentView.findViewById(0x102000a)).setAdapter(listAdapter);
    }

    private class AttendeeListAdapter extends android.support.v7.widget.RecyclerView.Adapter
    {

        public ImmutableList attendees;
        private final String sourceAccount;

        public final int getItemCount()
        {
            return attendees.size();
        }

        public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
        {
            Object obj;
            Object obj1;
            BadgedIconTile badgedicontile;
            obj1 = (TileHolder)viewholder;
            badgedicontile = ((TileHolder) (obj1)).tile;
            obj = (Attendee)attendees.get(i);
            viewholder = badgedicontile.getContext();
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

            BadgeViewFactory.setupAttendeeBadge(viewholder, new .Lambda._cls0(badgedicontile), ((TileView) (badgedicontile)).iconSize, sourceAccount, ((Attendee) (obj)).getDisplayName(), ((Attendee) (obj)).getEmail());
            ((Attendee) (obj)).getResponseStatus().ordinal();
            JVM INSTR tableswitch 1 3: default 100
        //                       1 140
        //                       2 100
        //                       3 149;
               goto _L1 _L2 _L1 _L3
_L1:
            viewholder = null;
_L8:
            badgedicontile.setBadge(viewholder);
            obj1 = ((TileHolder) (obj1)).name;
            viewholder = ((Attendee) (obj)).getDisplayName();
            obj = ((Attendee) (obj)).getEmail();
            if (viewholder == null) goto _L5; else goto _L4
_L4:
            ((TextView) (obj1)).setText((CharSequence)viewholder);
            return;
_L2:
            viewholder = Integer.valueOf(0x7f020112);
            continue; /* Loop/switch isn't completed */
_L3:
            viewholder = Integer.valueOf(0x7f020258);
            continue; /* Loop/switch isn't completed */
_L5:
            if (obj == null)
            {
                break; /* Loop/switch isn't completed */
            }
            viewholder = ((android.support.v7.widget.RecyclerView.ViewHolder) (obj));
            if (true) goto _L4; else goto _L6
_L6:
            throw new NullPointerException("Both parameters are null");
            if (true) goto _L8; else goto _L7
_L7:
        }

        public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
        {
            return TileHolder.create(viewgroup);
        }

        public AttendeeListAdapter(String s)
        {
            sourceAccount = s;
        }

        private class TileHolder extends android.support.v7.widget.RecyclerView.ViewHolder
        {

            public final TextView name;
            public final BadgedIconTile tile;

            static TileHolder create(ViewGroup viewgroup)
            {
                viewgroup = (BadgedIconTile)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f050025, viewgroup, false);
                return new TileHolder(viewgroup, (TextView)viewgroup.findViewById(0x7f10038f));
            }

            private TileHolder(BadgedIconTile badgedicontile, TextView textview)
            {
                super(badgedicontile);
                tile = badgedicontile;
                name = textview;
            }
        }

    }

}
