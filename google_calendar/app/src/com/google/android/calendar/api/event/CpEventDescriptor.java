// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventDescriptor, EventKey, CpEventKey

public final class CpEventDescriptor
    implements EventDescriptor
{

    public static final android.os.Parcelable.Creator CREATOR;
    public final CpEventKey key;
    public final CpEventKey originalKey;

    CpEventDescriptor(Parcel parcel)
    {
        this((CpEventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader()), (CpEventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader()));
    }

    public CpEventDescriptor(CpEventKey cpeventkey)
    {
        if (cpeventkey == null)
        {
            throw new NullPointerException();
        } else
        {
            key = cpeventkey;
            originalKey = null;
            return;
        }
    }

    public CpEventDescriptor(CpEventKey cpeventkey, CpEventKey cpeventkey1)
    {
        if (cpeventkey == null)
        {
            throw new NullPointerException();
        }
        boolean flag;
        if (cpeventkey1 == null || !cpeventkey.hasStartMillis() && cpeventkey1.hasStartMillis())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            key = cpeventkey;
            originalKey = cpeventkey1;
            return;
        }
    }

    public final CpEventDescriptor derivePhantomDescriptor(long l)
    {
        if (originalKey != null)
        {
            return new CpEventDescriptor(CpEventKey.newInstance(originalKey.localId(), l));
        } else
        {
            return new CpEventDescriptor(CpEventKey.newInstance(key.localId(), l));
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (CpEventDescriptor)obj;
        CpEventKey cpeventkey = key;
        CpEventKey cpeventkey1 = ((CpEventDescriptor) (obj)).key;
        boolean flag;
        if (cpeventkey == cpeventkey1 || cpeventkey != null && cpeventkey.equals(cpeventkey1))
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
        cpeventkey = originalKey;
        obj = ((CpEventDescriptor) (obj)).originalKey;
        if (cpeventkey == obj || cpeventkey != null && cpeventkey.equals(obj))
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

    public final EventKey getKey()
    {
        return key;
    }

    public final long getOriginalStart()
    {
        if (!isRecurring())
        {
            throw new IllegalStateException();
        }
        CpEventKey cpeventkey;
        if (originalKey != null)
        {
            cpeventkey = originalKey;
        } else
        {
            cpeventkey = key;
        }
        return cpeventkey.startMillis();
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            key, originalKey
        });
    }

    public final boolean isCommitted()
    {
        return true;
    }

    public final boolean isRecurring()
    {
label0:
        {
            boolean flag1 = false;
            boolean flag;
            if (originalKey != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if (originalKey == null && key.hasStartMillis())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final boolean isRecurringException()
    {
        return originalKey != null;
    }

    public final boolean isRecurringPhantom()
    {
        return originalKey == null && key.hasStartMillis();
    }

    public final String toString()
    {
        return String.format("EventDescriptor{key='%s', originalKey='%s'}", new Object[] {
            key, originalKey
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(key, i);
        parcel.writeParcelable(originalKey, i);
    }

    static 
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return new CpEventDescriptor((Parcel)obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        CREATOR = new com.google.android.apps.calendar.util.android.Creators._cls1(.Lambda._cls0..instance, com/google/android/calendar/api/event/EventDescriptor);
    }
}
