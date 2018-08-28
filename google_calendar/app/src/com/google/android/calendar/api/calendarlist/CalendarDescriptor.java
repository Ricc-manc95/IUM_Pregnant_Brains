// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.calendar.utils.account.AccountUtil;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarKey

public class CalendarDescriptor
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final Account account;
    public final String calendarId;
    public final CalendarKey calendarKey;

    public CalendarDescriptor(Account account1, String s, CalendarKey calendarkey)
    {
        if (account1 == null)
        {
            throw new NullPointerException();
        }
        account = (Account)account1;
        if (s == null)
        {
            throw new NullPointerException();
        }
        calendarId = (String)s;
        if (calendarkey == null)
        {
            throw new NullPointerException();
        } else
        {
            calendarKey = (CalendarKey)calendarkey;
            return;
        }
    }

    CalendarDescriptor(Parcel parcel)
    {
        this((Account)parcel.readParcelable(android/accounts/Account.getClassLoader()), parcel.readString(), (CalendarKey)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarKey.getClassLoader()));
    }

    public static CalendarDescriptor createWithoutLocalId(Account account1, String s)
    {
        if (account1 == null)
        {
            throw new NullPointerException();
        }
        if (!AccountUtil.isGoogleAccount(account1))
        {
            throw new IllegalArgumentException();
        } else
        {
            return new CalendarDescriptor(account1, s, CalendarKey.NONE);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (CalendarDescriptor)obj;
        Object obj1 = account;
        Object obj2 = ((CalendarDescriptor) (obj)).account;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = calendarId;
        obj2 = ((CalendarDescriptor) (obj)).calendarId;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = calendarKey;
        obj = ((CalendarDescriptor) (obj)).calendarKey;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public int hashCode()
    {
        return ((account.hashCode() + 527) * 31 + calendarId.hashCode()) * 31 + calendarKey.hashCode();
    }

    public final boolean matches(CalendarDescriptor calendardescriptor)
    {
        if (calendardescriptor == null)
        {
            throw new NullPointerException();
        }
        if (!equals(calendardescriptor)) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        boolean flag;
        Object obj = account;
        Object obj1 = calendardescriptor.account;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || TextUtils.isEmpty(calendarId)) goto _L4; else goto _L3
_L3:
        obj = calendarId;
        obj1 = calendardescriptor.calendarId;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L5
_L5:
        flag = true;
_L7:
        if (!flag)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        if (calendarKey != CalendarKey.NONE && calendardescriptor.calendarKey != CalendarKey.NONE)
        {
            CalendarKey calendarkey = calendarKey;
            calendardescriptor = calendardescriptor.calendarKey;
            boolean flag1;
            if (calendarkey == calendardescriptor || calendarkey != null && calendarkey.equals(calendardescriptor))
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
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    public String toString()
    {
        return String.format("CalendarDescriptor{account=%s, calendarId=%s, localId='%s'}", new Object[] {
            account, calendarId, calendarKey
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(account, i);
        parcel.writeString(calendarId);
        parcel.writeParcelable(calendarKey, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new CalendarDescriptor(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new CalendarDescriptor[i];
        }

        _cls1()
        {
        }
    }

}
