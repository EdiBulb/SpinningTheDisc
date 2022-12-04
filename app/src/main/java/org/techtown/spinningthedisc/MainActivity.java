package org.techtown.spinningthedisc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new MyGraphicView(this));

        // 원판을 어떻게 만들 것인가?
        // 돌리는 방법
        // 각 섹션을 인식하게 하는 방법
        //인식된 섹션을 텍스트로 출력하는 방법

    }

    private static class MyGraphicView extends View{
        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //페인트 클래스 변수 생성하기
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            //도형의 색을 지정한다.
            paint.setColor(Color.GREEN);

            //선을 그린다.
            canvas.drawLine(20,20,600,20,paint);


            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            canvas.drawLine(20,60,600,60,paint);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            paint.setStyle(Paint.Style.FILL);
            Rect rect1 = new Rect(20,100,20+200, 100+200);
            canvas.drawRect(rect1, paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect rect2 = new Rect(260, 100, 260+200, 100+200);
            canvas.drawRect(rect2,paint);

            RectF rect3 = new RectF(500,100,500+200, 100+200);
            canvas.drawRoundRect(rect3, 40,40,paint);
            canvas.drawCircle(120,440,100,paint);

            paint.setStrokeWidth(10);
            Path path1 = new Path();
            path1.moveTo(20,580);
            path1.lineTo(20+100, 580+100);
            path1.lineTo(20+200,580);
            path1.lineTo(20+300, 580+100);
            path1.lineTo(20+400, 580);
            canvas.drawPath(path1, paint);

            paint.setStrokeWidth(0);
            paint.setTextSize(60);
            canvas.drawText("안드로이드", 20, 780, paint);

//            RectF rect = new RectF();
//            rect.set(200,200,600,600);
//            canvas.drawArc(rect, (270), 290, false, paint);
//
//            rect = new RectF();
//            rect.set(300,700,700,1100);
//            canvas.drawArc(rect, 0, 290, true, paint);
        }
    }
}