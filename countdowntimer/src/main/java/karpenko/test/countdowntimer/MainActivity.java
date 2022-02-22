package karpenko.test.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvTimer;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            MyTimer timer = new MyTimer(5000,1000);
            timer.start();
        });



    }

    private class MyTimer extends CountDownTimer{

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            int tick = (int) (l / 1000);
            if (tick<10) tvTimer.setText("00:0" + tick);
            else tvTimer.setText("00:" + tick);
        }

        @Override
        public void onFinish() {
            Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {0, 2000,1000};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createWaveform(pattern,1));
            }else v.vibrate(pattern,1);
        }
    }

}