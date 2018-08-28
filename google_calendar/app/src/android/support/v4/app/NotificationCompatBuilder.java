// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor, NotificationCompatJellybean

final class NotificationCompatBuilder
    implements NotificationBuilderWithBuilderAccessor
{

    private final List mActionExtrasList;
    public RemoteViews mBigContentView;
    public final android.app.Notification.Builder mBuilder;
    public final NotificationCompat.Builder mBuilderCompat;
    public RemoteViews mContentView;
    public final Bundle mExtras;
    public int mGroupAlertBehavior;
    public RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(NotificationCompat.Builder builder)
    {
        RemoteInput aremoteinput[];
        Notification notification;
        NotificationCompat.Action action;
        android.app.Notification.Action.Builder builder2;
        android.support.v4.app.RemoteInput aremoteinput1[];
        int i;
        boolean flag1 = true;
        super();
        mActionExtrasList = new ArrayList();
        mExtras = new Bundle();
        mBuilderCompat = builder;
        android.app.Notification.Builder builder1;
        ArrayList arraylist1;
        int l;
        int j1;
        int k1;
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            mBuilder = new android.app.Notification.Builder(builder.mContext, builder.mChannelId);
        } else
        {
            mBuilder = new android.app.Notification.Builder(builder.mContext);
        }
        notification = builder.mNotification;
        builder1 = mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
        if ((notification.flags & 2) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        builder1 = builder1.setOngoing(flag);
        if ((notification.flags & 8) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        builder1 = builder1.setOnlyAlertOnce(flag);
        if ((notification.flags & 0x10) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        builder1 = builder1.setAutoCancel(flag).setDefaults(notification.defaults).setContentTitle(builder.mContentTitle).setContentText(builder.mContentText).setContentInfo(null).setContentIntent(builder.mContentIntent).setDeleteIntent(notification.deleteIntent);
        if ((notification.flags & 0x80) != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        builder1.setFullScreenIntent(null, flag).setLargeIcon(builder.mLargeIcon).setNumber(0).setProgress(0, 0, false);
        mBuilder.setSubText(builder.mSubText).setUsesChronometer(false).setPriority(builder.mPriority);
        arraylist1 = (ArrayList)builder.mActions;
        j1 = arraylist1.size();
        i = 0;
_L7:
        if (i >= j1) goto _L2; else goto _L1
_L1:
        action = (NotificationCompat.Action)arraylist1.get(i);
        builder2 = new android.app.Notification.Action.Builder(action.icon, action.title, action.actionIntent);
        if (action.mRemoteInputs == null)
        {
            break MISSING_BLOCK_LABEL_472;
        }
        aremoteinput1 = action.mRemoteInputs;
        if (aremoteinput1 != null) goto _L4; else goto _L3
_L3:
        aremoteinput = null;
_L6:
        k1 = aremoteinput.length;
        for (l = 0; l < k1; l++)
        {
            builder2.addRemoteInput(aremoteinput[l]);
        }

        break MISSING_BLOCK_LABEL_472;
_L4:
        aremoteinput = new RemoteInput[aremoteinput1.length];
        if (aremoteinput1.length >= 0) goto _L6; else goto _L5
_L5:
        builder = aremoteinput1[0];
        throw new NoSuchMethodError();
        Bundle bundle;
        if (action.mExtras != null)
        {
            bundle = new Bundle(action.mExtras);
        } else
        {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.mAllowGeneratedReplies);
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            builder2.setAllowGeneratedReplies(action.mAllowGeneratedReplies);
        }
        bundle.putInt("android.support.action.semanticAction", action.mSemanticAction);
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            builder2.setSemanticAction(action.mSemanticAction);
        }
        bundle.putBoolean("android.support.action.showsUserInterface", action.mShowsUserInterface);
        builder2.addExtras(bundle);
        mBuilder.addAction(builder2.build());
        i++;
          goto _L7
_L2:
        if (builder.mExtras != null)
        {
            mExtras.putAll(builder.mExtras);
        }
        mContentView = null;
        mBigContentView = null;
        mBuilder.setShowWhen(builder.mShowWhen);
        mBuilder.setLocalOnly(builder.mLocalOnly).setGroup(null).setGroupSummary(false).setSortKey(null);
        mGroupAlertBehavior = 0;
        mBuilder.setCategory(builder.mCategory).setColor(builder.mColor).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        ArrayList arraylist = (ArrayList)builder.mPeople;
        int i1 = arraylist.size();
        for (int j = 0; j < i1;)
        {
            Object obj = arraylist.get(j);
            j++;
            obj = (String)obj;
            mBuilder.addPerson(((String) (obj)));
        }

        mHeadsUpContentView = null;
        if (builder.mInvisibleActions.size() > 0)
        {
            if (builder.mExtras == null)
            {
                builder.mExtras = new Bundle();
            }
            Bundle bundle1 = builder.mExtras.getBundle("android.car.EXTENSIONS");
            if (bundle1 == null)
            {
                bundle1 = new Bundle();
            }
            Bundle bundle2 = new Bundle();
            for (int k = 0; k < builder.mInvisibleActions.size(); k++)
            {
                bundle2.putBundle(Integer.toString(k), NotificationCompatJellybean.getBundleForAction((NotificationCompat.Action)builder.mInvisibleActions.get(k)));
            }

            bundle1.putBundle("invisible_actions", bundle2);
            if (builder.mExtras == null)
            {
                builder.mExtras = new Bundle();
            }
            builder.mExtras.putBundle("android.car.EXTENSIONS", bundle1);
            mExtras.putBundle("android.car.EXTENSIONS", bundle1);
        }
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            mBuilder.setExtras(builder.mExtras).setRemoteInputHistory(null);
        }
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            mBuilder.setBadgeIconType(0).setShortcutId(null).setTimeoutAfter(0L).setGroupAlertBehavior(0);
            if (!TextUtils.isEmpty(builder.mChannelId))
            {
                mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        return;
    }

    static void removeSoundAndVibration(Notification notification)
    {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults = notification.defaults & -2;
        notification.defaults = notification.defaults & -3;
    }

    public final android.app.Notification.Builder getBuilder()
    {
        return mBuilder;
    }
}
