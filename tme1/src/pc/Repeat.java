package pc;


public class Repeat {
	public static String repeatNaive(char c, int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			s += c;
		}
		return s;
	}

	// Using "new StringBuilder()"
	public static String repeatDefault(char c, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i =0; i < n; i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	// Using "new StringBuilder(n)"
	public static String repeatCapacity(char c, int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i =0; i < n; i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String [] args) {
		int N = 100000;
		System.out.println("Test avec N = " + N);

		{
		long time = System.currentTimeMillis();
		String rep = Repeat.repeatNaive('a', N);		
		long elapsed = System.currentTimeMillis() - time;
		System.out.println("Naive Elapsed time: " + elapsed + "ms");
		}
		
		{
		long time = System.currentTimeMillis();
		String rep = Repeat.repeatDefault('a', N);		
		long elapsed = System.currentTimeMillis() - time;
		System.out.println("Default Elapsed time: " + elapsed + "ms");
		}

		{
		long time = System.currentTimeMillis();
		String rep = Repeat.repeatCapacity('a', N);		
		long elapsed = System.currentTimeMillis() - time;
		System.out.println("Capacity Elapsed time: " + elapsed + "ms");
		}
	}
}
