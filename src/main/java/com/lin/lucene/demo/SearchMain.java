package com.lin.lucene.demo;

import java.util.List;

import com.lin.lucene.demo.document.index.IndexDataService;
import com.lin.lucene.demo.param.StudentIndexReq;

public class SearchMain {

    public static void main(String[] args) throws Exception{
        IndexDataService service = new IndexDataService();
        // insert
        int expectedLength = 4;
        String text = "1|王者归来|男|专注于厨艺，棋牌的年轻人。#2|王者荣耀 |女|专注于娱乐，游戏，coding的年轻人。#3|王者传奇|女|专注于娱乐，厨艺的中年人。";
        List<StudentIndexReq> list = service.parseText2Req(text, expectedLength);
        int numInserted = service.insertData(list);
        System.out.println("创建索引文档："+numInserted+"个 ");
        
        // select
//        boolean explian = true;
//        service.selectData("desciption", "娱乐,coding",explian).forEach(s->System.out.println(s));
        // update 
//        int expectedLength = 4;
//        String textUpdate = "1|王者归来1232|男|专注于厨艺，棋牌的年轻人。";
//        List<StudentIndexReq> updateList = service.parseText2Req(textUpdate, expectedLength);
//        int numUpdated = service.updateData(updateList);
//        System.out.println("更新索引文档："+numUpdated+"个 ");
        // delete
//        long id = 1;
//        int numDeleted = service.deleteData(id);
//        System.out.println("删除索引文档："+numDeleted+"个 ");
        
    }
    
}
