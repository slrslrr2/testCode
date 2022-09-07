package com.test.code.organize.inflearnthejavatest.study;

import com.test.code.organize.inflearnthejavatest.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
