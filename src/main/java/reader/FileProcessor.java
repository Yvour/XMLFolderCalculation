package reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import calc.GeneralCalculation;
import calc.Operation;
import calc.Result;

public class FileProcessor {

	private static void writeResult(Result result, XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("result");
		writer.writeAttribute("id", result.getId());
		writer.writeCharacters(result.getStringValue());
		writer.writeEndElement();
	}

	private static void processInputFile(File file, XMLStreamWriter writer) {

		try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(file.toPath()))) {
			XMLStreamReader reader = processor.getReader();
			Stack<String> tagStack = new Stack<String>();
			while (reader.hasNext()) { // while not end of XML
				int event = reader.next(); // read next event

				if (event == XMLEvent.START_ELEMENT) {

					String id = reader.getAttributeValue(null, "id");

					if (Operation.isOperation(reader.getLocalName())) {
						if (id != null) {
							Result result = new Result(id);

							result.setValue(GeneralCalculation.calculate(reader, tagStack));
							FileProcessor.writeResult(result, writer);
						}

					}
				}

			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void processFile(File file, String outputDirectory) {
		Path outputPath = Paths.get(outputDirectory + "\\" + file.getName());

		Result result = null;
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = null;
		String fileName = file.getName();
		String filePath = outputDirectory + "\\" + fileName.substring(0, fileName.length() - 4) + "_result.xml";
		File outputFile = new File(filePath);
		outputFile.getParentFile().mkdirs();

		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			writer = factory.createXMLStreamWriter(fileWriter);
			writer.writeStartDocument();
			writer.writeStartElement("expressions");
			processInputFile(file, writer);

			writer.writeEndElement();
			writer.writeEndDocument();

			writer.flush();
			writer.close();
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
		}

	}
}
