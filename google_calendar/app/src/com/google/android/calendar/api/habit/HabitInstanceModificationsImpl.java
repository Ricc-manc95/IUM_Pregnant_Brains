// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;
import com.google.android.calendar.api.common.FieldModification;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitInstanceModifications, HabitInstance, HabitDescriptor, HabitUtil

public final class HabitInstanceModificationsImpl
    implements HabitInstanceModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final HabitInstance original;
    private final HabitDescriptor parentDescriptor;
    private final int parentType;
    private FieldModification status;
    private FieldModification statusInferred;

    HabitInstanceModificationsImpl(Parcel parcel)
    {
        status = new FieldModification();
        statusInferred = new FieldModification();
        original = (HabitInstance)parcel.readParcelable(com/google/android/calendar/api/habit/HabitInstance.getClassLoader());
        parentDescriptor = (HabitDescriptor)parcel.readParcelable(com/google/android/calendar/api/habit/HabitDescriptor.getClassLoader());
        parentType = HabitUtil.checkType(parcel.readInt());
        if (((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue())
        {
            int i = HabitUtil.intToHabitInstanceStatus(parcel.readInt());
            boolean flag = ((Boolean)parcel.readValue(java/lang/Boolean.getClassLoader())).booleanValue();
            status = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(i));
            statusInferred = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        }
    }

    public HabitInstanceModificationsImpl(HabitDescriptor habitdescriptor, int i)
    {
        status = new FieldModification();
        statusInferred = new FieldModification();
        original = null;
        if (habitdescriptor == null)
        {
            throw new NullPointerException();
        } else
        {
            parentDescriptor = (HabitDescriptor)habitdescriptor;
            status = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(1));
            statusInferred = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(false));
            parentType = i;
            return;
        }
    }

    public HabitInstanceModificationsImpl(HabitInstance habitinstance)
    {
        status = new FieldModification();
        statusInferred = new FieldModification();
        if (habitinstance == null)
        {
            throw new NullPointerException();
        } else
        {
            original = (HabitInstance)habitinstance;
            parentDescriptor = original.getHabitParentDescriptor();
            parentType = original.getParentType();
            return;
        }
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof HabitInstanceModificationsImpl)
        {
            obj = (HabitInstanceModificationsImpl)obj;
            HabitInstance habitinstance = original;
            HabitInstance habitinstance1 = ((HabitInstanceModificationsImpl) (obj)).original;
            boolean flag;
            if (habitinstance == habitinstance1 || habitinstance != null && habitinstance.equals(habitinstance1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                HabitDescriptor habitdescriptor = parentDescriptor;
                HabitDescriptor habitdescriptor1 = ((HabitInstanceModificationsImpl) (obj)).parentDescriptor;
                if (habitdescriptor == habitdescriptor1 || habitdescriptor != null && habitdescriptor.equals(habitdescriptor1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Integer integer = Integer.valueOf(parentType);
                    Integer integer1 = Integer.valueOf(((HabitInstanceModificationsImpl) (obj)).parentType);
                    if (integer == integer1 || integer != null && integer.equals(integer1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        FieldModification fieldmodification = status;
                        FieldModification fieldmodification2 = ((HabitInstanceModificationsImpl) (obj)).status;
                        if (fieldmodification == fieldmodification2 || fieldmodification != null && fieldmodification.equals(fieldmodification2))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            FieldModification fieldmodification1 = statusInferred;
                            obj = ((HabitInstanceModificationsImpl) (obj)).statusInferred;
                            if (fieldmodification1 == obj || fieldmodification1 != null && fieldmodification1.equals(obj))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final HabitDescriptor getHabitParentDescriptor()
    {
        return parentDescriptor;
    }

    public final int getParentType()
    {
        return parentType;
    }

    public final int getStatus()
    {
        if (status.shouldModify())
        {
            return HabitUtil.intToHabitInstanceStatus(((Integer)status.getModificationValue()).intValue());
        }
        if (original != null)
        {
            return original.getStatus();
        } else
        {
            return 1;
        }
    }

    public final boolean getStatusInferred()
    {
        if (status.shouldModify())
        {
            return ((Boolean)statusInferred.getModificationValue()).booleanValue();
        }
        if (original != null)
        {
            return original.getStatusInferred();
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, parentDescriptor, Integer.valueOf(parentType), status, statusInferred
        });
    }

    public final boolean isModified()
    {
        return status.shouldModify();
    }

    public final boolean isStatusModified()
    {
        return status.shouldModify();
    }

    public final HabitInstanceModifications setStatus(int i, boolean flag)
    {
        status = new com.google.android.calendar.api.common.FieldModification._cls1(Integer.valueOf(i));
        statusInferred = new com.google.android.calendar.api.common.FieldModification._cls1(Boolean.valueOf(flag));
        return this;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("HabitInstanceModificationsImpl{");
        String s = String.valueOf(original);
        stringbuilder = stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 10)).append("mOriginal=").append(s).toString());
        s = String.valueOf(parentDescriptor);
        stringbuilder = stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 20)).append(", mParentDescriptor=").append(s).toString());
        int i = parentType;
        stringbuilder = stringbuilder.append((new StringBuilder(25)).append(", mParentType=").append(i).toString());
        s = String.valueOf(status);
        stringbuilder = stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 10)).append(", mStatus=").append(s).toString());
        s = String.valueOf(statusInferred);
        return stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 18)).append(", mStatusInferred=").append(s).toString()).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(original, i);
        parcel.writeParcelable(parentDescriptor, i);
        parcel.writeInt(parentType);
        parcel.writeValue(Boolean.valueOf(status.shouldModify()));
        if (status.shouldModify())
        {
            parcel.writeInt(getStatus());
            parcel.writeValue(Boolean.valueOf(getStatusInferred()));
        }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new HabitInstanceModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new HabitInstanceModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
