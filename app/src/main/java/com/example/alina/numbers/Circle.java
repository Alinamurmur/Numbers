package com.example.alina.numbers;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Circle extends View {
    Paint paint,p1;
    int radius=50;
    private static int width;
    private static int height;
    int rx,ry;

    public Circle(Context context){
        super(context);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.circl));
        p1 = new Paint();
        p1.setStyle(Paint.Style.FILL);
        p1.setColor(getResources().getColor(R.color.numb));
        p1.setTextSize(50);
        p1.setTextAlign(Paint.Align.CENTER);
    }

    public int randX(int a) {
        rx = (int) (Math.random()*a + radius);
        return rx;
    }
    public int randY(int b) {
        ry = (int) (Math.random() * b + radius);
        return ry;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.fon));
        width = canvas.getWidth();
        height = canvas.getHeight();
        int w = width-2*radius;
        int h = height-2*radius;

        int[] xx = new int[20];
        int[] yy = new int[20];

        randX(w);    randY(h);    xx[0]=rx;   yy[0]= ry;
        canvas.drawCircle(rx, ry, radius, paint);
        canvas.drawText(Integer.toString(1), rx, ry, p1);

        int u =1;
        int d;
        while (u <20) {
            d=0;
            randX(w);
            randY(h);
            for (int i = 0; i < u; i++) { //цикл для проверки координат
                if (rx > (xx[i] + 2 * radius) | rx < (xx[i] - 2 * radius)
                        | ry > (yy[i] + 2 * radius) | ry < (yy[i] - 2 * radius)) {
                    d++;
                }
            }
            if (d==u){
                xx[u]=rx;     yy[u]= ry;
                u++;}
        }
        for (int i = 1; i < 20; i++) {
            canvas.drawCircle(xx[i], yy[i], radius, paint);
            canvas.drawText(Integer.toString(i+1), xx[i], yy[i], p1);
        }
    }
}

