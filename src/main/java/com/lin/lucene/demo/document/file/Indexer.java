package com.lin.lucene.demo.document.file;

import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.lin.lucene.demo.param.StudentIndexReq;

/**
 * 创建lucene索引的类
 * @author chenlin
 */
public class Indexer {

    /**
     * 创建文件对象,分词器，设置其中并返回写索引实例
     * @param indexDir 索引输出的地址
     * @throws Exception
     */
    private static IndexWriter getIndexWriter(String indexDir) throws Exception {
        // 通过文件路径读取文件
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // 中文分词器
        Analyzer analyzer = AnalyzerUtil.getAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setInfoStream(System.out);
        iwc.setUseCompoundFile(false);
        return new IndexWriter(dir, iwc);
    }

    /**
     * 关闭写索引实例
     * @param writer
     * @throws Exception
     */
    public static void close(IndexWriter writer) throws Exception {
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * 向索引中插入数据
     * @param indexDir
     * @param list
     * @return
     * @throws Exception
     */
    public static int insertDoc(String indexDir, List<StudentIndexReq> list) throws Exception {
        IndexWriter writer = getIndexWriter(indexDir);
        // 遍历所有的文件
        list.forEach(s -> {
            Thread t = new Thread(()->{
                try {
                    writer.addDocument(getDocument(s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        });
        int num = writer.numDocs();
        Thread.currentThread().join();
        writer.close();
        return num;
    }

    /**
     * 更新索引中的doc
     * @param indexDir
     * @param list
     * @return
     * @throws Exception
     */
    public static int updateDoc(String indexDir, List<StudentIndexReq> list) throws Exception {
        IndexWriter writer = getIndexWriter(indexDir);
        // 遍历需要更新的文档
        list.forEach(s -> {
            try {
                writer.updateDocument(new Term("id", String.valueOf(s.getId())), getDocument(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        int num = writer.numDocs();
        writer.close();
        return num;
    }

    /**
     * 删除索引中的doc
     * @param indexDir
     * @param id
     * @return
     * @throws Exception
     */
    public static int deleteDoc(String indexDir, long id) throws Exception {
        IndexWriter writer = getIndexWriter(indexDir);
        writer.deleteDocuments(new Term("id", String.valueOf(id)));
        int num = writer.numDocs();
        writer.close();
        return num;
    }

    /**
     * 获取文档，即将分档设置为字段
     * @param file
     * @return
     * @throws Exception
     */
    private static Document getDocument(StudentIndexReq student) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("id", String.valueOf(student.getId()), Field.Store.YES));
        doc.add(new TextField("name", student.getName(), Field.Store.YES));
        doc.add(new TextField("sex", student.getSex(), Field.Store.YES));
        doc.add(new TextField("desciption", student.getDesciption(), Field.Store.YES));
        return doc;
    }

}
