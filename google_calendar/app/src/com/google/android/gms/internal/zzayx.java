// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.people.internal.agg.zzd;
import com.google.android.gms.people.model.AvatarReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.internal:
//            zzayw, zzayp, zzayt, zzayj

public final class zzayx extends zzl
{

    private static volatile Bundle zzbXs;
    private static volatile Bundle zzbXt;
    private final String zzahF;
    private final String zzbXq;
    private final HashMap zzbXr = new HashMap();

    public zzayx(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String s, zzg zzg1)
    {
        super(context.getApplicationContext(), looper, 5, zzg1, connectioncallbacks, onconnectionfailedlistener);
        zzbXq = s;
        zzahF = zzg1.zzahF;
    }

    private final void zzZ(Bundle bundle)
    {
        this;
        JVM INSTR monitorenter ;
        if (bundle != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        zzd.zzbZg = bundle.getBoolean("use_contactables_api", true);
        zzayw.zzbXn.zza(bundle.getStringArray("config.url_uncompress.patterns"), bundle.getStringArray("config.url_uncompress.replacements"));
        zzbXs = bundle.getBundle("config.email_type_map");
        zzbXt = bundle.getBundle("config.phone_type_map");
        if (true) goto _L1; else goto _L3
_L3:
        bundle;
        throw bundle;
    }

    static Status zzb(int i, String s, Bundle bundle)
    {
        if (bundle == null)
        {
            s = null;
        } else
        {
            s = (PendingIntent)bundle.getParcelable("pendingIntent");
        }
        return new Status(i, null, s);
    }

    public final void disconnect()
    {
        HashMap hashmap = zzbXr;
        hashmap;
        JVM INSTR monitorenter ;
        if (isConnected())
        {
            Object obj = zzbXr.values().iterator();
            if (((Iterator) (obj)).hasNext())
            {
                obj = (zzv)((Iterator) (obj)).next();
                throw new NoSuchMethodError();
            }
        }
        break MISSING_BLOCK_LABEL_59;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        zzbXr.clear();
        hashmap;
        JVM INSTR monitorexit ;
        super.disconnect();
        return;
    }

    public final zzs zza(zzyq.zzb zzb1, AvatarReference avatarreference, com.google.android.gms.people.Images.LoadImageOptions loadimageoptions)
    {
        if (!isConnected())
        {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
        zzb1 = new zzaf(zzb1);
        try
        {
            avatarreference = ((zzayp)super.zzyP()).zza(zzb1, avatarreference, zzayt.zza(loadimageoptions));
        }
        // Misplaced declaration of an exception variable
        catch (AvatarReference avatarreference)
        {
            zzb1.zza(8, null, null, null);
            return null;
        }
        return avatarreference;
    }

    protected final void zza(int i, IBinder ibinder, Bundle bundle, int j)
    {
        if (i == 0 && bundle != null)
        {
            zzZ(bundle.getBundle("post_init_configuration"));
        }
        if (bundle == null)
        {
            bundle = null;
        } else
        {
            bundle = bundle.getBundle("post_init_resolution");
        }
        super.zza(i, ibinder, bundle, j);
    }

    public final void zza(zzyq.zzb zzb1, String s, com.google.android.gms.people.Autocomplete.AutocompleteOptions autocompleteoptions)
    {
        if (!isConnected())
        {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
        zzb1 = new zzq(zzb1);
        try
        {
            ((zzayp)super.zzyP()).zza(zzb1, autocompleteoptions.account, null, false, autocompleteoptions.directoryAccountType, s, autocompleteoptions.autocompleteType, 0, autocompleteoptions.numberOfResults, autocompleteoptions.useAndroidContactFallback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            zzb1.zza(8, null, null);
        }
    }

    public final zzs zzb(zzyq.zzb zzb1, String s, String s1, int i, int j)
    {
        zzb1 = new zzaf(zzb1);
        try
        {
            s = ((zzayp)super.zzyP()).zzb(zzb1, s, s1, i, j);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            zzb1.zza(8, null, null, null);
            return null;
        }
        return s;
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.people.service.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.people.internal.IPeopleService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.people.internal.IPeopleService");
        if (iinterface != null && (iinterface instanceof zzayp))
        {
            return (zzayp)iinterface;
        } else
        {
            return new zzayp.zza.zza(ibinder);
        }
    }

    protected final Bundle zzpF()
    {
        Bundle bundle = new Bundle();
        bundle.putString("social_client_application_id", zzbXq);
        bundle.putString("real_client_package_name", zzahF);
        bundle.putBoolean("support_new_image_callback", true);
        return bundle;
    }

    private class zzv extends zzayj
    {

        public final void zza(int i, Bundle bundle, Bundle bundle1)
        {
            throw new NoSuchMethodError();
        }
    }


    private class zzaf extends zzayj
    {

        private final zzyq.zzb zzbCp;

        public final void zza(int i, Bundle bundle, ParcelFileDescriptor parcelfiledescriptor, Bundle bundle1)
        {
            int j = 0;
            bundle = zzayx.zzb(i, null, bundle);
            boolean flag;
            if (bundle1 != null)
            {
                flag = bundle1.getBoolean("rewindable");
                i = bundle1.getInt("width");
                j = bundle1.getInt("height");
            } else
            {
                i = 0;
                flag = false;
            }
            zzbCp.setResult(new zzan(bundle, parcelfiledescriptor, flag, i, j));
        }

        public zzaf(zzyq.zzb zzb1)
        {
            zzbCp = zzb1;
        }

        private class zzan
            implements com.google.android.gms.people.Images.LoadImageResult
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

            public zzan(Status status, ParcelFileDescriptor parcelfiledescriptor, boolean flag, int i, int j)
            {
                zzahG = status;
                zzbav = parcelfiledescriptor;
            }
        }

    }


    private class zzq extends zzayj
    {

        private final zzyq.zzb zzbCp;

        public final void zza(int i, Bundle bundle, DataHolder dataholder)
        {
            Object obj = null;
            Status status = zzayx.zzb(i, null, bundle);
            if (dataholder == null)
            {
                bundle = obj;
            } else
            {
                bundle = new AutocompleteBuffer(dataholder);
            }
            zzbCp.setResult(new zzb(status, bundle));
        }

        public zzq(zzyq.zzb zzb1)
        {
            zzbCp = zzb1;
        }

        private class zzb
            implements com.google.android.gms.people.Autocomplete.AutocompleteResult
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

            public zzb(Status status, AutocompleteBuffer autocompletebuffer)
            {
                zzahG = status;
                zzbXk = autocompletebuffer;
            }
        }

    }

}
