package com.lzy.j2ee.server.impl.local;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.local.IStatelessSessionTimerServiceLocal;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzy on 2017/8/20.
 */
@Stateless(name = Constant.StatelessSessionTimerServiceLocal)
public class StatelessSessionTimerServiceLocal implements IStatelessSessionTimerServiceLocal {

    @Resource
    private TimerService timerService;

    private int count = 0;

    @Override
    public String createTimer() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = simpleDateFormat.format(new Date());

        try {
            /**
             * 3s执行一次
             */
            long intervalDuration = 3000;
            String desc = "Test EJB Timer";
            timerService.createTimer(simpleDateFormat.parse(currentDate),
                    intervalDuration, desc);

            return "Created EJB Timer Success！";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Created EJB Timer Faild！";
        }
    }

    @Timeout
    public void run(Timer timer){
        if (count >= 10){
            /**
             * 取消定时任务
             */
            timer.cancel();
        }else {
            System.out.println("execute run.......");
            count++;
        }
    }
}
