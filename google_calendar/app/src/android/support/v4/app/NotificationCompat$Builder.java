// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            NotificationCompatBuilder

public final class mPeople
{

    public ArrayList mActions;
    public int mBadgeIcon;
    public String mCategory;
    public String mChannelId;
    public int mColor;
    public PendingIntent mContentIntent;
    public CharSequence mContentText;
    public CharSequence mContentTitle;
    public Context mContext;
    public Bundle mExtras;
    public int mGroupAlertBehavior;
    public ArrayList mInvisibleActions;
    public Bitmap mLargeIcon;
    public boolean mLocalOnly;
    public Notification mNotification;
    public ArrayList mPeople;
    public int mPriority;
    public boolean mShowWhen;
    public mNotification mStyle;
    public CharSequence mSubText;
    public int mVisibility;

    public final Notification build()
    {
        derAccessor deraccessor;
        NotificationCompatBuilder notificationcompatbuilder;
        notificationcompatbuilder = new NotificationCompatBuilder(this);
        deraccessor = notificationcompatbuilder.mBuilderCompat.mStyle;
        if (deraccessor != null)
        {
            deraccessor.ply(notificationcompatbuilder);
        }
        if (android.os.lderWithBuilderAccessor < 26) goto _L2; else goto _L1
_L1:
        Notification notification = notificationcompatbuilder.mBuilder.mBuilder();
_L4:
        Bundle bundle;
        if (deraccessor != null)
        {
            bundle = notification.extras;
        }
        return notification;
_L2:
        if (android.os. >= 24)
        {
            Notification notification1 = notificationcompatbuilder.mBuilder.mBuilder();
            notification = notification1;
            if (notificationcompatbuilder.mGroupAlertBehavior != 0)
            {
                if (notification1.getGroup() != null && (notification1.flags & 0x200) != 0 && notificationcompatbuilder.mGroupAlertBehavior == 2)
                {
                    NotificationCompatBuilder.removeSoundAndVibration(notification1);
                }
                notification = notification1;
                if (notification1.getGroup() != null)
                {
                    notification = notification1;
                    if ((notification1.flags & 0x200) == 0)
                    {
                        notification = notification1;
                        if (notificationcompatbuilder.mGroupAlertBehavior == 1)
                        {
                            NotificationCompatBuilder.removeSoundAndVibration(notification1);
                            notification = notification1;
                        }
                    }
                }
            }
        } else
        {
            notificationcompatbuilder.mBuilder.ras(notificationcompatbuilder.mExtras);
            Notification notification2 = notificationcompatbuilder.mBuilder.mBuilder();
            if (notificationcompatbuilder.mContentView != null)
            {
                notification2.contentView = notificationcompatbuilder.mContentView;
            }
            if (notificationcompatbuilder.mBigContentView != null)
            {
                notification2.bigContentView = notificationcompatbuilder.mBigContentView;
            }
            if (notificationcompatbuilder.mHeadsUpContentView != null)
            {
                notification2.headsUpContentView = notificationcompatbuilder.mHeadsUpContentView;
            }
            notification = notification2;
            if (notificationcompatbuilder.mGroupAlertBehavior != 0)
            {
                if (notification2.getGroup() != null && (notification2.flags & 0x200) != 0 && notificationcompatbuilder.mGroupAlertBehavior == 2)
                {
                    NotificationCompatBuilder.removeSoundAndVibration(notification2);
                }
                notification = notification2;
                if (notification2.getGroup() != null)
                {
                    notification = notification2;
                    if ((notification2.flags & 0x200) == 0)
                    {
                        notification = notification2;
                        if (notificationcompatbuilder.mGroupAlertBehavior == 1)
                        {
                            NotificationCompatBuilder.removeSoundAndVibration(notification2);
                            notification = notification2;
                        }
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final emoveSoundAndVibration setAutoCancel(boolean flag)
    {
        if (flag)
        {
            Notification notification = mNotification;
            notification.flags = notification.flags | 0x10;
            return this;
        } else
        {
            Notification notification1 = mNotification;
            notification1.flags = notification1.flags & 0xffffffef;
            return this;
        }
    }

    public final mNotification setContentText(CharSequence charsequence)
    {
        CharSequence charsequence1 = charsequence;
        if (charsequence != null)
        {
            charsequence1 = charsequence;
            if (charsequence.length() > 5120)
            {
                charsequence1 = charsequence.subSequence(0, 5120);
            }
        }
        mContentText = charsequence1;
        return this;
    }

    public final mContentText setContentTitle(CharSequence charsequence)
    {
        CharSequence charsequence1 = charsequence;
        if (charsequence != null)
        {
            charsequence1 = charsequence;
            if (charsequence.length() > 5120)
            {
                charsequence1 = charsequence.subSequence(0, 5120);
            }
        }
        mContentTitle = charsequence1;
        return this;
    }

    public final mContentTitle setSound(Uri uri)
    {
        mNotification.sound = uri;
        mNotification.audioStreamType = -1;
        mNotification.audioAttributes = (new android.media.it>()).ContentType(4).Usage(5).ld();
        return this;
    }

    public final pe setStyle(pe pe)
    {
        if (mStyle != pe)
        {
            mStyle = pe;
            if (mStyle != null)
            {
                pe = mStyle;
                if (pe.uilder != this)
                {
                    pe.uilder = this;
                    if (pe.uilder != null)
                    {
                        pe.uilder.setStyle(pe);
                    }
                }
            }
        }
        return this;
    }

    public final setStyle setTicker(CharSequence charsequence)
    {
        Notification notification = mNotification;
        CharSequence charsequence1 = charsequence;
        if (charsequence != null)
        {
            charsequence1 = charsequence;
            if (charsequence.length() > 5120)
            {
                charsequence1 = charsequence.subSequence(0, 5120);
            }
        }
        notification.tickerText = charsequence1;
        return this;
    }

    public (Context context)
    {
        this(context, null);
    }

    private <init>(Context context, String s)
    {
        mActions = new ArrayList();
        mInvisibleActions = new ArrayList();
        mShowWhen = true;
        mLocalOnly = false;
        mColor = 0;
        mVisibility = 0;
        mBadgeIcon = 0;
        mGroupAlertBehavior = 0;
        mNotification = new Notification();
        mContext = context;
        mChannelId = null;
        mNotification.when = System.currentTimeMillis();
        mNotification.audioStreamType = -1;
        mPriority = 0;
        mPeople = new ArrayList();
    }
}
