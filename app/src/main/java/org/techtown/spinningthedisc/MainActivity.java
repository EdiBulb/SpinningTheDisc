package org.techtown.spinningthedisc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private LuckyWheel luckyWheel;
    List<WheelItem> wheelItems;
    String point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //변수에 담기기
       luckyWheel = findViewById(R.id.luck_wheel);

       //점수판 데이터 생성
        generateWheelItems();

        //점수판 타겟 정해지면 이벤트 발생
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                //아이템 변수에 담기
                WheelItem wheelItem = wheelItems.get(Integer.parseInt(point)-1);

                //아이템 텍스트 변수에 담기
                String money = wheelItem.text;

                //메시지
                Toast.makeText(MainActivity.this, money, Toast.LENGTH_SHORT).show();
            }
        });

        //시작버튼
        Button start = findViewById(R.id.spin_btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                point = String.valueOf(random.nextInt(6)+1);
                luckyWheel.rotateWheelTo(Integer.parseInt(point));
            }
        });

//        setContentView(new MyGraphicView(this));

        // 원판을 어떻게 만들 것인가?
        // 돌리는 방법
        // 각 섹션을 인식하게 하는 방법
        //인식된 섹션을 텍스트로 출력하는 방법

    }

    private void generateWheelItems() {
        wheelItems = new ArrayList<>();
        Drawable d = getResources().getDrawable(R.drawable.ic_money, null);
        Bitmap bitmap = drawableToBitmap(d);
        wheelItems.add(new WheelItem(Color.parseColor("#F44336"), bitmap, "100$"));
        wheelItems.add(new WheelItem(Color.parseColor("#E91E63"), bitmap, "200$"));
        wheelItems.add(new WheelItem(Color.parseColor("#9C27B0"), bitmap, "300$"));
        wheelItems.add(new WheelItem(Color.parseColor("#3F51B5"), bitmap, "400$"));
        wheelItems.add(new WheelItem(Color.parseColor("#1E88E5"), bitmap, "500$"));
        wheelItems.add(new WheelItem(Color.parseColor("#009688"), bitmap, "600$"));

        luckyWheel.addWheelItems(wheelItems);
    }

    public static Bitmap drawableToBitmap(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;

//    private static class MyGraphicView extends View {



//        private Paint myPaint;
//        int viewWidth = 0;
//        int viewHeight = 0;
//        int side = 0;
//
//
//        public MyGraphicView(Context context) {
//            super(context);
//            myPaint = new Paint();
//
//        }
//
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//
//            viewWidth = this.getMeasuredWidth();
//            viewHeight = this.getMeasuredHeight();
//
//
//            //색 채우기
//            myPaint.setStyle(Paint.Style.STROKE);
//
//            //빨간색
//           myPaint.setColor(Color.BLACK);
//           myPaint.setStrokeWidth(10);
//
//            if (viewWidth > viewHeight) {
//                side = viewHeight * 4 / 5;
//            } else {
//                side = viewWidth * 4 / 5;
//            }
//
//            //식작 각도
//            float startAngle = 0;
//            //45도 간격
//            float sweepAngle = 45;
//
//            for(int i=0;i<8;i++){
//                canvas.drawArc((viewWidth / 2) - (side / 2), (viewHeight / 2) - (side / 2), (viewWidth / 2) + (side / 2), (viewHeight / 2) + (side / 2), startAngle, sweepAngle, true, myPaint);
////                canvas.drawTextOnPath("A",Path, 20, 20, myPaint);
//                //시작 각도 변경
//                startAngle = startAngle + sweepAngle;
//            }
//        }

    }
}