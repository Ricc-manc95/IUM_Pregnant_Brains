// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            SelectCalendarsAdapter, DrawerSyncUIManager, LogoLockupHelper

public final class DrawerListAdapter extends SelectCalendarsAdapter
{

    private final android.view.View.OnClickListener drawerButtonClickListener;
    private final DrawerSyncUIManager drawerSyncUIManager;
    private final int drawerVerticalPadding;
    public final boolean isTabletConfig;
    private final List postCalendarButtons = new ArrayList();
    private final List preCalendarButtons = new ArrayList();
    public int selectedViewId;
    public int topWindowInset;
    public final SparseBooleanArray viewSwitcherIds = new SparseBooleanArray();

    public DrawerListAdapter(Activity activity, boolean flag, Set set, DrawerFragment.OnDrawerItemClickedListener ondraweritemclickedlistener)
    {
        boolean flag1 = false;
        super(activity, true, 1, set);
        set = activity.getResources();
        drawerSyncUIManager = new DrawerSyncUIManager(activity, this);
        isTabletConfig = activity.getResources().getBoolean(0x7f0c0016);
        drawerVerticalPadding = set.getDimensionPixelSize(0x7f0e0130);
        selectedViewId = PreferencesUtils.getLastUsedView(mContext, isTabletConfig);
        SparseIntArray sparseintarray = new SparseIntArray();
        sparseintarray.put(0x7f100004, 0x7f13009c);
        sparseintarray.put(0x7f100022, 0x7f1302f6);
        sparseintarray.put(0x7f100026, 0x7f130321);
        sparseintarray.put(0x7f100050, 0x7f1304c0);
        sparseintarray.put(0x7f100027, 0x7f13033c);
        SparseIntArray sparseintarray1 = new SparseIntArray();
        sparseintarray1.put(0x7f100004, 0x7f02011e);
        sparseintarray1.put(0x7f100022, 0x7f02012c);
        sparseintarray1.put(0x7f100026, 0x7f020144);
        sparseintarray1.put(0x7f100050, 0x7f020144);
        sparseintarray1.put(0x7f100027, 0x7f02012d);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final DrawerListAdapter arg$1;
            private final DrawerFragment.OnDrawerItemClickedListener arg$2;

            public final void onClick(View view)
            {
                DrawerListAdapter drawerlistadapter = arg$1;
                DrawerFragment.OnDrawerItemClickedListener ondraweritemclickedlistener1 = arg$2;
                view = (DrawerButtonItem)view.findViewById(0x7f10017b).getTag();
                if (drawerlistadapter.viewSwitcherIds.get(((DrawerButtonItem) (view)).id, false) && drawerlistadapter.viewSwitcherIds.size() != 0)
                {
                    drawerlistadapter.selectedViewId = PreferencesUtils.getLastUsedView(drawerlistadapter.mContext, drawerlistadapter.isTabletConfig);
                    drawerlistadapter.notifyDataSetChanged();
                }
                if (ondraweritemclickedlistener1 != null)
                {
                    ondraweritemclickedlistener1.onDrawerItemClicked(((DrawerButtonItem) (view)).id);
                }
            }

            .Lambda._cls0(DrawerFragment.OnDrawerItemClickedListener ondraweritemclickedlistener)
            {
                arg$1 = DrawerListAdapter.this;
                arg$2 = ondraweritemclickedlistener;
            }
        }

        drawerButtonClickListener = new .Lambda._cls0(ondraweritemclickedlistener);
        if (set.getConfiguration().orientation == 2 && !isTabletConfig)
        {
            set = new int[0];
        } else
        {
            ondraweritemclickedlistener = set.obtainTypedArray(0x7f0b0057);
            set = new int[ondraweritemclickedlistener.length()];
            for (int j = 0; j < ondraweritemclickedlistener.length(); j++)
            {
                set[j] = ondraweritemclickedlistener.getResourceId(j, 0);
            }

            ondraweritemclickedlistener.recycle();
        }
        preCalendarButtons.add(new StatusbarSpacingItem());
        preCalendarButtons.add(new LogoLockupItem());
        for (int i = ((flag1) ? 1 : 0); i < set.length; i++)
        {
            int k = set[i];
            viewSwitcherIds.put(k, true);
            preCalendarButtons.add(new DrawerButtonItem(k, sparseintarray.get(k), sparseintarray1.get(k)));
        }

        preCalendarButtons.add(new DrawerButtonItem(0x7f100037, 0x7f13042e, 0x7f020231));
        postCalendarButtons.add(new DrawerButtonItem(0x7f100039, 0x7f13019e, 0x7f020234));
        postCalendarButtons.add(new DrawerButtonItem(0x7f100020, 0x7f130198, 0x7f020204));
        set = postCalendarButtons;
        ondraweritemclickedlistener = mContext;
        ondraweritemclickedlistener = Features.instance;
        if (ondraweritemclickedlistener == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)ondraweritemclickedlistener).experimental_options();
        set.addAll(Collections.emptyList());
        getAppVersion(activity);
        activity = Features.instance;
        if (activity == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)activity).dogfood_features();
            return;
        }
    }

    private static String getAppVersion(Activity activity)
    {
        PackageManager packagemanager = activity.getPackageManager();
        try
        {
            activity = String.valueOf(packagemanager.getPackageInfo(activity.getPackageName(), 0).versionName);
            if (activity.length() != 0)
            {
                return "Version: ".concat(activity);
            }
            activity = new String("Version: ");
        }
        // Misplaced declaration of an exception variable
        catch (Activity activity)
        {
            return null;
        }
        return activity;
    }

    public final int getCount()
    {
        return preCalendarButtons.size() + super.getCount() + postCalendarButtons.size();
    }

    public final DrawerSyncUIManager getDrawerSyncUIManager()
    {
        return drawerSyncUIManager;
    }

    public final com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo getItem(int i)
    {
        boolean flag;
        if (i >= preCalendarButtons.size() && i < getCount() - postCalendarButtons.size())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return super.getItem(i - preCalendarButtons.size());
        }
        if (i < preCalendarButtons.size())
        {
            return (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)preCalendarButtons.get(i);
        } else
        {
            return (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)postCalendarButtons.get(i - (getCount() - postCalendarButtons.size()));
        }
    }

    public final volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public final long getItemId(int i)
    {
        boolean flag;
        if (i >= preCalendarButtons.size() && i < getCount() - postCalendarButtons.size())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return super.getItemId(i - preCalendarButtons.size());
        } else
        {
            return (long)((DrawerButtonItem)(com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)getItem(i)).id;
        }
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        boolean flag;
        if (i >= preCalendarButtons.size() && i < getCount() - postCalendarButtons.size())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            view = super.getView(i, view, viewgroup);
        } else
        {
            DrawerButtonItem drawerbuttonitem = (DrawerButtonItem)(com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)getItem(i);
            Object obj = view;
            int j;
            int k;
            if (view == null)
            {
                if (drawerbuttonitem.getType() == 7)
                {
                    Context context = mContext;
                    LogoLockupHelper.marginStart = (int)context.getResources().getDimension(0x7f0e012c);
                    viewgroup = LayoutInflater.from(context).inflate(0x7f050058, viewgroup, false);
                    StringBuilder stringbuilder = new StringBuilder();
                    stringbuilder.append(context.getString(0x7f13019a));
                    obj = context.getString(0x7f13019c);
                    view = ((View) (obj));
                    if (((String) (obj)).isEmpty())
                    {
                        view = " ";
                    }
                    stringbuilder.append(view);
                    stringbuilder.append(context.getString(0x7f13019b));
                    viewgroup.setContentDescription(stringbuilder.toString());
                    viewgroup.setOnClickListener(null);
                    boolean flag1;
                    if (!"Google".equalsIgnoreCase(context.getString(0x7f13019b)))
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j == 0)
                    {
                        view = (LinearLayout)viewgroup.findViewById(0x7f10017e);
                        obj = view.findViewById(0x7f10017f);
                        View view1 = view.findViewById(0x7f100181);
                        view.removeView(((View) (obj)));
                        view.removeView(view1);
                        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)((View) (obj)).getLayoutParams();
                        layoutparams.setMarginStart(0);
                        ((View) (obj)).setLayoutParams(layoutparams);
                        layoutparams = (android.widget.LinearLayout.LayoutParams)view1.getLayoutParams();
                        layoutparams.setMarginStart(LogoLockupHelper.marginStart);
                        view1.setLayoutParams(layoutparams);
                        view.addView(view1, 0);
                        view.addView(((View) (obj)), 2);
                    }
                    view = (TextView)viewgroup.findViewById(0x7f100181);
                    if (LogoLockupHelper.productSans == null)
                    {
                        LogoLockupHelper.productSans = Utils.createProductSans(context);
                    }
                    view.setTypeface(LogoLockupHelper.productSans);
                    if (!"Google".equalsIgnoreCase(context.getString(0x7f13019b)))
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j == 0)
                    {
                        view.setText(0x7f13019a);
                    }
                    if (!context.getString(0x7f13019c).isEmpty())
                    {
                        view = (TextView)viewgroup.findViewById(0x7f100180);
                        if (LogoLockupHelper.productSans == null)
                        {
                            LogoLockupHelper.productSans = Utils.createProductSans(context);
                        }
                        view.setTypeface(LogoLockupHelper.productSans);
                    }
                    view = viewgroup;
                } else
                if (drawerbuttonitem.getType() == 8)
                {
                    view = LayoutInflater.from(mContext).inflate(0x7f050057, null);
                } else
                {
                    view = LayoutInflater.from(mContext).inflate(0x7f050056, null);
                }
                if (view.findViewById(0x7f10017b) != null)
                {
                    view.setOnClickListener(drawerButtonClickListener);
                }
                obj = view;
            }
            if (drawerbuttonitem.getType() == 8)
            {
                ((View) (obj)).setMinimumHeight(topWindowInset);
            } else
            if (drawerbuttonitem.getType() != 7)
            {
                view = ((View) (obj)).findViewById(0x7f10017b);
                boolean flag2;
                if (view.getTag() == drawerbuttonitem)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j == 0)
                {
                    view.setTag(drawerbuttonitem);
                }
                view = (TextView)((View) (obj)).findViewById(0x7f10017c);
                if (j == 0)
                {
                    view.setCompoundDrawablesRelativeWithIntrinsicBounds(drawerbuttonitem.iconId, 0, 0, 0);
                    if (drawerbuttonitem.getType() == 9)
                    {
                        view.setText(((LabelItem)drawerbuttonitem).labelText);
                    } else
                    {
                        view.setText(drawerbuttonitem.labelId);
                    }
                }
                if (drawerbuttonitem.id == selectedViewId)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                view.setSelected(flag2);
            }
            flag1 = false;
            if (i == getCount() - postCalendarButtons.size())
            {
                flag1 = true;
            }
            if (i == getCount() - 1)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (flag1)
            {
                k = drawerVerticalPadding;
            } else
            {
                k = 0;
            }
            if (j != 0)
            {
                j = drawerVerticalPadding;
            } else
            {
                j = 0;
            }
            ((View) (obj)).setPadding(0, k, 0, j);
            view = ((View) (obj));
            if (drawerbuttonitem.getType() != 7)
            {
                view = ((View) (obj));
                if (drawerbuttonitem.getType() != 8)
                {
                    viewgroup = ((View) (obj)).findViewById(0x7f10017a);
                    view = ((View) (obj)).findViewById(0x7f10017d);
                    viewgroup.setVisibility(8);
                    view.setVisibility(8);
                    if (i == 2)
                    {
                        viewgroup.setVisibility(0);
                    }
                    if (i == preCalendarButtons.size() - 1)
                    {
                        view.setVisibility(0);
                    }
                    view = ((View) (obj));
                    if (i == getCount() - postCalendarButtons.size())
                    {
                        viewgroup.setVisibility(0);
                        return ((View) (obj));
                    }
                }
            }
        }
        return view;
    }

    public final int getViewTypeCount()
    {
        return super.getViewTypeCount() + 1 + 1 + 1;
    }

    private class StatusbarSpacingItem extends DrawerButtonItem
    {
        private class DrawerButtonItem
            implements com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo
        {

            public final int iconId;
            public final int id;
            public final int labelId;

            public final int getOrder()
            {
                return 0;
            }

            public int getType()
            {
                return 6;
            }

            public int getViewType()
            {
                return 3;
            }

            public DrawerButtonItem(int i, int j, int k)
            {
                id = i;
                labelId = j;
                iconId = k;
            }
        }


        public final int getType()
        {
            return 8;
        }

        public final int getViewType()
        {
            return 5;
        }

        public StatusbarSpacingItem()
        {
            super(0, 0, 0);
        }
    }


    private class LogoLockupItem extends DrawerButtonItem
    {

        public final int getType()
        {
            return 7;
        }

        public final int getViewType()
        {
            return 4;
        }

        public LogoLockupItem()
        {
            super(0x7f10017f, 0, 0);
        }
    }


    private class LabelItem extends DrawerButtonItem
    {

        public final String labelText;

        public final int getType()
        {
            return 9;
        }

        public final int getViewType()
        {
            return 3;
        }
    }

}
