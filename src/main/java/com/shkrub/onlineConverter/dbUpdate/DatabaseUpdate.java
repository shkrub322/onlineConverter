package com.shkrub.onlineConverter.dbUpdate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class DatabaseUpdate {
    private final FillingTable fillingTable;

    public DatabaseUpdate(FillingTable fillingTable) {
        this.fillingTable = fillingTable;
    }

    @Scheduled(fixedRate = 1800000)
    public void update(){
        try {
            Runtime.getRuntime().exec("src/main/java/com/shkrub/onlineConverter/parser/script/start.sh");
            TimeUnit.MINUTES.sleep(3);
            fillingTable.update();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("update");
    }
}
