// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneNumberUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Patterns;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.conference.AccessCode;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.utils.phone.PhoneUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.chromium.customtabsclient.shared.CustomTabsHelper;

// Referenced classes of package com.google.android.calendar.event:
//            ConferenceCallSpan, AccessCodePickerDialog

public class ConferenceCallUtils
{

    private static final ImmutableList ACCESS_CODE_REGEX = ImmutableList.of(Pattern.compile("()()()[pP]articipant [cC]ode[:]?[\\s]*([0-9 ]+[#]?)"), Pattern.compile("()()()[pP]articipant [pP]asscode[:]?[\\s]*([0-9 ]+[#]?)"), Pattern.compile("()()()[pP][cC]ode[:]?[\\s]*([0-9\\s]+[#]?)"), Pattern.compile("()()[hH]ost [cC]ode[:]?[\\s]*([0-9\\s]+[#]?)()"), Pattern.compile("()[aA]ccess [cC]ode[:]?[\\s]*([0-9\\s-]+[#]?)()()"), Pattern.compile("()[mM]eeting [nN]umber[:]?[\\s]*([0-9\\s]+[#]?)()()"), Pattern.compile("()[mM]eeting [iI][dD][:]?[\\s]*([0-9\\s-]+[#]?)()()"), Pattern.compile("CCP:[\\s]*([+0-9\\s-]+)()x()([0-9]+[#]?)"), Pattern.compile("CCM:[\\s]*([+0-9\\s-]+)()x([0-9]+[#]?)()"), Pattern.compile("()PIN[:]?[\\s]*([0-9 ]+[#]?)()()"));
    private static final ImmutableList CONFERENCE_NUM_REGEX = ImmutableList.of(Pattern.compile("CCP:[\\s]*([+0-9\\s-]+)()x()([0-9]+[#]?)"), Pattern.compile("CCM:[\\s]*([+0-9\\s-]+)()x([0-9]+[#]?)()"));
    private static final ImmutableList GROUP_ACCESS_CODE_TYPE = ImmutableList.of(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4));
    private static final Pattern PHONE_NUMBER_ACCESS_CODE_EXTENSION = Pattern.compile("[,;]+[0-9]+#?");
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/ConferenceCallUtils);

    private ConferenceCallUtils()
    {
    }

    public static void addLinks(Spannable spannable, ConferenceCallSpan.OnConferenceNumberClickListener onconferencenumberclicklistener)
    {
        Object obj;
        URLSpan aurlspan[];
        int j;
        obj = new SpannableString(spannable.toString());
        Linkify.addLinks(((Spannable) (obj)), 15);
        aurlspan = (URLSpan[])((Spannable) (obj)).getSpans(0, ((Spannable) (obj)).length(), android/text/style/URLSpan);
        j = aurlspan.length;
        int i = 0;
_L35:
        if (i >= j) goto _L2; else goto _L1
_L1:
        Object obj5 = aurlspan[i];
        int k = ((Spannable) (obj)).getSpanStart(obj5);
        int i1 = ((Spannable) (obj)).getSpanEnd(obj5);
        if (((URLSpan[])spannable.getSpans(k, i1, android/text/style/URLSpan)).length == 0)
        {
            spannable.setSpan(obj5, k, i1, ((Spannable) (obj)).getSpanFlags(obj5));
        }
          goto _L3
_L2:
        int j1;
        obj = (ImmutableList)ACCESS_CODE_REGEX;
        j1 = ((ImmutableList) (obj)).size();
        i = 0;
        Object obj2 = (UnmodifiableIterator)null;
_L38:
        if (i >= j1) goto _L5; else goto _L4
_L4:
        obj2 = ((Pattern)((ImmutableList) (obj)).get(i)).matcher(spannable);
_L9:
        if (!((Matcher) (obj2)).find()) goto _L7; else goto _L6
_L6:
        int i2;
        obj5 = (ImmutableList)GROUP_ACCESS_CODE_TYPE;
        i2 = ((ImmutableList) (obj5)).size();
        j = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
_L37:
        if (j >= i2) goto _L9; else goto _L8
_L8:
        int l = ((Integer)((ImmutableList) (obj5)).get(j)).intValue();
        if (TextUtils.isEmpty(((Matcher) (obj2)).group(l))) goto _L11; else goto _L10
_L10:
        URLSpan aurlspan2[];
        int j2;
        aurlspan2 = (URLSpan[])spannable.getSpans(((Matcher) (obj2)).start(l), ((Matcher) (obj2)).end(l), android/text/style/URLSpan);
        j2 = aurlspan2.length;
        l = 0;
_L36:
        if (l >= j2) goto _L11; else goto _L12
_L12:
        Object obj7 = aurlspan2[l];
        Object obj1;
        Object obj3;
        ClickableSpan aclickablespan[];
        URLSpan aurlspan1[];
        Object obj6;
        int k1;
        boolean flag;
        int k2;
        int l2;
        try
        {
            if ("tel".equals(Uri.parse(((URLSpan) (obj7)).getURL()).getScheme()))
            {
                spannable.removeSpan(obj7);
            }
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Spannable spannable)
        {
            LogUtils.e(TAG, spannable, "Failed to add links.", new Object[0]);
        }
          goto _L13
_L5:
        aurlspan1 = (URLSpan[])spannable.getSpans(0, spannable.length(), android/text/style/URLSpan);
        k2 = aurlspan1.length;
        i = 0;
_L30:
        if (i >= k2) goto _L15; else goto _L14
_L14:
        obj1 = aurlspan1[i];
        obj3 = Uri.parse(((URLSpan) (obj1)).getURL());
        if (!"tel".equals(((Uri) (obj3)).getScheme()))
        {
            continue; /* Loop/switch isn't completed */
        }
        l2 = spannable.getSpanStart(obj1);
        if (l2 < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        k1 = spannable.getSpanEnd(obj1);
        obj3 = ((Uri) (obj3)).getEncodedSchemeSpecificPart();
        spannable.removeSpan(obj1);
        ImmutableList immutablelist;
        Object obj4;
        String s;
        int l1;
        if (!TextUtils.isEmpty(((CharSequence) (obj3))) && ((CharSequence) (obj3)).length() >= 5 && (PhoneNumberUtils.isGlobalPhoneNumber(((CharSequence) (obj3)).toString()) || Patterns.PHONE.matcher(((CharSequence) (obj3))).matches()))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = false;
        obj7 = spannable.toString();
        l = ((flag) ? 1 : 0);
        obj1 = obj3;
        j = k1;
        if (((String) (obj7)).length() <= k1) goto _L17; else goto _L16
_L16:
        if (((String) (obj7)).charAt(k1) == ',') goto _L19; else goto _L18
_L18:
        l = ((flag) ? 1 : 0);
        obj1 = obj3;
        j = k1;
        if (((String) (obj7)).charAt(k1) != ';') goto _L17; else goto _L19
_L19:
        obj6 = PHONE_NUMBER_ACCESS_CODE_EXTENSION.matcher(((CharSequence) (obj7)));
        l = ((flag) ? 1 : 0);
        obj1 = obj3;
        j = k1;
        if (!((Matcher) (obj6)).find(k1)) goto _L17; else goto _L20
_L20:
        l = ((flag) ? 1 : 0);
        obj1 = obj3;
        j = k1;
        if (((Matcher) (obj6)).start() != k1) goto _L17; else goto _L21
_L21:
        l = 1;
        obj1 = String.valueOf(obj3);
        obj3 = String.valueOf(((String) (obj7)).substring(k1, ((Matcher) (obj6)).end()));
        if (((String) (obj3)).length() == 0) goto _L23; else goto _L22
_L22:
        obj1 = ((String) (obj1)).concat(((String) (obj3)));
_L28:
        j = ((Matcher) (obj6)).end();
_L17:
        obj1 = new ConferenceCallSpan(((String) (obj1)).replaceAll("[\\s-]+", ""), onconferencenumberclicklistener);
        spannable.setSpan(obj1, l2, j, 33);
        if (l == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        aclickablespan = (ClickableSpan[])spannable.getSpans(spannable.getSpanStart(obj1), spannable.getSpanEnd(obj1), android/text/style/ClickableSpan);
        k1 = aclickablespan.length;
        j = 0;
_L39:
        if (j >= k1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj6 = aclickablespan[j];
        if (obj1 == obj6) goto _L25; else goto _L24
_L24:
        if (obj1 == null) goto _L27; else goto _L26
_L26:
        if (!obj1.equals(obj6)) goto _L27; else goto _L25
_L29:
        if (l != 0)
        {
            break MISSING_BLOCK_LABEL_940;
        }
        spannable.removeSpan(obj6);
        break MISSING_BLOCK_LABEL_940;
_L23:
        obj1 = new String(((String) (obj1)));
          goto _L28
_L13:
        return;
_L27:
        l = 0;
          goto _L29
        i++;
          goto _L30
_L15:
        immutablelist = (ImmutableList)CONFERENCE_NUM_REGEX;
        l = immutablelist.size();
        i = 0;
        obj4 = (UnmodifiableIterator)null;
_L33:
        if (i >= l) goto _L13; else goto _L31
_L31:
        obj4 = immutablelist.get(i);
        j = i + 1;
        obj4 = ((Pattern)obj4).matcher(spannable);
_L34:
        i = j;
        if (!((Matcher) (obj4)).find()) goto _L33; else goto _L32
_L32:
        s = ((Matcher) (obj4)).group(1);
        if (!TextUtils.isEmpty(s))
        {
            i = ((Matcher) (obj4)).start(1);
            l1 = ((Matcher) (obj4)).end(1);
            spannable.setSpan(new ConferenceCallSpan(s.replaceAll("[\\s-]+", ""), onconferencenumberclicklistener), i, l1, 33);
        }
          goto _L34
          goto _L33
          goto _L13
_L3:
        i++;
          goto _L35
        l++;
          goto _L36
_L11:
        j++;
          goto _L37
_L7:
        i++;
          goto _L38
_L25:
        l = 1;
          goto _L29
        j++;
          goto _L39
    }

    public static Uri buildUri(String s, String s1)
    {
        s = new StringBuilder(s);
        if (!TextUtils.isEmpty(s1))
        {
            s.append(',');
            s.append(',');
            s.append(s1);
        }
        return Uri.fromParts("tel", s.toString(), null);
    }

    public static void dialConferenceCall(Context context, FragmentManager fragmentmanager, String s, Set set)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23 && !set.isEmpty()) goto _L2; else goto _L1
_L1:
        fragmentmanager = new StringBuilder(s);
        if (!TextUtils.isEmpty(null))
        {
            fragmentmanager.append(',');
            fragmentmanager.append(',');
            fragmentmanager.append(null);
        }
        fragmentmanager = Uri.fromParts("tel", fragmentmanager.toString(), null);
        s = new Intent("android.intent.action.DIAL");
        s.setData(fragmentmanager);
        if (s.resolveActivity(context.getPackageManager()) != null) goto _L4; else goto _L3
_L3:
        Toast.makeText(context, 0x7f13034b, 1).show();
_L6:
        return;
_L4:
        context.startActivity(s);
        return;
_L2:
        if (!fragmentmanager.isDestroyed())
        {
            context = new AccessCodePickerDialog();
            Bundle bundle = new Bundle(2);
            bundle.putString("key_conference_number", s);
            bundle.putParcelableArrayList("key_access_codes", new ArrayList(set));
            context.setArguments(bundle);
            context.show(fragmentmanager, AccessCodePickerDialog.TAG);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static void dialPhoneConference(PhoneUtil phoneutil, PhoneNumberDetails phonenumberdetails)
    {
        Object obj = phonenumberdetails.phoneNumber();
        phonenumberdetails = phonenumberdetails.passCode();
        obj = new StringBuilder(((String) (obj)));
        if (!TextUtils.isEmpty(phonenumberdetails))
        {
            ((StringBuilder) (obj)).append(';');
            ((StringBuilder) (obj)).append(phonenumberdetails);
            ((StringBuilder) (obj)).append('#');
        }
        phonenumberdetails = Uri.fromParts("tel", ((StringBuilder) (obj)).toString(), null);
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            phoneutil.makeCall(phonenumberdetails);
            return;
        } else
        {
            phoneutil.openDialer(phonenumberdetails);
            return;
        }
    }

    public static void logAction(Context context, String s, String s1)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "one_click_dialing", s, s1, null);
            return;
        }
    }

    public static Set parseAccessCode(String s)
    {
        HashSet hashset = new HashSet();
        if (!TextUtils.isEmpty(s))
        {
            ImmutableList immutablelist = (ImmutableList)ACCESS_CODE_REGEX;
            int l = immutablelist.size();
            int i = 0;
            UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
            do
            {
                if (i >= l)
                {
                    break;
                }
                for (Matcher matcher = ((Pattern)immutablelist.get(i)).matcher(s); matcher.find();)
                {
                    ImmutableList immutablelist1 = (ImmutableList)GROUP_ACCESS_CODE_TYPE;
                    int i1 = immutablelist1.size();
                    int j = 0;
                    UnmodifiableIterator unmodifiableiterator1 = (UnmodifiableIterator)null;
                    while (j < i1) 
                    {
                        Object obj = immutablelist1.get(j);
                        int k = j + 1;
                        int j1 = ((Integer)obj).intValue();
                        obj = matcher.group(j1);
                        j = k;
                        if (obj != null)
                        {
                            j = k;
                            if (!TextUtils.isEmpty(((String) (obj)).trim()))
                            {
                                obj = ((String) (obj)).replaceAll("[\\s-]+", "");
                                if (j1 == 2)
                                {
                                    obj = new AccessCode(((String) (obj)), 1);
                                } else
                                if (j1 == 3)
                                {
                                    obj = new AccessCode(((String) (obj)), 2);
                                } else
                                if (j1 == 4)
                                {
                                    obj = new AccessCode(((String) (obj)), 3);
                                } else
                                {
                                    obj = null;
                                }
                                hashset.add(obj);
                                j = k;
                            }
                        }
                    }
                }

                i++;
            } while (true);
        }
        return hashset;
    }

    public static void showInteropInstructions(Context context, Uri uri, int i)
    {
        Object obj = new android.support.customtabs.CustomTabsIntent.Builder();
        ((android.support.customtabs.CustomTabsIntent.Builder) (obj)).mIntent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
        ((android.support.customtabs.CustomTabsIntent.Builder) (obj)).mIntent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", i);
        obj = ((android.support.customtabs.CustomTabsIntent.Builder) (obj)).build();
        if (CustomTabsHelper.getPackageNameToUse(context) != null)
        {
            ((CustomTabsIntent) (obj)).intent.setPackage(CustomTabsHelper.getPackageNameToUse(context));
            ((CustomTabsIntent) (obj)).intent.setData(uri);
            ContextCompat.startActivity(context, ((CustomTabsIntent) (obj)).intent, ((CustomTabsIntent) (obj)).startAnimationBundle);
            return;
        } else
        {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            context.startActivity(intent);
            return;
        }
    }

    public static void showMoreThorPhones(Context context, Uri uri, Account account, int i, boolean flag, boolean flag1)
    {
        if (uri == null)
        {
            throw new NullPointerException();
        }
        Intent intent = new Intent("ConferencePhoneNumbersActivity.intent.action.Launch");
        intent.putExtra("conference_uri", uri);
        intent.putExtra("use_gstatic", flag);
        intent.putExtra("has_interop", flag1);
        intent.setPackage("com.google.android.calendar");
        intent.putExtra("account", account);
        intent.putExtra("event_color", i);
        context.startActivity(intent);
        uri = AnalyticsLoggerHolder.instance;
        if (uri == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)uri).trackEvent(context, "one_click_dialing", "conference_more_phones", "conference_phones_activity", null);
            return;
        }
    }

}
