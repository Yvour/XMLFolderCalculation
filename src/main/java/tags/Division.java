package tags;

import java.util.List;

public class Division {
	public static double getResult(List<OperationMember> members) {
		double result = 1;
		for (OperationMember member : members) {
			if ("dividend".equals(member.getName())) {
				result *= member.getValue();
			}
			if ("divisor".equals(member.getName())) {
				result /= member.getValue();
			}
		}
		return result;
	}

}
