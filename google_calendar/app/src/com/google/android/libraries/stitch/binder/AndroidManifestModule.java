// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.binder;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.stitch.binder:
//            Module, Binder

public final class AndroidManifestModule
    implements Module
{

    private static final int BINDER_MODULE_META_DATA_PREFIX_LENGTH = 7;
    private static final int MODULE_META_DATA_PREFIX_LENGTH = 7;
    private final Module mModules[];

    public AndroidManifestModule(Context context, Set set)
    {
        Object obj;
        Object obj1;
        HashSet hashset;
        obj1 = context.getPackageName();
        Iterator iterator;
        try
        {
            obj = context.getPackageManager().getApplicationInfo(((String) (obj1)), 128);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Log.wtf(String.format("Could not find application info for package: %s", new Object[] {
                obj1
            }), context);
            obj = null;
        }
        if (obj == null)
        {
            context = null;
        } else
        {
            context = ((ApplicationInfo) (obj)).metaData;
        }
        obj1 = new ArrayList();
        hashset = new HashSet();
        if (context == null) goto _L2; else goto _L1
_L1:
        iterator = ((ApplicationInfo) (obj)).metaData.keySet().iterator();
_L4:
        if (iterator.hasNext())
        {
            obj = (String)iterator.next();
            if (((String) (obj)).startsWith("MODULE."))
            {
                ((List) (obj1)).add(instantiateModule(context.getString(((String) (obj)))));
            }
            if (((String) (obj)).startsWith("MODULE:"))
            {
                ((List) (obj1)).add(instantiateModule(((String) (obj)).substring(MODULE_META_DATA_PREFIX_LENGTH)));
            } else
            if (((String) (obj)).startsWith("module:"))
            {
                String s1 = ((String) (obj)).substring(BINDER_MODULE_META_DATA_PREFIX_LENGTH);
                hashset.add(s1);
                if (set == null || !set.contains(s1))
                {
                    String s = String.valueOf("gen_binder.");
                    String s2 = String.valueOf(s1.replace('.', '$'));
                    if (s2.length() != 0)
                    {
                        s = s.concat(s2);
                    } else
                    {
                        s = new String(s);
                    }
                    ((List) (obj1)).add(instantiateModule(s));
                    if (set != null && Log.isLoggable("Binder", 5))
                    {
                        s = String.valueOf(s1);
                        if (s.length() != 0)
                        {
                            s = "***WARNING*** Root module does not include ".concat(s);
                        } else
                        {
                            s = new String("***WARNING*** Root module does not include ");
                        }
                        Log.w("Binder", s);
                    }
                }
            }
            continue; /* Loop/switch isn't completed */
        }
_L2:
        mModules = (Module[])((List) (obj1)).toArray(new Module[((List) (obj1)).size()]);
        return;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static Module instantiateModule(String s)
    {
        Module module;
        try
        {
            module = (Module)Class.forName(s).newInstance();
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Failed to add stitch module ".concat(s);
            } else
            {
                s = new String("Failed to add stitch module ");
            }
            throw new RuntimeException(s, illegalaccessexception);
        }
        catch (InstantiationException instantiationexception)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Failed to add stitch module ".concat(s);
            } else
            {
                s = new String("Failed to add stitch module ");
            }
            throw new RuntimeException(s, instantiationexception);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Failed to add stitch module ".concat(s);
            } else
            {
                s = new String("Failed to add stitch module ");
            }
            throw new RuntimeException(s, classnotfoundexception);
        }
        return module;
    }

    public final void configure(Context context, Class class1, Binder binder)
    {
        for (int i = 0; i < mModules.length; i++)
        {
            mModules[i].configure(context, class1, binder);
        }

    }

}
