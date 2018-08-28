// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.Context;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.TwoStatePreference;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.VerifyException;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceViewModel

public class GeneralPreferenceBinder
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/settings/general/GeneralPreferenceBinder);
    private final ListPreference alternateCalendarPreference;
    private final Preference calendarNotifications;
    private final Preference debugNotifications;
    public final Preference eventDuration;
    public final ListPreference firstDayOfWeekPreference;
    public final SwitchPreference flingingBluetooth;
    public final Fragment fragment;
    public final Preference goals;
    public final Preference goalsDisconnect;
    private final SwitchPreference notifyOnThisDevice;
    public final PreferenceScreen preferenceScreen;
    public final Preference quickResponse;
    public final SwitchPreference showDeclinedEvents;
    public final SwitchPreference showMoreEvents;
    public final SwitchPreference showWeekNumberPreference;
    public final Preference timezonePreference;
    private final Preference tonePreference;
    public final SwitchPreference useDeviceTimezonePreference;
    private final SwitchPreference useStandardTone;
    private final SwitchPreference vibrate;
    public GeneralPreferenceViewModel viewModel;

    public GeneralPreferenceBinder(Fragment fragment1, PreferenceScreen preferencescreen)
    {
        boolean flag1 = true;
        super();
        fragment = fragment1;
        preferenceScreen = preferencescreen;
        fragment1 = (ListPreference)preferenceScreen.findPreference("start_of_week");
        boolean flag;
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        firstDayOfWeekPreference = (ListPreference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("use_device_timezone");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        useDeviceTimezonePreference = (SwitchPreference)fragment1;
        fragment1 = preferenceScreen.findPreference("timezone");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        timezonePreference = (Preference)fragment1;
        fragment1 = (ListPreference)preferenceScreen.findPreference("alternate_calendar");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        alternateCalendarPreference = (ListPreference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("show_week_number");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        showWeekNumberPreference = (SwitchPreference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("show_declined");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        showDeclinedEvents = (SwitchPreference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("show_more_events");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        showMoreEvents = (SwitchPreference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("notify_on_this_device");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        notifyOnThisDevice = (SwitchPreference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("use_standard_tone");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        useStandardTone = (SwitchPreference)fragment1;
        fragment1 = preferenceScreen.findPreference("ringtone");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        tonePreference = (Preference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("vibrate");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        vibrate = (SwitchPreference)fragment1;
        fragment1 = preferenceScreen.findPreference("calendar_notifications");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        calendarNotifications = (Preference)fragment1;
        fragment1 = preferenceScreen.findPreference("debug_notifications");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        debugNotifications = (Preference)fragment1;
        fragment1 = preferenceScreen.findPreference("default_event_duration");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        eventDuration = (Preference)fragment1;
        fragment1 = preferenceScreen.findPreference("quick_responses");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        quickResponse = (Preference)fragment1;
        fragment1 = preferenceScreen.findPreference("goals");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        goals = (Preference)fragment1;
        fragment1 = preferenceScreen.findPreference("goals_disconnect");
        if (fragment1 != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        }
        goalsDisconnect = (Preference)fragment1;
        fragment1 = (SwitchPreference)preferenceScreen.findPreference("flinging_bluetooth");
        if (fragment1 != null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException(Strings.lenientFormat("expected a non-null reference", new Object[0]));
        } else
        {
            flingingBluetooth = (SwitchPreference)fragment1;
            return;
        }
    }

    static final boolean lambda$bindSwitchPreference$11$GeneralPreferenceBinder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCPQMSORKD5NMSBQ3DTN76TBDCLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEOOJ8BRGE9IMCPBICLN66P9FADRMIT33D1874PB6CLP6ARJ3CKTKOORFDKNMERRFCTM6ABR3DTMMQRRE5TH62SR55T9NAS3GDHKMASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFE1P6APJ5E9IMSOR55T874PB6CLP6ARJ3CKTKOQJ1EPGIUR31DPJIUJR2D9IM6T1R55D0____0(Consumer consumer, SwitchPreference switchpreference, Supplier supplier, Object obj)
    {
        consumer.accept((Boolean)obj);
        switchpreference.setChecked(((Boolean)supplier.get()).booleanValue());
        return true;
    }

    final void bindAlternateCalendar(GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        boolean flag = true;
        int j = 0;
        Object obj = alternateCalendarPreference;
        boolean flag1;
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (((Preference) (obj)).mVisible != flag1)
        {
            obj.mVisible = flag1;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        if (android.os.Build.VERSION.SDK_INT < 24)
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        }
        obj = ((Preference) (preferenceScreen)).mContext.getResources();
        int k = generalpreferenceviewmodel.alternateCalendar;
        int ai[] = ((Resources) (obj)).getIntArray(0x7f0b0043);
        CharSequence acharsequence[] = new CharSequence[ai.length];
        byte byte0 = -1;
        int i = j;
        j = byte0;
        for (; i < ai.length; i++)
        {
            acharsequence[i] = Integer.toString(ai[i]);
            if (k == ai[i])
            {
                j = i;
            }
        }

        alternateCalendarPreference.mEntryValues = acharsequence;
        alternateCalendarPreference.setValue(Integer.toString(k));
        if (j >= 0)
        {
            alternateCalendarPreference.setSummary(((Resources) (obj)).getStringArray(0x7f0b0042)[j]);
        }
        class .Lambda._cls18
            implements android.support.v7.preference.Preference.OnPreferenceChangeListener
        {

            private final GeneralPreferenceBinder arg$1;
            private final GeneralPreferenceViewModel arg$2;

            public final boolean onPreferenceChange(Preference preference, Object obj1)
            {
                preference = arg$1;
                GeneralPreferenceViewModel generalpreferenceviewmodel1 = arg$2;
                int l = Integer.parseInt((String)obj1);
                obj1 = ((GeneralPreferenceBinder) (preference)).viewModel;
                if (((GeneralPreferenceViewModel) (obj1)).alternateCalendar != l)
                {
                    obj1.alternateCalendar = l;
                    SettingsShims.instance.setAlternateCalendar(((GeneralPreferenceViewModel) (obj1)).context, l);
                }
                preference.bindAlternateCalendar(generalpreferenceviewmodel1);
                return true;
            }

            .Lambda._cls18(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = GeneralPreferenceBinder.this;
                arg$2 = generalpreferenceviewmodel;
            }
        }

        alternateCalendarPreference.mOnChangeListener = new .Lambda._cls18(generalpreferenceviewmodel);
    }

    final void bindNotifications(GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        Object obj;
        boolean flag;
        boolean flag2;
        boolean flag1 = true;
        obj = notifyOnThisDevice;
        generalpreferenceviewmodel.getClass();
        class .Lambda._cls6
            implements Supplier
        {

            private final GeneralPreferenceViewModel arg$1;

            public final Object get()
            {
                return Boolean.valueOf(arg$1.notifyOnThisDevice);
            }

            .Lambda._cls6(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = generalpreferenceviewmodel;
            }
        }

        Object obj1 = new .Lambda._cls6(generalpreferenceviewmodel);
        class .Lambda._cls7
            implements Consumer
        {

            private final GeneralPreferenceBinder arg$1;
            private final GeneralPreferenceViewModel arg$2;

            public final void accept(Object obj3)
            {
                GeneralPreferenceBinder generalpreferencebinder = arg$1;
                GeneralPreferenceViewModel generalpreferenceviewmodel1 = arg$2;
                boolean flag3 = ((Boolean)obj3).booleanValue();
                if (generalpreferenceviewmodel1.notifyOnThisDevice != flag3)
                {
                    generalpreferenceviewmodel1.notifyOnThisDevice = flag3;
                    generalpreferenceviewmodel1.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preferences_alerts", generalpreferenceviewmodel1.notifyOnThisDevice).apply();
                }
                generalpreferencebinder.bindNotifications(generalpreferenceviewmodel1);
            }

            .Lambda._cls7(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = GeneralPreferenceBinder.this;
                arg$2 = generalpreferenceviewmodel;
            }
        }

        Object obj2 = new .Lambda._cls7(generalpreferenceviewmodel);
        ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj1)).get()).booleanValue());
        class .Lambda._cls21
            implements android.support.v7.preference.Preference.OnPreferenceChangeListener
        {

            private final Consumer arg$1;
            private final SwitchPreference arg$2;
            private final Supplier arg$3;

            public final boolean onPreferenceChange(Preference preference, Object obj3)
            {
                return GeneralPreferenceBinder.lambda$bindSwitchPreference$11$GeneralPreferenceBinder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCPQMSORKD5NMSBQ3DTN76TBDCLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEOOJ8BRGE9IMCPBICLN66P9FADRMIT33D1874PB6CLP6ARJ3CKTKOORFDKNMERRFCTM6ABR3DTMMQRRE5TH62SR55T9NAS3GDHKMASHR9HGMSP3IDTKM8BRJELO70RRIEGNNCDPFE1P6APJ5E9IMSOR55T874PB6CLP6ARJ3CKTKOQJ1EPGIUR31DPJIUJR2D9IM6T1R55D0____0(arg$1, arg$2, arg$3, obj3);
            }

            .Lambda._cls21(Consumer consumer, SwitchPreference switchpreference, Supplier supplier)
            {
                arg$1 = consumer;
                arg$2 = switchpreference;
                arg$3 = supplier;
            }
        }

        obj.mOnChangeListener = new .Lambda._cls21(((Consumer) (obj2)), ((SwitchPreference) (obj)), ((Supplier) (obj1)));
        obj = useStandardTone;
        generalpreferenceviewmodel.getClass();
        class .Lambda._cls8
            implements Supplier
        {

            private final GeneralPreferenceViewModel arg$1;

            public final Object get()
            {
                return Boolean.valueOf(arg$1.useStandardTone);
            }

            .Lambda._cls8(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = generalpreferenceviewmodel;
            }
        }

        obj1 = new .Lambda._cls8(generalpreferenceviewmodel);
        class .Lambda._cls9
            implements Consumer
        {

            private final GeneralPreferenceBinder arg$1;
            private final GeneralPreferenceViewModel arg$2;

            public final void accept(Object obj3)
            {
                GeneralPreferenceBinder generalpreferencebinder = arg$1;
                GeneralPreferenceViewModel generalpreferenceviewmodel1 = arg$2;
                boolean flag3 = ((Boolean)obj3).booleanValue();
                if (flag3 != generalpreferenceviewmodel1.useStandardTone)
                {
                    generalpreferenceviewmodel1.useStandardTone = flag3;
                    generalpreferenceviewmodel1.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preferences_use_standard_tone", generalpreferenceviewmodel1.useStandardTone).apply();
                }
                generalpreferencebinder.bindNotifications(generalpreferenceviewmodel1);
            }

            .Lambda._cls9(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = GeneralPreferenceBinder.this;
                arg$2 = generalpreferenceviewmodel;
            }
        }

        obj2 = new .Lambda._cls9(generalpreferenceviewmodel);
        ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj1)).get()).booleanValue());
        obj.mOnChangeListener = new .Lambda._cls21(((Consumer) (obj2)), ((SwitchPreference) (obj)), ((Supplier) (obj1)));
        obj = useStandardTone;
        class .Lambda._cls10
            implements android.support.v7.preference.Preference.OnPreferenceClickListener
        {

            private final GeneralPreferenceBinder arg$1;
            private final GeneralPreferenceViewModel arg$2;

            public final boolean onPreferenceClick(Preference preference)
            {
                GeneralPreferenceBinder generalpreferencebinder = arg$1;
                preference = arg$2.ringtoneUri;
                Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
                if (Platform.stringIsNullOrEmpty(preference))
                {
                    preference = null;
                } else
                {
                    preference = Uri.parse(preference);
                }
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", preference);
                intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(2));
                generalpreferencebinder.fragment.startActivityForResult(intent, 1);
                return true;
            }

            .Lambda._cls10(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = GeneralPreferenceBinder.this;
                arg$2 = generalpreferenceviewmodel;
            }
        }

        class .Lambda._cls11
            implements Supplier
        {

            private final GeneralPreferenceViewModel arg$1;

            public final Object get()
            {
                return Boolean.valueOf(arg$1.vibrate);
            }

            .Lambda._cls11(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = generalpreferenceviewmodel;
            }
        }

        class .Lambda._cls12
            implements Consumer
        {

            private final GeneralPreferenceViewModel arg$1;

            public final void accept(Object obj3)
            {
                GeneralPreferenceViewModel generalpreferenceviewmodel1 = arg$1;
                boolean flag3 = ((Boolean)obj3).booleanValue();
                if (flag3 != generalpreferenceviewmodel1.vibrate)
                {
                    generalpreferenceviewmodel1.vibrate = flag3;
                    generalpreferenceviewmodel1.context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putBoolean("preferences_alerts_vibrate", generalpreferenceviewmodel1.vibrate).apply();
                }
            }

            .Lambda._cls12(GeneralPreferenceViewModel generalpreferenceviewmodel)
            {
                arg$1 = generalpreferenceviewmodel;
            }
        }

        class .Lambda._cls13
            implements android.support.v7.preference.Preference.OnPreferenceClickListener
        {

            private final GeneralPreferenceBinder arg$1;

            public final boolean onPreferenceClick(Preference preference)
            {
                preference = arg$1;
                SettingsShims.instance.manageNotificationChannel$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEDIN8T39DPJN6BQJCLQ78QBECTPL6Q39DLPI8JJFEHKMCQB3C5Q6IRRE8DK62RJECLM3MAAM0(((GeneralPreferenceBinder) (preference)).fragment.getContext(), android.support.v4.content.ModernAsyncTask.Status.REMINDERS$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJCLQ78QBECTPIUKR5EHQ6IRJ7ED9MGQBDECI4SRRKD5J6IOR1EHKMURI3D1GMSRJ5DGTG____0);
                return true;
            }

            .Lambda._cls13()
            {
                arg$1 = GeneralPreferenceBinder.this;
            }
        }

        if (viewModel.notifyOnThisDevice && android.os.Build.VERSION.SDK_INT < 26)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (((Preference) (obj)).mVisible != flag2)
        {
            obj.mVisible = flag2;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        obj = tonePreference;
        obj1 = viewModel;
        ((Preference) (obj)).setSummary(PreferencesUtils.getRingtoneTitleFromUri(((GeneralPreferenceViewModel) (obj1)).context, ((GeneralPreferenceViewModel) (obj1)).ringtoneUri));
        obj = tonePreference;
        obj1 = viewModel;
        if (((GeneralPreferenceViewModel) (obj1)).notifyOnThisDevice && android.os.Build.VERSION.SDK_INT < 26 && !((GeneralPreferenceViewModel) (obj1)).useStandardTone)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (((Preference) (obj)).mVisible != flag2)
        {
            obj.mVisible = flag2;
            if (((Preference) (obj)).mListener != null)
            {
                ((Preference) (obj)).mListener.onPreferenceVisibilityChange(((Preference) (obj)));
            }
        }
        tonePreference.mOnClickListener = new .Lambda._cls10(generalpreferenceviewmodel);
        obj = vibrate;
        generalpreferenceviewmodel.getClass();
        obj1 = new .Lambda._cls11(generalpreferenceviewmodel);
        generalpreferenceviewmodel.getClass();
        generalpreferenceviewmodel = new .Lambda._cls12(generalpreferenceviewmodel);
        ((TwoStatePreference) (obj)).setChecked(((Boolean)((Supplier) (obj1)).get()).booleanValue());
        obj.mOnChangeListener = new .Lambda._cls21(generalpreferenceviewmodel, ((SwitchPreference) (obj)), ((Supplier) (obj1)));
        generalpreferenceviewmodel = vibrate;
        obj = viewModel;
        if (!((GeneralPreferenceViewModel) (obj)).notifyOnThisDevice || android.os.Build.VERSION.SDK_INT >= 26)
        {
            break MISSING_BLOCK_LABEL_592;
        }
        obj = (Vibrator)((GeneralPreferenceViewModel) (obj)).context.getSystemService("vibrator");
        if (obj != null && ((Vibrator) (obj)).hasVibrator())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_592;
        }
        flag2 = true;
_L1:
        if (((Preference) (generalpreferenceviewmodel)).mVisible != flag2)
        {
            generalpreferenceviewmodel.mVisible = flag2;
            if (((Preference) (generalpreferenceviewmodel)).mListener != null)
            {
                ((Preference) (generalpreferenceviewmodel)).mListener.onPreferenceVisibilityChange(generalpreferenceviewmodel);
            }
        }
        generalpreferenceviewmodel = calendarNotifications;
        if (viewModel.notifyOnThisDevice && android.os.Build.VERSION.SDK_INT >= 26)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (((Preference) (generalpreferenceviewmodel)).mVisible != flag2)
        {
            generalpreferenceviewmodel.mVisible = flag2;
            if (((Preference) (generalpreferenceviewmodel)).mListener != null)
            {
                ((Preference) (generalpreferenceviewmodel)).mListener.onPreferenceVisibilityChange(generalpreferenceviewmodel);
            }
        }
        calendarNotifications.mOnClickListener = new .Lambda._cls13();
        generalpreferenceviewmodel = debugNotifications;
        if (viewModel.notifyOnThisDevice && android.os.Build.VERSION.SDK_INT >= 26)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_620;
        }
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        break MISSING_BLOCK_LABEL_610;
        flag2 = false;
          goto _L1
        ((FeatureConfig)obj).dogfood_features();
        if (((Preference) (generalpreferenceviewmodel)).mVisible)
        {
            generalpreferenceviewmodel.mVisible = false;
            if (((Preference) (generalpreferenceviewmodel)).mListener != null)
            {
                ((Preference) (generalpreferenceviewmodel)).mListener.onPreferenceVisibilityChange(generalpreferenceviewmodel);
            }
        }
        class .Lambda._cls14
            implements android.support.v7.preference.Preference.OnPreferenceClickListener
        {

            private final GeneralPreferenceBinder arg$1;

            public final boolean onPreferenceClick(Preference preference)
            {
                preference = arg$1;
                SettingsShims.instance.manageNotificationChannel$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEDIN8T39DPJN6BQJCLQ78QBECTPL6Q39DLPI8JJFEHKMCQB3C5Q6IRRE8DK62RJECLM3MAAM0(((GeneralPreferenceBinder) (preference)).fragment.getContext(), android.support.v4.content.ModernAsyncTask.Status.DEBUG$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRJCLQ78QBECTPIUKR5EHQ6IRJ7ED9MGQBDECI4SRRKD5J6IOR1EHKMURI3D1GMSRJ5DGTG____0);
                return true;
            }

            .Lambda._cls14()
            {
                arg$1 = GeneralPreferenceBinder.this;
            }
        }

        debugNotifications.mOnClickListener = new .Lambda._cls14();
        return;
    }

    final void setFirstDayOfWeekSummary(Resources resources, String s)
    {
        String as[] = resources.getStringArray(0x7f0b0046);
        for (int i = 0; i < as.length; i++)
        {
            if (as[i].equals(s))
            {
                firstDayOfWeekPreference.setSummary(resources.getStringArray(0x7f0b0045)[i]);
                return;
            }
        }

        LogUtils.wtf(TAG, "Unable to determine day of week index: %s", new Object[] {
            s
        });
    }

}
