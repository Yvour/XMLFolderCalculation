package tags;

import java.util.List;

public class Multiplication {
	public static double getResult(List<OperationMember> members) {
		double result = 1;
		for (OperationMember member : members) {
			if ("factor".equals(member.getName())) {
				result *= member.getValue();
			}

		}
		return result;
	}

}
