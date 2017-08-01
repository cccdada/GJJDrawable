package com.sheng.gjjdialog;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

public class AccelerationInterpolator
        implements Interpolator {
    private final float[] a;
    private final float[] b;

    public AccelerationInterpolator(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
        this(a(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
    }

    public AccelerationInterpolator(Path paramPath) {
        PathMeasure localPathMeasure = new PathMeasure(paramPath, false);
        float f = localPathMeasure.getLength();
        int i = 1 + (int) (f / 0.002F);
        this.a = new float[i];
        this.b = new float[i];
        float[] arrayOfFloat = new float[2];
        for (int j = 0; j < i; j++) {
            localPathMeasure.getPosTan(f * j / (i - 1), arrayOfFloat, null);
            this.a[j] = arrayOfFloat[0];
            this.b[j] = arrayOfFloat[1];
        }
    }

    private static Path a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
        Path localPath = new Path();
        localPath.moveTo(0.0F, 0.0F);
        localPath.cubicTo(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 1.0F, 1.0F);
        return localPath;
    }

    public float getInterpolation(float paramFloat) {
        float f1 = 1.0F;
        if (paramFloat <= 0.0F)
            f1 = 0.0F;
        while (paramFloat >= f1)
            return f1;
        int i = 0;
        int j = -1 + this.a.length;
        if (j - i > 1) {
            int k = (i + j) / 2;
            int n = 0;
            int m = j;
            n = k;
            k = m;
            if (paramFloat < this.a[k])
                n = i;
            while (true) {
                i = n;
                j = k;
                break;
            }
        }
        float f2 = this.a[j] - this.a[i];
        if (f2 == 0.0F)
            return this.b[i];
        float f3 = (paramFloat - this.a[i]) / f2;
        float f4 = this.b[i];
        return f4 + f3 * (this.b[j] - f4);
    }
}