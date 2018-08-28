// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;
import com.google.android.calendar.api.common.FieldModification;
import com.google.common.base.Function;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.api.event:
//            GooglePrivateDataModification, GooglePrivateData

public class GooglePrivateDataModificationImpl
    implements GooglePrivateDataModification
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private FieldModification everyoneDeclinedDismissedModification;
    private FieldModification everyoneDeclinedModification;
    private FieldModification guestNotificationModification;
    private GooglePrivateData original;

    public GooglePrivateDataModificationImpl()
    {
        guestNotificationModification = new FieldModification();
        everyoneDeclinedModification = new FieldModification();
        everyoneDeclinedDismissedModification = new FieldModification();
        original = null;
    }

    GooglePrivateDataModificationImpl(Parcel parcel)
    {
        guestNotificationModification = new FieldModification();
        everyoneDeclinedModification = new FieldModification();
        everyoneDeclinedDismissedModification = new FieldModification();
        original = (GooglePrivateData)parcel.readParcelable(com/google/android/calendar/api/event/GooglePrivateData.getClassLoader());
        Object obj;
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            obj = new com.google.android.calendar.api.common.FieldModification._cls1(GooglePrivateData.GuestNotification.values()[parcel.readInt()]);
        } else
        {
            obj = new FieldModification();
        }
        guestNotificationModification = ((FieldModification) (obj));
        everyoneDeclinedModification = readBooleanFieldModification(parcel);
        everyoneDeclinedDismissedModification = readBooleanFieldModification(parcel);
    }

    public GooglePrivateDataModificationImpl(GooglePrivateData googleprivatedata)
    {
        guestNotificationModification = new FieldModification();
        everyoneDeclinedModification = new FieldModification();
        everyoneDeclinedDismissedModification = new FieldModification();
        original = googleprivatedata;
    }

    private final Object get(FieldModification fieldmodification, Function function)
    {
        if (fieldmodification.shouldModify())
        {
            return fieldmodification.getModificationValue();
        }
        if (original != null)
        {
            return function.apply(original);
        } else
        {
            return function.apply(GooglePrivateData.DEFAULT);
        }
    }

    private static FieldModification readBooleanFieldModification(Parcel parcel)
    {
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            return new com.google.android.calendar.api.common.FieldModification._cls1((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader()));
        } else
        {
            return new FieldModification();
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof GooglePrivateDataModificationImpl))
        {
            return false;
        }
        obj = (GooglePrivateDataModificationImpl)obj;
        Object obj1 = original;
        Object obj2 = ((GooglePrivateDataModificationImpl) (obj)).original;
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
        obj1 = guestNotificationModification;
        obj2 = ((GooglePrivateDataModificationImpl) (obj)).guestNotificationModification;
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
        obj1 = everyoneDeclinedModification;
        obj2 = ((GooglePrivateDataModificationImpl) (obj)).everyoneDeclinedModification;
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
        obj1 = everyoneDeclinedDismissedModification;
        obj = ((GooglePrivateDataModificationImpl) (obj)).everyoneDeclinedDismissedModification;
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

    public final GooglePrivateData get()
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return ((GooglePrivateData)obj).getGuestNotification();
            }


            private .Lambda._cls0()
            {
            }
        }

        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                return Boolean.valueOf(((GooglePrivateData)obj).hasEveryoneDeclined());
            }


            private .Lambda._cls1()
            {
            }
        }

        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj)
            {
                return Boolean.valueOf(((GooglePrivateData)obj).isEveryoneDeclinedDismissed());
            }


            private .Lambda._cls2()
            {
            }
        }

        if (isModified())
        {
            return GooglePrivateData.create((GooglePrivateData.GuestNotification)get(guestNotificationModification, .Lambda._cls0..instance), ((Boolean)get(everyoneDeclinedModification, .Lambda._cls1..instance)).booleanValue(), ((Boolean)get(everyoneDeclinedDismissedModification, .Lambda._cls2..instance)).booleanValue());
        }
        if (original == null)
        {
            return GooglePrivateData.DEFAULT;
        } else
        {
            return original;
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, guestNotificationModification, everyoneDeclinedModification, everyoneDeclinedDismissedModification
        });
    }

    public final boolean isModified()
    {
        return guestNotificationModification.shouldModify() || everyoneDeclinedModification.shouldModify() || everyoneDeclinedDismissedModification.shouldModify();
    }

    public final void setGuestNotification(GooglePrivateData.GuestNotification guestnotification)
    {
        if (original == null || original.getGuestNotification() != guestnotification)
        {
            guestNotificationModification = new com.google.android.calendar.api.common.FieldModification._cls1(guestnotification);
        }
    }

    public final void setIsEveryoneDeclinedDismissed(boolean flag)
    {
        if (original == null || !original.isEveryoneDeclinedDismissed())
        {
            everyoneDeclinedDismissedModification = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(true));
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        FieldModification fieldmodification = guestNotificationModification;
        parcel.writeValue(Boolean.valueOf(fieldmodification.shouldModify()));
        if (fieldmodification.shouldModify())
        {
            parcel.writeInt(((GooglePrivateData.GuestNotification)fieldmodification.getModificationValue()).ordinal());
        }
        fieldmodification = everyoneDeclinedModification;
        parcel.writeValue(Boolean.valueOf(fieldmodification.shouldModify()));
        if (fieldmodification.shouldModify())
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
        fieldmodification = everyoneDeclinedDismissedModification;
        parcel.writeValue(Boolean.valueOf(fieldmodification.shouldModify()));
        if (fieldmodification.shouldModify())
        {
            parcel.writeValue(fieldmodification.getModificationValue());
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GooglePrivateDataModificationImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GooglePrivateDataModification[i];
        }

        _cls1()
        {
        }
    }

}
