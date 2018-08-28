// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.zzb;
import com.google.android.gms.drive.metadata.internal.zzh;
import com.google.android.gms.drive.metadata.internal.zzn;
import com.google.android.gms.drive.metadata.internal.zzq;
import com.google.android.gms.drive.metadata.internal.zzr;
import com.google.android.gms.drive.metadata.internal.zzs;
import java.util.Collections;

// Referenced classes of package com.google.android.gms.internal:
//            zzakv

public final class zzaks
{

    public static final MetadataField zzbbQ;
    public static final MetadataField zzbbR = new zzr("alternateLink", 0x419ce0);
    public static final zza zzbbS = new zza(0x4c4b40);
    public static final MetadataField zzbbT = new zzr("description", 0x419ce0);
    public static final MetadataField zzbbU = new zzr("embedLink", 0x419ce0);
    public static final MetadataField zzbbV = new zzr("fileExtension", 0x419ce0);
    public static final MetadataField zzbbW = new com.google.android.gms.drive.metadata.internal.zzh("fileSize", 0x419ce0);
    public static final MetadataField zzbbX = new zzr("folderColorRgb", 0x7270e0);
    public static final MetadataField zzbbY = new com.google.android.gms.drive.metadata.internal.zzb("hasThumbnail", 0x419ce0);
    public static final MetadataField zzbbZ = new zzr("indexableText", 0x419ce0);
    public static final MetadataField zzbcA = new zzr("uniqueIdentifier", 0x4c4b40);
    public static final com.google.android.gms.drive.metadata.internal.zzb zzbcB = new com.google.android.gms.drive.metadata.internal.zzb("writersCanShare", 0x5b8d80);
    public static final MetadataField zzbcC = new zzr("role", 0x5b8d80);
    public static final MetadataField zzbcD = new zzr("md5Checksum", 0x6acfc0);
    public static final zze zzbcE = new zze(0x6acfc0);
    public static final MetadataField zzbcF = new zzr("recencyReason", 0x7a1200);
    public static final MetadataField zzbcG = new com.google.android.gms.drive.metadata.internal.zzb("subscribed", 0x7a1200);
    public static final MetadataField zzbca = new com.google.android.gms.drive.metadata.internal.zzb("isAppData", 0x419ce0);
    public static final MetadataField zzbcb = new com.google.android.gms.drive.metadata.internal.zzb("isCopyable", 0x419ce0);
    public static final MetadataField zzbcc = new com.google.android.gms.drive.metadata.internal.zzb("isEditable", 0x3e8fa0);
    public static final MetadataField zzbcd = new _cls1("isExplicitlyTrashed", Collections.singleton("trashed"), Collections.emptySet(), 0x6acfc0);
    public static final MetadataField zzbce = new com.google.android.gms.drive.metadata.internal.zzb("isLocalContentUpToDate", 0x7704c0);
    public static final zzb zzbcf = new zzb("isPinned", 0x3e8fa0);
    public static final MetadataField zzbcg = new com.google.android.gms.drive.metadata.internal.zzb("isOpenable", 0x6ddd00);
    public static final MetadataField zzbch = new com.google.android.gms.drive.metadata.internal.zzb("isRestricted", 0x419ce0);
    public static final MetadataField zzbci = new com.google.android.gms.drive.metadata.internal.zzb("isShared", 0x419ce0);
    public static final MetadataField zzbcj = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosFolder", 0x6acfc0);
    public static final MetadataField zzbck = new com.google.android.gms.drive.metadata.internal.zzb("isGooglePhotosRootFolder", 0x6acfc0);
    public static final MetadataField zzbcl = new com.google.android.gms.drive.metadata.internal.zzb("isTrashable", 0x432380);
    public static final MetadataField zzbcm = new com.google.android.gms.drive.metadata.internal.zzb("isViewed", 0x419ce0);
    public static final zzc zzbcn = new zzc(0x3e8fa0);
    public static final MetadataField zzbco = new zzr("originalFilename", 0x419ce0);
    public static final com.google.android.gms.drive.metadata.zzb zzbcp = new zzq("ownerNames", 0x419ce0);
    public static final zzs zzbcq = new zzs("lastModifyingUser", 0x5b8d80);
    public static final zzs zzbcr = new zzs("sharingUser", 0x5b8d80);
    public static final zzn zzbcs = new zzn(0x3e8fa0);
    public static final zzd zzbct = new zzd("quotaBytesUsed", 0x419ce0);
    public static final zzf zzbcu = new zzf("starred", 0x3e8fa0);
    public static final MetadataField zzbcv = new _cls2("thumbnail", Collections.emptySet(), Collections.emptySet(), 0x432380);
    public static final zzg zzbcw = new zzg("title", 0x3e8fa0);
    public static final zzh zzbcx = new zzh("trashed", 0x3e8fa0);
    public static final MetadataField zzbcy = new zzr("webContentLink", 0x419ce0);
    public static final MetadataField zzbcz = new zzr("webViewLink", 0x419ce0);

    static 
    {
        zzbbQ = zzakv.zzbcO;
    }

    private class zza extends zzakt
        implements MetadataField
    {

        public zza(int i)
        {
            super(0x4c4b40);
        }
    }


    private class _cls1 extends com.google.android.gms.drive.metadata.internal.zzb
    {

        _cls1(String s, Collection collection, Collection collection1, int i)
        {
            super(s, collection, collection1, 0x6acfc0);
        }
    }


    private class zzb extends com.google.android.gms.drive.metadata.internal.zzb
        implements MetadataField
    {

        public zzb(String s, int i)
        {
            super(s, 0x3e8fa0);
        }
    }


    private class zzc extends zzr
        implements MetadataField
    {

        public zzc(int i)
        {
            super("mimeType", 0x3e8fa0);
        }
    }


    private class zzd extends com.google.android.gms.drive.metadata.internal.zzh
        implements MetadataField
    {

        public zzd(String s, int i)
        {
            super(s, 0x419ce0);
        }
    }


    private class zzf extends com.google.android.gms.drive.metadata.internal.zzb
        implements MetadataField
    {

        public zzf(String s, int i)
        {
            super(s, 0x3e8fa0);
        }
    }


    private class _cls2 extends zzl
    {

        _cls2(String s, Collection collection, Collection collection1, int i)
        {
            super(s, collection, collection1, 0x432380);
        }
    }


    private class zzg extends zzr
        implements MetadataField
    {

        public zzg(String s, int i)
        {
            super(s, 0x3e8fa0);
        }
    }


    private class zzh extends com.google.android.gms.drive.metadata.internal.zzb
        implements MetadataField
    {

        public zzh(String s, int i)
        {
            super(s, 0x3e8fa0);
        }
    }


    private class zze extends zzk
    {

        public zze(int i)
        {
            super("spaces", Arrays.asList(new String[] {
                "inDriveSpace", "isAppData", "inGooglePhotosSpace"
            }), Collections.emptySet(), 0x6acfc0);
        }
    }

}
