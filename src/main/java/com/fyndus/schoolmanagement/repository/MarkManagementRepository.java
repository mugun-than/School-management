package com.fyndus.schoolmanagement.repository;

import com.fyndus.schoolmanagement.entity.MarkManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkManagementRepository extends JpaRepository<MarkManagement, Long>{
}
