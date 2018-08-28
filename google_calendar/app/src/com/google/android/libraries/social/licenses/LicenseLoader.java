// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            Licenses

final class LicenseLoader extends AsyncTaskLoader
{

    private List extraPluginPaths;
    private List licenses;

    LicenseLoader(Context context)
    {
        super(context.getApplicationContext());
    }

    LicenseLoader(Context context, List list)
    {
        this(context);
        extraPluginPaths = list;
    }

    public final void deliverResult(Object obj)
    {
        obj = (List)obj;
        licenses = ((List) (obj));
        super.deliverResult(obj);
    }

    public final Object loadInBackground()
    {
        TreeSet treeset = new TreeSet();
        treeset.addAll(Licenses.getLicenseListFromMetadata(Licenses.getTextFromResource(super.mContext.getApplicationContext(), "third_party_license_metadata", 0L, -1), ""));
        if (extraPluginPaths != null)
        {
            Iterator iterator = extraPluginPaths.iterator();
            while (iterator.hasNext()) 
            {
                Object obj = (String)iterator.next();
                String s = Licenses.getTextFromJar("res/raw/third_party_license_metadata", ((String) (obj)), 0L, -1);
                if (s != null)
                {
                    obj = Licenses.getLicenseListFromMetadata(s, ((String) (obj)));
                } else
                {
                    obj = new ArrayList();
                }
                treeset.addAll(((java.util.Collection) (obj)));
            }
        }
        return Collections.unmodifiableList(new ArrayList(treeset));
    }

    protected final void onStartLoading()
    {
        if (licenses != null)
        {
            List list = licenses;
            licenses = list;
            super.deliverResult(list);
            return;
        } else
        {
            onForceLoad();
            return;
        }
    }

    protected final void onStopLoading()
    {
        onCancelLoad();
    }

    static 
    {
        com/google/android/libraries/social/licenses/LicenseLoader.getCanonicalName();
    }
}
