// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import com.google.android.libraries.directboot.DirectBootUtils;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            ConfigurationContentLoader

public abstract class PhenotypeFlag
{

    public static Context context = null;
    private static volatile Boolean hasGservicesReadPermission = null;
    private static final Object setContextLock = new Object();
    private static boolean testMode = false;
    private volatile ConfigurationContentLoader configurationContentLoader;
    private final Object defaultValue;
    private final Factory factory;
    public final String gservicesFlagName;
    public final String mendelFlagName;
    private Object override;
    private volatile SharedPreferences sharedPreferences;

    PhenotypeFlag(Factory factory1, String s, Object obj)
    {
        override = null;
        configurationContentLoader = null;
        sharedPreferences = null;
        if (factory1.sharedPrefsName == null && factory1.contentProviderUri == null)
        {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (factory1.sharedPrefsName != null && factory1.contentProviderUri != null)
        {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        factory = factory1;
        String s1 = String.valueOf(factory1.gservicesPrefix);
        String s2 = String.valueOf(s);
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        gservicesFlagName = s1;
        factory1 = String.valueOf(factory1.phenotypePrefix);
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            factory1 = factory1.concat(s);
        } else
        {
            factory1 = new String(factory1);
        }
        mendelFlagName = factory1;
        defaultValue = obj;
    }

    private static boolean canReadGservices()
    {
        boolean flag;
label0:
        {
            flag = false;
            boolean flag1 = false;
            if (hasGservicesReadPermission == null)
            {
                if (context == null)
                {
                    break label0;
                }
                flag = flag1;
                if (PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0)
                {
                    flag = true;
                }
                hasGservicesReadPermission = Boolean.valueOf(flag);
            }
            flag = hasGservicesReadPermission.booleanValue();
        }
        return flag;
    }

    private static Object executeBinderAware(BinderAwareFunction binderawarefunction)
    {
        Object obj = binderawarefunction.execute();
        return obj;
        SecurityException securityexception;
        securityexception;
        long l = Binder.clearCallingIdentity();
        binderawarefunction = ((BinderAwareFunction) (binderawarefunction.execute()));
        Binder.restoreCallingIdentity(l);
        return binderawarefunction;
        binderawarefunction;
        Binder.restoreCallingIdentity(l);
        throw binderawarefunction;
    }

    static boolean getGservicesBoolean(String s, boolean flag)
    {
label0:
        {
            try
            {
                if (!canReadGservices())
                {
                    break label0;
                }
                class .Lambda._cls2
                    implements BinderAwareFunction
                {

                    private final String arg$1;
                    private final boolean arg$2;

                    public final Object execute()
                    {
                        String s1 = arg$1;
                        boolean flag1 = arg$2;
                        return Boolean.valueOf(Gservices.getBoolean(PhenotypeFlag.context.getContentResolver(), s1, flag1));
                    }

            .Lambda._cls2(String s, boolean flag)
            {
                arg$1 = s;
                arg$2 = flag;
            }
                }

                flag = ((Boolean)executeBinderAware(new .Lambda._cls2(s, false))).booleanValue();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", s);
                return false;
            }
            return flag;
        }
        return false;
    }

    public static void init(Context context1)
    {
        Object obj = setContextLock;
        obj;
        JVM INSTR monitorenter ;
        if (android.os.Build.VERSION.SDK_INT < 24 || !context1.isDeviceProtectedStorage()) goto _L2; else goto _L1
_L1:
        if (context != context1)
        {
            hasGservicesReadPermission = null;
        }
        context = context1;
        testMode = false;
        return;
_L2:
        Context context2 = context1.getApplicationContext();
        if (context2 != null)
        {
            context1 = context2;
        }
          goto _L1
        context1;
        obj;
        JVM INSTR monitorexit ;
        throw context1;
    }

    private final Object loadValueFromGservices()
    {
        if (factory.skipGservices || !canReadGservices())
        {
            break MISSING_BLOCK_LABEL_74;
        }
        class .Lambda._cls1
            implements BinderAwareFunction
        {

            private final PhenotypeFlag arg$1;

            public final Object execute()
            {
                PhenotypeFlag phenotypeflag = arg$1;
                return Gservices.getString(PhenotypeFlag.context.getContentResolver(), phenotypeflag.gservicesFlagName, null);
            }

            .Lambda._cls1()
            {
                arg$1 = PhenotypeFlag.this;
            }
        }

        Object obj = (String)executeBinderAware(new .Lambda._cls1());
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        obj = fromString(((String) (obj)));
        return obj;
        SecurityException securityexception;
        securityexception;
        String s = String.valueOf(mendelFlagName);
        if (s.length() != 0)
        {
            s = "Unable to read GServices for flag: ".concat(s);
        } else
        {
            s = new String("Unable to read GServices for flag: ");
        }
        Log.e("PhenotypeFlag", s, securityexception);
        return null;
    }

    private final Object loadValueFromPhenotype()
    {
        boolean flag1 = true;
        if (!getGservicesBoolean("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false))
        {
            if (factory.contentProviderUri != null)
            {
                if (configurationContentLoader == null)
                {
                    ContentResolver contentresolver = context.getContentResolver();
                    Object obj1 = factory.contentProviderUri;
                    ConfigurationContentLoader configurationcontentloader1 = (ConfigurationContentLoader)ConfigurationContentLoader.LOADERS_BY_URI.get(obj1);
                    ConfigurationContentLoader configurationcontentloader = configurationcontentloader1;
                    if (configurationcontentloader1 == null)
                    {
                        ConfigurationContentLoader configurationcontentloader2 = new ConfigurationContentLoader(contentresolver, ((Uri) (obj1)));
                        obj1 = (ConfigurationContentLoader)ConfigurationContentLoader.LOADERS_BY_URI.putIfAbsent(obj1, configurationcontentloader2);
                        configurationcontentloader = ((ConfigurationContentLoader) (obj1));
                        if (obj1 == null)
                        {
                            configurationcontentloader2.resolver.registerContentObserver(configurationcontentloader2.uri, false, configurationcontentloader2.observer);
                            configurationcontentloader = configurationcontentloader2;
                        }
                    }
                    configurationContentLoader = configurationcontentloader;
                }
                class .Lambda._cls0
                    implements BinderAwareFunction
                {

                    private final PhenotypeFlag arg$1;
                    private final ConfigurationContentLoader arg$2;

                    public final Object execute()
                    {
                        PhenotypeFlag phenotypeflag = arg$1;
                        return (String)arg$2.getFlags().get(phenotypeflag.mendelFlagName);
                    }

            .Lambda._cls0(ConfigurationContentLoader configurationcontentloader)
            {
                arg$1 = PhenotypeFlag.this;
                arg$2 = configurationcontentloader;
            }
                }

                String s = (String)executeBinderAware(new .Lambda._cls0(configurationContentLoader));
                if (s != null)
                {
                    return fromString(s);
                }
            } else
            if (factory.sharedPrefsName != null)
            {
                Object obj = factory.sharedPrefsName;
                boolean flag = flag1;
                if (DirectBootUtils.supportsDirectBoot())
                {
                    flag = flag1;
                    if (!((String) (obj)).startsWith("direct_boot:"))
                    {
                        obj = context;
                        flag = flag1;
                        if (android.os.Build.VERSION.SDK_INT >= 24)
                        {
                            if (DirectBootUtils.checkUserUnlocked(((Context) (obj))))
                            {
                                flag = flag1;
                            } else
                            {
                                flag = false;
                            }
                        }
                    }
                }
                if (!flag)
                {
                    return null;
                }
                if (sharedPreferences == null)
                {
                    if (factory.sharedPrefsName.startsWith("direct_boot:"))
                    {
                        obj = context;
                        if (DirectBootUtils.supportsDirectBoot())
                        {
                            obj = context.createDeviceProtectedStorageContext();
                        }
                        sharedPreferences = ((Context) (obj)).getSharedPreferences(factory.sharedPrefsName.substring(12), 0);
                    } else
                    {
                        sharedPreferences = context.getSharedPreferences(factory.sharedPrefsName, 0);
                    }
                }
                obj = sharedPreferences;
                if (((SharedPreferences) (obj)).contains(mendelFlagName))
                {
                    return fromSharedPreferences(((SharedPreferences) (obj)));
                }
            }
        } else
        {
            String s1 = String.valueOf(mendelFlagName);
            if (s1.length() != 0)
            {
                s1 = "Bypass reading Phenotype values for flag: ".concat(s1);
            } else
            {
                s1 = new String("Bypass reading Phenotype values for flag: ");
            }
            Log.w("PhenotypeFlag", s1);
        }
        return null;
    }

    public static PhenotypeFlag value(Factory factory1, String s, long l)
    {
        return new _cls1(factory1, s, Long.valueOf(l));
    }

    public static PhenotypeFlag value(Factory factory1, String s, String s1)
    {
        return new _cls6(factory1, s, s1);
    }

    public static PhenotypeFlag value(Factory factory1, String s, boolean flag)
    {
        return new _cls3(factory1, s, Boolean.valueOf(flag));
    }

    protected abstract Object fromSharedPreferences(SharedPreferences sharedpreferences);

    protected abstract Object fromString(String s);

    public final Object get()
    {
        if (context == null)
        {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        if (!factory.preferGservices) goto _L2; else goto _L1
_L1:
        Object obj = loadValueFromGservices();
        if (obj == null) goto _L4; else goto _L3
_L3:
        return obj;
_L4:
        Object obj1 = loadValueFromPhenotype();
        obj = obj1;
        if (obj1 != null)
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
        return defaultValue;
_L2:
        Object obj2 = loadValueFromPhenotype();
        obj = obj2;
        if (obj2 != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = loadValueFromGservices();
        if (obj != null)
        {
            return obj;
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (true) goto _L3; else goto _L7
_L7:
    }


    private class Factory
    {

        public final Uri contentProviderUri;
        public final String gservicesPrefix;
        public final String phenotypePrefix;
        public final boolean preferGservices;
        public final String sharedPrefsName;
        public final boolean skipGservices;

        public final Factory withGservicePrefix(String s)
        {
            if (skipGservices)
            {
                throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
            } else
            {
                return new Factory(sharedPrefsName, contentProviderUri, s, phenotypePrefix, skipGservices, preferGservices);
            }
        }

        public Factory(String s)
        {
            this(s, null, "", "", false, false);
        }

        public Factory(String s, Uri uri, String s1, String s2, boolean flag, boolean flag1)
        {
            sharedPrefsName = s;
            contentProviderUri = uri;
            gservicesPrefix = s1;
            phenotypePrefix = s2;
            skipGservices = flag;
            preferGservices = flag1;
        }
    }


    private class BinderAwareFunction
    {

        public abstract Object execute();
    }


    private class _cls1 extends PhenotypeFlag
    {

        private final Long fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            long l;
            try
            {
                l = sharedpreferences.getLong(mendelFlagName, 0L);
            }
            catch (ClassCastException classcastexception)
            {
                sharedpreferences = String.valueOf(mendelFlagName);
                if (sharedpreferences.length() != 0)
                {
                    sharedpreferences = "Invalid long value in SharedPreferences for ".concat(sharedpreferences);
                } else
                {
                    sharedpreferences = new String("Invalid long value in SharedPreferences for ");
                }
                Log.e("PhenotypeFlag", sharedpreferences, classcastexception);
                return null;
            }
            return Long.valueOf(l);
        }

        private final Long fromString(String s)
        {
            long l;
            try
            {
                l = Long.parseLong(s);
            }
            catch (NumberFormatException numberformatexception)
            {
                String s1 = mendelFlagName;
                Log.e("PhenotypeFlag", (new StringBuilder(String.valueOf(s1).length() + 25 + String.valueOf(s).length())).append("Invalid long value for ").append(s1).append(": ").append(s).toString());
                return null;
            }
            return Long.valueOf(l);
        }

        protected final volatile Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return fromSharedPreferences(sharedpreferences);
        }

        protected final volatile Object fromString(String s)
        {
            return fromString(s);
        }

        _cls1(Factory factory1, String s, Long long1)
        {
            super(factory1, s, long1);
        }
    }


    private class _cls6 extends PhenotypeFlag
    {

        private final String fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            try
            {
                sharedpreferences = sharedpreferences.getString(mendelFlagName, null);
            }
            catch (ClassCastException classcastexception)
            {
                sharedpreferences = String.valueOf(mendelFlagName);
                if (sharedpreferences.length() != 0)
                {
                    sharedpreferences = "Invalid string value in SharedPreferences for ".concat(sharedpreferences);
                } else
                {
                    sharedpreferences = new String("Invalid string value in SharedPreferences for ");
                }
                Log.e("PhenotypeFlag", sharedpreferences, classcastexception);
                return null;
            }
            return sharedpreferences;
        }

        protected final volatile Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return fromSharedPreferences(sharedpreferences);
        }

        protected final Object fromString(String s)
        {
            return s;
        }

        _cls6(Factory factory1, String s, String s1)
        {
            super(factory1, s, s1);
        }
    }


    private class _cls3 extends PhenotypeFlag
    {

        private final Boolean fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            boolean flag;
            try
            {
                flag = sharedpreferences.getBoolean(mendelFlagName, false);
            }
            catch (ClassCastException classcastexception)
            {
                sharedpreferences = String.valueOf(mendelFlagName);
                if (sharedpreferences.length() != 0)
                {
                    sharedpreferences = "Invalid boolean value in SharedPreferences for ".concat(sharedpreferences);
                } else
                {
                    sharedpreferences = new String("Invalid boolean value in SharedPreferences for ");
                }
                Log.e("PhenotypeFlag", sharedpreferences, classcastexception);
                return null;
            }
            return Boolean.valueOf(flag);
        }

        protected final volatile Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return fromSharedPreferences(sharedpreferences);
        }

        protected final Object fromString(String s)
        {
            if (Gservices.TRUE_PATTERN.matcher(s).matches())
            {
                return Boolean.valueOf(true);
            }
            if (Gservices.FALSE_PATTERN.matcher(s).matches())
            {
                return Boolean.valueOf(false);
            } else
            {
                String s1 = mendelFlagName;
                Log.e("PhenotypeFlag", (new StringBuilder(String.valueOf(s1).length() + 28 + String.valueOf(s).length())).append("Invalid boolean value for ").append(s1).append(": ").append(s).toString());
                return null;
            }
        }

        _cls3(Factory factory1, String s, Boolean boolean1)
        {
            super(factory1, s, boolean1);
        }
    }

}
