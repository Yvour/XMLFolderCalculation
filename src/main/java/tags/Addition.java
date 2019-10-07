package tags;

import java.util.List;

public class Addition {
	public static double getResult(List<OperationMember> members) {
		double result = 0;
		for (OperationMember member : members) {
			if ("item".equals(member.getName())) {
				result += member.getValue();
			}
		}
		return result;
	}

}
