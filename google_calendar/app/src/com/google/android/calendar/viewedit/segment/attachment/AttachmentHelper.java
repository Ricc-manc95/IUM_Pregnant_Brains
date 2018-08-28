// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.viewedit.segment.attachment;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;

public final class AttachmentHelper
{

    private static final ImmutableMap ATTACHMENT_TYPES = (new com.google.common.collect.ImmutableMap.Builder()).put("application/pdf", new AttachmentDescriptor(0x7f1300ca, "icon_11_pdf_list.png", 0x7f0d0014)).put("text/plain", new AttachmentDescriptor(0x7f1300cc, "icon_10_text_list.png", 0x7f0d0016)).put("image/jpeg", new AttachmentDescriptor(0x7f1300c9, "icon_11_image_list.png", 0x7f0d0013)).put("image/png", new AttachmentDescriptor(0x7f1300c9, "icon_11_image_list.png", 0x7f0d0013)).put("image/gif", new AttachmentDescriptor(0x7f1300c9, "icon_11_image_list.png", 0x7f0d0013)).put("application/vnd.google-apps.document", new AttachmentDescriptor(0x7f1300c4, "icon_11_document_list.png", 0x7f0d000e)).put("application/vnd.google-apps.spreadsheet", new AttachmentDescriptor(0x7f1300c6, "icon_11_spreadsheet_list.png", 0x7f0d0010)).put("application/vnd.google-apps.presentation", new AttachmentDescriptor(0x7f1300c7, "icon_11_presentation_list.png", 0x7f0d0011)).put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", new AttachmentDescriptor(0x7f1300ce, "icon_10_word_list.png", 0x7f0d0018)).put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new AttachmentDescriptor(0x7f1300c8, "icon_10_excel_list.png", 0x7f0d0012)).put("application/vnd.openxmlformats-officedocument.presentationml.presentation", new AttachmentDescriptor(0x7f1300cb, "icon_10_powerpoint_list.png", 0x7f0d0015)).put("application/msword", new AttachmentDescriptor(0x7f1300ce, "icon_10_word_list.png", 0x7f0d0018)).put("application/vnd.ms-excel", new AttachmentDescriptor(0x7f1300c8, "icon_10_excel_list.png", 0x7f0d0012)).put("application/vnd.ms-powerpoint", new AttachmentDescriptor(0x7f1300cb, "icon_10_powerpoint_list.png", 0x7f0d0015)).put("application/vnd.google-apps.audio", new AttachmentDescriptor(0x7f1300c2, "icon_10_audio_list.png", 0x7f0d0009)).put("application/vnd.google-apps.drawing", new AttachmentDescriptor(0x7f1300c5, "icon_11_drawing_list.png", 0x7f0d000f)).put("application/vnd.google-apps.photo", new AttachmentDescriptor(0x7f1300c9, "icon_11_image_list.png", 0x7f0d0013)).put("application/vnd.google-apps.video", new AttachmentDescriptor(0x7f1300cd, "icon_11_video_list.png", 0x7f0d0017)).build();

    public static int getTypeColor(Context context, String s)
    {
        context = context.getResources();
        s = (AttachmentDescriptor)ATTACHMENT_TYPES.get(s);
        if (s != null)
        {
            return context.getColor(((AttachmentDescriptor) (s)).colorId);
        } else
        {
            return context.getColor(0x7f0d000d);
        }
    }

    public static String getTypeDescription(Context context, String s)
    {
        context = context.getResources();
        s = (AttachmentDescriptor)ATTACHMENT_TYPES.get(s);
        if (s != null)
        {
            return context.getString(((AttachmentDescriptor) (s)).descriptionId);
        } else
        {
            return context.getString(0x7f1300c3);
        }
    }

    public static String getTypeIconUrl(String s)
    {
        Object obj = (AttachmentDescriptor)ATTACHMENT_TYPES.get(s);
        if (obj != null)
        {
            s = String.valueOf("https://ssl.gstatic.com/docs/doclist/images/");
            obj = String.valueOf(((AttachmentDescriptor) (obj)).icon);
            if (((String) (obj)).length() != 0)
            {
                return s.concat(((String) (obj)));
            } else
            {
                return new String(s);
            }
        } else
        {
            return "https://ssl.gstatic.com/docs/doclist/images/icon_10_generic_list.png";
        }
    }

    public static String inferMimeType(String s)
    {
label0:
        {
            if (TextUtils.isEmpty(s))
            {
                break label0;
            }
            s = Uri.parse(s).getLastPathSegment();
            if (s == null)
            {
                break label0;
            }
            UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)((ImmutableSet)ATTACHMENT_TYPES.entrySet()).iterator();
            java.util.Map.Entry entry;
            do
            {
                if (!unmodifiableiterator.hasNext())
                {
                    break label0;
                }
                entry = (java.util.Map.Entry)unmodifiableiterator.next();
            } while (!s.equals(((AttachmentDescriptor)entry.getValue()).icon));
            return (String)entry.getKey();
        }
        return "";
    }


    private class AttachmentDescriptor
    {

        public final int colorId;
        public final int descriptionId;
        public final String icon;

        public AttachmentDescriptor(int i, String s, int j)
        {
            descriptionId = i;
            icon = s;
            colorId = j;
        }
    }

}
