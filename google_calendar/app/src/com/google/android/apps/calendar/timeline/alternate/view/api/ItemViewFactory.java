// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;

import android.view.View;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.api:
//            ViewMode

public interface ItemViewFactory
{

    public abstract void bindView$51662RJ4E9NMIP1FEPKMATPFAPKMATPR9HL62TJ15TM62RJ75T7M4QJ5CDQ3MIACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNM2S395TB6IPBN9LNM8P9RB9666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR1E1KIUIBKCLMLCQB5ET362ORKDTP7I923D1KN0L3PE1IJMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UOBGE1PIUOR1DHIMSP31E8NNAT39DGNMCTBECDQ6IRRE5T1MURJJELMMASHR8OKLC___0(View view, Object obj, int i, ViewMode viewmode, boolean flag, int j, Consumer consumer, 
            float f);

    public abstract View createView();

    public abstract void onRecycle(View view);
}
