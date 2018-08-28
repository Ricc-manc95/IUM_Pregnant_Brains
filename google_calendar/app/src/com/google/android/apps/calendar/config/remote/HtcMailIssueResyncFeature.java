// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import com.google.android.gms.phenotype.PhenotypeFlag;

// Referenced classes of package com.google.android.apps.calendar.config.remote:
//            RemoteFeature

public final class HtcMailIssueResyncFeature extends RemoteFeature
{

    private final Memoized log;
    private final Memoized maxSdk;
    private final Memoized minCode;
    private final Memoized minSdk;
    public final Memoized minTime;
    public final Memoized minUpdated;
    public final Memoized reset;
    private final Memoized stage;

    HtcMailIssueResyncFeature()
    {
        super("HtcResync", "HTCR", false);
        com.google.android.gms.phenotype.PhenotypeFlag.Factory factory = super.flagBuilder;
        Object obj = String.valueOf(factory.zzcaQ);
        String s = String.valueOf("stage");
        class .Lambda._cls0
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls0(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls1
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls1(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls2
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls2(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls3
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls3(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls4
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls4(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls5
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls5(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls6
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls6(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        class .Lambda._cls7
            implements Provider
        {

            private final PhenotypeFlag arg$1;

            public final Object provide()
            {
                return arg$1.get();
            }

            .Lambda._cls7(PhenotypeFlag phenotypeflag)
            {
                arg$1 = phenotypeflag;
            }
        }

        String s1;
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("stage");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, 0);
        obj.getClass();
        stage = new Memoized(new .Lambda._cls0(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("log");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("log");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, false);
        obj.getClass();
        log = new Memoized(new .Lambda._cls1(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("reset");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("reset");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, false);
        obj.getClass();
        reset = new Memoized(new .Lambda._cls2(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("minSdk");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("minSdk");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, 24);
        obj.getClass();
        minSdk = new Memoized(new .Lambda._cls3(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("maxSdk");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("maxSdk");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, 24);
        obj.getClass();
        maxSdk = new Memoized(new .Lambda._cls4(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("minCode");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("minCode");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, 0x33ad64de);
        obj.getClass();
        minCode = new Memoized(new .Lambda._cls5(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("minTime");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("minTime");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, 0x15b6ee8c400L);
        obj.getClass();
        minTime = new Memoized(new .Lambda._cls6(((PhenotypeFlag) (obj))));
        factory = super.flagBuilder;
        obj = String.valueOf(factory.zzcaQ);
        s = String.valueOf("minUpdated");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        s = String.valueOf(factory.zzcaR);
        s1 = String.valueOf("minUpdated");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        obj = PhenotypeFlag.zzb(((String) (obj)), s, factory.zzcaO, factory.zzagh, "2017-04-15");
        obj.getClass();
        minUpdated = new Memoized(new .Lambda._cls7(((PhenotypeFlag) (obj))));
    }

    public final boolean log()
    {
        Memoized memoized = log;
        if (memoized.value == null)
        {
            memoized.value = memoized.provider.provide();
        }
        return ((Boolean)memoized.value).booleanValue();
    }

    public final int maxAffectedSdk()
    {
        Memoized memoized = maxSdk;
        if (memoized.value == null)
        {
            memoized.value = memoized.provider.provide();
        }
        return ((Integer)memoized.value).intValue();
    }

    public final int minAffectedSdk()
    {
        Memoized memoized = minSdk;
        if (memoized.value == null)
        {
            memoized.value = memoized.provider.provide();
        }
        return ((Integer)memoized.value).intValue();
    }

    public final int minVersionCode()
    {
        Memoized memoized = minCode;
        if (memoized.value == null)
        {
            memoized.value = memoized.provider.provide();
        }
        return ((Integer)memoized.value).intValue();
    }

    public final int stage()
    {
        Memoized memoized = stage;
        if (memoized.value == null)
        {
            memoized.value = memoized.provider.provide();
        }
        return ((Integer)memoized.value).intValue();
    }

    private class Memoized
    {

        public final Provider provider;
        public Object value;

        Memoized(Provider provider1)
        {
            value = null;
            provider = provider1;
        }
    }


    private class Provider
    {

        public abstract Object provide();
    }

}
