package team.exm.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeTimerService extends Thread {

    private static Logger log = LoggerFactory.getLogger(CodeTimerService.class);

    private int id;/*需要定时删除的code数据的主键*/
    private boolean stop = false;/*调用函数传参设置true时线程安全退出*/
    @Autowired
    CodeService cs;

    public void setId(int id) {
        this.id = id;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        int count = 120;
        while (count-- != 0) {
            //log.info("-------------------" + String.valueOf(count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stop) {
                log.info("线程以正常退出");
                return;
            }
        }
        cs.deleteCode(id);
        log.info("验证码成功删除");
    }
}

