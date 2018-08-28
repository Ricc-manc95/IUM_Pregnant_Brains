// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import com.google.caribou.smartmail.Address;
import com.google.caribou.smartmail.GoTo;
import com.google.caribou.smartmail.SmartMailClientProtos;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.LazyField;
import com.google.protobuf.SmallSortedMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailAddress, SmartMailActionTarget

public final class V2ASmartMailInfoAdapter
{

    public static SmartMailAddress toSmartMailAddress(Address address)
    {
        int i;
        String s = address.name_;
        String s1 = address.streetAddress_;
        String s2 = address.locality_;
        String s3 = address.region_;
        String s4 = address.postalCode_;
        Object obj2 = GeneratedMessageLite.checkIsLite(SmartMailClientProtos.googleMapLink);
        if (((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).containingTypeDefaultInstance != (GeneratedMessageLite)address.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_DEFAULT_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null))
        {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
        Object obj = ((com.google.protobuf.GeneratedMessageLite.ExtendableMessage) (address)).extensions;
        Object obj1 = ((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).descriptor;
        obj = ((FieldSet) (obj)).fields.get(obj1);
        obj1 = obj;
        if (obj instanceof LazyField)
        {
            obj1 = LazyField.getValue();
        }
        if (obj1 == null)
        {
            obj = ((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).defaultValue;
        } else
        if (((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).descriptor.isRepeated)
        {
            obj = obj1;
            if (((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).descriptor.type.javaType == com.google.protobuf.WireFormat.JavaType.ENUM)
            {
                obj = new ArrayList();
                obj1 = ((List)obj1).iterator();
                while (((Iterator) (obj1)).hasNext()) 
                {
                    ((List) (obj)).add(((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).singularFromFieldSetType(((Iterator) (obj1)).next()));
                }
            }
        } else
        {
            obj = ((com.google.protobuf.GeneratedMessageLite.GeneratedExtension) (obj2)).singularFromFieldSetType(obj1);
        }
        obj2 = (GoTo)obj;
        obj1 = com.google.caribou.smartmail.GoTo.Type.forNumber(((GoTo) (obj2)).type_);
        obj = obj1;
        if (obj1 == null)
        {
            obj = com.google.caribou.smartmail.GoTo.Type.UNKNOWN;
        }
        ((com.google.caribou.smartmail.GoTo.Type) (obj)).ordinal();
        JVM INSTR tableswitch 1 10: default 192
    //                   1 330
    //                   2 336
    //                   3 377
    //                   4 390
    //                   5 363
    //                   6 342
    //                   7 370
    //                   8 383
    //                   9 356
    //                   10 349;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L1:
        i = 1;
_L13:
        return new SmartMailAddress(s, s1, s2, s3, s4, new SmartMailActionTarget(i, ((GoTo) (obj2)).uri_, ((GoTo) (obj2)).text_), address.latitude_, address.longitude_);
_L2:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 3;
        continue; /* Loop/switch isn't completed */
_L7:
        i = 7;
        continue; /* Loop/switch isn't completed */
_L11:
        i = 11;
        continue; /* Loop/switch isn't completed */
_L10:
        i = 10;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 6;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 8;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 4;
        continue; /* Loop/switch isn't completed */
_L9:
        i = 9;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 5;
        if (true) goto _L13; else goto _L12
_L12:
    }
}
