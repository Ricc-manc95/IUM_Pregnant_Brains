// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zzagw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            zzi, zzf

public final class MetadataBundle extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzi();
    public final int mVersionCode;
    public final Bundle zzbbM;

    MetadataBundle(int i, Bundle bundle)
    {
        mVersionCode = i;
        if (bundle == null)
        {
            throw new NullPointerException("null reference");
        }
        zzbbM = (Bundle)bundle;
        zzbbM.setClassLoader(getClass().getClassLoader());
        ArrayList arraylist = new ArrayList();
        Iterator iterator = zzbbM.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            bundle = (String)iterator.next();
            if ((MetadataField)zzf.zzbbK.get(bundle) == null)
            {
                arraylist.add(bundle);
                bundle = String.valueOf(bundle);
                zzq zzq1;
                if (bundle.length() != 0)
                {
                    bundle = "Ignored unknown metadata field in bundle: ".concat(bundle);
                } else
                {
                    bundle = new String("Ignored unknown metadata field in bundle: ");
                }
                zzq1 = zzagw.zzbad;
                if (Log.isLoggable(zzq1.zzaQE, 5))
                {
                    if (zzq1.zzaQF != null)
                    {
                        bundle = zzq1.zzaQF.concat(bundle);
                    }
                    Log.w("MetadataBundle", bundle);
                }
            }
        } while (true);
        bundle = (ArrayList)arraylist;
        int j = bundle.size();
        for (i = 0; i < j;)
        {
            Object obj = bundle.get(i);
            i++;
            obj = (String)obj;
            zzbbM.remove(((String) (obj)));
        }

    }

    public MetadataBundle(Bundle bundle)
    {
        this(1, bundle);
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof MetadataBundle))
        {
            return false;
        }
        obj = (MetadataBundle)obj;
        Object obj1 = zzbbM.keySet();
        if (!((Set) (obj1)).equals(((MetadataBundle) (obj)).zzbbM.keySet()))
        {
            return false;
        }
        for (obj1 = ((Set) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
        {
            Object obj3 = (String)((Iterator) (obj1)).next();
            Object obj2 = zzbbM.get(((String) (obj3)));
            obj3 = ((MetadataBundle) (obj)).zzbbM.get(((String) (obj3)));
            boolean flag;
            if (obj2 == obj3 || obj2 != null && obj2.equals(obj3))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return false;
            }
        }

        return true;
    }

    public final int hashCode()
    {
        Iterator iterator = zzbbM.keySet().iterator();
        String s;
        int i;
        for (i = 1; iterator.hasNext(); i = zzbbM.get(s).hashCode() + i * 31)
        {
            s = (String)iterator.next();
        }

        return i;
    }

    public final String toString()
    {
        String s = String.valueOf(zzbbM);
        return (new StringBuilder(String.valueOf(s).length() + 24)).append("MetadataBundle [values=").append(s).append("]").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzbbM, false);
        zzc.zzJ(parcel, i);
    }

}
