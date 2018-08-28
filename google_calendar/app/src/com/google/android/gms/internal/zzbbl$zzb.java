// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            zzzv

final class zzaZF extends zzaZF
{

    public final zzzv zzaZF;

    public final void onReminderCreated(String s, String s1)
    {
        if (zzaZF != null)
        {
            zzzv zzzv1 = zzaZF;
            class _cls1
                implements zzzv.zzc
            {

                private final String zzcgj;
                private final String zzcgk;
                private final zzbbl.zzb zzcgl;

                public final void zzw(Object obj)
                {
                    ((com.google.android.gms.reminders.RemindersApi.ReminderCreatedListener)obj).onReminderCreated$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0();
                    zzcgl.zzaZF.mListener = null;
                }

                public final void zzxk()
                {
                    Log.e("Reminders", "Notify reminder created listener failed");
                }

            _cls1(String s, String s1)
            {
                zzcgl = zzbbl.zzb.this;
                zzcgj = s;
                zzcgk = s1;
                super();
            }
            }

            s = new _cls1(s, s1);
            if (s == null)
            {
                throw new NullPointerException(String.valueOf("Notifier must not be null"));
            }
            s = zzzv1.zzaMJ.btainMessage(1, s);
            zzzv1.zzaMJ.endMessage(s);
        }
    }

    public _cls1(_cls1 _pcls1, zzzv zzzv1)
    {
        super(_pcls1);
        zzaZF = zzzv1;
    }
}
