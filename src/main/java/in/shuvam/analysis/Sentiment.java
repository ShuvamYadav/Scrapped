package in.shuvam.analysis;

import java.util.HashMap;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class Sentiment {
	Properties props;
	StanfordCoreNLP pipeline;
	String sentiment_value;
	Annotation annotation;
	HashMap<String, Integer> map=new HashMap<>();
	Sentiment(){
		props=new Properties();
		props.setProperty("annotators", "tokenize,ssplit,parse,sentiment");
		pipeline= new StanfordCoreNLP(props);
		map.put("Positive",0);
		map.put("Very positive",0);
		map.put("Very negative",0);
		map.put("Negative",0);
		map.put("Neutral",0);
	}
	public String senti(String st) {
		String text=st;
		sentiment_value = "";
		annotation= pipeline.process(text);
		for(CoreMap sentence:annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
			sentiment_value=sentence.get(SentimentCoreAnnotations.SentimentClass.class);
		}
		map.put(sentiment_value,map.get(sentiment_value)+1);
		return sentiment_value;
	}
}
