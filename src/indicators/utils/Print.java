package indicators.utils;

public class Print {
	public static void out(Object s) {
		System.out.println(s);
	}
	public static void out(String s, Object ... params) {
		out(String.format(s,params));
	}
	public static long milli() {
		return System.currentTimeMillis();
	}
}