package tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import calc.GeneralCalculation;
import calc.Operation;

public final class TagProcessor {

	public static List<OperationMember> calculate(XMLStreamReader reader, Stack stack, boolean isComplex) {
		int initialDepth = stack.size();
		String name = reader.getLocalName();

		stack.push(name);
		List<OperationMember> members = new ArrayList<OperationMember>();
		boolean isInside = false;
		String numberCandidate = "";
		try {
			while (reader.hasNext() && stack.size() > initialDepth) {
				int event;
				try {
					event = reader.next();
					switch (event) {

					case XMLEvent.CHARACTERS:
					case XMLEvent.CDATA:
						if (isInside) {
							if ("".equals(numberCandidate.trim())) {// if no
																	// candidate
																	// already
																	// set
								numberCandidate = reader.getText();
							}
						}

						break;
					case XMLEvent.START_ELEMENT: {
						isInside = true;
						String tag = reader.getLocalName();

						if (Operation.isOperation(tag)) {
							numberCandidate = String.valueOf(GeneralCalculation.calculate(reader, stack));

						} else {
							numberCandidate = "";
							stack.push(tag);
						}
					}
						break;
					case XMLEvent.END_ELEMENT: {
						String tag = (String) stack.lastElement();
						if (!Operation.isOperation(reader.getLocalName())) {
							if (!"".equals(numberCandidate.trim())) {

								double gottenValue = Float.valueOf(numberCandidate);
								members.add(new OperationMember((String) stack.lastElement(), gottenValue));
								numberCandidate = "";
							}
						} else {
							isInside = false;

						}
						stack.pop();
					}
						break;

					}

				} catch (XMLStreamException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // read next event
			}
		} catch (

		XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}
}
