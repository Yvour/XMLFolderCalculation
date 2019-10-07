package tags;

import java.util.List;

public class Subtraction {

	public static double getResult(List<OperationMember> members) {
		double result = 0;
		for (OperationMember member : members) {
			if ("minuend".equals(member.getName())) {
				result += member.getValue();
			}
			if ("subtrahend".equals(member.getName())) {
				result -= member.getValue();
			}
		}
		return result;
	}

}
