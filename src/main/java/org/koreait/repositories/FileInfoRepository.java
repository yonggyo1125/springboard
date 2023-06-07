package org.koreait.repositories;

import org.koreait.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QuerydslPredicateExecutor<FileInfo> {
}
