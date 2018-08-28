// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

// Referenced classes of package com.google.android.gms.internal:
//            zzbqq, zzyg

public final class zzyj
    implements com.google.android.gms.clearcut.ClearcutLogger.LogSampler
{

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static Boolean zzaIc = null;
    private final zza zzaId;

    public zzyj()
    {
        this(new zza(null));
    }

    public zzyj(Context context)
    {
        this(new zza(context));
    }

    private zzyj(zza zza1)
    {
        if (zza1 == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzaId = (zza)zza1;
            return;
        }
    }

    private static zzb zzcD(String s)
    {
        int i = 0;
        if (s == null)
        {
            return null;
        }
        Object obj = "";
        int j = s.indexOf(',');
        if (j >= 0)
        {
            obj = s.substring(0, j);
            i = j + 1;
        }
        j = s.indexOf('/', i);
        if (j <= 0)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Failed to parse the rule: ".concat(s);
            } else
            {
                s = new String("Failed to parse the rule: ");
            }
            Log.e("LogSamplerImpl", s);
            return null;
        }
        long l;
        long l1;
        try
        {
            l = Long.parseLong(s.substring(i, j));
            l1 = Long.parseLong(s.substring(j + 1));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "parseLong() failed while parsing: ".concat(s);
            } else
            {
                s = new String("parseLong() failed while parsing: ");
            }
            Log.e("LogSamplerImpl", s, ((Throwable) (obj)));
            return null;
        }
        if (l < 0L || l1 < 0L)
        {
            Log.e("LogSamplerImpl", (new StringBuilder(72)).append("negative values not supported: ").append(l).append("/").append(l1).toString());
            return null;
        } else
        {
            return new zzb(((String) (obj)), l, l1);
        }
    }

    public final boolean shouldLog(String s, int i)
    {
        Object obj;
        long l;
        long l1;
        long l2;
        if (s == null || s.isEmpty())
        {
            if (i >= 0)
            {
                s = String.valueOf(i);
            } else
            {
                s = null;
            }
        }
        if (s == null)
        {
            return true;
        }
        obj = zzaId;
        if (((zza) (obj)).mContentResolver == null)
        {
            l = 0L;
        } else
        {
            l = zzbqq.getLong(((zza) (obj)).mContentResolver, "android_id", 0L);
        }
        obj = zzaId;
        if (((zza) (obj)).mContentResolver == null)
        {
            s = null;
        } else
        {
            obj = ((zza) (obj)).mContentResolver;
            String s1 = String.valueOf("gms:playlog:service:sampling_");
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = s1.concat(s);
            } else
            {
                s = new String(s1);
            }
            s = zzbqq.zza(((ContentResolver) (obj)), s, null);
        }
        s = zzcD(s);
        if (s == null)
        {
            return true;
        }
        obj = ((zzb) (s)).zzaIe;
        if (obj == null || ((String) (obj)).isEmpty())
        {
            l = zzyg.zzo(ByteBuffer.allocate(8).putLong(l).array());
        } else
        {
            byte abyte0[] = ((String) (obj)).getBytes(UTF_8);
            ByteBuffer bytebuffer = ByteBuffer.allocate(abyte0.length + 8);
            bytebuffer.put(abyte0);
            bytebuffer.putLong(l);
            l = zzyg.zzo(bytebuffer.array());
        }
        l1 = ((zzb) (s)).zzaIf;
        l2 = ((zzb) (s)).zzaIg;
        if (l1 < 0L || l2 < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder(72)).append("negative values not supported: ").append(l1).append("/").append(l2).toString());
        }
        if (l2 > 0L)
        {
            if (l >= 0L)
            {
                l %= l2;
            } else
            {
                l = ((l & 0x7fffffffffffffffL) % l2 + (0x7fffffffffffffffL % l2 + 1L)) % l2;
            }
            if (l < l1)
            {
                return true;
            }
        }
        return false;
    }


    private class zza
    {

        public final ContentResolver mContentResolver;

        zza(Context context)
        {
label0:
            {
                super();
                if (context != null)
                {
                    if (zzyj.zzaIc == null)
                    {
                        boolean flag;
                        if (context.checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        zzyj.zzaIc = Boolean.valueOf(flag);
                    }
                    if (zzyj.zzaIc.booleanValue())
                    {
                        break label0;
                    }
                }
                mContentResolver = null;
                return;
            }
            mContentResolver = context.getContentResolver();
            zzbqq.zzb(mContentResolver, new String[] {
                "gms:playlog:service:sampling_"
            });
        }
    }


    private class zzb
    {

        public final String zzaIe;
        public final long zzaIf;
        public final long zzaIg;

        public final boolean equals(Object obj)
        {
            if (obj != this) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            if (!(obj instanceof zzb))
            {
                return false;
            }
            obj = (zzb)obj;
            Object obj1 = zzaIe;
            Object obj2 = ((zzb) (obj)).zzaIe;
            boolean flag;
            if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            obj1 = Long.valueOf(zzaIf);
            obj2 = Long.valueOf(((zzb) (obj)).zzaIf);
            if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            obj1 = Long.valueOf(zzaIg);
            obj = Long.valueOf(((zzb) (obj)).zzaIg);
            if (obj1 == obj || obj1 != null && obj1.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag) goto _L1; else goto _L3
_L3:
            return false;
        }

        public final int hashCode()
        {
            return Arrays.hashCode(new Object[] {
                zzaIe, Long.valueOf(zzaIf), Long.valueOf(zzaIg)
            });
        }

        public zzb(String s, long l, long l1)
        {
            zzaIe = s;
            zzaIf = l;
            zzaIg = l1;
        }
    }

}
