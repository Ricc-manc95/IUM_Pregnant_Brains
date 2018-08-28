// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            DateTime, zzk, zzaj, Time

public class zzl extends zza
    implements DateTime
{

    public static final android.os.Parcelable.Creator CREATOR = new zzk();
    public final int mVersionCode;
    public final Boolean zzchT;
    public final Integer zzchV;
    public final Integer zzchW;
    public final Integer zzchX;
    public final Integer zzchZ;
    public final Integer zzcia;
    public final Long zzcib;
    public final Boolean zzcic;
    public final zzaj zzcid;

    zzl(int i, Integer integer, Integer integer1, Integer integer2, zzaj zzaj1, Integer integer3, Integer integer4, 
            Long long1, Boolean boolean1, Boolean boolean2)
    {
        zzchV = integer;
        zzchW = integer1;
        zzchX = integer2;
        zzcid = zzaj1;
        zzchZ = integer3;
        zzcia = integer4;
        zzcib = long1;
        zzcic = boolean1;
        zzchT = boolean2;
        mVersionCode = i;
    }

    public zzl(DateTime datetime)
    {
        this(datetime.getYear(), datetime.getMonth(), datetime.getDay(), datetime.getTime(), datetime.getPeriod(), datetime.getDateRange(), datetime.getAbsoluteTimeMs(), datetime.getUnspecifiedFutureTime(), datetime.getAllDay(), false);
    }

    zzl(Integer integer, Integer integer1, Integer integer2, Time time, Integer integer3, Integer integer4, Long long1, 
            Boolean boolean1, Boolean boolean2, boolean flag)
    {
        mVersionCode = 2;
        zzchV = integer;
        zzchW = integer1;
        zzchX = integer2;
        zzchZ = integer3;
        zzcia = integer4;
        zzcib = long1;
        zzcic = boolean1;
        zzchT = boolean2;
        if (flag)
        {
            integer = (zzaj)time;
        } else
        if (time == null)
        {
            integer = null;
        } else
        {
            integer = new zzaj(time);
        }
        zzcid = integer;
    }

    public static boolean zza(DateTime datetime, DateTime datetime1)
    {
        Integer integer = datetime.getYear();
        Integer integer5 = datetime1.getYear();
        boolean flag;
        if (integer == integer5 || integer != null && integer.equals(integer5))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer1 = datetime.getMonth();
            Integer integer6 = datetime1.getMonth();
            if (integer1 == integer6 || integer1 != null && integer1.equals(integer6))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Integer integer2 = datetime.getDay();
                Integer integer7 = datetime1.getDay();
                if (integer2 == integer7 || integer2 != null && integer2.equals(integer7))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Time time = datetime.getTime();
                    Time time1 = datetime1.getTime();
                    if (time == time1 || time != null && time.equals(time1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        Integer integer3 = datetime.getPeriod();
                        Integer integer8 = datetime1.getPeriod();
                        if (integer3 == integer8 || integer3 != null && integer3.equals(integer8))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            Integer integer4 = datetime.getDateRange();
                            Integer integer9 = datetime1.getDateRange();
                            if (integer4 == integer9 || integer4 != null && integer4.equals(integer9))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                Long long1 = datetime.getAbsoluteTimeMs();
                                Long long2 = datetime1.getAbsoluteTimeMs();
                                if (long1 == long2 || long1 != null && long1.equals(long2))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    Boolean boolean1 = datetime.getUnspecifiedFutureTime();
                                    Boolean boolean2 = datetime1.getUnspecifiedFutureTime();
                                    if (boolean1 == boolean2 || boolean1 != null && boolean1.equals(boolean2))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        datetime = datetime.getAllDay();
                                        datetime1 = datetime1.getAllDay();
                                        if (datetime == datetime1 || datetime != null && datetime.equals(datetime1))
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
                }
            }
        }
        return false;
    }

    public static int zzb(DateTime datetime)
    {
        return Arrays.hashCode(new Object[] {
            datetime.getYear(), datetime.getMonth(), datetime.getDay(), datetime.getTime(), datetime.getPeriod(), datetime.getDateRange(), datetime.getAbsoluteTimeMs(), datetime.getUnspecifiedFutureTime(), datetime.getAllDay()
        });
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof DateTime))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (DateTime)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final Long getAbsoluteTimeMs()
    {
        return zzcib;
    }

    public final Boolean getAllDay()
    {
        return zzchT;
    }

    public final Integer getDateRange()
    {
        return zzcia;
    }

    public final Integer getDay()
    {
        return zzchX;
    }

    public final Integer getMonth()
    {
        return zzchW;
    }

    public final Integer getPeriod()
    {
        return zzchZ;
    }

    public final Time getTime()
    {
        return zzcid;
    }

    public final Boolean getUnspecifiedFutureTime()
    {
        return zzcic;
    }

    public final Integer getYear()
    {
        return zzchV;
    }

    public int hashCode()
    {
        return zzb(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        Object obj = zzchV;
        if (obj != null)
        {
            zzc.zzb(parcel, 2, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzchW;
        if (obj != null)
        {
            zzc.zzb(parcel, 3, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzchX;
        if (obj != null)
        {
            zzc.zzb(parcel, 4, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        zzc.zza(parcel, 5, zzcid, i, false);
        obj = zzchZ;
        if (obj != null)
        {
            zzc.zzb(parcel, 6, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzcia;
        if (obj != null)
        {
            zzc.zzb(parcel, 7, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzcib;
        if (obj != null)
        {
            zzc.zzb(parcel, 8, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        obj = zzcic;
        if (obj != null)
        {
            zzc.zzb(parcel, 9, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }
        obj = zzchT;
        if (obj != null)
        {
            zzc.zzb(parcel, 10, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }
        zzc.zzJ(parcel, j);
    }

}
