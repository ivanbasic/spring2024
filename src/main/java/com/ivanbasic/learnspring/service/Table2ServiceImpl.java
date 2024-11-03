package com.ivanbasic.learnspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ivanbasic.learnspring.model.db2.Table2;
import com.ivanbasic.learnspring.repository.db2.Table2Repo;

@Service
public class Table2ServiceImpl implements Table2Service {

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
