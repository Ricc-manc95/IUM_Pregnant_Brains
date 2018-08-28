// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.dynamic.zze;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

// Referenced classes of package com.google.android.gms.dynamite:
//            zzb, zza

public final class DynamiteModule
{
    public static class DynamiteLoaderClassLoader
    {

        public static ClassLoader sClassLoader;

        public DynamiteLoaderClassLoader()
        {
        }
    }

    public static final class zza extends Exception
    {

        zza(String s)
        {
            super(s);
        }

        zza(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }

    public static interface zzb
    {

        public abstract zzb zza(Context context, String s, zza zza1)
            throws zza;
    }


    private static com.google.android.gms.dynamite.zza zzbgD;
    private static final HashMap zzbgE = new HashMap();
    private static String zzbgF;
    private static final zzb.zza zzbgG = new _cls1();
    private static final zzb.zza zzbgH = new _cls2();
    public static final zzb zzbgM = new _cls7();
    private final Context zzbgN;

    private DynamiteModule(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzbgN = (Context)context;
            return;
        }
    }

    public static int zzB(Context context, String s)
    {
        Object obj;
        context = context.getApplicationContext().getClassLoader();
        obj = String.valueOf("com.google.android.gms.dynamite.descriptors.");
        String s1 = String.valueOf("ModuleDescriptor");
        obj = context.loadClass((new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(s).length() + String.valueOf(s1).length())).append(((String) (obj))).append(s).append(".").append(s1).toString());
        context = ((Class) (obj)).getDeclaredField("MODULE_ID");
        obj = ((Class) (obj)).getDeclaredField("MODULE_VERSION");
        if (context.get(null).equals(s))
        {
            break MISSING_BLOCK_LABEL_171;
        }
        context = String.valueOf(context.get(null));
        Log.e("DynamiteModule", (new StringBuilder(String.valueOf(context).length() + 51 + String.valueOf(s).length())).append("Module descriptor id '").append(context).append("' didn't match expected id '").append(s).append("'").toString());
        return 0;
        int i = ((Field) (obj)).getInt(null);
        return i;
        context;
        Log.w("DynamiteModule", (new StringBuilder(String.valueOf(s).length() + 45)).append("Local module descriptor class for ").append(s).append(" not found.").toString());
_L2:
        return 0;
        context;
        context = String.valueOf(context.getMessage());
        if (context.length() != 0)
        {
            context = "Failed to load module descriptor class: ".concat(context);
        } else
        {
            context = new String("Failed to load module descriptor class: ");
        }
        Log.e("DynamiteModule", context);
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static DynamiteModule zzD(Context context, String s)
    {
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            "Selected local version of ".concat(s);
        } else
        {
            new String("Selected local version of ");
        }
        return new DynamiteModule(context.getApplicationContext());
    }

    private static ClassLoader zzE(Context context, String s)
        throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException
    {
        com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader;
        JVM INSTR monitorenter ;
        if (DynamiteLoaderClassLoader.sClassLoader == null)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        context = DynamiteLoaderClassLoader.sClassLoader;
        com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader;
        JVM INSTR monitorexit ;
        return context;
        context = context.getApplicationContext().getClassLoader().loadClass(com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader.getName());
        Field field = context.getDeclaredField("sClassLoader");
        context;
        JVM INSTR monitorenter ;
        ClassLoader classloader;
        classloader = (ClassLoader)field.get(null);
        DynamiteLoaderClassLoader.sClassLoader = classloader;
        if (classloader == null)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        s = DynamiteLoaderClassLoader.sClassLoader;
        context;
        JVM INSTR monitorexit ;
        com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader;
        JVM INSTR monitorexit ;
        return s;
        context;
        com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader;
        JVM INSTR monitorexit ;
        throw context;
        DynamiteLoaderClassLoader.sClassLoader = new _cls9(s, ClassLoader.getSystemClassLoader());
        field.set(null, DynamiteLoaderClassLoader.sClassLoader);
        s = DynamiteLoaderClassLoader.sClassLoader;
        context;
        JVM INSTR monitorexit ;
        com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader;
        JVM INSTR monitorexit ;
        return s;
        s;
        context;
        JVM INSTR monitorexit ;
        throw s;
    }

    private static Context zza(Context context, String s, byte abyte0[], String s1)
    {
        if (s1 == null || s1.isEmpty())
        {
            Log.e("DynamiteModule", "No valid DynamiteLoader APK path");
            return null;
        }
        android.os.IInterface iinterface;
        try
        {
            s1 = (IBinder)zzE(context, s1).loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            context = String.valueOf(context.toString());
            if (context.length() != 0)
            {
                context = "Failed to load DynamiteLoader: ".concat(context);
            } else
            {
                context = new String("Failed to load DynamiteLoader: ");
            }
            Log.e("DynamiteModule", context);
            return null;
        }
        if (s1 != null) goto _L2; else goto _L1
_L1:
        s1 = null;
_L3:
        return (Context)zze.zzH(s1.zza(new zze(context), s, abyte0));
_L2:
        iinterface = s1.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
        if (iinterface == null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        if (!(iinterface instanceof com.google.android.gms.dynamite.zzb))
        {
            break MISSING_BLOCK_LABEL_110;
        }
        s1 = (com.google.android.gms.dynamite.zzb)iinterface;
          goto _L3
        s1 = new zzb.zza.zza(s1);
          goto _L3
    }

    public static DynamiteModule zza(Context context, zzb zzb1, String s)
        throws zza
    {
        if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName()))
        {
            return zza(context, zzb1, s, zzbgG);
        }
        DynamiteModule dynamitemodule;
        try
        {
            dynamitemodule = zza(context, zzb1, s, zzbgH);
        }
        catch (zza zza1)
        {
            String s1 = String.valueOf(zza1.toString());
            if (s1.length() != 0)
            {
                s1 = "Failed to load module via fast route".concat(s1);
            } else
            {
                s1 = new String("Failed to load module via fast route");
            }
            Log.w("DynamiteModule", s1);
            return zza(context, zzb1, s, zzbgG);
        }
        return dynamitemodule;
    }

    private static DynamiteModule zza(Context context, zzb zzb1, String s, zzb.zza zza1)
        throws zza
    {
        zzb.zzb zzb2 = zzb1.zza(context, s, zza1);
        class zzb.zzb
        {

            public int zzbgP;
            public int zzbgQ;
            public int zzbgR;

            public zzb.zzb()
            {
                zzbgP = 0;
                zzbgQ = 0;
                zzbgR = 0;
            }
        }

        int i = zzb2.zzbgP;
        int l = zzb2.zzbgQ;
        (new StringBuilder(String.valueOf(s).length() + 68 + String.valueOf(s).length())).append("Considering local module ").append(s).append(":").append(i).append(" and remote module ").append(s).append(":").append(l);
        if (zzb2.zzbgR == 0 || zzb2.zzbgR == -1 && zzb2.zzbgP == 0 || zzb2.zzbgR == 1 && zzb2.zzbgQ == 0)
        {
            int j = zzb2.zzbgP;
            int i1 = zzb2.zzbgQ;
            throw new zza((new StringBuilder(91)).append("No acceptable module found. Local version is ").append(j).append(" and remote version is ").append(i1).append(".").toString());
        }
        if (zzb2.zzbgR == -1)
        {
            return zzD(context, s);
        }
        if (zzb2.zzbgR == 1)
        {
            class zzb.zza
            {

                public abstract int zzB(Context context1, String s1);

                public abstract DynamiteModule zza(Context context1, String s1, int j1)
                    throws zza;

                public abstract int zzb(Context context1, String s1, boolean flag)
                    throws zza;
            }

            try
            {
                zza1 = zza1.zza(context, s, zzb2.zzbgQ);
            }
            catch (zza zza2)
            {
                zza1 = String.valueOf(zza2.getMessage());
                if (zza1.length() != 0)
                {
                    zza1 = "Failed to load remote module: ".concat(zza1);
                } else
                {
                    zza1 = new String("Failed to load remote module: ");
                }
                Log.w("DynamiteModule", zza1);
                if (zzb2.zzbgP != 0 && zzb1.zza(context, s, new _cls8(zzb2.zzbgP)).zzbgR == -1)
                {
                    return zzD(context, s);
                } else
                {
                    throw new zza("Remote load failed. No local fallback found.", zza2);
                }
            }
            return zza1;
        } else
        {
            int k = zzb2.zzbgR;
            throw new zza((new StringBuilder(47)).append("VersionPolicy returned invalid code:").append(k).toString());
        }
    }

    private static DynamiteModule zza(Context context, String s, int i)
        throws zza
    {
        (new StringBuilder(String.valueOf(s).length() + 51)).append("Selected remote version of ").append(s).append(", version >= ").append(i);
        com.google.android.gms.dynamite.zza zza1 = zzaX(context);
        if (zza1 == null)
        {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try
        {
            context = zza1.zza(new zze(context), s, i);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new zza("Failed to load remote module.", context);
        }
        if (zze.zzH(context) == null)
        {
            throw new zza("Failed to load remote module.");
        } else
        {
            return new DynamiteModule((Context)zze.zzH(context));
        }
    }

    private static com.google.android.gms.dynamite.zza zzaX(Context context)
    {
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorenter ;
        if (zzbgD == null)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        context = zzbgD;
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        return context;
        if (GoogleApiAvailabilityLight.zzaIo.isGooglePlayServicesAvailable(context) == 0)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        return null;
        context = (IBinder)context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
        if (context != null) goto _L2; else goto _L1
_L1:
        context = null;
_L5:
        if (context == null) goto _L4; else goto _L3
_L3:
        zzbgD = context;
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        return context;
        context;
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        throw context;
_L2:
        android.os.IInterface iinterface = context.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
        if (iinterface == null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        if (!(iinterface instanceof com.google.android.gms.dynamite.zza))
        {
            break MISSING_BLOCK_LABEL_111;
        }
        context = (com.google.android.gms.dynamite.zza)iinterface;
          goto _L5
        context = new zza.zza.zza(context);
          goto _L5
        context;
        context = String.valueOf(context.getMessage());
        if (context.length() == 0)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        context = "Failed to load IDynamiteLoader from GmsCore: ".concat(context);
_L6:
        Log.e("DynamiteModule", context);
_L4:
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        return null;
        context = new String("Failed to load IDynamiteLoader from GmsCore: ");
          goto _L6
    }

    public static int zzb(Context context, String s, boolean flag)
    {
        com.google.android.gms.dynamite.zza zza1 = zzaX(context);
        if (zza1 == null)
        {
            return 0;
        }
        int i;
        try
        {
            i = zza1.zza(new zze(context), s, flag);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            context = String.valueOf(context.getMessage());
            if (context.length() != 0)
            {
                context = "Failed to retrieve remote module version: ".concat(context);
            } else
            {
                context = new String("Failed to retrieve remote module version: ");
            }
            Log.w("DynamiteModule", context);
            return 0;
        }
        return i;
    }

    private static DynamiteModule zzb(Context context, String s, int i)
        throws zza
    {
        (new StringBuilder(String.valueOf(s).length() + 51)).append("Selected remote version of ").append(s).append(", version >= ").append(i);
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorenter ;
        byte abyte0[];
        String s1;
        abyte0 = (byte[])zzbgE.get((new StringBuilder(String.valueOf(s).length() + 12)).append(s).append(":").append(i).toString());
        s1 = zzbgF;
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        if (abyte0 == null)
        {
            throw new zza("Module implementation could not be found.");
        }
        break MISSING_BLOCK_LABEL_114;
        context;
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        throw context;
        context = zza(context.getApplicationContext(), s, abyte0, s1);
        if (context == null)
        {
            throw new zza("Failed to get module context");
        } else
        {
            return new DynamiteModule(context);
        }
    }

    public static int zzc(Context context, String s, boolean flag)
        throws zza
    {
        Object obj;
        Object obj1;
        obj1 = null;
        String s1;
        if (flag)
        {
            obj = "api_force_staging";
        } else
        {
            obj = "api";
        }
        s1 = String.valueOf("content://com.google.android.gms.chimera/");
        obj = Uri.parse((new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(obj).length() + String.valueOf(s).length())).append(s1).append(((String) (obj))).append("/").append(s).toString());
        if (context == null) goto _L2; else goto _L1
_L1:
        context = context.getContentResolver();
        if (context != null) goto _L3; else goto _L2
_L2:
        try
        {
            throw new zza("Failed to get dynamite module ContentResolver.");
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            obj = null;
        }
        finally
        {
            s = obj1;
        }
_L7:
        context = ((Context) (obj));
        if (!(s instanceof zza))
        {
            break MISSING_BLOCK_LABEL_301;
        }
        context = ((Context) (obj));
        throw s;
_L3:
        obj = context.query(((Uri) (obj)), null, null, null, null);
        if (obj == null) goto _L5; else goto _L4
_L4:
        context = ((Context) (obj));
        if (((Cursor) (obj)).moveToFirst()) goto _L6; else goto _L5
_L5:
        context = ((Context) (obj));
        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
        context = ((Context) (obj));
        int i;
        try
        {
            throw new zza("Failed to connect to dynamite module ContentResolver.");
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        finally
        {
            s = context;
            context = ((Context) (obj));
            if (s != null)
            {
                s.close();
            }
            throw context;
        }
        if (true) goto _L7; else goto _L6
_L6:
        context = ((Context) (obj));
        i = ((Cursor) (obj)).getInt(0);
        if (i <= 0) goto _L9; else goto _L8
_L8:
        context = ((Context) (obj));
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorenter ;
        context = Base64.decode(((Cursor) (obj)).getString(3), 0);
        zzbgE.put((new StringBuilder(String.valueOf(s).length() + 12)).append(s).append(":").append(i).toString(), context);
        zzbgF = ((Cursor) (obj)).getString(2);
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
_L9:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return i;
        s;
        com/google/android/gms/dynamite/DynamiteModule;
        JVM INSTR monitorexit ;
        context = ((Context) (obj));
        throw s;
        context = ((Context) (obj));
        throw new zza("V2 version check failed", s);
    }

    static DynamiteModule zzc(Context context, String s, int i)
        throws zza
    {
        return zza(context, s, i);
    }

    static DynamiteModule zzd(Context context, String s, int i)
        throws zza
    {
        return zzb(context, s, i);
    }

    public final IBinder zzdJ(String s)
        throws zza
    {
        IBinder ibinder = (IBinder)zzbgN.getClassLoader().loadClass(s).newInstance();
        return ibinder;
        Object obj;
        obj;
_L2:
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "Failed to instantiate module class: ".concat(s);
        } else
        {
            s = new String("Failed to instantiate module class: ");
        }
        throw new zza(s, ((Throwable) (obj)));
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        new _cls3();
        new _cls4();
        new _cls5();
        new _cls6();
    }

    private class _cls9 extends PathClassLoader
    {

        protected final Class loadClass(String s, boolean flag)
            throws ClassNotFoundException
        {
            if (s.startsWith("java.") || s.startsWith("android."))
            {
                break MISSING_BLOCK_LABEL_27;
            }
            Class class1 = findClass(s);
            return class1;
            ClassNotFoundException classnotfoundexception;
            classnotfoundexception;
            return super.loadClass(s, flag);
        }

        _cls9(String s, ClassLoader classloader)
        {
            super(s, classloader);
        }
    }


    private class _cls8
        implements zzb.zza
    {

        private final int zzbgO;

        public final int zzB(Context context, String s)
        {
            return zzbgO;
        }

        public final DynamiteModule zza(Context context, String s, int i)
            throws zza
        {
            throw new zza("local only VersionPolicy should not load from remote");
        }

        public final int zzb(Context context, String s, boolean flag)
        {
            return 0;
        }

        _cls8(int i)
        {
            zzbgO = i;
            super();
        }
    }


    private class _cls1
        implements zzb.zza
    {

        public final int zzB(Context context, String s)
        {
            return DynamiteModule.zzB(context, s);
        }

        public final DynamiteModule zza(Context context, String s, int i)
            throws zza
        {
            return DynamiteModule.zzc(context, s, i);
        }

        public final int zzb(Context context, String s, boolean flag)
            throws zza
        {
            return DynamiteModule.zzb(context, s, flag);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements zzb.zza
    {

        public final int zzB(Context context, String s)
        {
            return DynamiteModule.zzB(context, s);
        }

        public final DynamiteModule zza(Context context, String s, int i)
            throws zza
        {
            return DynamiteModule.zzd(context, s, i);
        }

        public final int zzb(Context context, String s, boolean flag)
            throws zza
        {
            return DynamiteModule.zzc(context, s, flag);
        }

        _cls2()
        {
        }
    }


    private class _cls3
        implements zzb
    {

        public final zzb.zzb zza(Context context, String s, zzb.zza zza1)
            throws zza
        {
            zzb.zzb zzb1 = new zzb.zzb();
            zzb1.zzbgQ = zza1.zzb(context, s, true);
            if (zzb1.zzbgQ != 0)
            {
                zzb1.zzbgR = 1;
            } else
            {
                zzb1.zzbgP = zza1.zzB(context, s);
                if (zzb1.zzbgP != 0)
                {
                    zzb1.zzbgR = -1;
                    return zzb1;
                }
            }
            return zzb1;
        }

        _cls3()
        {
        }
    }


    private class _cls4
        implements zzb
    {

        public final zzb.zzb zza(Context context, String s, zzb.zza zza1)
            throws zza
        {
            zzb.zzb zzb1 = new zzb.zzb();
            zzb1.zzbgP = zza1.zzB(context, s);
            if (zzb1.zzbgP != 0)
            {
                zzb1.zzbgR = -1;
            } else
            {
                zzb1.zzbgQ = zza1.zzb(context, s, true);
                if (zzb1.zzbgQ != 0)
                {
                    zzb1.zzbgR = 1;
                    return zzb1;
                }
            }
            return zzb1;
        }

        _cls4()
        {
        }
    }


    private class _cls5
        implements zzb
    {

        public final zzb.zzb zza(Context context, String s, zzb.zza zza1)
            throws zza
        {
            zzb.zzb zzb1 = new zzb.zzb();
            zzb1.zzbgP = zza1.zzB(context, s);
            zzb1.zzbgQ = zza1.zzb(context, s, true);
            if (zzb1.zzbgP == 0 && zzb1.zzbgQ == 0)
            {
                zzb1.zzbgR = 0;
                return zzb1;
            }
            if (zzb1.zzbgP >= zzb1.zzbgQ)
            {
                zzb1.zzbgR = -1;
                return zzb1;
            } else
            {
                zzb1.zzbgR = 1;
                return zzb1;
            }
        }

        _cls5()
        {
        }
    }


    private class _cls6
        implements zzb
    {

        public final zzb.zzb zza(Context context, String s, zzb.zza zza1)
            throws zza
        {
            zzb.zzb zzb1 = new zzb.zzb();
            zzb1.zzbgP = zza1.zzB(context, s);
            zzb1.zzbgQ = zza1.zzb(context, s, true);
            if (zzb1.zzbgP == 0 && zzb1.zzbgQ == 0)
            {
                zzb1.zzbgR = 0;
                return zzb1;
            }
            if (zzb1.zzbgQ >= zzb1.zzbgP)
            {
                zzb1.zzbgR = 1;
                return zzb1;
            } else
            {
                zzb1.zzbgR = -1;
                return zzb1;
            }
        }

        _cls6()
        {
        }
    }


    private class _cls7
        implements zzb
    {

        public final zzb.zzb zza(Context context, String s, zzb.zza zza1)
            throws zza
        {
            zzb.zzb zzb1 = new zzb.zzb();
            zzb1.zzbgP = zza1.zzB(context, s);
            if (zzb1.zzbgP != 0)
            {
                zzb1.zzbgQ = zza1.zzb(context, s, false);
            } else
            {
                zzb1.zzbgQ = zza1.zzb(context, s, true);
            }
            if (zzb1.zzbgP == 0 && zzb1.zzbgQ == 0)
            {
                zzb1.zzbgR = 0;
                return zzb1;
            }
            if (zzb1.zzbgQ >= zzb1.zzbgP)
            {
                zzb1.zzbgR = 1;
                return zzb1;
            } else
            {
                zzb1.zzbgR = -1;
                return zzb1;
            }
        }

        _cls7()
        {
        }
    }

}
