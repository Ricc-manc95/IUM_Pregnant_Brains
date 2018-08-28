// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.content.Context;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.common.drawable.DefaultableBitmapDrawable;
import com.google.android.calendar.event.image.cache.contactphoto.ContactPhotoCacheHolder;

// Referenced classes of package com.google.android.calendar.avatar:
//            ContactInfo, ContactPhotoRequestKey

public final class BadgeViewFactory
{

    public static void setupAttendeeBadge(Context context, Consumer consumer, int i, String s, String s1, String s2)
    {
        Context context1 = context.getApplicationContext();
        ContactInfo.Builder builder = ContactInfo.newBuilder();
        builder.sourceAccountName = s;
        builder.primaryEmail = s2;
        s = new ContactPhotoRequestKey(context1, new ContactInfo(builder));
        class .Lambda._cls1
            implements com.google.android.calendar.common.drawable.DefaultableBitmapDrawable.Listener
        {

            private final Consumer arg$1;
            private final Context arg$2;
            private final String arg$3;
            private final String arg$4;

            public final void onEmptyBitmapSet(DefaultableBitmapDrawable defaultablebitmapdrawable)
            {
                Consumer consumer1;
                Context context2;
                String s3;
                String s4;
                consumer1 = arg$1;
                context2 = arg$2;
                s4 = arg$3;
                s3 = arg$4;
                defaultablebitmapdrawable.unbind();
                boolean flag;
                if (!TextUtils.isEmpty(s4))
                {
label0:
                    {
                        if (s4 == null)
                        {
                            throw new NullPointerException();
                        }
                        char c = ((String)s4).charAt(0);
                        if (Character.isLetterOrDigit(c) && (' ' <= c && c <= '\u024F' || '\u0370' <= c && c <= '\u058F' || '\u10A0' <= c && c <= '\u10FF' || '\u0590' <= c && c <= '\u06FF' || '\u13A0' <= c && c <= '\u13FF'))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            flag = true;
                            break label0;
                        }
                    }
                }
                flag = false;
                if (true) goto _L2; else goto _L1
_L2:
                int j;
                if (!flag)
                {
                    break MISSING_BLOCK_LABEL_446;
                }
                defaultablebitmapdrawable = context2.getResources();
                if (LetterTileDrawableFactory.colors == null)
                {
                    LetterTileDrawableFactory.colors = defaultablebitmapdrawable.obtainTypedArray(0x7f0b0031);
                    LetterTileDrawableFactory.defaultColor = defaultablebitmapdrawable.getColor(0x7f0d013a);
                    LetterTileDrawableFactory.tileFontColor = defaultablebitmapdrawable.getColor(0x7f0d013b);
                }
                if (TextUtils.isEmpty(s4))
                {
                    break; /* Loop/switch isn't completed */
                }
                if (s4 == null)
                {
                    throw new NullPointerException();
                }
                char c1 = ((String)s4).charAt(0);
                if (Character.isLetterOrDigit(c1) && (' ' <= c1 && c1 <= '\u024F' || '\u0370' <= c1 && c1 <= '\u058F' || '\u10A0' <= c1 && c1 <= '\u10FF' || '\u0590' <= c1 && c1 <= '\u06FF' || '\u13A0' <= c1 && c1 <= '\u13FF'))
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j == 0) goto _L1; else goto _L3
_L3:
                j = 1;
_L4:
                if (j != 0)
                {
                    defaultablebitmapdrawable = String.valueOf(Character.toUpperCase(s4.charAt(0)));
                } else
                {
                    defaultablebitmapdrawable = null;
                }
                if (TextUtils.isEmpty(s3))
                {
                    j = LetterTileDrawableFactory.defaultColor;
                } else
                {
                    if (s3 == null)
                    {
                        throw new NullPointerException();
                    }
                    j = Math.abs(((String)s3).hashCode());
                    int k = LetterTileDrawableFactory.colors.length();
                    j = LetterTileDrawableFactory.colors.getColor(j % k, LetterTileDrawableFactory.defaultColor);
                }
                defaultablebitmapdrawable = new CircledScalingStringDrawable(context2, defaultablebitmapdrawable, j, LetterTileDrawableFactory.tileFontColor, 0.6666667F);
_L5:
                consumer1.accept(defaultablebitmapdrawable);
                return;
_L1:
                j = 0;
                  goto _L4
                defaultablebitmapdrawable = ContextCompat.getDrawable(context2, 0x7f020280);
                  goto _L5
            }

            .Lambda._cls1(Consumer consumer, Context context, String s, String s1)
            {
                arg$1 = consumer;
                arg$2 = context;
                arg$3 = s;
                arg$4 = s1;
            }
        }

        context = new DefaultableBitmapDrawable(context.getResources(), ContactPhotoCacheHolder.getContactPhotoCache(), false, new .Lambda._cls1(consumer, context, s1, s2), 0);
        context.setDecodeDimensions(i, i);
        consumer.accept(context);
        context.bind(s);
    }
}
