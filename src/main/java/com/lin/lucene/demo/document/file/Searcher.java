package com.lin.lucene.demo.document.file;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.lin.lucene.demo.param.Student;
import com.lin.lucene.demo.param.StudentIndexResp;

/**
 * 构造查询索引对象
 * @author chenlin
 *
 */
public class Searcher {
    
    /**
     * 读索引对象的获取
     * @param indexDir 索引文件文件
     * @return
     * @throws IOException
     */
    private static IndexSearcher getSearcher(String indexDir) throws IOException {
        // 获取文件对象(不存在则创建)
        Directory directory = FSDirectory.open(Paths.get(indexDir));
        // 打开读索引对象
        IndexReader reader = DirectoryReader.open(directory);
        // 当索引发生变化时，可以获取到新的reader
        // DirectoryReader.openIfChanged((DirectoryReader)reader);
        return new IndexSearcher(reader);
    }
    
    public static List<StudentIndexResp> query(String indexDir,String filed,String value,boolean explian)  {
        List<StudentIndexResp> respList = new ArrayList<StudentIndexResp>();
        try {
            IndexSearcher searcher = getSearcher(indexDir);
            //创建分词器
            Analyzer analyzer = AnalyzerUtil.getAnalyzer();
            // 创建查询解析对象
            //使用analyzer分词器，查询description搜索filed
            QueryParser parser = new QueryParser(filed,analyzer);
            Query query = parser.parse(value);
            
            TopDocs docs = searcher.search(query, 10);
            //查询条件匹配总结果数
            long count = docs.totalHits;
            System.out.println("查询filed域:"+filed+",查询的值："+value+",匹配的记录条数："+count+" ");
            //根据查询条件匹配出的记录
            Arrays.stream(docs.scoreDocs).forEach(sd->{
                try {
                    float score = sd.score;
                    int docId = sd.doc;
                    Document doc = searcher.doc(docId);
                    StudentIndexResp resp = new StudentIndexResp(
                       new Student(Long.parseLong(doc.get("id")),doc.get("name"),doc.get("sex"),doc.get("desciption")),score);
                    if(explian) {
                        resp.setExplanation(searcher.explain(query, 2));
                    }
                    respList.add(resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respList;
    }
    
   
}
