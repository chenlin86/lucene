package com.lin.lucene.demo.document.file;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class AnalyzerUtil {
    
    public static Analyzer getAnalyzer(){
        // 自定义停词(不需要索引搜索的词)
        List<String> stopWords = new ArrayList<>();
        stopWords.add("于");
        stopWords.add("的");
        CharArraySet cas = new CharArraySet(stopWords,true);
        SmartChineseAnalyzer.getDefaultStopSet().forEach(o->cas.add(o));
        Analyzer analyzer = new SmartChineseAnalyzer(cas);
        return analyzer;
    }
    
    
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = AnalyzerUtil.getAnalyzer();
        String testData = "专注于娱乐，厨艺的中年人。";
        TokenStream ts = analyzer.tokenStream("keyword", new StringReader(testData));
        CharTermAttribute ch =  ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while(ts.incrementToken()) {
            System.out.println(ch.toString());
        }
        analyzer.close();
        ts.end();
        ts.close();
    }
}
