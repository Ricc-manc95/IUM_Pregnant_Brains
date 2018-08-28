// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ListView;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.timely.AllCalendarsHiddenDialogManager;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            DrawerListAdapter, SelectCalendarsAdapter, AccountPhotoLoader

public final class DrawerFragment extends Fragment
    implements OnApplyWindowInsetsListener, com.google.android.calendar.CalendarController.Command.Handler
{

    public DrawerListAdapter adapter;
    private Subscription calendarSubscription;
    public boolean calendarsLoaded;
    private Context context;
    private CalendarController controller;
    public final List drawerClosedListeners = new ArrayList();
    public OnDrawerItemClickedListener drawerItemClickedListener;
    public ListView list;
    public ImmutableMap settings;
    public boolean settingsLoaded;
    private Subscription settingsSubscription;

    public DrawerFragment()
    {
    }

    public final long getSupportedCommands()
    {
        return 128L;
    }

    public final void handleCommand(com.google.android.calendar.CalendarController.Command command)
    {
        if (adapter != null)
        {
            adapter.notifyDataSetChanged();
        }
    }

    public final void onActivityCreated(Bundle bundle)
    {
        Trace.beginSection("DrawerFragment.onActivityCreated");
        super.onActivityCreated(bundle);
        if (adapter != null) goto _L2; else goto _L1
_L1:
        if (super.mHost != null)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        bundle = null;
_L3:
        adapter = new DrawerListAdapter(bundle, true, new HashSet(Arrays.asList(new Integer[] {
            Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)
        })), drawerItemClickedListener);
_L2:
        list.setAdapter(adapter);
        bundle = adapter.getDrawerSyncUIManager();
        drawerClosedListeners.add(bundle);
        bundle = new AllCalendarsHiddenDialogManager(context, adapter);
        drawerClosedListeners.add(bundle);
        ViewCompat.setOnApplyWindowInsetsListener(super.mView, this);
        ViewCompat.requestApplyInsets(super.mView);
        Trace.endSection();
        return;
        bundle = (FragmentActivity)super.mHost.mActivity;
          goto _L3
        bundle;
        Trace.endSection();
        throw bundle;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        adapter.topWindowInset = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop();
        return windowinsetscompat;
    }

    public final void onAttach(Activity activity)
    {
        Trace.beginSection("DrawerFragment.onAttach");
        super.onAttach(activity);
        context = activity;
        controller = (CalendarController)CalendarController.instances.getInstance(activity);
        controller.registerHandler(0x7f050051, this);
        Trace.endSection();
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        Trace.beginSection("DrawerFragment.onCreateView");
        super.onCreateView(layoutinflater, viewgroup, bundle);
        layoutinflater = layoutinflater.inflate(0x7f050051, null);
        list = (ListView)layoutinflater.findViewById(0x7f100170);
        list.setEmptyView(layoutinflater.findViewById(0x7f10016e));
        Trace.endSection();
        return layoutinflater;
    }

    public final void onDestroy()
    {
        drawerClosedListeners.clear();
        super.onDestroy();
    }

    public final void onDetach()
    {
        super.onDetach();
        controller.deregisterHandler(Integer.valueOf(0x7f050051));
    }

    public final void onPause()
    {
        super.onPause();
        adapter.reorderItems();
    }

    public final void onStart()
    {
        Trace.beginSection("DrawerFragment.onStart");
        super.onStart();
        Object obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        class .Lambda._cls0
            implements Consumer
        {

            private final DrawerFragment arg$1;

            public final void accept(Object obj1)
            {
                DrawerFragment drawerfragment = arg$1;
                CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj1;
                obj1 = drawerfragment.adapter;
                CalendarListEntry calendarlistentry = BirthdayManager.getPrimaryBirthdayCalendar(((SelectCalendarsAdapter) (obj1)).mContext, acalendarlistentry);
                if (calendarlistentry != null)
                {
                    Context context1 = ((SelectCalendarsAdapter) (obj1)).mContext;
                    int i = calendarlistentry.getColor().getValue();
                    context1.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("preferences_birthdays_color", i).apply();
                    (new BackupManager(context1)).dataChanged();
                } else
                if (!((SelectCalendarsAdapter) (obj1)).mContext.getSharedPreferences("com.google.android.calendar_preferences", 0).contains("preferences_birthdays_color"))
                {
                    obj3 = ((SelectCalendarsAdapter) (obj1)).mContext;
                    ((Context) (obj3)).getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("preferences_birthdays_color", 0xff92e1c0).apply();
                    (new BackupManager(((Context) (obj3)))).dataChanged();
                }
                boolean flag2;
                if (CalendarListUtils.processCalendars(acalendarlistentry, ((SelectCalendarsAdapter) (obj1)).mLastItems, ((SelectCalendarsAdapter) (obj1)).originalVisibilities, false))
                {
                    for (Iterator iterator = ((SelectCalendarsAdapter) (obj1)).mLastItems.iterator(); iterator.hasNext();)
                    {
                        Object obj3 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)iterator.next();
                        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj3)).getType() == 3)
                        {
                            obj3 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)obj3;
                            obj3.areVisible = ((SelectCalendarsAdapter) (obj1)).mContext.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_birthdays_master_visibility", true);
                            ((SelectCalendarsAdapter) (obj1)).saveBirthdayVisibility(((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (obj3)));
                        } else
                        if (obj3 instanceof com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)
                        {
                            obj3 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)obj3;
                            if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (obj3)).calendars.size() > 0)
                            {
                                ArrayList arraylist = (ArrayList)((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (obj3)).calendars;
                                int k = arraylist.size();
                                int j = 0;
                                boolean flag = false;
                                do
                                {
                                    if (j >= k)
                                    {
                                        break;
                                    }
                                    Object obj6 = arraylist.get(j);
                                    j++;
                                    obj6 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)obj6;
                                    if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj6)).isVisible != ((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (obj3)).areVisible)
                                    {
                                        flag = ((SelectCalendarsAdapter) (obj1)).saveCalendarVisibility(((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj6)), ((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (obj3)).areVisible) | flag;
                                    }
                                } while (true);
                                if (flag)
                                {
                                    LatencyLoggerHolder.get().markAt(5);
                                }
                            }
                        }
                    }

                    Object obj2 = ((SelectCalendarsAdapter) (obj1)).mContext;
                    if (SyncOffNotificationsManager.instance == null)
                    {
                        SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj2)));
                    }
                    obj2 = SyncOffNotificationsManager.instance;
                    Object obj5 = ((SelectCalendarsAdapter) (obj1)).mLastItems;
                    Object obj4 = new HashSet();
                    obj5 = ((List) (obj5)).iterator();
                    do
                    {
                        if (!((Iterator) (obj5)).hasNext())
                        {
                            break;
                        }
                        com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo calendarlistiteminfo = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)((Iterator) (obj5)).next();
                        if (calendarlistiteminfo instanceof com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem)
                        {
                            ((Set) (obj4)).add(((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem)calendarlistiteminfo).account);
                        }
                    } while (true);
                    ((SyncOffNotificationsManager) (obj2)).allAccounts.clear();
                    ((SyncOffNotificationsManager) (obj2)).allAccounts.addAll(((java.util.Collection) (obj4)));
                    ((SyncOffNotificationsManager) (obj2)).syncOffAccounts.clear();
                    obj4 = ((Set) (obj4)).iterator();
                    do
                    {
                        if (!((Iterator) (obj4)).hasNext())
                        {
                            break;
                        }
                        Account account = (Account)((Iterator) (obj4)).next();
                        boolean flag1;
                        if (SyncUtils.isSyncable(account) && !SyncUtils.getSyncAutomatically(account))
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (flag1)
                        {
                            ((SyncOffNotificationsManager) (obj2)).syncOffAccounts.add(account);
                        }
                    } while (true);
                    ((SyncOffNotificationsManager) (obj2)).checkDismissCycle();
                    if (((SyncOffNotificationsManager) (obj2)).needsRefresh)
                    {
                        ((SyncOffNotificationsManager) (obj2)).onAppOpen();
                    }
                    if (((SyncOffNotificationsManager) (obj2)).isShowing)
                    {
                        if (!ContentResolver.getMasterSyncAutomatically())
                        {
                            flag2 = true;
                        } else
                        {
                            flag2 = false;
                        }
                        if (!flag2 && (((SyncOffNotificationsManager) (obj2)).account != null || ((SyncOffNotificationsManager) (obj2)).syncOffAccounts.size() <= 0) && (((SyncOffNotificationsManager) (obj2)).account == null || !((SyncOffNotificationsManager) (obj2)).syncOffAccounts.contains(((SyncOffNotificationsManager) (obj2)).account)))
                        {
                            ((SyncOffNotificationsManager) (obj2)).notification.hide(false, true);
                        }
                    }
                    ((SelectCalendarsAdapter) (obj1)).updateItemList();
                } else
                {
                    ((SelectCalendarsAdapter) (obj1)).notifyDataSetChanged();
                }
                drawerfragment.calendarsLoaded = true;
                if (drawerfragment.settingsLoaded && drawerfragment.calendarsLoaded)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (flag2)
                {
                    drawerfragment.adapter.settings = drawerfragment.settings;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = DrawerFragment.this;
            }
        }

        calendarSubscription = ((ListenableFutureCache)obj).subscribe(new .Lambda._cls0(), CalendarExecutor.MAIN, true);
        obj = SettingsCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        class .Lambda._cls1
            implements Consumer
        {

            private final DrawerFragment arg$1;

            public final void accept(Object obj1)
            {
                boolean flag = true;
                DrawerFragment drawerfragment = arg$1;
                drawerfragment.settings = (ImmutableMap)obj1;
                drawerfragment.settingsLoaded = true;
                if (!drawerfragment.settingsLoaded || !drawerfragment.calendarsLoaded)
                {
                    flag = false;
                }
                if (flag)
                {
                    drawerfragment.adapter.settings = drawerfragment.settings;
                }
                drawerfragment.adapter.updateItemList();
            }

            .Lambda._cls1()
            {
                arg$1 = DrawerFragment.this;
            }
        }

        settingsSubscription = ((ListenableFutureCache)obj).subscribe(new .Lambda._cls1(), CalendarExecutor.MAIN, true);
        obj = context;
        if (AccountPhotoLoader.instance == null)
        {
            AccountPhotoLoader.instance = new AccountPhotoLoader(((Context) (obj)));
        }
        obj = AccountPhotoLoader.instance;
        if (((AccountPhotoLoader) (obj)).client != null && !((AccountPhotoLoader) (obj)).client.isConnected() && !((AccountPhotoLoader) (obj)).client.isConnecting())
        {
            ((AccountPhotoLoader) (obj)).client.connect();
        }
        obj.closed = false;
        Trace.endSection();
    }

    public final void onStop()
    {
        Object obj = context;
        if (AccountPhotoLoader.instance == null)
        {
            AccountPhotoLoader.instance = new AccountPhotoLoader(((Context) (obj)));
        }
        obj = AccountPhotoLoader.instance;
        if (((AccountPhotoLoader) (obj)).client != null && (((AccountPhotoLoader) (obj)).client.isConnected() || ((AccountPhotoLoader) (obj)).client.isConnecting()))
        {
            ((AccountPhotoLoader) (obj)).client.disconnect();
        }
        ((AccountPhotoLoader) (obj)).requests.clear();
        obj.closed = true;
        calendarSubscription.cancel(false);
        calendarSubscription = null;
        calendarsLoaded = false;
        settingsSubscription.cancel(false);
        settingsSubscription = null;
        settingsLoaded = false;
        super.onStop();
    }
}
