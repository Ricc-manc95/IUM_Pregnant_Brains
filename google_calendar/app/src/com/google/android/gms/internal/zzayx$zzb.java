// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.model.AutocompleteBuffer;

final class zzbXk
    implements com.google.android.gms.people.te.AutocompleteResult
{

    private final Status zzahG;
    private final AutocompleteBuffer zzbXk;

    public final AutocompleteBuffer getAutocompleteEntries()
    {
        return zzbXk;
    }

    public final Status getStatus()
    {
        return zzahG;
    }

    public final void release()
    {
        if (zzbXk != null)
        {
            AutocompleteBuffer autocompletebuffer = zzbXk;
            if (((AbstractDataBuffer) (autocompletebuffer)).zzaKT != null)
            {
                ((AbstractDataBuffer) (autocompletebuffer)).zzaKT.close();
            }
        }
    }

    public mpleteBuffer(Status status, AutocompleteBuffer autocompletebuffer)
    {
        zzahG = status;
        zzbXk = autocompletebuffer;
    }
}
