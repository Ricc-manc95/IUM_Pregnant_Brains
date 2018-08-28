// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.accounts.Account;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntryModifications;
import com.google.android.calendar.api.calendarlist.CalendarListFactory;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.android.calendar.calendarlist.common.CalendarListUtils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.prefs.PrefService;
import com.google.android.calendar.timely.DrawerColorCheckBox;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.ImageUtils;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.calendar.utils.account.PrimaryAccountSelector;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            AccountPhotoLoader, DrawerSyncUIManager, AccountSyncState

public class SelectCalendarsAdapter extends BaseAdapter
    implements android.view.View.OnClickListener
{

    private final int birthdayColor = 0xff92e1c0;
    private final String birthdayText;
    private final ImmutableSet clickableViewTypes;
    private boolean enablePostProcess;
    private final int groupTopMarginIncrease;
    private final String holidayText;
    public final Context mContext;
    public final ArrayList mItems = new ArrayList();
    public final List mLastItems = new ArrayList();
    private final Drawable moreDrawable;
    private final String moreLabelText;
    private final int operationMode = 1;
    public final HashMap originalVisibilities = new HashMap();
    private final String primaryCalendarText;
    private final Resources resources;
    public ImmutableMap settings;
    private final String showCalendarsText;
    private final ArrayList showHiddenCalendars = new ArrayList();
    private final String showMoreText;

    public SelectCalendarsAdapter(Context context, boolean flag, int i, Set set)
    {
        mContext = context;
        enablePostProcess = flag;
        resources = mContext.getResources();
        groupTopMarginIncrease = resources.getDimensionPixelOffset(0x7f0e0124);
        moreDrawable = resources.getDrawable(0x7f02005a);
        moreLabelText = resources.getString(0x7f13033f);
        birthdayText = resources.getString(0x7f130194);
        holidayText = resources.getString(0x7f130199);
        primaryCalendarText = resources.getString(0x7f1303b3);
        showMoreText = resources.getString(0x7f130448);
        showCalendarsText = resources.getString(0x7f130446);
        clickableViewTypes = ImmutableSet.copyOf(set);
    }

    private final View getElementView(View view, String s, boolean flag, int i)
    {
        View view1 = view;
        if (view == null)
        {
            int j;
            if (operationMode == 0)
            {
                j = 0x7f050053;
            } else
            {
                j = 0x7f050052;
            }
            view1 = View.inflate(mContext, j, null);
        }
        ((TextView)view1.findViewById(0x7f100173)).setText(s);
        view = view1.findViewById(0x7f100172);
        view.setVisibility(0);
        i = ColorUtils.getDisplayColorFromColor(i);
        if (operationMode == 1)
        {
            view = (DrawerColorCheckBox)view;
            view.setColor(i);
            view.setChecked(flag);
        } else
        {
            view.getBackground().setColorFilter(i, android.graphics.PorterDuff.Mode.SRC_ATOP);
        }
        view = resources;
        if (flag)
        {
            i = 0x7f1300ee;
        } else
        {
            i = 0x7f1300f1;
        }
        view1.setContentDescription(view.getString(i, new Object[] {
            s
        }));
        view1.invalidate();
        return view1;
    }

    private final View getGroupView(View view, String s)
    {
        View view1 = view;
        if (view == null)
        {
            int i;
            if (operationMode == 0)
            {
                i = 0x7f050055;
            } else
            {
                i = 0x7f050054;
            }
            view1 = View.inflate(mContext, i, null);
        }
        ((TextView)view1.findViewById(0x7f100177)).setText(s);
        view1.setAccessibilityDelegate(new GroupItemAccessibilityDelegate());
        return view1;
    }

    public int getCount()
    {
        return mItems.size();
    }

    public DrawerSyncUIManager getDrawerSyncUIManager()
    {
        return null;
    }

    public com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo getItem(int i)
    {
        return (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)mItems.get(i);
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public long getItemId(int i)
    {
        return (long)((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)mItems.get(i)).hashCode();
    }

    public int getItemViewType(int i)
    {
        return ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)getItem(i)).getViewType();
    }

    public View getView(int i, final View account, ViewGroup viewgroup)
    {
        com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo calendarlistiteminfo;
        viewgroup = Features.instance;
        if (viewgroup == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)viewgroup).resync();
        calendarlistiteminfo = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)getItem(i);
        calendarlistiteminfo.getType();
        JVM INSTR tableswitch 0 5: default 88
    //                   0 181
    //                   1 1044
    //                   2 1188
    //                   3 1218
    //                   4 1280
    //                   5 827;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L6:
        break MISSING_BLOCK_LABEL_1280;
_L1:
        account = null;
_L8:
        Object obj;
        Object obj1;
        Object obj2;
        View view;
        View view1;
        Object obj3;
        TextView textview;
        int j;
        boolean flag;
        if (clickableViewTypes.contains(Integer.valueOf(calendarlistiteminfo.getType())))
        {
            account.setOnClickListener(this);
            account.setEnabled(true);
        } else
        {
            account.setOnClickListener(null);
            account.setClickable(false);
            account.setBackground(null);
            account.setEnabled(false);
        }
        j = calendarlistiteminfo.getViewType();
        if ((j == 0 || j == 1) && i > 0)
        {
            account.setPadding(0, groupTopMarginIncrease, 0, 0);
        }
        viewgroup = mContext;
        viewgroup = Features.instance;
        if (viewgroup == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)viewgroup).experimental_options();
            account.setTag(calendarlistiteminfo);
            return account;
        }
_L2:
        obj1 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem)calendarlistiteminfo;
        viewgroup = mContext;
        if (AccountPhotoLoader.instance == null)
        {
            AccountPhotoLoader.instance = new AccountPhotoLoader(viewgroup);
        }
        obj = AccountPhotoLoader.instance;
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj1)).account == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj1)).account.name;
        }
        viewgroup = getGroupView(account, viewgroup);
        if (operationMode == 1)
        {
            account = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj1)).account;
            obj2 = getDrawerSyncUIManager().getSyncState(account);
            obj3 = (ImageView)viewgroup.findViewById(0x7f100179);
            view = viewgroup.findViewById(0x7f100144);
            textview = (TextView)viewgroup.findViewById(0x7f100178);
            view1 = viewgroup.findViewById(0x7f100175);
            if (((AccountSyncState) (obj2)).textId != 0)
            {
                textview.setText(((AccountSyncState) (obj2)).textId);
            }
            if (((AccountSyncState) (obj2)).iconId != 0)
            {
                ((ImageView) (obj3)).setImageResource(((AccountSyncState) (obj2)).iconId);
            }
            if (((AccountSyncState) (obj2)).iconId != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (obj3 != null)
            {
                if (j != 0)
                {
                    j = 0;
                } else
                {
                    j = 8;
                }
                ((View) (obj3)).setVisibility(j);
            }
            if (((AccountSyncState) (obj2)).textId != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (textview != null)
            {
                if (j != 0)
                {
                    j = 0;
                } else
                {
                    j = 8;
                }
                textview.setVisibility(j);
            }
            obj3 = mContext.getResources();
            if (((AccountSyncState) (obj2)).iconId != 0)
            {
                j = 0x7f0e0399;
            } else
            {
                j = 0x7f0e0194;
            }
            j = ((Resources) (obj3)).getDimensionPixelSize(j);
            obj3 = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            obj3.rightMargin = j;
            ((android.widget.FrameLayout.LayoutParams) (obj3)).setMarginEnd(j);
            view.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj3)));
            if (AccountSyncState.DISABLED.equals(obj2))
            {
                view1.setOnClickListener(new _cls1());
                view1.setEnabled(true);
                view1.setAccessibilityDelegate(null);
                getDrawerSyncUIManager().showedSyncOff = true;
            } else
            {
                view1.setOnClickListener(null);
                view1.setClickable(false);
                view1.setEnabled(false);
                view1.setAccessibilityDelegate(new GroupItemAccessibilityDelegate());
            }
        }
        obj2 = (ImageView)viewgroup.findViewById(0x7f100176);
        if (AccountPhotoLoader.placeholder == null)
        {
            AccountPhotoLoader.placeholder = ImageUtils.frameBitmapInCircle(BitmapFactory.decodeResource(((AccountPhotoLoader) (obj)).context.getResources(), 0x7f020132));
        }
        ((ImageView) (obj2)).setImageBitmap(AccountPhotoLoader.placeholder);
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj1)).account == null)
        {
            account = null;
        } else
        {
            account = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj1)).account.name;
        }
        if (((AccountPhotoLoader) (obj)).images.containsKey(account))
        {
            ((ImageView) (obj2)).setImageBitmap((Bitmap)((AccountPhotoLoader) (obj)).images.get(account));
            account = viewgroup;
        } else
        {
            account = new AccountPhotoLoader.OwnerAvatarRequest(((AccountPhotoLoader) (obj)), ((ImageView) (obj2)), account, null, 1);
            ((ImageView) (obj2)).setTag(null);
            for (j = 0; j < ((AccountPhotoLoader) (obj)).requests.size();)
            {
                if (((AccountPhotoLoader.OwnerAvatarRequest)((AccountPhotoLoader) (obj)).requests.get(j)).view == obj2)
                {
                    ((AccountPhotoLoader) (obj)).requests.remove(j);
                } else
                {
                    j++;
                }
            }

            ((ImageView) (obj2)).setTag(account);
            ((AccountPhotoLoader) (obj)).requests.add(account);
            account = viewgroup;
            if (((AccountPhotoLoader) (obj)).requests.size() > 0)
            {
                account = viewgroup;
                if (((AccountPhotoLoader) (obj)).client.isConnected())
                {
                    ((AccountPhotoLoader) (obj)).processNextRequest();
                    account = viewgroup;
                }
            }
        }
          goto _L8
_L7:
        viewgroup = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfHiddenCalendarsItem)calendarlistiteminfo;
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (viewgroup)).account == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (viewgroup)).account.name;
        }
        obj1 = mItems.iterator();
        j = 0;
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)((Iterator) (obj1)).next();
            if (obj instanceof com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)
            {
                obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)obj;
                if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).priority != 4)
                {
                    if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj)).account == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj)).account.name;
                    }
                    if (viewgroup.equals(obj))
                    {
                        j++;
                    }
                }
            }
        } while (true);
        if (j == 0)
        {
            obj = showCalendarsText;
        } else
        {
            obj = showMoreText;
        }
        viewgroup = account;
        if (account == null)
        {
            if (operationMode == 0)
            {
                j = 0x7f050053;
            } else
            {
                j = 0x7f050052;
            }
            viewgroup = View.inflate(mContext, j, null);
        }
        ((TextView)viewgroup.findViewById(0x7f100173)).setText(((CharSequence) (obj)));
        viewgroup.findViewById(0x7f100172).setVisibility(4);
        viewgroup.setContentDescription(null);
        account = viewgroup;
          goto _L8
_L3:
        obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)calendarlistiteminfo;
        obj1 = primaryCalendarText;
        flag = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).isPrimary;
        obj2 = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).displayName;
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj)).account == null)
        {
            viewgroup = null;
        } else
        {
            viewgroup = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj)).account.type;
        }
        viewgroup = Utils.getCalendarNameToDisplay(flag, ((String) (obj2)), viewgroup, ((String) (obj1)));
        if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).isVisible)
        {
            break MISSING_BLOCK_LABEL_1182;
        }
        if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).isSynced)
        {
            obj1 = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).account.type;
            if ("com.htc.pcsc".equals(obj1))
            {
                flag = true;
            } else
            {
                flag = "LOCAL".equals(obj1);
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_1182;
            }
        }
        flag = true;
_L9:
        account = getElementView(account, viewgroup, flag, ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).color);
          goto _L8
        flag = false;
          goto _L9
_L4:
        account = getGroupView(account, moreLabelText);
        ((ImageView)account.findViewById(0x7f100176)).setImageDrawable(moreDrawable);
          goto _L8
_L5:
        obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)calendarlistiteminfo;
        viewgroup = birthdayText;
        flag = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (obj)).areVisible;
        obj = mContext;
        j = birthdayColor;
        account = getElementView(account, viewgroup, flag, ((Context) (obj)).getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("preferences_birthdays_color", j));
          goto _L8
        viewgroup = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)calendarlistiteminfo;
        obj = mContext;
        if (PrefService.instance == null)
        {
            if (PrimaryAccountSelector.instance == null)
            {
                PrimaryAccountSelector.instance = new PrimaryAccountSelector(((Context) (obj)));
            }
            PrefService.instance = new PrefService(PrimaryAccountSelector.instance);
        }
        j = PrefService.instance.holidaysColor.getValue();
        account = getElementView(account, holidayText, ((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (viewgroup)).areVisible, j);
          goto _L8
    }

    public int getViewTypeCount()
    {
        return 3;
    }

    public void onClick(View view)
    {
        Object obj;
        boolean flag1;
        flag1 = true;
        obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)view.getTag();
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj)).getType() != 5) goto _L2; else goto _L1
_L1:
        obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfHiddenCalendarsItem)view.getTag();
        ArrayList arraylist = showHiddenCalendars;
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj)).account == null)
        {
            view = null;
        } else
        {
            view = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (obj)).account.name;
        }
        arraylist.add(view);
        mItems.remove(obj);
        mItems.addAll(((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfHiddenCalendarsItem) (obj)).calendars);
        updateItemList();
_L4:
        return;
_L2:
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj)).getType() != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        view = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)view.getTag();
        if (originalVisibilities.containsKey(((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (view)).calendarDescriptor))
        {
            originalVisibilities.remove(((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (view)).calendarDescriptor);
        } else
        {
            originalVisibilities.put(((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (view)).calendarDescriptor, Boolean.valueOf(((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (view)).isVisible));
        }
        if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (view)).isVisible)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (saveCalendarVisibility(view, flag1))
        {
            LatencyLoggerHolder.get().markAt(5);
            view = AnalyticsLoggerHolder.instance;
            if (view == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)view).trackEvent(mContext, "menu_item", "toggle_calendar");
                notifyDataSetChanged();
                return;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj)).getType() == 4)
        {
            view = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)view.getTag();
            boolean flag;
            int i;
            int j;
            if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (view)).areVisible)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            view.areVisible = flag1;
            view = (ArrayList)((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (view)).calendars;
            j = view.size();
            i = 0;
            flag = false;
            while (i < j) 
            {
                obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)view.get(i);
                if (!((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).isVisible)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                flag1 = saveCalendarVisibility(((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)), flag1);
                i++;
                flag = flag1 | flag;
            }
            view = AnalyticsLoggerHolder.instance;
            if (view == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)view).trackEvent(mContext, "menu_item", "toggle_calendar");
            if (flag)
            {
                LatencyLoggerHolder.get().markAt(5);
            }
            notifyDataSetChanged();
            return;
        }
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj)).getType() == 3)
        {
            view = (com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem)view.getTag();
            Context context;
            if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (view)).areVisible)
            {
                flag1 = false;
            }
            view.areVisible = flag1;
            context = mContext;
            flag1 = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem) (view)).areVisible;
            context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preferences_birthdays_master_visibility", flag1).apply();
            (new BackupManager(context)).dataChanged();
            if (saveBirthdayVisibility(view))
            {
                view = AnalyticsLoggerHolder.instance;
                if (view == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                } else
                {
                    ((AnalyticsLogger)view).trackEvent(mContext, "menu_item", "toggle_calendar");
                    notifyDataSetChanged();
                    return;
                }
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final void reorderItems()
    {
        showHiddenCalendars.clear();
        originalVisibilities.clear();
        ArrayList arraylist = (ArrayList)mItems;
        int k = arraylist.size();
        int i = 0;
        while (i < k) 
        {
            Object obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)arraylist.get(i);
            if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj)).getType() != 1)
            {
                continue;
            }
            obj = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)obj;
            if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).priority == 3 || ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).priority == 4)
            {
                int j;
                if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem) (obj)).isVisible)
                {
                    j = 3;
                } else
                {
                    j = 4;
                }
                obj.priority = j;
            }
            i++;
        }
        updateItemList();
    }

    final boolean saveBirthdayVisibility(com.google.android.calendar.calendarlist.common.CalendarListUtils.GroupOfCalendarsItem groupofcalendarsitem)
    {
        ArrayList arraylist;
        int i;
        int j;
        boolean flag;
        flag = false;
        if (settings == null)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        arraylist = (ArrayList)groupofcalendarsitem.calendars;
        j = arraylist.size();
        i = 0;
        flag = false;
_L2:
        boolean flag1;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem calendaritem = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)arraylist.get(i);
        if (!groupofcalendarsitem.areVisible)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = (Settings)settings.get(((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (calendaritem)).account);
        if (obj instanceof GoogleSettings)
        {
            obj = (GoogleSettings)obj;
        } else
        {
            obj = null;
        }
        if (obj != null && ((GoogleSettings) (obj)).getBirthdayMode() == com.google.android.calendar.api.settings.GoogleSettings.BirthdayMode.NONE)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = true;
_L3:
        flag |= saveCalendarVisibility(calendaritem, flag1);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        flag1 = false;
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
        if (flag)
        {
            LatencyLoggerHolder.get().markAt(5);
        }
        return flag;
    }

    final boolean saveCalendarVisibility(com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem calendaritem, boolean flag)
    {
        boolean flag2 = false;
        boolean flag3 = calendaritem.isVisible;
        boolean flag4 = calendaritem.isSynced;
        boolean flag1;
        if (flag3 != flag)
        {
            flag1 = true;
        } else
        if (flag && !flag4)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            return false;
        }
        if (calendaritem instanceof com.google.android.calendar.calendarlist.common.CalendarListUtils.TaskCalendarItem)
        {
            calendaritem = (com.google.android.calendar.calendarlist.common.CalendarListUtils.TaskCalendarItem)calendaritem;
            calendaritem = CalendarApi.SettingsFactory.modifySettings(((com.google.android.calendar.calendarlist.common.CalendarListUtils.TaskCalendarItem) (calendaritem)).settings);
            if (!(calendaritem instanceof GoogleSettingsModifications))
            {
                return false;
            } else
            {
                ((GoogleSettingsModifications)calendaritem).setAreTasksVisible(flag);
                CalendarApi.Settings.update(calendaritem);
                return true;
            }
        }
        CalendarListEntryModifications calendarlistentrymodifications = CalendarApi.CalendarListFactory.modifyCalendarListEntry(calendaritem.calendarListEntry);
        calendaritem.isVisible = flag;
        calendarlistentrymodifications.setIsVisible(flag);
        String s;
        if (((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (calendaritem)).account == null)
        {
            s = null;
        } else
        {
            s = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem) (calendaritem)).account.type;
        }
        if ("com.htc.pcsc".equals(s))
        {
            flag = true;
        } else
        {
            flag = "LOCAL".equals(s);
        }
        flag1 = flag2;
        if (!flag)
        {
            flag1 = flag2;
            if (calendaritem.isVisible)
            {
                flag1 = flag2;
                if (!calendaritem.isSynced)
                {
                    flag1 = true;
                }
            }
        }
        if (flag1)
        {
            calendarlistentrymodifications.setIsSyncEnabled(true);
        }
        CalendarApi.CalendarList.update(calendarlistentrymodifications);
        return true;
    }

    protected final void updateItemList()
    {
        mItems.clear();
        mItems.addAll(mLastItems);
        if (enablePostProcess)
        {
            CalendarListUtils.postProcessItems(mContext, mItems, mContext.getString(0x7f1303e2));
        }
        CalendarListUtils.processHiddenCalendars(mItems, showHiddenCalendars, originalVisibilities);
        Context context = mContext;
        Collections.sort(mItems, new com.google.android.calendar.calendarlist.common.CalendarListUtils.ListItemComparator(AccountsUtil.getGoogleAccounts(context)));
        notifyDataSetChanged();
    }

    private class GroupItemAccessibilityDelegate extends android.view.View.AccessibilityDelegate
    {

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfo);
            accessibilitynodeinfo.setEnabled(true);
        }

        GroupItemAccessibilityDelegate()
        {
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final SelectCalendarsAdapter this$0;
        private final Account val$account;

        public final void onClick(View view)
        {
            view = getDrawerSyncUIManager();
            Object obj = account;
            ((DrawerSyncUIManager) (view)).stateMap.put(obj, AccountSyncState.SYNCING);
            Object obj1 = new Handler(Looper.getMainLooper());
            DrawerListAdapter drawerlistadapter = ((DrawerSyncUIManager) (view)).adapter;
            drawerlistadapter.getClass();
            ((Handler) (obj1)).post(new DrawerSyncUIManager..Lambda._cls1(drawerlistadapter));
            obj1 = new DrawerSyncUIManager..Lambda._cls0(view, ((Account) (obj)));
            if (SyncUtils.isSyncable(((Account) (obj))))
            {
                SyncUtils.enableAutomaticSyncAndSyncNow(((DrawerSyncUIManager) (view)).context, ((Account) (obj)), false, ((com.google.android.apps.calendar.util.function.Consumer) (obj1)));
            } else
            {
                LogUtils.e(DrawerSyncUIManager.TAG, "Cannot start a sync for a non-syncable account...", new Object[0]);
            }
            view = SelectCalendarsAdapter.this;
            obj = AnalyticsLoggerHolder.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)obj).trackEvent(((SelectCalendarsAdapter) (view)).mContext, "sync_warnings", "sync_off_status_in_drawer", "enabled", null);
                return;
            }
        }

        _cls1()
        {
            this$0 = SelectCalendarsAdapter.this;
            account = account1;
            super();
        }
    }

}
