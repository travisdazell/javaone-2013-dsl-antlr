package net.travisdazell.javaone.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import net.travisdazell.javaone.GolfSchedulerLexer;
import net.travisdazell.javaone.GolfSchedulerParser;
import net.travisdazell.javaone.model.TeeTime;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

public class GolfScheduleDriver {
	public static void main(String args[]) throws RecognitionException, IOException {
		String filePath = "scripts/test.golf";
		Reader reader = null;
		
		try {
			reader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			System.err.println("Error: Unable to find source code file at " + filePath);
			return;
		}
		
		CharStream charStream = new ANTLRReaderStream(reader);
		GolfSchedulerLexer lexer = new GolfSchedulerLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		GolfSchedulerParser parser = new GolfSchedulerParser(tokenStream);

		TeeTime teeTime = parser.teetime();
		
		System.out.println(teeTime.bookTeeTime());
	}
}
