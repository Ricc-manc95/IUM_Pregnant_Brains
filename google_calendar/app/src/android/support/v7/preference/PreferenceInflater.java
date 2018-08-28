// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceGroup, PreferenceManager

final class PreferenceInflater
{

    private static final HashMap CONSTRUCTOR_MAP = new HashMap();
    private static final Class CONSTRUCTOR_SIGNATURE[] = {
        android/content/Context, android/util/AttributeSet
    };
    private final Object mConstructorArgs[] = new Object[2];
    private final Context mContext;
    private String mDefaultPackages[] = {
        (new StringBuilder()).append(android/support/v7/preference/Preference.getPackage().getName()).append(".").toString(), (new StringBuilder()).append(android/support/v14/preference/SwitchPreference.getPackage().getName()).append(".").toString()
    };
    private PreferenceManager mPreferenceManager;

    public PreferenceInflater(Context context, PreferenceManager preferencemanager)
    {
        mContext = context;
        mPreferenceManager = preferencemanager;
    }

    private final Preference createItem(String s, String as[], AttributeSet attributeset)
        throws ClassNotFoundException, InflateException
    {
        Object obj;
        Object obj1;
        obj1 = (Constructor)CONSTRUCTOR_MAP.get(s);
        obj = obj1;
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        ClassLoader classloader = mContext.getClassLoader();
        if (as == null) goto _L4; else goto _L3
_L3:
        if (as.length != 0) goto _L5; else goto _L4
_L4:
        obj1 = classloader.loadClass(s);
_L6:
        obj = ((Class) (obj1)).getConstructor(CONSTRUCTOR_SIGNATURE);
        ((Constructor) (obj)).setAccessible(true);
        CONSTRUCTOR_MAP.put(s, obj);
_L2:
        as = ((String []) (mConstructorArgs));
        as[1] = attributeset;
        return (Preference)((Constructor) (obj)).newInstance(as);
_L5:
        int j = as.length;
        int i;
        i = 0;
        obj = null;
_L7:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_254;
        }
        obj1 = as[i];
        obj1 = classloader.loadClass((new StringBuilder()).append(((String) (obj1))).append(s).toString());
        as = ((String []) (obj1));
_L8:
        obj1 = as;
        if (as == null)
        {
            if (obj == null)
            {
                try
                {
                    throw new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s).toString());
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    throw s;
                }
                // Misplaced declaration of an exception variable
                catch (String as[])
                {
                    s = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s).toString());
                }
                break MISSING_BLOCK_LABEL_246;
            }
            break MISSING_BLOCK_LABEL_206;
        }
          goto _L6
        obj;
        i++;
          goto _L7
        throw obj;
        s.initCause(as);
        throw s;
        as = null;
          goto _L8
    }

    private final Preference createItemFromTag(String s, AttributeSet attributeset)
    {
        Preference preference;
        try
        {
            if (-1 == s.indexOf('.'))
            {
                return createItem(s, mDefaultPackages, attributeset);
            }
            preference = createItem(s, null, attributeset);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw s;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            s = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class (not found)").append(s).toString());
            s.initCause(classnotfoundexception);
            throw s;
        }
        catch (Exception exception)
        {
            s = new InflateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error inflating class ").append(s).toString());
            s.initCause(exception);
            throw s;
        }
        return preference;
    }

    private final Preference inflate(XmlPullParser xmlpullparser, PreferenceGroup preferencegroup)
    {
        Object aobj[] = mConstructorArgs;
        aobj;
        JVM INSTR monitorenter ;
        AttributeSet attributeset;
        attributeset = Xml.asAttributeSet(xmlpullparser);
        mConstructorArgs[0] = mContext;
        int i;
        do
        {
            i = xmlpullparser.next();
        } while (i != 2 && i != 1);
        if (i == 2)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        throw new InflateException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": No start tag found!").toString());
        xmlpullparser;
        throw xmlpullparser;
        xmlpullparser;
        aobj;
        JVM INSTR monitorexit ;
        throw xmlpullparser;
        PreferenceGroup preferencegroup1 = (PreferenceGroup)createItemFromTag(xmlpullparser.getName(), attributeset);
        if (preferencegroup != null)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        preferencegroup1.onAttachedToHierarchy(mPreferenceManager);
        preferencegroup = preferencegroup1;
        rInflate(xmlpullparser, preferencegroup, attributeset);
        aobj;
        JVM INSTR monitorexit ;
        return preferencegroup;
        xmlpullparser;
        preferencegroup = new InflateException(xmlpullparser.getMessage());
        preferencegroup.initCause(xmlpullparser);
        throw preferencegroup;
        preferencegroup;
        xmlpullparser = new InflateException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": ").append(preferencegroup.getMessage()).toString());
        xmlpullparser.initCause(preferencegroup);
        throw xmlpullparser;
    }

    private final void rInflate(XmlPullParser xmlpullparser, Preference preference, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
_L5:
        Object obj;
        int j = xmlpullparser.next();
        if (j == 3 && xmlpullparser.getDepth() <= i || j == 1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = xmlpullparser.getName();
        if ("intent".equals(obj))
        {
            try
            {
                obj = Intent.parseIntent(mContext.getResources(), xmlpullparser, attributeset);
            }
            // Misplaced declaration of an exception variable
            catch (XmlPullParser xmlpullparser)
            {
                preference = new XmlPullParserException("Error parsing preference");
                preference.initCause(xmlpullparser);
                throw preference;
            }
            preference.mIntent = ((Intent) (obj));
            continue; /* Loop/switch isn't completed */
        }
        if (!"extra".equals(obj))
        {
            break MISSING_BLOCK_LABEL_219;
        }
        obj = mContext.getResources();
        if (preference.mExtras == null)
        {
            preference.mExtras = new Bundle();
        }
        ((Resources) (obj)).parseBundleExtra("extra", attributeset, preference.mExtras);
        int k = xmlpullparser.getDepth();
_L2:
        int l = xmlpullparser.next();
        if (l == 1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (l != 3) goto _L2; else goto _L1
_L1:
        try
        {
            l = xmlpullparser.getDepth();
        }
        // Misplaced declaration of an exception variable
        catch (XmlPullParser xmlpullparser)
        {
            preference = new XmlPullParserException("Error parsing preference");
            preference.initCause(xmlpullparser);
            throw preference;
        }
        if (l > k) goto _L2; else goto _L3
_L3:
        continue; /* Loop/switch isn't completed */
        obj = createItemFromTag(((String) (obj)), attributeset);
        ((PreferenceGroup)preference).addPreference(((Preference) (obj)));
        rInflate(xmlpullparser, ((Preference) (obj)), attributeset);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final Preference inflate(int i, PreferenceGroup preferencegroup)
    {
        XmlResourceParser xmlresourceparser = mContext.getResources().getXml(i);
        preferencegroup = inflate(((XmlPullParser) (xmlresourceparser)), preferencegroup);
        xmlresourceparser.close();
        return preferencegroup;
        preferencegroup;
        xmlresourceparser.close();
        throw preferencegroup;
    }

}
