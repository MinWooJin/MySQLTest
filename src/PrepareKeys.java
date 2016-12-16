import org.apache.commons.lang3.RandomStringUtils;

import java.lang.Math;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public final class PrepareKeys {
	public List<String> seller_id = new ArrayList<>();
	public List<String> date = new ArrayList<>();
	public List<String> summary = new ArrayList<>();
	public List<String> detail = new ArrayList<>();

	public PrepareKeys() {
		System.out.println("Start make strings");
		for (int i = 0; i < 10000; i++) {
			String makeSid = genNumKeyFixedString(16, 10000);
			if (!seller_id.contains(makeSid)) {
				seller_id.add(i, makeSid);
			} else {
				i = i-1;
			}
		}
		System.out.println("Make seller_id end");
		for (int i = 0; i < 50; i++) {
			int standard = i + 1;
			StringBuilder makeDate = new StringBuilder();
			makeDate.append("2016-");
			if (standard < 31) {
				makeDate.append("11-");
				if (standard < 10) {
					makeDate.append(0);
				}
				makeDate.append(String.valueOf(standard));
			} else {
				int nextDay = standard - 30;
				makeDate.append("12-");
				if (standard < 10) {
					makeDate.append(0);
				}
				makeDate.append(String.valueOf(nextDay));
			}
			date.add(i, makeDate.toString());
		}
		System.out.println("Make date end");
		for (int i = 0; i < 10; i++) {
			double randomValue = Math.random();
			int rnd = (int)(randomValue * 64) + 1;
			String makeSum = RandomStringUtils.randomAlphanumeric(rnd);
			if (!summary.contains(makeSum)) {
				summary.add(i, makeSum);
			} else {
				i = i-1;
			}
		}
		System.out.println("Make summary end");
		for (int i = 0; i < 10; i++) {
			double randomValue = Math.random();
			int rnd = (int)(randomValue * 1024);
			String makeDetail = RandomStringUtils.randomAlphanumeric(rnd);
			if (!detail.contains(makeDetail)) {
				detail.add(i, makeDetail);
			} else {
				i = i-1;
			}
		}
		System.out.println("Make detail end");
	}

	public String getPid(int num) {
		String ret = String.valueOf(num);
		if (ret.length() < 16) {
			StringBuilder zero = new StringBuilder();
			for (int i = ret.length(); i < 16; i++) {
				zero.append(0);
			}
			ret = zero.toString() + ret;
		}
		return ret;
	}

	public String getSid() {
		double randomValue = Math.random();
		int rnd = (int)(randomValue * 10000);
		return seller_id.get(rnd);
	}

	public String getDate() {
		double randomValue = Math.random();
		int rnd = (int)(randomValue * 50);
		return date.get(rnd);
	}

	public String getSummary() {
		double randomValue = Math.random();
		int rnd = (int)(randomValue * 10);
		return summary.get(rnd);
	}

	public String getDetail() {
		double randomValue = Math.random();
		int rnd = (int)(randomValue * 10);
		return detail.get(rnd);
	}

	public static String genNumKeyFixedString(int length, int numRange) {
		if (numRange < Integer.MIN_VALUE || numRange > Integer.MAX_VALUE)
			throw new IllegalArgumentException("numRange must be integer value (" + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE + ")");

		if (length < String.valueOf(numRange).length())
			throw new IllegalArgumentException("Length is too short");

		Random rand = new Random();
		String ret = String.valueOf(rand.nextInt(numRange + 1));

		if (ret.length() < length) {
			StringBuilder zero = new StringBuilder();
			for (int i = ret.length(); i < length; i++) {
				zero.append(0);
			}

			ret = zero.toString() + ret;
		}

		return ret;
	}
}
