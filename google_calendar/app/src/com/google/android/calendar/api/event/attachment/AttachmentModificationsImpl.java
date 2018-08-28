// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.attachment:
//            AttachmentModifications, Attachment

public class AttachmentModificationsImpl
    implements AttachmentModifications
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final ArrayList attachments;
    private final List original;

    AttachmentModificationsImpl(Parcel parcel)
    {
        original = parcel.createTypedArrayList(Attachment.CREATOR);
        attachments = parcel.createTypedArrayList(Attachment.CREATOR);
    }

    public AttachmentModificationsImpl(List list)
    {
        if (list == null)
        {
            throw new NullPointerException();
        } else
        {
            original = (List)list;
            attachments = new ArrayList(list);
            return;
        }
    }

    public final void addAttachment(Attachment attachment)
    {
        for (int i = 0; i < attachments.size(); i++)
        {
            if (((Attachment)attachments.get(i)).fileUrl.equals(attachment.fileUrl))
            {
                attachments.set(i, attachment);
                return;
            }
        }

        attachments.add(attachment);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof AttachmentModificationsImpl)
        {
            obj = (AttachmentModificationsImpl)obj;
            List list = original;
            List list1 = ((AttachmentModificationsImpl) (obj)).original;
            boolean flag;
            if (list == list1 || list != null && list.equals(list1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ArrayList arraylist = attachments;
                obj = ((AttachmentModificationsImpl) (obj)).attachments;
                if (arraylist == obj || arraylist != null && arraylist.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            original, attachments
        });
    }

    public final boolean isModified()
    {
        return !attachments.equals(original);
    }

    public final void removeAttachment(Attachment attachment)
    {
        attachments.remove(attachment);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("AttachmentModificationsImpl{");
        String s = String.valueOf(original);
        stringbuilder = stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 9)).append("original=").append(s).toString());
        s = String.valueOf(attachments);
        return stringbuilder.append((new StringBuilder(String.valueOf(s).length() + 14)).append(", attachments=").append(s).toString()).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(original);
        parcel.writeTypedList(attachments);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AttachmentModificationsImpl(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AttachmentModificationsImpl[i];
        }

        _cls1()
        {
        }
    }

}
