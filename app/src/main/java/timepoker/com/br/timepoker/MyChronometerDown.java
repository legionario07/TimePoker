package timepoker.com.br.timepoker;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by PauLinHo on 11/03/2018.
 */

public class MyChronometerDown extends CountDownTimer {

    private long timerRemining;
    private long tempoInicial = 0;
    private TextView tv;
    private Context context;
    private boolean isPaused = false;
    private boolean isActive = true;
    private long interval;


    public MyChronometerDown(Context context, TextView tv, long timeOnFuture, long interval){
        super(timeOnFuture, interval);
        tempoInicial = timeOnFuture;
        this.setTimerRemining(timeOnFuture);
        this.setInterval(interval);
        this.context = context;
        this.tv = tv;

    }

    @Override
    public void onTick(long millisUntilFinished) {


        if(isPaused()){
            millisUntilFinished = getTimerRemining();
            isPaused= false;
        }

        setTimerRemining(millisUntilFinished);

        tv.setText(getCorrectTimer(true, millisUntilFinished)+":"+getCorrectTimer(false, millisUntilFinished));
    }

    @Override
    public void onFinish() {
        setTimerRemining(getTimerRemining() - getTimerRemining());

        tv.setText(getCorrectTimer(true, getTimerRemining())+":"+getCorrectTimer(false, getTimerRemining()));

        new MyChronometerDown(context, tv, tempoInicial , interval).start();
    }

    private String getCorrectTimer(boolean isMinute, long millisUntilFinished){

        String aux;
        int constCalendar = isMinute ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);

        aux = c.get(constCalendar) < 10 ? "0"+c.get(constCalendar) : ""+c.get(constCalendar);

        return(aux);
    }

    public boolean isActive(){
        return isActive;
    }

    public void pause(){

        this.cancel();
        isPaused = true;
    }



    public boolean isPaused(){
        return isPaused;
    }


    public long getTimerRemining() {
        return timerRemining;
    }

    public void setTimerRemining(long timerRemining) {
        this.timerRemining = timerRemining;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
