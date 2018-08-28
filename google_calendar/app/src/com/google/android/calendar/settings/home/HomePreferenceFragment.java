// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.google.android.calendar.settings.SettingsFragment;

// Referenced classes of package com.google.android.calendar.settings.home:
//            HomePreferenceBinder

public final class HomePreferenceFragment extends SettingsFragment
{

    public HomePreferenceBinder homePreferenceBinder;

    public HomePreferenceFragment()
    {
        super("home");
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final HomePreferenceFragment arg$1;

            public final void accept(Object obj)
            {
                boolean flag1 = true;
                Object obj1 = arg$1;
                HomeViewModel homeviewmodel = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090008);
                obj1.homePreferenceBinder = new HomePreferenceBinder(((HomePreferenceFragment) (obj1)));
                obj1 = ((HomePreferenceFragment) (obj1)).homePreferenceBinder;
                obj = ((HomePreferenceBinder) (obj1)).more;
                Iterator iterator;
                boolean flag;
                if (homeviewmodel.showHolidays || homeviewmodel.showBirthdays)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                ((HomePreferenceBinder) (obj1)).addColorUpdater(((HomePreferenceBinder) (obj1)).holidayPreference, new HomePreferenceBinder..Lambda._cls2(homeviewmodel));
                obj = ((HomePreferenceBinder) (obj1)).holidayPreference;
                flag = homeviewmodel.showHolidays;
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                ((HomePreferenceBinder) (obj1)).holidayPreference.mOnClickListener = new HomePreferenceBinder..Lambda._cls3(((HomePreferenceBinder) (obj1)));
                ((HomePreferenceBinder) (obj1)).addColorUpdater(((HomePreferenceBinder) (obj1)).birthdayPreference, new HomePreferenceBinder..Lambda._cls4(homeviewmodel));
                obj = ((HomePreferenceBinder) (obj1)).birthdayPreference;
                flag = homeviewmodel.showBirthdays;
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                ((HomePreferenceBinder) (obj1)).birthdayPreference.mOnClickListener = new HomePreferenceBinder..Lambda._cls5(((HomePreferenceBinder) (obj1)));
                iterator = homeviewmodel.calendarMap.keySet().iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    Account account = (Account)iterator.next();
                    android.support.v7.preference.PreferenceScreen preferencescreen = ((PreferenceFragmentCompat) (((HomePreferenceBinder) (obj1)).fragment)).mPreferenceManager.mPreferenceScreen;
                    ArrayList arraylist = new ArrayList(homeviewmodel.calendarMap.get(account));
                    PreferenceCategory preferencecategory = new PreferenceCategory(((Preference) (preferencescreen)).mContext);
                    obj = String.valueOf("CALENDAR_CATEGORY_KEY_PREFIX:");
                    String s = String.valueOf(account.name);
                    int j;
                    if (s.length() != 0)
                    {
                        obj = ((String) (obj)).concat(s);
                    } else
                    {
                        obj = new String(((String) (obj)));
                    }
                    preferencecategory.setKey(((String) (obj)));
                    preferencecategory.setTitle(account.name);
                    preferencescreen.addPreference(preferencecategory);
                    obj = (ArrayList)arraylist;
                    j = ((ArrayList) (obj)).size();
                    for (int i = 0; i < j;)
                    {
                        Object obj2 = ((ArrayList) (obj)).get(i);
                        i++;
                        ((HomePreferenceBinder) (obj1)).addPreferenceForCalendar(preferencecategory, (CalendarListItemViewModel)obj2);
                    }

                    if (homeviewmodel.expandable.contains(account))
                    {
                        obj = new Preference(new ContextThemeWrapper(((Preference) (preferencescreen)).mContext, 0x7f1400de));
                        ((Preference) (obj)).setTitle(((Preference) (obj)).mContext.getString(0x7f130448));
                        ((Preference) (obj)).setIcon(new ColorDrawable(0));
                        ((Preference) (obj)).setKey("SHOW_MORE_ACTION");
                        obj.mOnClickListener = new HomePreferenceBinder..Lambda._cls6(((HomePreferenceBinder) (obj1)), homeviewmodel, account, preferencecategory, ((Preference) (obj)), arraylist);
                        preferencecategory.addPreference(((Preference) (obj)));
                    }
                } while (true);
                ((HomePreferenceBinder) (obj1)).generalPreference.mOnClickListener = new HomePreferenceBinder..Lambda._cls0(((HomePreferenceBinder) (obj1)));
                obj = ((HomePreferenceBinder) (obj1)).smartMailPreference;
                if (!homeviewmodel.smartMailViewModel.smartMailModes.isEmpty())
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                ((HomePreferenceBinder) (obj1)).smartMailPreference.mOnClickListener = new HomePreferenceBinder..Lambda._cls1(((HomePreferenceBinder) (obj1)));
                ((HomePreferenceBinder) (obj1)).updateColors();
            }

            .Lambda._cls0()
            {
                arg$1 = HomePreferenceFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onResume()
    {
        super.onResume();
        if (homePreferenceBinder != null)
        {
            homePreferenceBinder.updateColors();
        }
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f1303af));
    }
}
