package com.paytm.assignment.repository;

import com.paytm.assignment.model.TopStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopStoryRepository extends CrudRepository<TopStory, Long> {
}
