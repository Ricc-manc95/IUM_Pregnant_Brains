// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.image.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.URLSpan;
import com.google.android.calendar.api.event.smartmail.SmartMailImage;
import com.google.android.calendar.event.image.helper.ImageHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.newapi.image.helper:
//            SmartMailHeaderAttributeImageHelper, SmartMailLicense

final class arg._cls1
    implements Callable
{

    private final SmartMailHeaderAttributeImageHelper arg$1;

    public final Object call()
    {
        Object obj;
        Object obj1;
        Object obj2;
        Iterator iterator = null;
        obj1 = arg$1;
        obj = ((ImageHelper) (obj1)).context.getResources().getConfiguration().locale;
        Context context;
        SmartMailLicense smartmaillicense;
        if (obj != null)
        {
            obj = ((Locale) (obj)).getLanguage();
        } else
        {
            obj = "en";
        }
        smartmaillicense = SmartMailLicense.loadLicense(((SmartMailHeaderAttributeImageHelper) (obj1)).image.imageMetadataUrl, ((String) (obj)));
        context = ((ImageHelper) (obj1)).context;
        if (smartmaillicense == null)
        {
            return null;
        }
        Object obj3 = new ArrayList();
        Set set = smartmaillicense.getRequirements();
        if (set.contains(Integer.valueOf(1)))
        {
            int i;
            if (!TextUtils.isEmpty(smartmaillicense.getName()) && smartmaillicense.getName().startsWith("CC-BY"))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                obj = smartmaillicense.getName();
                String s1 = smartmaillicense.getUri();
                String s = smartmaillicense.getLicenseAttribution();
                obj2 = smartmaillicense.getAuthorField("uri");
                obj1 = obj2;
                if (TextUtils.isEmpty(((CharSequence) (obj2))))
                {
                    obj1 = smartmaillicense.getReferrerUrl();
                }
                obj2 = new ArrayList();
                if (!TextUtils.isEmpty(((CharSequence) (obj))) && !TextUtils.isEmpty(s1))
                {
                    ((List) (obj2)).add(new arg._cls1(((String) (obj)), s1));
                }
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(((CharSequence) (obj1))))
                {
                    if (!TextUtils.isEmpty(s1))
                    {
                        if (TextUtils.isEmpty(((CharSequence) (obj))))
                        {
                            obj = String.format(" (%s)", new Object[] {
                                s1
                            });
                        } else
                        {
                            obj = String.format(" [%s (%s)]", new Object[] {
                                obj, s1
                            });
                        }
                    }
                    if (TextUtils.isEmpty(((CharSequence) (obj))) || !s.contains(((CharSequence) (obj))))
                    {
                        obj = null;
                    } else
                    {
                        obj = s.replace(((CharSequence) (obj)), "");
                    }
                    if (!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        ((List) (obj2)).add(new arg._cls1(((String) (obj)), ((String) (obj1))));
                    }
                }
                ((List) (obj3)).addAll(((java.util.Collection) (obj2)));
            }
        }
        if (!((List) (obj3)).isEmpty() || !set.contains(Integer.valueOf(0)) && !set.contains(Integer.valueOf(2))) goto _L2; else goto _L1
_L1:
        obj1 = smartmaillicense.getAuthorField("author");
        obj2 = smartmaillicense.getAuthorField("uri");
        obj = iterator;
        if (TextUtils.isEmpty(((CharSequence) (obj1)))) goto _L4; else goto _L3
_L3:
        if (!TextUtils.isEmpty(((CharSequence) (obj2)))) goto _L6; else goto _L5
_L5:
        obj = iterator;
_L4:
        if (obj != null)
        {
            ((List) (obj3)).add(obj);
        }
_L2:
        obj = new SpannableStringBuilder();
        obj1 = context.getResources().getString(0x7f130320);
        iterator = ((List) (obj3)).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            obj2 = (arg._cls1)iterator.next();
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                ((SpannableStringBuilder) (obj)).append(((CharSequence) (obj1)));
            }
            i = ((SpannableStringBuilder) (obj)).length();
            ((SpannableStringBuilder) (obj)).append(((arg._cls1) (obj2)).ext);
            obj3 = ((ext) (obj2)).ext;
            if (!TextUtils.isEmpty(((CharSequence) (obj3))))
            {
                ((SpannableStringBuilder) (obj)).setSpan(new URLSpan(((String) (obj3))), i, ((ext) (obj2)).ext.length() + i, 33);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_625;
_L6:
        if (!((String) (obj1)).contains("\251"))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = obj1;
_L8:
        obj = new ext(((String) (obj)), ((String) (obj2)));
        if (true) goto _L4; else goto _L7
_L7:
        obj = String.valueOf("\251 ");
        obj1 = String.valueOf(obj1);
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
          goto _L8
        if (true) goto _L4; else goto _L9
_L9:
        return SmartMailHeaderAttributeImageHelper.removeUnderlines(((android.text.Spannable) (obj)));
    }

    (SmartMailHeaderAttributeImageHelper smartmailheaderattributeimagehelper)
    {
        arg$1 = smartmailheaderattributeimagehelper;
    }
}
