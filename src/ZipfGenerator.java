import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ZipfGenerator {
	private Random rnd = new Random(System.currentTimeMillis());
	private int size;
	private double skew;
	private double bottom = 0;

	public ZipfGenerator(int size, double skew) {
		this.size = size;
		this.skew = skew;

		for(int i=1;i < size; i++) {
			this.bottom += (1/Math.pow(i, this.skew));
		}
	}

	// the next() method returns an random rank id.
	// The frequency of returned rank ids are follows Zipf distribution.
	public int next() {
		int rank;
		double friquency = 0;
		double dice;

		rank = rnd.nextInt(size);
		friquency = (1.0d / Math.pow(rank, this.skew)) / this.bottom;
		dice = rnd.nextDouble();

		while(!(dice < friquency)) {
			rank = rnd.nextInt(size);
			friquency = (1.0d / Math.pow(rank, this.skew)) / this.bottom;
			dice = rnd.nextDouble();
		}

		return rank;
	}

	// This method returns a probability that the given rank occurs.
	public double getProbability(int rank) {
		return (1.0d / Math.pow(rank, this.skew)) / this.bottom;
	}

	public String getZipfProductId(int N) {
		int val = 1;
		for (int n = 1; n < N; n++) {
			double sumGetProbability = 0.0;
			for (int i = 1; i <= n; i++) {
				sumGetProbability += getProbability(i);
			}
			if (rnd.nextDouble() < sumGetProbability) {
				val = n;
				break;
			}
		}
		String ret = String.valueOf(val);
		if (ret.length() < 16) {
			StringBuilder zero = new StringBuilder();
			for (int i = ret.length(); i < 16; i++) {
				zero.append(0);
			}
			ret = zero.toString() + ret;
		}
		return ret;
	}

	public void ZipfTest(int Num) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < Num; i++) {
			list.add(i, getZipfProductId(Num));
		}
		Collections.sort(list);
		int count = 1;
		int sum = 0;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i-1).equals(list.get(i))) {
				count++;
			} else {
				System.out.println(list.get(i-1) + " = " + count);
				sum += count;
				count = 1;
			}
		}
		System.out.println("sum = " + sum);
	}

	/*public List<String> makeZipfProductIdList(int Num) {
		List<String> ret = new ArrayList<>();
		for (int i = 0; i < Num; i++) {
			ret.add(i, getZipfProductId(Num));
		}
		return ret;
	}*/
}
