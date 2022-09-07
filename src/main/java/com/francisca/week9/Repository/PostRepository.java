package com.francisca.week9.Repository;

import com.francisca.week9.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
List<Post> findByTitleContainingIgnoreCase(String keyword);

}
