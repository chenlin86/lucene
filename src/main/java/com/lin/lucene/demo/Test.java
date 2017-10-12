package com.lin.lucene.demo;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.lin.lucene.demo.document.file.AnalyzerUtil;

public class Test {

    public static void main(String[] args) throws Exception {
        String indexDir = "f:\\luence\\index";
        // 通过文件路径读取文件
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // 中文分词器
        Analyzer analyzer = AnalyzerUtil.getAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
//        iwc.setInfoStream(System.out);
//        iwc.setUseCompoundFile(true);
        IndexWriter indexWrite= new IndexWriter(dir, iwc);
        
        Document doc = new Document();
//        doc.add(new TextField("desciption1", "中国影帝，小时候在河南少林寺，之后来北京当群众演员，拍电影比较牛逼", Field.Store.YES));  
//        doc.add(new TextField("desciption2", "​七集政论专题片《不忘初心继续前进》第七集《永立潮头》10月11日20点在中央电视台综合频道首播。" + 
//                "2012年12月，习近平总书记首次离京考察，不清场、不封路，带头执行八项规定。深圳市民：“因为很多人都不知道习主席会来，所以到山顶上好多人还在那儿健身呢。" + 
//                "深圳市民：“绝对是没有安检，真是肯定是没有安检的。这不仅让许多群众倍感意外和惊喜，更令全党上下深感震撼、深受触动。习近平总书记："
//                + "“说到的就要做到，承诺的就要兑现。中央政治局同志，从我本人做起。作风建设，永远在路上。理想信念是精神上的钙。要把纪律和规矩挺在前面。", Field.Store.YES));   
//        doc.add(new TextField("desciption3", "“祖国越来越强大，人民生活质量一天比一天好”“政府为老百姓办了很多实事”。10月2日，在“砥砺奋进的五年”大型成就展上，"
//                + "人们纷纷走进“以人民为中心增进群众获得感”展区，一边参观，一边道出心中的获得感。", Field.Store.YES));  
//        doc.add(new TextField("desciption4", "​“安得广厦千万间，大庇天下寒士俱欢颜。这是千百年来一代代中国人的安居梦。”正如展区宣传片所说，党的十八大以来，"
//                + "各地区、各有关部门把住房保障工作放在经济社会发展的突出位置，帮助近8000万住房困难群众圆了安居梦。", Field.Store.YES));   
//        doc.add(new TextField("desciption5", "“今年房子问题解决了，心情特别舒畅。”参观者李阿姨说，为照顾外孙，老两口几年前来到北京，在女儿家里住。"
//                + "“经济适用房买在良乡，价格合适、交通便利。”李阿姨的老伴补充道。", Field.Store.YES));  
//        doc.add(new TextField("desciption6", "​5年来，党中央不仅让百姓住有所居，更要病有所医。“正在推行的医保异地结算政策解决了我看病报销的大问题。”"
//                + "退休后就来到北京的武阿姨医保关系在老家晋城，以往每次在北京住院后都要坐10多个小时火车回去报销。“以后，我在北京能直接报销了，实在太好了。”武阿姨激动地说。", Field.Store.YES));   
        doc.add(new TextField("desciption6", 
        "2012年至2016年，我国高等教育入学率由30%提高到42.7%，教育经费支出从23148亿元增长到31373亿元，教育普及水平进一步提高。"
                + "“这些年，人们受教育的机会确实在不断增加。”看着展板上的数字，现场一位大学老师不住点头。此外，“一考定终身”的高考制度被打破，综合评价、多元录取机制将为我国发展培育各类人才。"
                + "“上高中时，学校里有许多兴趣班和社团活动。”上大一的张同学说：“我高一时参加了一个化学科研项目。老师带着我们搜集资料做实验，让我看到更加广阔的化学世界。”", Field.Store.YES));
        indexWrite.updateDocument(null,doc);
        indexWrite.close();
    }
    
}
