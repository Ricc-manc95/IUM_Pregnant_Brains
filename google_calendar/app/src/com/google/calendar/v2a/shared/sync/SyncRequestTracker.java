// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.shared.sync;


public interface SyncRequestTracker
{
    public static final class Status extends Enum
    {

        private static final Status $VALUES[];
        public static final Status FAILURE;
        public static final Status PENDING;
        public static final Status RUNNING;
        public static final Status SUCCESS;

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        static 
        {
            PENDING = new Status("PENDING", 0);
            RUNNING = new Status("RUNNING", 1);
            SUCCESS = new Status("SUCCESS", 2);
            FAILURE = new Status("FAILURE", 3);
            $VALUES = (new Status[] {
                PENDING, RUNNING, SUCCESS, FAILURE
            });
        }

        private Status(String s, int i)
        {
            super(s, i);
        }
    }

}
