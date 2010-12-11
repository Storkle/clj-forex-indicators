package indicators.core;



public class Check {
	static String defaultAssertionError = "assertion failed: invalid indicator parameter(s)";

	static class InvalidIndicatorParameter extends RuntimeException {
		public InvalidIndicatorParameter(String string) {
			// TODO Auto-generated constructor stub
			super(string);
		}
	}
	
	public static void is(boolean result, String s, Object... params) {
		if (result == false) {
			String errMessage = defaultAssertionError;
			try {
				errMessage = String.format(s, params);
			} catch (RuntimeException e) {
				errMessage = defaultAssertionError;
				System.out
						.println("Warning: when calling Check.is(...) and formatting error string, error was thrown: "
								+ e.getMessage());
			}
			if (errMessage == "") {
				errMessage = defaultAssertionError;
			}
			throw new InvalidIndicatorParameter(errMessage);
		}
	}
}