package in.test;

import java.util.Properties;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Sentiment {

	public String senti(String st) {
		String text = st;
		String sentiment_value = "";
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		CoreDocument doc = new CoreDocument(text);
		pipeline.annotate(doc);
		for (CoreSentence sent : doc.sentences()) {
			sentiment_value = sent.sentiment();
		}
		return sentiment_value;
	}
}
