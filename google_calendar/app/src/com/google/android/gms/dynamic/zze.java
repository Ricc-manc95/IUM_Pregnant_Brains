// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamic;

import java.lang.reflect.Field;

// Referenced classes of package com.google.android.gms.dynamic:
//            zzd

public final class zze extends zzd.zza
{

    private final Object mWrappedObject;

    public zze(Object obj)
    {
        mWrappedObject = obj;
    }

    public static Object zzH(zzd zzd1)
    {
        int k = 0;
        if (zzd1 instanceof zze)
        {
            return ((zze)zzd1).mWrappedObject;
        }
        android.os.IBinder ibinder = zzd1.asBinder();
        Field afield[] = ibinder.getClass().getDeclaredFields();
        zzd1 = null;
        int l = afield.length;
        for (int i = 0; i < l; i++)
        {
            Field field = afield[i];
            if (!field.isSynthetic())
            {
                k++;
                zzd1 = field;
            }
        }

        if (k == 1)
        {
            if (!zzd1.isAccessible())
            {
                zzd1.setAccessible(true);
                try
                {
                    zzd1 = ((zzd) (zzd1.get(ibinder)));
                }
                // Misplaced declaration of an exception variable
                catch (zzd zzd1)
                {
                    throw new IllegalArgumentException("Binder object is null.", zzd1);
                }
                // Misplaced declaration of an exception variable
                catch (zzd zzd1)
                {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", zzd1);
                }
                return zzd1;
            } else
            {
                throw new IllegalArgumentException("IObjectWrapper declared field not private!");
            }
        } else
        {
            int j = afield.length;
            throw new IllegalArgumentException((new StringBuilder(64)).append("Unexpected number of IObjectWrapper declared fields: ").append(j).toString());
        }
    }
}
