// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import java.io.IOException;

final class zzbav
    implements com.google.android.gms.people.ImageResult
{

    private final Status zzahG;
    private final ParcelFileDescriptor zzbav;

    public final ParcelFileDescriptor getParcelFileDescriptor()
    {
        return zzbav;
    }

    public final Status getStatus()
    {
        return zzahG;
    }

    public final void release()
    {
        ParcelFileDescriptor parcelfiledescriptor;
        if (zzbav == null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        parcelfiledescriptor = zzbav;
        if (parcelfiledescriptor == null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        parcelfiledescriptor.close();
        return;
        IOException ioexception;
        ioexception;
    }

    public ageResult(Status status, ParcelFileDescriptor parcelfiledescriptor, boolean flag, int i, int j)
    {
        zzahG = status;
        zzbav = parcelfiledescriptor;
    }
}
