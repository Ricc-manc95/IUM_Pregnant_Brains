// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CancelableFutureCallback;
import com.google.android.calendar.settings.SettingsFragment;
import com.google.android.calendar.settings.ViewModelLoader;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder

public class GeneralPreferenceFragment extends SettingsFragment
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/settings/general/GeneralPreferenceFragment);
    private CancelableFutureCallback activityResultCallback;
    public GeneralPreferenceBinder binder;

    public GeneralPreferenceFragment()
    {
        super("general");
        activityResultCallback = new CancelableFutureCallback(null);
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        activityResultCallback.nestedFutureCallbackReference.set(null);
        class .Lambda._cls1
            implements Consumer
        {

            private final GeneralPreferenceFragment arg$1;
            private final int arg$2;
            private final Intent arg$3;
            private final int arg$4;

            public final void accept(Object obj)
            {
                Object obj1;
                int k;
                boolean flag1;
                boolean flag2;
                boolean flag3;
                int l;
                flag2 = false;
                flag3 = false;
                flag1 = false;
                obj = arg$1;
                k = arg$2;
                obj1 = arg$3;
                l = arg$4;
                if (k != 1) goto _L2; else goto _L1
_L1:
                obj = ((GeneralPreferenceFragment) (obj)).binder;
                if (obj1 == null) goto _L4; else goto _L3
_L3:
                Object obj2 = ((Intent) (obj1)).getExtras().get("android.intent.extra.ringtone.PICKED_URI");
                if (obj2 != null) goto _L6; else goto _L5
_L5:
label0:
                {
                    obj1 = ((GeneralPreferenceBinder) (obj)).viewModel;
                    obj2 = ((GeneralPreferenceViewModel) (obj1)).ringtoneUri;
                    if (obj2 != "")
                    {
                        k = ((flag1) ? 1 : 0);
                        if (obj2 == null)
                        {
                            break label0;
                        }
                        k = ((flag1) ? 1 : 0);
                        if (!obj2.equals(""))
                        {
                            break label0;
                        }
                    }
                    k = 1;
                }
                if (k == 0)
                {
                    obj1.ringtoneUri = "";
                    PreferencesUtils.setRingtonePreference(((GeneralPreferenceViewModel) (obj1)).context, "");
                }
_L9:
                ((GeneralPreferenceBinder) (obj)).bindNotifications(((GeneralPreferenceBinder) (obj)).viewModel);
_L4:
                return;
_L6:
label1:
                {
                    if (!(obj2 instanceof Uri))
                    {
                        continue; /* Loop/switch isn't completed */
                    }
                    obj1 = ((GeneralPreferenceBinder) (obj)).viewModel;
                    obj2 = obj2.toString();
                    String s = ((GeneralPreferenceViewModel) (obj1)).ringtoneUri;
                    if (s != obj2)
                    {
                        k = ((flag2) ? 1 : 0);
                        if (s == null)
                        {
                            break label1;
                        }
                        k = ((flag2) ? 1 : 0);
                        if (!s.equals(obj2))
                        {
                            break label1;
                        }
                    }
                    k = 1;
                }
                if (k == 0)
                {
                    obj1.ringtoneUri = ((String) (obj2));
                    PreferencesUtils.setRingtonePreference(((GeneralPreferenceViewModel) (obj1)).context, ((String) (obj2)));
                }
                continue; /* Loop/switch isn't completed */
_L2:
                if (k != 2)
                {
                    break MISSING_BLOCK_LABEL_345;
                }
                obj1 = TimeZonePickerHelper.processResultsIntent(((Fragment) (obj)).getContext(), l, ((Intent) (obj1)));
                if (!((com.android.timezonepicker.fullscreen.TimeZonePickerHelper.Result) (obj1)).timeZoneWasSelected()) goto _L4; else goto _L7
_L7:
                Object obj3;
                boolean flag;
label2:
                {
                    obj = ((GeneralPreferenceFragment) (obj)).binder;
                    obj1 = ((com.android.timezonepicker.fullscreen.TimeZonePickerHelper.Result) (obj1)).getId();
                    obj3 = ((GeneralPreferenceBinder) (obj)).viewModel;
                    String s1 = ((GeneralPreferenceViewModel) (obj3)).timezone;
                    if (obj1 != s1)
                    {
                        flag = flag3;
                        if (obj1 == null)
                        {
                            break label2;
                        }
                        flag = flag3;
                        if (!obj1.equals(s1))
                        {
                            break label2;
                        }
                    }
                    flag = true;
                }
                if (!flag)
                {
                    obj3.timezone = ((String) (obj1));
                    ((GeneralPreferenceViewModel) (obj3)).updateTimezone();
                }
                obj1 = ((GeneralPreferenceBinder) (obj)).timezonePreference;
                obj = ((GeneralPreferenceBinder) (obj)).viewModel;
                obj3 = SettingsShims.instance;
                Context context = ((GeneralPreferenceViewModel) (obj)).context;
                if (((GeneralPreferenceViewModel) (obj)).useDeviceTimezone)
                {
                    obj = ((GeneralPreferenceViewModel) (obj)).deviceTimezone;
                } else
                {
                    obj = ((GeneralPreferenceViewModel) (obj)).timezone;
                }
                ((Preference) (obj1)).setSummary(((SettingsShims) (obj3)).getTimezoneName(context, ((String) (obj))));
                return;
                LogUtils.w(GeneralPreferenceFragment.TAG, "Received onActivityResult for result code other than ringtone and time zone picker", new Object[0]);
                return;
                if (true) goto _L9; else goto _L8
_L8:
            }

            .Lambda._cls1(int i, Intent intent, int j)
            {
                arg$1 = GeneralPreferenceFragment.this;
                arg$2 = i;
                arg$3 = intent;
                arg$4 = j;
            }
        }

        activityResultCallback = new CancelableFutureCallback(LogUtils.newFailureLoggingCallback(new .Lambda._cls1(i, intent, j), TAG, "Dropped activity result", new Object[0]));
        intent = ViewModelLoader.getViewModelAsync(getContext());
        CancelableFutureCallback cancelablefuturecallback = activityResultCallback;
        com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
        if (cancelablefuturecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            intent.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(intent, cancelablefuturecallback), _lcls0);
            return;
        }
    }

    public final void onCreatePreferences$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0()
    {
        class .Lambda._cls0
            implements Consumer
        {

            private final GeneralPreferenceFragment arg$1;

            public final void accept(Object obj)
            {
                Object obj1 = arg$1;
                obj = (HomeViewModel)obj;
                ((PreferenceFragmentCompat) (obj1)).addPreferencesFromResource(0x7f090006);
                obj1.binder = new GeneralPreferenceBinder(((Fragment) (obj1)), ((PreferenceFragmentCompat) (obj1)).mPreferenceManager.mPreferenceScreen);
                obj1 = ((GeneralPreferenceFragment) (obj1)).binder;
                GeneralPreferenceViewModel generalpreferenceviewmodel = ((HomeViewModel) (obj)).generalPreferenceViewModel;
                obj1.viewModel = generalpreferenceviewmodel;
                obj = ((Preference) (((GeneralPreferenceBinder) (obj1)).preferenceScreen)).mContext.getResources();
                Object obj3 = Integer.toString(generalpreferenceviewmodel.firstDayOfWeek);
                ((GeneralPreferenceBinder) (obj1)).firstDayOfWeekPreference.setValue(((String) (obj3)));
                ((GeneralPreferenceBinder) (obj1)).setFirstDayOfWeekSummary(((Resources) (obj)), ((String) (obj3)));
                ((GeneralPreferenceBinder) (obj1)).firstDayOfWeekPreference.mOnChangeListener = new GeneralPreferenceBinder..Lambda._cls15(((GeneralPreferenceBinder) (obj1)), ((Resources) (obj)));
                ((GeneralPreferenceBinder) (obj1)).useDeviceTimezonePreference.setChecked(generalpreferenceviewmodel.useDeviceTimezone);
                ((GeneralPreferenceBinder) (obj1)).useDeviceTimezonePreference.mOnChangeListener = new GeneralPreferenceBinder..Lambda._cls16(((GeneralPreferenceBinder) (obj1)));
                obj3 = ((GeneralPreferenceBinder) (obj1)).timezonePreference;
                obj = ((GeneralPreferenceBinder) (obj1)).viewModel;
                Object obj4 = SettingsShims.instance;
                Context context = ((GeneralPreferenceViewModel) (obj)).context;
                boolean flag;
                if (((GeneralPreferenceViewModel) (obj)).useDeviceTimezone)
                {
                    obj = ((GeneralPreferenceViewModel) (obj)).deviceTimezone;
                } else
                {
                    obj = ((GeneralPreferenceViewModel) (obj)).timezone;
                }
                ((Preference) (obj3)).setSummary(((SettingsShims) (obj4)).getTimezoneName(context, ((String) (obj))));
                obj = ((GeneralPreferenceBinder) (obj1)).timezonePreference;
                if (!((GeneralPreferenceBinder) (obj1)).viewModel.useDeviceTimezone)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (((Preference) (obj)).mSelectable != flag)
                {
                    obj.mSelectable = flag;
                    ((Preference) (obj)).notifyChanged();
                }
                ((GeneralPreferenceBinder) (obj1)).timezonePreference.mOnClickListener = new GeneralPreferenceBinder..Lambda._cls17(((GeneralPreferenceBinder) (obj1)));
                ((GeneralPreferenceBinder) (obj1)).bindAlternateCalendar(generalpreferenceviewmodel);
                obj = ((GeneralPreferenceBinder) (obj1)).showWeekNumberPreference;
                generalpreferenceviewmodel.getClass();
                obj3 = new GeneralPreferenceBinder..Lambda._cls0(generalpreferenceviewmodel);
                generalpreferenceviewmodel.getClass();
                obj4 = new GeneralPreferenceBinder..Lambda._cls1(generalpreferenceviewmodel);
                ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj3)).get()).booleanValue());
                obj.mOnChangeListener = new GeneralPreferenceBinder..Lambda._cls21(((Consumer) (obj4)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj3)));
                obj = ((GeneralPreferenceBinder) (obj1)).showDeclinedEvents;
                generalpreferenceviewmodel.getClass();
                obj3 = new GeneralPreferenceBinder..Lambda._cls2(generalpreferenceviewmodel);
                generalpreferenceviewmodel.getClass();
                obj4 = new GeneralPreferenceBinder..Lambda._cls3(generalpreferenceviewmodel);
                ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj3)).get()).booleanValue());
                obj.mOnChangeListener = new GeneralPreferenceBinder..Lambda._cls21(((Consumer) (obj4)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj3)));
                obj = ((GeneralPreferenceBinder) (obj1)).showMoreEvents;
                flag = NycUtils.isDeviceTablet(((GeneralPreferenceBinder) (obj1)).viewModel.context);
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                obj = ((GeneralPreferenceBinder) (obj1)).showMoreEvents;
                generalpreferenceviewmodel.getClass();
                obj3 = new GeneralPreferenceBinder..Lambda._cls4(generalpreferenceviewmodel);
                generalpreferenceviewmodel.getClass();
                obj4 = new GeneralPreferenceBinder..Lambda._cls5(generalpreferenceviewmodel);
                ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj3)).get()).booleanValue());
                obj.mOnChangeListener = new GeneralPreferenceBinder..Lambda._cls21(((Consumer) (obj4)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj3)));
                ((GeneralPreferenceBinder) (obj1)).bindNotifications(generalpreferenceviewmodel);
                ((GeneralPreferenceBinder) (obj1)).eventDuration.mOnClickListener = new GeneralPreferenceBinder..Lambda._cls19(((GeneralPreferenceBinder) (obj1)));
                ((GeneralPreferenceBinder) (obj1)).quickResponse.mOnClickListener = new GeneralPreferenceBinder..Lambda._cls20(((GeneralPreferenceBinder) (obj1)));
                obj = ((GeneralPreferenceBinder) (obj1)).goals;
                flag = ((GeneralPreferenceBinder) (obj1)).viewModel.hasHabits;
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                obj = ((GeneralPreferenceBinder) (obj1)).goalsDisconnect;
                flag = ((GeneralPreferenceBinder) (obj1)).viewModel.hasHabits;
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                if (((GeneralPreferenceBinder) (obj1)).viewModel.hasHabits)
                {
                    ((GeneralPreferenceBinder) (obj1)).goalsDisconnect.mOnClickListener = new GeneralPreferenceBinder..Lambda._cls22(((GeneralPreferenceBinder) (obj1)));
                }
                obj = ((GeneralPreferenceBinder) (obj1)).flingingBluetooth;
                flag = ((GeneralPreferenceBinder) (obj1)).viewModel.showFlinging;
                if (((Preference) (obj)).mVisible != flag)
                {
                    obj.mVisible = flag;
                    if (((Preference) (obj)).mListener != null)
                    {
                        ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
                    }
                }
                if (((GeneralPreferenceBinder) (obj1)).viewModel.showFlinging)
                {
                    obj = ((GeneralPreferenceBinder) (obj1)).flingingBluetooth;
                    Object obj2 = ((GeneralPreferenceBinder) (obj1)).viewModel;
                    obj2.getClass();
                    obj2 = new GeneralPreferenceBinder..Lambda._cls23(((GeneralPreferenceViewModel) (obj2)));
                    obj1 = ((GeneralPreferenceBinder) (obj1)).viewModel;
                    obj1.getClass();
                    obj1 = new GeneralPreferenceBinder..Lambda._cls24(((GeneralPreferenceViewModel) (obj1)));
                    ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj2)).get()).booleanValue());
                    obj.mOnChangeListener = new GeneralPreferenceBinder..Lambda._cls21(((Consumer) (obj1)), ((android.support.v14.preference.SwitchPreference) (obj)), ((Supplier) (obj2)));
                }
            }

            .Lambda._cls0()
            {
                arg$1 = GeneralPreferenceFragment.this;
            }
        }

        loadModel(new .Lambda._cls0());
    }

    public final void onDestroy()
    {
        activityResultCallback.nestedFutureCallbackReference.set(null);
        super.onDestroy();
    }

    public final void onStart()
    {
        super.onStart();
        setActionBarTitle(requireContext().getResources().getString(0x7f13032f));
    }

}
