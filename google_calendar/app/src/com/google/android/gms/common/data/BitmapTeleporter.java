// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.android.gms.common.data:
//            zza

public class BitmapTeleporter extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.common.data.zza();
    public final int mVersionCode;
    public ParcelFileDescriptor zzSc;
    private Bitmap zzaNK;
    public File zzaNM;
    public final int zzagZ;

    BitmapTeleporter(int i, ParcelFileDescriptor parcelfiledescriptor, int j)
    {
        mVersionCode = i;
        zzSc = parcelfiledescriptor;
        zzagZ = j;
        zzaNK = null;
    }

    public BitmapTeleporter(Bitmap bitmap)
    {
        mVersionCode = 1;
        zzSc = null;
        zzagZ = 0;
        zzaNK = bitmap;
    }

    private final FileOutputStream zzyt()
    {
        if (zzaNM == null)
        {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        File file;
        FileOutputStream fileoutputstream;
        try
        {
            file = File.createTempFile("teleporter", ".tmp", zzaNM);
        }
        catch (IOException ioexception)
        {
            throw new IllegalStateException("Could not create temporary file", ioexception);
        }
        try
        {
            fileoutputstream = new FileOutputStream(file);
            zzSc = ParcelFileDescriptor.open(file, 0x10000000);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new IllegalStateException("Temporary file is somehow already deleted");
        }
        file.delete();
        return fileoutputstream;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj;
        Bitmap bitmap;
        byte abyte0[];
        if (zzSc != null)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        bitmap = zzaNK;
        obj = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
        bitmap.copyPixelsToBuffer(((java.nio.Buffer) (obj)));
        abyte0 = ((ByteBuffer) (obj)).array();
        obj = new DataOutputStream(zzyt());
        ((DataOutputStream) (obj)).writeInt(abyte0.length);
        ((DataOutputStream) (obj)).writeInt(bitmap.getWidth());
        ((DataOutputStream) (obj)).writeInt(bitmap.getHeight());
        ((DataOutputStream) (obj)).writeUTF(bitmap.getConfig().toString());
        ((DataOutputStream) (obj)).write(abyte0);
        int j;
        int k;
        try
        {
            ((Closeable) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            Log.w("BitmapTeleporter", "Could not close stream", ((Throwable) (obj)));
        }
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        j = parcel.dataPosition();
        k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        obj = zzSc;
        if (obj != null)
        {
            parcel.writeInt(-65534);
            parcel.writeInt(0);
            int l = parcel.dataPosition();
            ((Parcelable) (obj)).writeToParcel(parcel, i | 1);
            i = parcel.dataPosition();
            parcel.setDataPosition(l - 4);
            parcel.writeInt(i - l);
            parcel.setDataPosition(i);
        }
        i = zzagZ;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(i);
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
        zzSc = null;
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
            Log.w("BitmapTeleporter", "Could not close stream", ioexception);
        }
        throw parcel;
    }

}
