package com.sheng.gjjdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ScrollerCompat;

public class GJJDrawable extends Drawable
        implements Animatable {
    //a ContextCompat
    private Context a;
    private int b = 0;
    private boolean c = false;
    private boolean d = true;
    private OnProgressListener listener;
    private Paint f = new Paint(1);
    private ScrollerCompat g;
    private ScrollerCompat h;
    private a i;
    private Rect j = new Rect();
    private RectF k = new RectF();

    public GJJDrawable(Context paramContext) {
        this.a = paramContext.getApplicationContext();
        AccelerationInterpolator locald = new AccelerationInterpolator(0.0F, 1.1F, 1.5F, 1.1F);
        this.g = ScrollerCompat.create(this.a, locald);
        this.h = ScrollerCompat.create(this.a);
    }

    private void a(Canvas paramCanvas) {
        Rect localRect = getBounds();
        if (localRect.width() <= 0)
            return;
        float f1 = this.b / 10000.0F;
        if (this.i == null) {
            this.i = new a(this.a);
            this.i.a(localRect);
        }
        this.f.setColor(ContextCompat.getColor(this.a, R.color.trans_grey));//颜色待定
        this.f.setStyle(Paint.Style.FILL);
        this.f.setStrokeCap(Paint.Cap.ROUND);
        int m = (int) (f1 * (this.i.m - this.i.l));
        float f2 = 0.5F * (this.i.f - this.i.e);
        float f3 = 0.5F * this.i.f;
        this.k.set(this.i.l - f2, localRect.bottom - this.i.f, f2 + this.i.m, localRect.bottom);
        paramCanvas.drawRoundRect(this.k, f3, f3, this.f);
        this.f.setColor(ContextCompat.getColor(this.a, R.color.yellow));//颜色待定
        float f4 = 0.5F * this.i.e;
        float f5 = localRect.bottom - this.i.f + this.i.e / 2;
        this.k.set(this.i.l, f5, m + this.i.l, f5 + this.i.e);
        paramCanvas.drawRoundRect(this.k, f4, f4, this.f);
        if (this.listener != null)
            this.listener.a(Math.round(f1 * 100.0F));
        this.i.c.draw(paramCanvas);
        this.j.set(this.i.j);
        this.j.offset(m, 0);
        this.i.b.setBounds(this.j);
        this.i.b.draw(paramCanvas);
        this.j.set(this.i.k);
        this.j.offset(m, 0);
        int n = m * 250 * this.i.d.length / 10000 % this.i.d.length;
        this.i.d[n].setBounds(this.j);
        this.i.d[n].draw(paramCanvas);
        this.j.set(this.i.i);
        this.j.offset(m, 0);
        paramCanvas.rotate(360 * (m % this.i.h) / this.i.h, this.j.centerX(), this.j.centerY());
        this.i.a.setBounds(this.j);
        this.i.a.draw(paramCanvas);
    }

    public void a() {
        this.h.abortAnimation();
        this.g.abortAnimation();
        this.b = 0;
        this.c = false;
        this.d = false;
    }

    public void a(float paramFloat) {
        int m = (int) (10000.0F * paramFloat);
        if (this.b != m) {
            this.g.abortAnimation();
            this.h.startScroll(this.b, 0, m - this.b, 0, 3000);
            invalidateSelf();
        }
    }

    public void a(OnProgressListener paramb) {
        this.listener = paramb;
    }

    public void draw(Canvas paramCanvas) {
        if (this.h.computeScrollOffset())
            this.b = this.h.getCurrX();
        do
            while (true) {
                a(paramCanvas);
                if ((!this.c) || ((!this.h.computeScrollOffset()) && (!this.g.computeScrollOffset())))
                    break;
                invalidateSelf();
                if (this.g.computeScrollOffset()) {
                    this.b = this.g.getCurrX();
                }
                return;
            }
        while (!this.c);
        this.c = false;
        this.i = null;
        if (this.listener != null)
            this.listener.a(this.d);
        this.c = false;
    }

    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public boolean isRunning() {
        return this.c;
    }

    protected void onBoundsChange(Rect paramRect) {
        if (this.i != null)
            this.i.a(paramRect);
        super.onBoundsChange(paramRect);
    }

    public void setAlpha(int paramInt) {
        this.f.setAlpha(paramInt);
    }

    public void setColorFilter(ColorFilter paramColorFilter) {
        this.f.setColorFilter(paramColorFilter);
    }

    public void start() {
        this.b = 0;
        this.c = true;
        this.d = true;
        this.h.abortAnimation();
        this.g.startScroll(0, 0, 10000, 0, 12000);
        invalidateSelf();
    }

    public void stop() {
        this.d = false;
        a(1.0F);
    }

    private static final class a {
        Drawable a;
        Drawable b;
        Drawable c;
        Drawable[] d;
        int e;
        int f;
        int g;
        int h;
        Rect i = new Rect();
        Rect j = new Rect();
        Rect k = new Rect();
        int l;
        int m;

        a(Context paramContext) {
            this.a = ContextCompat.getDrawable(paramContext, R.drawable.gjj_loading_ball);//ContextCompat  导报
            this.b = ContextCompat.getDrawable(paramContext, R.drawable.gjj_loading_head);//ContextCompat
            this.c = ContextCompat.getDrawable(paramContext, R.drawable.gjj_loading_house);
            int[] arrayOfInt = {R.drawable.gjj_loading_leg_01, R.drawable.gjj_loading_leg_03, R.drawable.gjj_loading_leg_04, R.drawable
                    .gjj_loading_leg_05, R.drawable.gjj_loading_leg_06, R.drawable.gjj_loading_leg_07, R.drawable.gjj_loading_leg_07, R.drawable.gjj_loading_leg_08, R.drawable.gjj_loading_leg_09};
            this.d = new Drawable[arrayOfInt.length];
            for (int n = 0; n < arrayOfInt.length; n++)
                this.d[n] = ContextCompat.getDrawable(paramContext, arrayOfInt[n]);
        }

        void a(Rect paramRect) {
            float f1 = paramRect.height() / 260.0F;
            this.g = ((int) (72.0F * f1));
            float f2 = 2.0F * this.g / this.a.getIntrinsicWidth();
            this.e = ((int) (26.0F * f1));
            this.f = ((int) (f1 * 52.0F));
            float f3 = 0.5F * (this.f - this.e);
            int n = (int) (f2 * this.b.getIntrinsicWidth());
            int i1 = (int) (f2 * this.b.getIntrinsicHeight());
            this.j.set(paramRect.left, paramRect.top, n + paramRect.left, i1 + paramRect.top);
            this.k.set(this.j.left, this.j.bottom, this.j.right, (int) (this.j.bottom + f2 * this.d[0].getIntrinsicHeight()));
            int i2 = (int) (paramRect.bottom - f3 - 2 * this.g);
            this.i.set(n - this.g, i2, n + this.g, (int) (paramRect.bottom - f3));
            int i3 = (int) (f2 * this.c.getIntrinsicWidth());
            int i4 = (int) (f2 * this.c.getIntrinsicHeight());
            int i5 = paramRect.right - this.g;
            int i6 = paramRect.bottom - this.f;
            this.c.setBounds(i5 - i3, i6 - i4, i5, i6);
            this.l = (n + paramRect.left);
            this.m = (paramRect.right - this.g);
            this.h = ((int) (2.0D * (3.141592653589793D * this.g)));
        }
    }

    public static abstract interface OnProgressListener {
        public abstract void a(int paramInt);

        public abstract void a(boolean paramBoolean);
    }
}

/* Location:           /Users/wangsheng/Downloads/bj_gjj_dex2jar.jar
 * Qualified Name:     com.caiyi.ui.dialog.c
 * JD-Core Version:    0.6.2
 */