package com.hust.repo;

import com.hust.model.StatisticalOutput;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<StatisticalOutput> getCount(){
        String sql = "SELECT MONTH(u.createdDate), count(u.id) from UserEntity u group by MONTH(u.createdDate)";

        Query query = entityManager.createQuery(sql);
        List<Object[]> result = query.getResultList();
        List<StatisticalOutput> output = new ArrayList<>();
        for(Object[] tuple: result){
            long month = (int) tuple[0];
            long count = (long) tuple[1];
            output.add(new StatisticalOutput(month, count));
        }
        return output;
    }

    public List<StatisticalOutput> getCountAttend(){
        String sql = "SELECT MONTH(a.attendTime), count(a) from AttendEntity a group by MONTH(a.attendTime)";

        Query query = entityManager.createQuery(sql);
        List<Object[]> result = query.getResultList();
        List<StatisticalOutput> output = new ArrayList<>();
        for(Object[] tuple: result){
            long month = (int) tuple[0];
            long count = (long) tuple[1];
            output.add(new StatisticalOutput(month, count));
        }
        return output;
    }
}
