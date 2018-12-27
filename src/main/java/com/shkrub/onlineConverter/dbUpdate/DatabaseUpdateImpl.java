package com.shkrub.onlineConverter.dbUpdate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class DatabaseUpdateImpl implements DatabaseUpdate {
    private final FillingTable fillingTable;

    public DatabaseUpdateImpl(FillingTable fillingTable) {
        this.fillingTable = fillingTable;
    }

    @Override
    public void save() {
        fillingTable.save();
    }

    @Override
    @Scheduled(fixedRate = 1800000)
    public void update() {
//        try {
//            Runtime.getRuntime().exec("src/main/java/com/shkrub/onlineConverter/parser/script/start.sh");
//            TimeUnit.MINUTES.sleep(2);
//            fillingTable.update();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("update");
    }
}
