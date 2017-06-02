//2.    Написать совсем небольшой метод, в котором 3 потока построчно пишут данные в файл (штук по 10 записей, с периодом в 20 мс).

package lesson4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Task2 {
    Object mon = new Object();

	void printFile() throws Exception {
		FileWriter fw = new FileWriter("out.txt");
		ExecutorService eS = Executors.newFixedThreadPool(3);
		List<Future> futures = new ArrayList<Future>();
		boolean flag;
		for (int i = 0; i < 3; i++) {
			int finalI = i + 1;
			futures.add(eS.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10; j++)
						try {
							synchronized (mon) {
								fw.write("Thread-" + finalI + " " + j + "\n");
							}
							Thread.currentThread().sleep(20);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
				}
			}));
		}
		eS.shutdown();
		do {
			flag = true;
			for(Future future : futures)
				if (!future.isDone())
					flag = false;
		} while (!flag);
		fw.close();
	}

	public static void main(String[] args) {
        try {
            Task2 t2 = new Task2();
            t2.printFile();
        }
        catch (Exception ex) {}
    }
}