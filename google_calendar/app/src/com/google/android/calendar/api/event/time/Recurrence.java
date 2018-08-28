// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.api.common.CopyHelper;
import com.google.android.calendar.api.common.ParcelHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            RecurRulePart

public class Recurrence
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final List exdates;
    public final List exrules;
    public final List rdates;
    public final List rrules;

    Recurrence(Parcel parcel)
    {
        Object obj = parcel.createTypedArrayList(RecurRulePart.CREATOR);
        Object obj1;
        List list;
        if (((List) (obj)).isEmpty())
        {
            obj = Collections.emptyList();
        } else
        {
            obj = Collections.unmodifiableList(((List) (obj)));
        }
        list = ParcelHelper.unparcelUnmodifiableLongList(parcel);
        obj1 = parcel.createTypedArrayList(RecurRulePart.CREATOR);
        if (((List) (obj1)).isEmpty())
        {
            obj1 = Collections.emptyList();
        } else
        {
            obj1 = Collections.unmodifiableList(((List) (obj1)));
        }
        this(((List) (obj)), list, ((List) (obj1)), ParcelHelper.unparcelUnmodifiableLongList(parcel));
    }

    private Recurrence(List list, List list1, List list2, List list3)
    {
        boolean flag = true;
        super();
        rrules = list;
        rdates = list1;
        if (list2.size() > 1)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            exrules = list2;
            exdates = list3;
            return;
        }
    }

    public Recurrence(RecurRulePart arecurrulepart[])
    {
        if (arecurrulepart == null)
        {
            throw new NullPointerException();
        } else
        {
            this((RecurRulePart[])arecurrulepart, ((long []) (null)), ((RecurRulePart []) (null)), ((long []) (null)));
            return;
        }
    }

    public Recurrence(RecurRulePart arecurrulepart[], long al[], RecurRulePart arecurrulepart1[], long al1[])
    {
        if (arecurrulepart == null || arecurrulepart.length == 0)
        {
            arecurrulepart = Collections.emptyList();
        } else
        {
            if (arecurrulepart == null || arecurrulepart.length == 0)
            {
                arecurrulepart = Collections.emptyList();
            } else
            {
                arecurrulepart = Arrays.asList(Arrays.copyOf(arecurrulepart, arecurrulepart.length));
            }
            arecurrulepart = Collections.unmodifiableList(arecurrulepart);
        }
        if (al == null || al.length == 0)
        {
            al = Collections.emptyList();
        } else
        {
            al = Collections.unmodifiableList(CopyHelper.copyArrayToList(al));
        }
        if (arecurrulepart1 == null)
        {
            arecurrulepart1 = Collections.emptyList();
        } else
        {
            if (arecurrulepart1 == null || arecurrulepart1.length == 0)
            {
                arecurrulepart1 = Collections.emptyList();
            } else
            {
                arecurrulepart1 = Arrays.asList(Arrays.copyOf(arecurrulepart1, arecurrulepart1.length));
            }
            arecurrulepart1 = Collections.unmodifiableList(arecurrulepart1);
        }
        if (al1 == null || al1.length == 0)
        {
            al1 = Collections.emptyList();
        } else
        {
            al1 = Collections.unmodifiableList(CopyHelper.copyArrayToList(al1));
        }
        this(((List) (arecurrulepart)), ((List) (al)), ((List) (arecurrulepart1)), ((List) (al1)));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (Recurrence)obj;
            if (rrules == null ? ((Recurrence) (obj)).rrules != null : !rrules.equals(((Recurrence) (obj)).rrules))
            {
                return false;
            }
            if (rdates == null ? ((Recurrence) (obj)).rdates != null : !rdates.equals(((Recurrence) (obj)).rdates))
            {
                return false;
            }
            if (exrules == null ? ((Recurrence) (obj)).exrules != null : !exrules.equals(((Recurrence) (obj)).exrules))
            {
                return false;
            }
            if (exdates != null)
            {
                return exdates.equals(((Recurrence) (obj)).exdates);
            }
            if (((Recurrence) (obj)).exdates != null)
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if (rrules != null)
        {
            i = rrules.hashCode();
        } else
        {
            i = 0;
        }
        if (rdates != null)
        {
            j = rdates.hashCode();
        } else
        {
            j = 0;
        }
        if (exrules != null)
        {
            k = exrules.hashCode();
        } else
        {
            k = 0;
        }
        if (exdates != null)
        {
            l = exdates.hashCode();
        }
        return (k + (j + i * 31) * 31) * 31 + l;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(rrules);
        parcel.writeList(rdates);
        parcel.writeTypedList(exrules);
        parcel.writeList(exdates);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Recurrence(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Recurrence[i];
        }

        _cls1()
        {
        }
    }

}
