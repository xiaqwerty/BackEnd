package com.example.backend.dao;

import com.example.backend.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit,Long>
{
    Commit findById(long id);
}
