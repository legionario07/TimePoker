package timepoker.com.br.timepoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import timepoker.com.br.timepoker.database.TimePokerBD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtTempo;
    private ImageButton btnPauseOrCancel;
    private MyChronometerDown timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTempo = findViewById(R.id.txtTempo);
        btnPauseOrCancel = findViewById(R.id.btnPauseOrStart);
        btnPauseOrCancel.setOnClickListener(this);

        Intent i = new Intent(this, BlindsActivity.class);
        startActivity(i);

        TimePokerBD t = new TimePokerBD(this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null) {
            timer.cancel();
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnPauseOrStart:
                if (timer == null) {
                    timer = new MyChronometerDown(this, txtTempo, 60* 10* 1000, 1000);
                    timer.start();
                    btnPauseOrCancel.setImageResource(R.drawable.ic_pause_circle_filled_black_78dp);

                    return;
                }

                if (!timer.isPaused()) {
                    timer.pause();
                    btnPauseOrCancel.setImageResource(R.drawable.ic_play_circle_filled_black_78dp);
                } else if (timer.isPaused()) {
                    long timerReimaning = timer.getTimerRemining();
                    long interval = timer.getInterval();
                    timer = null;
                    timer = new MyChronometerDown(this, txtTempo, timerReimaning, interval);
                    timer.start();
                    btnPauseOrCancel.setImageResource(R.drawable.ic_pause_circle_filled_black_78dp);
                }
                break;
        }

    }
}
