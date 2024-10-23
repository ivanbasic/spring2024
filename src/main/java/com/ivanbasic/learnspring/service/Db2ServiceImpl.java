package com.ivanbasic.learnspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivanbasic.learnspring.db2.model.Table2;
import com.ivanbasic.learnspring.db2.repo.Table2Repo;

@Service
public class Db2ServiceImpl implements Db2Service {

    @Autowired
    Table2Repo  table2Repo;

    @Override
    public long test() {
        table2Repo.deleteAll();
        table2Repo.save( new Table2(2,"2","2",2));
        table2Repo.save( new Table2(3,"3","3",3));
        table2Repo.save( new Table2(4,"4","4",4));
        return table2Repo.count();
    }
}
