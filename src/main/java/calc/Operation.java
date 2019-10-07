package calc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Operation {

	public static final String ADDITION_TAG = "addition";
	public static final String SUBTRACTION_TAG = "subtraction";
	public static final String MULTIPLICATION_TAG = "multiplication";
	public static final String DIVISION_TAG = "division";

	private static final Set<String> OPERATION_TAGS = new HashSet<>(
			Arrays.asList(ADDITION_TAG, SUBTRACTION_TAG, MULTIPLICATION_TAG, DIVISION_TAG));

	public static boolean isOperation(String tagName) {
		return OPERATION_TAGS.contains(tagName);

	}
}
