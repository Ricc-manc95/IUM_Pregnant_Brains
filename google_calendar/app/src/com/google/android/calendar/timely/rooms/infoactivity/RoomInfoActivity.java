// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.infoactivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.common.base.Platform;

public class RoomInfoActivity extends CalendarSupportActivity
{

    public RoomInfoActivity()
    {
    }

    private final void configureTile(int i, CharSequence charsequence)
    {
        TextTileView texttileview = (TextTileView)findViewById(i);
        if (TextUtils.isEmpty(charsequence))
        {
            texttileview.setVisibility(8);
            return;
        } else
        {
            texttileview.setPrimaryText(new CharSequence[] {
                charsequence
            });
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj2)
            {
                return ((RoomFeature)obj2).getName();
            }


            private .Lambda._cls1()
            {
            }
        }

        Object obj;
        Room room;
        Object obj1;
        String s;
        String s1;
        obj = null;
        super.onCreate(bundle);
        setContentView(0x7f050147);
        room = (Room)getIntent().getParcelableExtra("room");
        ((TextView)findViewById(0x7f10017c)).setText(room.getShortName());
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final RoomInfoActivity arg$1;

            public final void onClick(View view)
            {
                arg$1.onBackPressed();
            }

            .Lambda._cls0()
            {
                arg$1 = RoomInfoActivity.this;
            }
        }

        findViewById(0x7f100266).setOnClickListener(new .Lambda._cls0());
        bundle = room.getCapacity();
        if (bundle == null)
        {
            bundle = null;
        } else
        {
            bundle = getResources().getQuantityString(0x7f120044, bundle.intValue(), new Object[] {
                bundle
            });
        }
        configureTile(0x7f100348, bundle);
        bundle = room.getFeatures();
        obj1 = com.google.android.calendar.timely.rooms.data.Room..Lambda._cls1.$instance;
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        bundle = new com.google.common.collect.Iterables._cls4(bundle, ((com.google.common.base.Predicate) (obj1)));
        obj1 = .Lambda._cls1..instance;
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        configureTile(0x7f100349, TextUtils.join(", ", new com.google.common.collect.Iterables._cls5(bundle, ((Function) (obj1)))));
        obj1 = getResources();
        bundle = room.getBuildingName();
        s = room.getFloorName();
        s1 = room.getFloorSectionName();
        if (!TextUtils.isEmpty(bundle)) goto _L2; else goto _L1
_L1:
        bundle = ((Bundle) (obj));
_L4:
        configureTile(0x7f10034a, bundle);
        bundle = room.getFeatures();
        obj = new com.google.common.base.Predicates.NotPredicate(com.google.android.calendar.timely.rooms.data.Room..Lambda._cls2.$instance);
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (!TextUtils.isEmpty(s) || !TextUtils.isEmpty(s1))
        {
            bundle = ((Resources) (obj1)).getString(0x7f13041c, new Object[] {
                bundle, Platform.nullToEmpty(s), Platform.nullToEmpty(s1)
            });
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (obj == null)
        {
            throw new NullPointerException();
        }
        bundle = new com.google.common.collect.Iterables._cls4(bundle, ((com.google.common.base.Predicate) (obj)));
        obj = .Lambda._cls1..instance;
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            configureTile(0x7f10034b, TextUtils.join(", ", new com.google.common.collect.Iterables._cls5(bundle, ((Function) (obj)))));
            configureTile(0x7f10034c, room.getDescription());
            return;
        }
    }
}
