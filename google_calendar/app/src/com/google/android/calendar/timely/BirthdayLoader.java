// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.timebox.Birthday;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BirthdayLoader
    implements AsyncFunction
{

    public final Context context;

    public BirthdayLoader(Context context1)
    {
        context = context1;
    }

    public final volatile ListenableFuture apply(Object obj)
        throws Exception
    {
        return apply((List)obj);
    }

    public final ListenableFuture apply(List list)
    {
        String s = context.getResources().getString(0x7f1302e6);
        ArrayList arraylist = new ArrayList(list.size());
        class .Lambda._cls0
            implements Function
        {

            private final BirthdayLoader arg$1;
            private final Birthday arg$2;
            private final String arg$3;

            public final Object apply(Object obj)
            {
                Object obj2 = arg$1;
                Birthday birthday1 = arg$2;
                Object obj1 = arg$3;
                ImmutableMap immutablemap = (ImmutableMap)obj;
                obj2 = ((BirthdayLoader) (obj2)).context;
                com.google.android.calendar.timebox.Birthday.Builder builder = birthday1.toBuilder();
                obj = (String)immutablemap.get("goo.contactsEventType");
                if ("BIRTHDAY".equals(obj))
                {
                    obj = (String)immutablemap.get("goo.contactsEmail");
                    com.google.android.calendar.timebox.Birthday.Builder builder1 = builder.fullName((String)immutablemap.get("goo.contactsFullName"));
                    if (obj == null)
                    {
                        obj1 = "";
                    } else
                    {
                        obj1 = obj;
                    }
                    builder1.email(((String) (obj1))).isGPlusUser("true".equals(immutablemap.get("goo.isGPlusUser"))).profileId((String)immutablemap.get("goo.contactsProfileId")).photoUrl((String)immutablemap.get("goo.contactsPhotoUrl"));
                } else
                if ("SELF".equals(obj))
                {
                    obj = CalendarListEntryCache.findByLocalId(birthday1.calendarId());
                    if (obj == null)
                    {
                        obj = "";
                    } else
                    {
                        obj = ((CalendarListEntry) (obj)).getDescriptor().account.name;
                    }
                    builder.isSelfBirthday(true).email(((String) (obj))).fullName(((String) (obj1))).originalTitle(((String) (obj1))).build();
                } else
                {
                    return builder.isBirthday(false).build();
                }
                if (!TextUtils.isEmpty(((CharSequence) (obj))))
                {
                    obj1 = ContactInfo.newBuilder();
                    obj1.primaryEmail = ((String) (obj));
                    obj1.sourceAccountName = birthday1.sourceAccount();
                    obj = new ContactInfo(((com.google.android.calendar.avatar.ContactInfo.Builder) (obj1)));
                    boolean flag;
                    if (obj != (new ContactInfoLoader(((Context) (obj2)))).load(((ContactInfo) (obj))))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    builder.isContactAvailable(flag);
                }
                return builder.build();
            }

            .Lambda._cls0(Birthday birthday, String s)
            {
                arg$1 = BirthdayLoader.this;
                arg$2 = birthday;
                arg$3 = s;
            }
        }

        Birthday birthday;
        for (list = list.iterator(); list.hasNext(); arraylist.add(AbstractTransformFuture.create(CalendarApi.Events.readGadgetPreferences(birthday.eventId(), birthday.calendarId()), new .Lambda._cls0(birthday, s), CalendarExecutor.DISK)))
        {
            birthday = (Birthday)list.next();
        }

        return new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(arraylist), true);
    }
}
