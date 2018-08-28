// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.EventModifications;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventSaveFlow

final class ification
    implements FutureCallback
{

    private final EventSaveFlow this$0;
    private final com.google.android.calendar.api.event.a.GuestNotification val$guestNotification;

    public final void onFailure(Throwable throwable)
    {
        Object obj;
        EventSaveFlow eventsaveflow;
        Object obj1;
        Object obj2;
        boolean flag1;
        obj = null;
        flag1 = false;
        eventsaveflow = EventSaveFlow.this;
        obj1 = val$guestNotification;
        obj2 = eventsaveflow.getContext();
        if (obj2 == null) goto _L2; else goto _L1
_L1:
        if (throwable == null) goto _L4; else goto _L3
_L3:
        LogUtils.e(EventSaveFlow.TAG, throwable, "Unable to save", new Object[0]);
        Toast.makeText(((Context) (obj2)), 0x7f1301ab, 0).show();
        obj2 = new ambda._cls2(eventsaveflow);
        obj1 = ((Fragment) (eventsaveflow)).mTarget;
        if (obj1 == null) goto _L6; else goto _L5
_L5:
        if (obj1 == null) goto _L8; else goto _L7
_L7:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = ((Fragment) (obj1)).mFragmentManager;
        if (obj1 == null) goto _L10; else goto _L9
_L9:
        boolean flag;
        if (((Fragment) (obj1)).mHost != null && ((Fragment) (obj1)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L11; else goto _L10
_L10:
        flag = false;
_L13:
        if (!flag) goto _L6; else goto _L12
_L12:
        flag = true;
_L15:
        if (flag)
        {
            ((Consumer) (obj2)).accept(obj1);
        }
        flag = flag1;
        if (eventsaveflow != null)
        {
            obj1 = ((Fragment) (eventsaveflow)).mFragmentManager;
            flag = flag1;
            if (eventsaveflow != null)
            {
                if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    flag = flag1;
                } else
                {
                    if (((Fragment) (eventsaveflow)).mHost == null)
                    {
                        throwable = ((Throwable) (obj));
                    } else
                    {
                        throwable = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
                    }
                    flag = flag1;
                    if (throwable != null)
                    {
                        flag = flag1;
                        if (!throwable.isDestroyed())
                        {
                            flag = flag1;
                            if (!throwable.isFinishing())
                            {
                                flag = flag1;
                                if (obj1 != null)
                                {
                                    flag = flag1;
                                    if (!((FragmentManager) (obj1)).isDestroyed())
                                    {
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (flag)
        {
            ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
        }
_L2:
        return;
_L11:
label0:
        {
            if (((Fragment) (obj1)).mHost == null)
            {
                throwable = null;
            } else
            {
                throwable = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
            }
            if (throwable != null && !throwable.isDestroyed() && !throwable.isFinishing())
            {
                break label0;
            }
            flag = false;
        }
          goto _L13
        if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed()) goto _L8; else goto _L14
_L14:
        flag = true;
          goto _L13
_L8:
        flag = false;
          goto _L13
_L6:
        flag = false;
          goto _L15
_L4:
        int i;
label1:
        {
            boolean flag2;
            if (true)
            {
                if (false)
                {
                    ((Context) (obj2)).getContentResolver().notifyChange(null, null);
                }
                if (!eventsaveflow.showConfirmationToast)
                {
                    break label1;
                }
                obj = eventsaveflow.getContext();
                flag2 = eventsaveflow.event.isNewEvent();
                throwable = Features.instance;
                if (throwable == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
            } else
            {
                throw new NullPointerException();
            }
            ((FeatureConfig)throwable).notify_guests();
            if (obj1 != com.google.android.calendar.api.event.a.GuestNotification.ENABLED)
            {
                if (flag2)
                {
                    i = 0x7f130143;
                } else
                {
                    i = 0x7f130426;
                }
            } else
            {
                if (false && AttendeeUtils.hasGuests(null))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (flag2)
                {
                    if (i != 0)
                    {
                        i = 0x7f130144;
                    } else
                    {
                        i = 0x7f130143;
                    }
                } else
                if (i != 0)
                {
                    i = 0x7f130427;
                } else
                {
                    i = 0x7f130426;
                }
            }
            Toast.makeText(((Context) (obj)), i, 0).show();
        }
        obj2 = new ambda._cls3(eventsaveflow, null);
        obj = ((Fragment) (eventsaveflow)).mTarget;
        if (obj == null) goto _L17; else goto _L16
_L16:
        if (obj == null) goto _L19; else goto _L18
_L18:
        obj1 = ((Fragment) (obj)).mFragmentManager;
        if (obj == null) goto _L21; else goto _L20
_L20:
        if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L22; else goto _L21
_L21:
        i = 0;
_L27:
        if (i == 0) goto _L17; else goto _L23
_L23:
        i = 1;
_L29:
        if (i != 0)
        {
            ((Consumer) (obj2)).accept(obj);
        }
        if (eventsaveflow == null)
        {
            break MISSING_BLOCK_LABEL_848;
        }
        obj = ((Fragment) (eventsaveflow)).mFragmentManager;
        if (eventsaveflow == null) goto _L25; else goto _L24
_L24:
        if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L26; else goto _L25
_L25:
        i = 0;
_L30:
        if (i != 0)
        {
            ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
            return;
        }
          goto _L2
_L22:
label2:
        {
            if (((Fragment) (obj)).mHost == null)
            {
                throwable = null;
            } else
            {
                throwable = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            if (throwable != null && !throwable.isDestroyed() && !throwable.isFinishing())
            {
                break label2;
            }
            i = 0;
        }
          goto _L27
        if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed()) goto _L19; else goto _L28
_L28:
        i = 1;
          goto _L27
_L19:
        i = 0;
          goto _L27
_L17:
        i = 0;
          goto _L29
_L26:
        if (((Fragment) (eventsaveflow)).mHost == null)
        {
            throwable = null;
        } else
        {
            throwable = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
        }
        if (throwable == null || throwable.isDestroyed() || throwable.isFinishing())
        {
            i = 0;
        } else
        {
            if (obj == null || ((FragmentManager) (obj)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_848;
            }
            i = 1;
        }
          goto _L30
        i = 0;
          goto _L30
    }

    public final void onSuccess(Object obj)
    {
        Object obj1;
        EventSaveFlow eventsaveflow;
        Object obj2;
        Object obj3;
        Object obj4;
        boolean flag1;
        obj1 = null;
        flag1 = false;
        obj2 = (Event)obj;
        eventsaveflow = EventSaveFlow.this;
        obj3 = val$guestNotification;
        obj4 = eventsaveflow.getContext();
        if (obj4 == null) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
        LogUtils.e(EventSaveFlow.TAG, null, "Unable to save", new Object[0]);
        Toast.makeText(((Context) (obj4)), 0x7f1301ab, 0).show();
        obj2 = new ambda._cls2(eventsaveflow);
        obj3 = ((Fragment) (eventsaveflow)).mTarget;
        if (obj3 == null) goto _L6; else goto _L5
_L5:
        if (obj3 == null) goto _L8; else goto _L7
_L7:
        obj4 = ((Fragment) (obj3)).mFragmentManager;
        if (obj3 == null) goto _L10; else goto _L9
_L9:
        boolean flag;
        if (((Fragment) (obj3)).mHost != null && ((Fragment) (obj3)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L11; else goto _L10
_L10:
        flag = false;
_L13:
        if (!flag) goto _L6; else goto _L12
_L12:
        flag = true;
_L15:
        if (flag)
        {
            ((Consumer) (obj2)).accept(obj3);
        }
        flag = flag1;
        if (eventsaveflow != null)
        {
            obj2 = ((Fragment) (eventsaveflow)).mFragmentManager;
            flag = flag1;
            if (eventsaveflow != null)
            {
                if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    flag = flag1;
                } else
                {
                    if (((Fragment) (eventsaveflow)).mHost == null)
                    {
                        obj = obj1;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
                    }
                    flag = flag1;
                    if (obj != null)
                    {
                        flag = flag1;
                        if (!((Activity) (obj)).isDestroyed())
                        {
                            flag = flag1;
                            if (!((Activity) (obj)).isFinishing())
                            {
                                flag = flag1;
                                if (obj2 != null)
                                {
                                    flag = flag1;
                                    if (!((FragmentManager) (obj2)).isDestroyed())
                                    {
                                        flag = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (flag)
        {
            ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
        }
_L2:
        return;
_L11:
label0:
        {
            if (((Fragment) (obj3)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj3)).mHost.mActivity;
            }
            if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
            {
                break label0;
            }
            flag = false;
        }
          goto _L13
        if (obj4 == null || ((FragmentManager) (obj4)).isDestroyed()) goto _L8; else goto _L14
_L14:
        flag = true;
          goto _L13
_L8:
        flag = false;
          goto _L13
_L6:
        flag = false;
          goto _L15
_L4:
        int i;
        if (obj2 == null)
        {
            obj = null;
        } else
        {
            obj = (Uri)((Event) (obj2)).getDescriptor().getKey().uri().orNull();
        }
        if (obj != null)
        {
            ((Context) (obj4)).getContentResolver().notifyChange(((Uri) (obj)), null);
        }
        if (eventsaveflow.showConfirmationToast)
        {
            obj = eventsaveflow.getContext();
            boolean flag2 = eventsaveflow.event.isNewEvent();
            obj1 = Features.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)obj1).notify_guests();
            if (obj3 != com.google.android.calendar.api.event.a.GuestNotification.ENABLED)
            {
                if (flag2)
                {
                    i = 0x7f130143;
                } else
                {
                    i = 0x7f130426;
                }
            } else
            {
                if (obj2 != null && AttendeeUtils.hasGuests(((Event) (obj2))))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (flag2)
                {
                    if (i != 0)
                    {
                        i = 0x7f130144;
                    } else
                    {
                        i = 0x7f130143;
                    }
                } else
                if (i != 0)
                {
                    i = 0x7f130427;
                } else
                {
                    i = 0x7f130426;
                }
            }
            Toast.makeText(((Context) (obj)), i, 0).show();
        }
        obj1 = new ambda._cls3(eventsaveflow, ((Event) (obj2)));
        obj2 = ((Fragment) (eventsaveflow)).mTarget;
        if (obj2 == null) goto _L17; else goto _L16
_L16:
        if (obj2 == null) goto _L19; else goto _L18
_L18:
        obj3 = ((Fragment) (obj2)).mFragmentManager;
        if (obj2 == null) goto _L21; else goto _L20
_L20:
        if (((Fragment) (obj2)).mHost != null && ((Fragment) (obj2)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L22; else goto _L21
_L21:
        i = 0;
_L27:
        if (i == 0) goto _L17; else goto _L23
_L23:
        i = 1;
_L29:
        if (i != 0)
        {
            ((Consumer) (obj1)).accept(obj2);
        }
        if (eventsaveflow == null)
        {
            break MISSING_BLOCK_LABEL_885;
        }
        obj1 = ((Fragment) (eventsaveflow)).mFragmentManager;
        if (eventsaveflow == null) goto _L25; else goto _L24
_L24:
        if (((Fragment) (eventsaveflow)).mHost != null && ((Fragment) (eventsaveflow)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L26; else goto _L25
_L25:
        i = 0;
_L30:
        if (i != 0)
        {
            ((Fragment) (eventsaveflow)).mFragmentManager.beginTransaction().remove(eventsaveflow);
            return;
        }
          goto _L2
_L22:
label1:
        {
            if (((Fragment) (obj2)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj2)).mHost.mActivity;
            }
            if (obj != null && !((Activity) (obj)).isDestroyed() && !((Activity) (obj)).isFinishing())
            {
                break label1;
            }
            i = 0;
        }
          goto _L27
        if (obj3 == null || ((FragmentManager) (obj3)).isDestroyed()) goto _L19; else goto _L28
_L28:
        i = 1;
          goto _L27
_L19:
        i = 0;
          goto _L27
_L17:
        i = 0;
          goto _L29
_L26:
        if (((Fragment) (eventsaveflow)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            i = 0;
        } else
        {
            if (obj1 == null || ((FragmentManager) (obj1)).isDestroyed())
            {
                break MISSING_BLOCK_LABEL_885;
            }
            i = 1;
        }
          goto _L30
        i = 0;
          goto _L30
    }

    ification()
    {
        this$0 = final_eventsaveflow;
        val$guestNotification = com.google.android.calendar.api.event.a.GuestNotification.this;
        super();
    }
}
