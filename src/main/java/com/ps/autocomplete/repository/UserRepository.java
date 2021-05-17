package com.ps.autocomplete.repository;

import com.ps.autocomplete.model.User;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {
    List<User> findAll();

    List<User> search(QueryBuilder query);
}
