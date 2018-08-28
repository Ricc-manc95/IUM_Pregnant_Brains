// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.feedback:
//            zzc

public class FileTeleporter extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.feedback.zzc();
    public final String mMimeType;
    public final int mVersionCode;
    public ParcelFileDescriptor zzSc;
    public File zzaNM;
    public final String zzbhe;
    private byte zzbhf[];

    FileTeleporter(int i, ParcelFileDescriptor parcelfiledescriptor, String s, String s1)
    {
        mVersionCode = i;
        zzSc = parcelfiledescriptor;
        mMimeType = s;
        zzbhe = s1;
    }

    public FileTeleporter(byte abyte0[], String s, String s1)
    {
        this(1, null, s, s1);
        zzbhf = abyte0;
    }

    private final FileOutputStream zzyt()
    {
        if (zzaNM == null)
        {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel.");
        }
        File file;
        FileOutputStream fileoutputstream;
        try
        {
            file = File.createTempFile("teleporter", ".tmp", zzaNM);
        }
        catch (IOException ioexception)
        {
            throw new IllegalStateException("Could not create temporary file:", ioexception);
        }
        try
        {
            fileoutputstream = new FileOutputStream(file);
            zzSc = ParcelFileDescriptor.open(file, 0x10000000);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new IllegalStateException("Temporary file is somehow already deleted.");
        }
        file.delete();
        return fileoutputstream;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj;
        if (zzSc != null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        obj = new DataOutputStream(zzyt());
        ((DataOutputStream) (obj)).writeInt(zzbhf.length);
        ((DataOutputStream) (obj)).writeUTF(mMimeType);
        ((DataOutputStream) (obj)).writeUTF(zzbhe);
        ((DataOutputStream) (obj)).write(zzbhf);
        int j;
        int k;
        try
        {
            ((Closeable) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            Log.w("FileTeleporter", "Could not close stream", ((Throwable) (obj)));
        }
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        j = parcel.dataPosition();
        k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzSc, i, false);
        zzc.zza(parcel, 3, mMimeType, false);
        zzc.zza(parcel, 4, zzbhe, false);
        zzc.zzJ(parcel, j);
        return;
        parcel;
        throw new IllegalStateException("Could not write into unlinked file", parcel);
        parcel;
        try
        {
            ((Closeable) (obj)).close();
        }
        catch (IOException ioexception)
        {
            Log.w("FileTeleporter", "Could not close stream", ioexception);
        }
        throw parcel;
    }

}
