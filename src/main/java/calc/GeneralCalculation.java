package calc;

import java.util.List;
import java.util.Stack;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import tags.Addition;
import tags.Division;
import tags.Multiplication;
import tags.OperationMember;
import tags.Subtraction;
import tags.TagProcessor;

public class GeneralCalculation {
	public static double calculate(XMLStreamReader reader, Stack stack) throws XMLStreamException {

		String operation = reader.getLocalName();
		boolean isComplex = "true".equals(reader.getAttributeValue(null, "complex"));
		int initialStackSize = stack.size();
		List<OperationMember> members = TagProcessor.calculate(reader, stack, isComplex);
		double result = 0;

		if (stack.size() != initialStackSize) {
			throw new Error("Stack overflow");
		}
		if (Operation.isOperation(operation)) {

			switch (operation) {
			case Operation.SUBTRACTION_TAG: {

				result = Subtraction.getResult(members);
				break;
			}

			case Operation.ADDITION_TAG: {
				result = Addition.getResult(members);
				break;
			}
			case Operation.DIVISION_TAG: {
				result = Division.getResult(members);
				break;
			}
			case Operation.MULTIPLICATION_TAG: {
				result = Multiplication.getResult(members);
				break;
			}
			default:
				throw new Error("Wrongly praced tag <" + operation + ">");
			}

		} else {
			throw new IllegalArgumentException("<" + operation + "> is not correct tag");
		}

		return result;
	}
}
