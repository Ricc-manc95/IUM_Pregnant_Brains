// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            UnknownFieldSchema, UnknownFieldSetLite, GeneratedMessageLite, ByteString, 
//            CodedOutputStream, Writer

final class UnknownFieldSetLiteSchema extends UnknownFieldSchema
{

    UnknownFieldSetLiteSchema()
    {
    }

    final void addFixed32(Object obj, int i, int j)
    {
        ((UnknownFieldSetLite)obj).storeField(5 | i << 3, Integer.valueOf(j));
    }

    final void addFixed64(Object obj, int i, long l)
    {
        ((UnknownFieldSetLite)obj).storeField(1 | i << 3, Long.valueOf(l));
    }

    final void addGroup(Object obj, int i, Object obj1)
    {
        ((UnknownFieldSetLite)obj).storeField(3 | i << 3, (UnknownFieldSetLite)obj1);
    }

    final void addLengthDelimited(Object obj, int i, ByteString bytestring)
    {
        ((UnknownFieldSetLite)obj).storeField(2 | i << 3, bytestring);
    }

    final void addVarint(Object obj, int i, long l)
    {
        ((UnknownFieldSetLite)obj).storeField(0 | i << 3, Long.valueOf(l));
    }

    final Object getBuilderFromMessage(Object obj)
    {
        UnknownFieldSetLite unknownfieldsetlite1 = ((GeneratedMessageLite)obj).unknownFields;
        UnknownFieldSetLite unknownfieldsetlite = unknownfieldsetlite1;
        if (unknownfieldsetlite1 == UnknownFieldSetLite.DEFAULT_INSTANCE)
        {
            unknownfieldsetlite = new UnknownFieldSetLite();
            ((GeneratedMessageLite)obj).unknownFields = unknownfieldsetlite;
        }
        return unknownfieldsetlite;
    }

    final Object getFromMessage(Object obj)
    {
        return ((GeneratedMessageLite)obj).unknownFields;
    }

    final int getSerializedSize(Object obj)
    {
        return ((UnknownFieldSetLite)obj).getSerializedSize();
    }

    final int getSerializedSizeAsMessageSet(Object obj)
    {
        obj = (UnknownFieldSetLite)obj;
        int i = ((UnknownFieldSetLite) (obj)).memoizedSerializedSize;
        if (i != -1)
        {
            return i;
        }
        i = 0;
        int j = 0;
        for (; i < ((UnknownFieldSetLite) (obj)).count; i++)
        {
            j += CodedOutputStream.computeRawMessageSetExtensionSize(((UnknownFieldSetLite) (obj)).tags[i] >>> 3, (ByteString)((UnknownFieldSetLite) (obj)).objects[i]);
        }

        obj.memoizedSerializedSize = j;
        return j;
    }

    final void makeImmutable(Object obj)
    {
        ((GeneratedMessageLite)obj).unknownFields.isMutable = false;
    }

    final Object merge(Object obj, Object obj1)
    {
        obj = (UnknownFieldSetLite)obj;
        obj1 = (UnknownFieldSetLite)obj1;
        if (((UnknownFieldSetLite) (obj1)).equals(UnknownFieldSetLite.DEFAULT_INSTANCE))
        {
            return obj;
        } else
        {
            return UnknownFieldSetLite.mutableCopyOf(((UnknownFieldSetLite) (obj)), ((UnknownFieldSetLite) (obj1)));
        }
    }

    final Object newBuilder()
    {
        return new UnknownFieldSetLite();
    }

    final void setBuilderToMessage(Object obj, Object obj1)
    {
        obj1 = (UnknownFieldSetLite)obj1;
        ((GeneratedMessageLite)obj).unknownFields = ((UnknownFieldSetLite) (obj1));
    }

    final void setToMessage(Object obj, Object obj1)
    {
        obj1 = (UnknownFieldSetLite)obj1;
        ((GeneratedMessageLite)obj).unknownFields = ((UnknownFieldSetLite) (obj1));
    }

    final boolean shouldDiscardUnknownFields$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONL4PB1CHIN4EP9B8______0()
    {
        return false;
    }

    final Object toImmutable(Object obj)
    {
        obj = (UnknownFieldSetLite)obj;
        obj.isMutable = false;
        return obj;
    }

    final void writeAsMessageSetTo(Object obj, Writer writer)
        throws IOException
    {
        obj = (UnknownFieldSetLite)obj;
        if (writer.fieldOrder$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULRID5Q6ASH48PKMAR349TP68PBI7C______0() == android.support.v4.content.ModernAsyncTask.Status.DESCENDING$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65TBN4QBKCLP28HJ9CLM68JRICHIN4EO_0)
        {
            for (int i = ((UnknownFieldSetLite) (obj)).count - 1; i >= 0; i--)
            {
                writer.writeMessageSetItem(((UnknownFieldSetLite) (obj)).tags[i] >>> 3, ((UnknownFieldSetLite) (obj)).objects[i]);
            }

        } else
        {
            for (int j = 0; j < ((UnknownFieldSetLite) (obj)).count; j++)
            {
                writer.writeMessageSetItem(((UnknownFieldSetLite) (obj)).tags[j] >>> 3, ((UnknownFieldSetLite) (obj)).objects[j]);
            }

        }
    }

    final void writeTo(Object obj, Writer writer)
        throws IOException
    {
        ((UnknownFieldSetLite)obj).writeTo(writer);
    }
}
