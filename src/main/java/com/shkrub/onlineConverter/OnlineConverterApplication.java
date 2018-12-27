package com.shkrub.onlineConverter;

import com.shkrub.onlineConverter.dbUpdate.FillingTable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class OnlineConverterApplication implements CommandLineRunner{
	private final FillingTable fillingTable;

	public OnlineConverterApplication(FillingTable fillingTable) {
		this.fillingTable = fillingTable;
	}

	public static void main(String[] args) {
//		try {
//			Runtime.getRuntime().exec("src/main/java/com/shkrub/onlineConverter/parser/script/start.sh");
//			TimeUnit.MINUTES.sleep(2);
//		} catch (IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
		SpringApplication.run(OnlineConverterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		fillingTable.save();
		System.out.println("Success!!!");
	}


}
