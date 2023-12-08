package com.example.dependencyInjection.repository;

import com.example.dependencyInjection.model.BorrowInfo;
import org.springframework.data.repository.CrudRepository;

public interface BorrowInfoRepository extends CrudRepository<BorrowInfo, Long> {
}
