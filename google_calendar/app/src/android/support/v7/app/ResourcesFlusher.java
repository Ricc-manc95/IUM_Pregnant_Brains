// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.res.Resources;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

final class ResourcesFlusher
{

    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    static void flushLollipops(Resources resources)
    {
        if (!sDrawableCacheFieldFetched)
        {
            try
            {
                Field field = android/content/res/Resources.getDeclaredField("mDrawableCache");
                sDrawableCacheField = field;
                field.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", nosuchfieldexception);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null)
        {
            try
            {
                resources = (Map)sDrawableCacheField.get(resources);
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources)
            {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", resources);
                resources = null;
            }
            if (resources != null)
            {
                resources.clear();
            }
        }
    }

    static void flushMarshmallows(Resources resources)
    {
        if (!sDrawableCacheFieldFetched)
        {
            try
            {
                Field field = android/content/res/Resources.getDeclaredField("mDrawableCache");
                sDrawableCacheField = field;
                field.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", nosuchfieldexception);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField == null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        resources = ((Resources) (sDrawableCacheField.get(resources)));
_L1:
        if (resources == null)
        {
            return;
        } else
        {
            flushThemedResourcesCache(resources);
            return;
        }
        resources;
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", resources);
        resources = null;
          goto _L1
    }

    static void flushNougats(Resources resources)
    {
        if (!sResourcesImplFieldFetched)
        {
            try
            {
                Field field = android/content/res/Resources.getDeclaredField("mResourcesImpl");
                sResourcesImplField = field;
                field.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", nosuchfieldexception);
            }
            sResourcesImplFieldFetched = true;
        }
        if (sResourcesImplField != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        try
        {
            resources = ((Resources) (sResourcesImplField.get(resources)));
        }
        // Misplaced declaration of an exception variable
        catch (Resources resources)
        {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", resources);
            resources = null;
        }
        if (resources == null) goto _L1; else goto _L3
_L3:
        if (!sDrawableCacheFieldFetched)
        {
            try
            {
                Field field1 = resources.getClass().getDeclaredField("mDrawableCache");
                sDrawableCacheField = field1;
                field1.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception1)
            {
                Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", nosuchfieldexception1);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField == null)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        resources = ((Resources) (sDrawableCacheField.get(resources)));
_L4:
        if (resources != null)
        {
            flushThemedResourcesCache(resources);
            return;
        }
          goto _L1
        resources;
        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", resources);
        resources = null;
          goto _L4
    }

    private static void flushThemedResourcesCache(Object obj)
    {
        if (!sThemedResourceCacheClazzFetched)
        {
            try
            {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", classnotfoundexception);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        if (sThemedResourceCacheClazz != null)
        {
            if (!sThemedResourceCache_mUnthemedEntriesFieldFetched)
            {
                try
                {
                    Field field = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                    sThemedResourceCache_mUnthemedEntriesField = field;
                    field.setAccessible(true);
                }
                catch (NoSuchFieldException nosuchfieldexception)
                {
                    Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", nosuchfieldexception);
                }
                sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
            }
            if (sThemedResourceCache_mUnthemedEntriesField != null)
            {
                try
                {
                    obj = (LongSparseArray)sThemedResourceCache_mUnthemedEntriesField.get(obj);
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", ((Throwable) (obj)));
                    obj = null;
                }
                if (obj != null)
                {
                    ((LongSparseArray) (obj)).clear();
                    return;
                }
            }
        }
    }
}
