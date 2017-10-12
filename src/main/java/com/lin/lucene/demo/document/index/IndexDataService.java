package com.lin.lucene.demo.document.index;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.lin.lucene.demo.document.file.Indexer;
import com.lin.lucene.demo.document.file.Searcher;
import com.lin.lucene.demo.param.StudentIndexReq;
import com.lin.lucene.demo.param.StudentIndexResp;
import com.lin.lucene.demo.util.StringUtil;

public class IndexDataService{
    
    public static final String INDEX_FILE = "f:\\luence\\index";
    
    public int insertData(List<StudentIndexReq> list) {
        try {
         return Indexer.insertDoc(INDEX_FILE,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int updateData(List<StudentIndexReq> list) {
        try {
            return Indexer.updateDoc(INDEX_FILE,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int deleteData(long id) {
        try {
            return Indexer.deleteDoc(INDEX_FILE,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<StudentIndexResp> selectData(String filed, String value,boolean explain) {
        return Searcher.query(INDEX_FILE,filed, value,explain);
    }
    
    public List<StudentIndexReq> parseText2Req(String text, int expectedLength) {
        if(StringUtil.isEmpty(text)) {
            return null;
        }
        String[] strArr = text.split("#");
        return Arrays.stream(strArr).map(str->{
            String[] arr = str.split("\\|");
            if(arr.length!=expectedLength) {
                return null;
            }
            return new StudentIndexReq(Long.parseLong(arr[0]),arr[1],arr[2],arr[3]);
        }).filter(s->s!=null).collect(Collectors.toList());
    }

}
