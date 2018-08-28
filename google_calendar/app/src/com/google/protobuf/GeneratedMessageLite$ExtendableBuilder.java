// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            MessageLiteOrBuilder, GeneratedMessageLite, FieldSet, SmallSortedMap, 
//            MessageLite

public static class  extends 
    implements MessageLiteOrBuilder
{

    public volatile GeneratedMessageLite buildPartial()
    {
        return ()al();
    }

    public MessageLite buildPartial()
    {
        if (isBuilt)
        {
            return (isBuilt)instance;
        }
        FieldSet fieldset = ((instance)instance).extensions;
        if (!fieldset.isImmutable)
        {
            fieldset.fields.makeImmutable();
            fieldset.isImmutable = true;
        }
        return (extensions)super.al();
    }

    protected final void copyOnWrite()
    {
        if (!isBuilt)
        {
            return;
        } else
        {
            super.e();
            ((e)instance).extensions = (FieldSet)((extensions)instance).extensions.clone();
            return;
        }
    }

    public ( )
    {
        super();
    }
}
