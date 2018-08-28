// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.conference;

import android.os.Parcelable;
import android.support.v4.text.BidiFormatter;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.telephony.PhoneNumberUtils;
import com.google.common.base.Platform;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.event.conference:
//            Availability, LocalPhoneSource

public abstract class PhoneNumberDetails
    implements Parcelable
{

    public PhoneNumberDetails()
    {
    }

    public abstract Availability availability();

    public final String getFormattedPhoneNumber(Locale locale)
    {
        if (Platform.stringIsNullOrEmpty(phoneNumber()))
        {
            return null;
        } else
        {
            return BidiFormatter.getInstance(locale).unicodeWrap(PhoneNumberUtils.formatNumber(phoneNumber(), locale.getCountry()), TextDirectionHeuristicsCompat.LTR);
        }
    }

    public abstract String passCode();

    public abstract String phoneNumber();

    public abstract Locale region();

    public abstract LocalPhoneSource source();
}
