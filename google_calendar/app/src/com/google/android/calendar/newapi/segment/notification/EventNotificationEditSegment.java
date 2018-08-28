// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public class EventNotificationEditSegment extends EditSegment
    implements android.view.View.OnClickListener
{
    public static interface Listener
    {

        public abstract void onAddNotificationClicked();

        public abstract void onNotificationClicked(Notification notification, int i);

        public abstract void onRemoveButtonClicked(Notification notification);
    }


    private TextTileView addNotification;
    public boolean allDay;
    private final LayoutInflater inflater;
    public int maxNotifications;
    public LinearLayout notificationList;
    public final ReminderUtils reminderUtils = new ReminderUtils(getContext());

    public EventNotificationEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        inflater = LayoutInflater.from(context);
    }

    final void addNotificationStealthy(Notification notification)
    {
        TextTileView texttileview = new TextTileView(getContext());
        texttileview.treatAsButton(true).indentContent();
        int i = notificationList.getChildCount();
        texttileview.setTag(notification);
        texttileview.setOnClickListener(this);
        texttileview.setPrimaryText(new CharSequence[] {
            reminderUtils.constructLabel(notification.minutesBefore, notification.method, allDay)
        });
        View view = inflater.inflate(0x7f05013f, texttileview, false);
        texttileview.rightActionView = view;
        texttileview.addView(view);
        if (((TileView) (texttileview)).rightActionView != null && !texttileview.hasOnClickListeners())
        {
            texttileview.treatAsButton(true);
            texttileview.setOnClickListener(new com.google.android.calendar.tiles.view.TileView..Lambda._cls1(texttileview));
        }
        texttileview.setFocusable(true);
        ((ImageView)view.findViewById(0x7f1000d4)).setImageResource(0x7f0201d8);
        view.setContentDescription(getResources().getString(0x7f1303e8));
        view.setId(0x7f100031);
        view.setTag(notification);
        view.setOnClickListener(this);
        texttileview.treatAsButton(false);
        texttileview.setOnClickListener(null);
        texttileview.setClickable(false);
        notificationList.addView(texttileview, i - 1);
        updateAddNotificationLabel();
    }

    public void onClick(View view)
    {
        ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (view.getId() == 0x7f100270)
        {
            ((Listener)super.mListener).onAddNotificationClicked();
            return;
        }
        if (view.getId() == 0x7f100031)
        {
            view = (Notification)view.getTag();
            ((Listener)super.mListener).onRemoveButtonClicked(view);
            return;
        } else
        {
            int i = notificationList.indexOfChild(view);
            view = (Notification)view.getTag();
            ((Listener)super.mListener).onNotificationClicked(view, i);
            return;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        addNotification = (TextTileView)findViewById(0x7f100270);
        addNotification.setOnClickListener(this);
        notificationList = (LinearLayout)findViewById(0x7f10026f);
    }

    public final void removeNotification(int i)
    {
        notificationList.removeViewAt(i);
        updateAddNotificationLabel();
        announceForAccessibility(getResources().getString(0x7f13000f, new Object[0]));
    }

    final void updateAddNotificationLabel()
    {
        if (notificationList.getChildCount() >= maxNotifications + 1)
        {
            addNotification.setVisibility(8);
            return;
        }
        addNotification.setVisibility(0);
        if (notificationList.getChildCount() > 1)
        {
            TextTileView texttileview = addNotification;
            texttileview.setPrimaryText(new CharSequence[] {
                texttileview.getResources().getString(0x7f13008b, new Object[0])
            });
            return;
        } else
        {
            TextTileView texttileview1 = addNotification;
            texttileview1.setPrimaryText(new CharSequence[] {
                texttileview1.getResources().getString(0x7f130097, new Object[0])
            });
            return;
        }
    }
}
