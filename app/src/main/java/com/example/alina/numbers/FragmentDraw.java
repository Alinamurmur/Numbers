package com.example.alina.numbers;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FragmentDraw extends Fragment {
    Graphics drawView;

    public FragmentDraw() {
        // Required empty public constructor
    }

    public static FragmentDraw getInstance(){
        FragmentDraw fragment = new FragmentDraw();
        fragment.setRetainInstance(true);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_drawing_fragment,container,false);
        drawView = (Graphics)view.findViewById(R.id.drawing);
        return view;

    }

    static public class Graphics extends View {

        private Circle  circle;
        private Paint paint, p1;

        private static int width;
        private static int height;
        int rx,ry;

        public Graphics(Context context) {
            super(context);
            initPaint();
            initCircle();

        }

        private void initCircle() {
            circle = new Circle(50,50);
        }
        private void initPaint() {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R.color.circl));

            //  paint.setShadowLayer(0, 0, 0, Color.BLACK);

            p1 = new Paint();
            p1.setStyle(Paint.Style.FILL);
            p1.setColor(getResources().getColor(R.color.numb));
            p1.setTextSize(50);
            p1.setTextAlign(Paint.Align.CENTER);
        }
        public int randX(int a) {
            rx = (int) (Math.random()*a + circle.getRadius());
            return rx;
        }
        public int randY(int b) {
            ry = (int) (Math.random() * b + circle.getRadius());
            return ry;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(getResources().getColor(R.color.fon));
            width = canvas.getWidth();
            height = canvas.getHeight();
            int w = width-2*circle.getRadius();
            int h = height-2*circle.getRadius();

            int[] xx = new int[20];
            int[] yy = new int[20];

            randX(w);    randY(h);    xx[0]=rx;   yy[0]= ry;
            canvas.drawCircle(rx, ry, circle.getRadius(), paint);
            canvas.drawText(Integer.toString(1), rx, ry, p1);

            int u =1;
            int d;
            while (u <20) {
                d=0;
                randX(w);
                randY(h);
                for (int i = 0; i < u; i++) { //цикл для проверки координат
                    if (rx > (xx[i] + 2 * circle.getRadius()) | rx < (xx[i] - 2 * circle.getRadius())
                            | ry > (yy[i] + 2 * circle.getRadius()) | ry < (yy[i] - 2 * circle.getRadius())) {
                        d++;
                    }
                }
                if (d==u){
                    xx[u]=rx;     yy[u]= ry;
                    u++;}
            }
            for (int i = 1; i < 20; i++) {
                canvas.drawCircle(xx[i], yy[i], circle.getRadius(), paint);
                canvas.drawText(Integer.toString(i+1), xx[i], yy[i], p1);
            }
        }
    }

}
