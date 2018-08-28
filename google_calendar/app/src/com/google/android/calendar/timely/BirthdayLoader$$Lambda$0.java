// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.text.TextUtils;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.avatar.ContactInfoLoader;
import com.google.android.calendar.timebox.Birthday;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely:
//            BirthdayLoader

final class arg._cls3
    implements Function
{

    private final BirthdayLoader arg$1;
    private final Birthday arg$2;
    private final String arg$3;

    public final Object apply(Object obj)
    {
        Object obj2 = arg$1;
        Birthday birthday = arg$2;
        Object obj1 = arg$3;
        ImmutableMap immutablemap = (ImmutableMap)obj;
        obj2 = ((BirthdayLoader) (obj2)).context;
        com.google.android.calendar.timebox.rg._cls3 _lcls3 = birthday.toBuilder();
        obj = (String)immutablemap.get("goo.contactsEventType");
        if ("BIRTHDAY".equals(obj))
        {
            obj = (String)immutablemap.get("goo.contactsEmail");
            com.google.android.calendar.timebox.rg._cls3 _lcls3_1 = _lcls3.((String)immutablemap.get("goo.contactsFullName"));
            if (obj == null)
            {
                obj1 = "";
            } else
            {
                obj1 = obj;
            }
            _lcls3_1.(((String) (obj1))).ser("true".equals(immutablemap.get("goo.isGPlusUser"))).d((String)immutablemap.get("goo.contactsProfileId")).((String)immutablemap.get("goo.contactsPhotoUrl"));
        } else
        if ("SELF".equals(obj))
        {
            obj = CalendarListEntryCache.findByLocalId(birthday.calendarId());
            if (obj == null)
            {
                obj = "";
            } else
            {
                obj = ((CalendarListEntry) (obj)).getDescriptor().account.name;
            }
            _lcls3.rthday(true).rthday(((String) (obj))).(((String) (obj1))).Title(((String) (obj1))).Title();
        } else
        {
            return _lcls3.ay(false).ay();
        }
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj1 = ContactInfo.newBuilder();
            obj1.ryEmail = ((String) (obj));
            obj1.eAccountName = birthday.sourceAccount();
            obj = new ContactInfo(((com.google.android.calendar.avatar.eAccountName) (obj1)));
            boolean flag;
            if (obj != (new ContactInfoLoader(((android.content.Context) (obj2)))).load(((ContactInfo) (obj))))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            _lcls3.tAvailable(flag);
        }
        return _lcls3.tAvailable();
    }

    tor(BirthdayLoader birthdayloader, Birthday birthday, String s)
    {
        arg$1 = birthdayloader;
        arg$2 = birthday;
        arg$3 = s;
    }
}
