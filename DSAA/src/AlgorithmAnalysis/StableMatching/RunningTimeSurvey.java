package AlgorithmAnalysis.StableMatching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import java.io.File;
import java.lang.reflect.Method;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class RunningTimeSurvey {
	// task name function name run times upper
	static String[][] taskList = { { "LinearTimeTest", "linearTime", "100000000" },
			{ "LinearTimeTest", "linearTimeCollections", "100000000" },
			/* {"QuadraticTimeTest", "xxxxx", "10000"} */ };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		try {
			File xlsFile = new File("RunningTimeSurvey.xls");
			// Create a workbook
			WritableWorkbook workbook;
			workbook = Workbook.createWorkbook(xlsFile);

			// Create a worksheet
			WritableSheet sheet = workbook.createSheet("RunningTime", 0);
			// the first row
			for (int j = 1, n = 1; j <= 8; j++) {
				n = 10 * n;
				sheet.addCell(new Label(j + 1, 0, "n = " + n));
			}
			for (int i = 0; i < taskList.length; i++) {
				String[] taskInfo = taskList[i];

				Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
				Method method = me.getMethod(taskInfo[1], int.class);
				int upper = Integer.parseInt(taskInfo[2]);
				sheet.addCell(new Label(0, i + 1, taskInfo[0]));
				sheet.addCell(new Label(1, i + 1, taskInfo[1]));

				for (int j = 1, n = 1; Math.pow(10, j) <= upper; j++) {
					n = 10 * n;
					Long time = (Long) method.invoke(null, n);
					// 向工作表中添加数据
					sheet.addCell(new Label(j + 1, i + 1, time.toString()));
				}

			}
			workbook.write();
			workbook.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static long linearTimeCollections(int n) {
		ArrayList<Long> arrayList = new ArrayList<Long>(n);
		generateArrayList(n, arrayList);
		long timeStart = System.currentTimeMillis();
		getMax(n, arrayList);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static long linearTime(int n) {
		long[] list = new long[n];
		generateList(n, list);
		long timeStart = System.currentTimeMillis();
		getMax(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}
	public static long xxxxx(int n) {
		//to generate you test input data
		long timeStart = System.currentTimeMillis();
		//to write a algorithm
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static long getMax(long n, long[] list) {
		long max = list[0];
		for (int i = 1; i < n; i++) {
			if (list[i] > max) {
				max = list[i];
			}
		}
		return max;
	}

	public static long quatraticTime(int n) {
		long[] list = new long[n];
		generateList(n, list);
		long timeStart = System.currentTimeMillis();
		getMax(n, list);
		long timeEnd = System.currentTimeMillis();
		long timeCost = timeEnd - timeStart;
		return timeCost;
	}

	public static void generateList(int n, long[] list) {
		for (int i = 0; i < n; i++) {
			list[i] = i;
		}
		// shuffle
		Random rnd = new Random();
		for (int i = list.length; i > 1; i--) {
			int j = rnd.nextInt(i);
			long temp = list[j];
			list[j] = list[i - 1];
			list[i - 1] = temp;
		}
	}

	public static void generateArrayList(int n, ArrayList<Long> arrayList) {
		for (long i = 0; i < n; i++) {
			arrayList.add(i);
		}
		// shuffle
		Collections.shuffle(arrayList);
	}

	public static long getMax(long n, ArrayList<Long> arrayList) {
		long max = arrayList.get(0);
		for (int i = 1; i < n; i++) {
			if (arrayList.get(i) > max) {
				max = arrayList.get(i);
			}
		}
		return max;
	}

}
