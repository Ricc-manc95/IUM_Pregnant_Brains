// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


// Referenced classes of package android.arch.lifecycle:
//            LifecycleObserver

public abstract class Lifecycle
{
    public static final class Event extends Enum
    {

        public static final Event $VALUES[];
        public static final Event ON_ANY;
        public static final Event ON_CREATE;
        public static final Event ON_DESTROY;
        public static final Event ON_PAUSE;
        public static final Event ON_RESUME;
        public static final Event ON_START;
        public static final Event ON_STOP;

        public static Event[] values()
        {
            return (Event[])$VALUES.clone();
        }

        static 
        {
            ON_CREATE = new Event("ON_CREATE", 0);
            ON_START = new Event("ON_START", 1);
            ON_RESUME = new Event("ON_RESUME", 2);
            ON_PAUSE = new Event("ON_PAUSE", 3);
            ON_STOP = new Event("ON_STOP", 4);
            ON_DESTROY = new Event("ON_DESTROY", 5);
            ON_ANY = new Event("ON_ANY", 6);
            $VALUES = (new Event[] {
                ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY, ON_ANY
            });
        }

        private Event(String s, int i)
        {
            super(s, i);
        }
    }


    public Lifecycle()
    {
    }

    public abstract void addObserver(LifecycleObserver lifecycleobserver);

    public abstract State getCurrentState();

    public abstract void removeObserver(LifecycleObserver lifecycleobserver);
}
