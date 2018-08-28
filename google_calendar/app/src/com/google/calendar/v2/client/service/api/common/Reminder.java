// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.common;

import com.google.calendar.v2.client.service.api.time.Duration;
import java.util.Arrays;

public final class Reminder
{

    public final Duration before;
    public final DeliveryMethod deliveryMethod;

    public Reminder(DeliveryMethod deliverymethod, Duration duration)
    {
        if (deliverymethod == null)
        {
            throw new NullPointerException();
        }
        if (duration == null)
        {
            throw new NullPointerException();
        } else
        {
            deliveryMethod = deliverymethod;
            before = duration;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof Reminder))
            {
                return false;
            }
            obj = (Reminder)obj;
            if (!before.equals(((Reminder) (obj)).before) || deliveryMethod != ((Reminder) (obj)).deliveryMethod)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            deliveryMethod, before
        });
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName());
        Object obj = deliveryMethod.name();
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("Delivery method" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"Delivery method";
        obj = before;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("Before" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"Before";
            return tostringhelper.toString();
        }
    }

    private class DeliveryMethod extends Enum
    {

        private static final DeliveryMethod $VALUES[];
        public static final DeliveryMethod ALERT;
        public static final DeliveryMethod EMAIL;
        public static final DeliveryMethod SMS;

        public static DeliveryMethod[] values()
        {
            return (DeliveryMethod[])$VALUES.clone();
        }

        static 
        {
            ALERT = new DeliveryMethod("ALERT", 0);
            EMAIL = new DeliveryMethod("EMAIL", 1);
            SMS = new DeliveryMethod("SMS", 2);
            $VALUES = (new DeliveryMethod[] {
                ALERT, EMAIL, SMS
            });
        }

        private DeliveryMethod(String s, int i)
        {
            super(s, i);
        }
    }

}
