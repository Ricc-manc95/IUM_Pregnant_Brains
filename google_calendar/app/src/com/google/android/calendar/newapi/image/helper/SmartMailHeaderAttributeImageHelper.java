// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.image.helper;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.event.smartmail.SmartMailImage;
import com.google.android.calendar.event.image.EventImageRequestKey;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.image.helper:
//            HeaderAttributeImageHelper

public final class SmartMailHeaderAttributeImageHelper extends HeaderAttributeImageHelper
{

    public final SmartMailImage image;

    public SmartMailHeaderAttributeImageHelper(Context context, TimelineItem timelineitem, SmartMailImage smartmailimage, FutureCallback futurecallback)
    {
        super(context, timelineitem, futurecallback);
        image = smartmailimage;
    }

    protected final ListenableFuture loadAttributeLicenseAsync(Context context, EventImageRequestKey eventimagerequestkey)
    {
        class .Lambda._cls0
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
                Context context1;
                SmartMailLicense smartmaillicense;
                if (obj != null)
                {
                    obj = ((Locale) (obj)).getLanguage();
                } else
                {
                    obj = "en";
                }
                smartmaillicense = SmartMailLicense.loadLicense(((SmartMailHeaderAttributeImageHelper) (obj1)).image.imageMetadataUrl, ((String) (obj)));
                context1 = ((ImageHelper) (obj1)).context;
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
                            ((List) (obj2)).add(new AttributedImageHelper.AttributionSpan(((String) (obj)), s1));
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
                                ((List) (obj2)).add(new AttributedImageHelper.AttributionSpan(((String) (obj)), ((String) (obj1))));
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
                obj1 = context1.getResources().getString(0x7f130320);
                iterator = ((List) (obj3)).iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    obj2 = (AttributedImageHelper.AttributionSpan)iterator.next();
                    if (!TextUtils.isEmpty(((CharSequence) (obj))))
                    {
                        ((SpannableStringBuilder) (obj)).append(((CharSequence) (obj1)));
                    }
                    i = ((SpannableStringBuilder) (obj)).length();
                    ((SpannableStringBuilder) (obj)).append(((AttributedImageHelper.AttributionSpan) (obj2)).displayText);
                    obj3 = ((AttributedImageHelper.AttributionSpan) (obj2)).url;
                    if (!TextUtils.isEmpty(((CharSequence) (obj3))))
                    {
                        ((SpannableStringBuilder) (obj)).setSpan(new URLSpan(((String) (obj3))), i, ((AttributedImageHelper.AttributionSpan) (obj2)).displayText.length() + i, 33);
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
                obj = new AttributedImageHelper.AttributionSpan(((String) (obj)), ((String) (obj2)));
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

            .Lambda._cls0()
            {
                arg$1 = SmartMailHeaderAttributeImageHelper.this;
            }
        }

        if (image == null || TextUtils.isEmpty(image.imageMetadataUrl))
        {
            if ("" == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture("");
            }
        } else
        {
            return (FluentFuture)CalendarExecutor.NET.submit(new .Lambda._cls0());
        }
    }
}
