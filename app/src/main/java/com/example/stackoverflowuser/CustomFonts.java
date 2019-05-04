package com.example.stackoverflowuser;

import android.content.Context;
import android.graphics.Typeface;

/*
 * Created by deepakjoshi on 13/04/18.
 */

public class CustomFonts {

    private static CustomFonts fonts;

    private Typeface  proxima_light,
                            proxima_thin,
                            proxima_bold,
                            proxima_regular,
                            proxima_semibold;

    private CustomFonts(Context context){

        initFonts(context);
    }

    public static CustomFonts getSingletonInstance(Context ctx){

        synchronized (CustomFonts.class) {
            if (fonts == null)
                fonts = new CustomFonts(ctx);
        }

        return fonts;
    }


    public Typeface getLightFont() {
        return proxima_light;
    }

    public Typeface getThinFont() {
        return proxima_thin;
    }

    public Typeface getBoldFont() {
        return proxima_bold;
    }

    public Typeface getRegularFont() {
        return proxima_regular;
    }

    public Typeface getSemiboldFont() {
        return proxima_semibold;
    }

    private void initFonts(Context context){

        proxima_light = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Light.otf");
        proxima_thin = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Thin.otf");
        proxima_bold = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Bold.otf");
        proxima_regular = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Regular.otf");
        proxima_semibold = Typeface.createFromAsset(context.getAssets(),"Proxima-Nova-Semibold.otf");
    }
}
