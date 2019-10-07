package tags;

import java.util.Stack;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import calc.GeneralCalculation;

public class TagValueGetter {

	public static double getTagValue(XMLStreamReader reader, boolean isComplex, Stack stack)

			throws XMLStreamException {
		// if (!isComplex) {
		// return Float.valueOf(reader.getElementText());
		// } else

		{
			{
				try {
					return Float.valueOf(reader.getElementText());
				} catch (Exception e) {

					return GeneralCalculation.calculate(reader, stack);
				}
			}
		}
	}
}
