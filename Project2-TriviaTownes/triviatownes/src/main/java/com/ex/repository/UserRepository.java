package com.ex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.data.HighScorePlayerBean;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<HighScorePlayerBean, Integer> {

	public List<HighScorePlayerBean> findAll();
	
	@SuppressWarnings("unchecked")
	public HighScorePlayerBean save(HighScorePlayerBean persisted);
	
}
