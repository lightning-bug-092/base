package com.hius.repository;

import com.hius.dao.db.BaseRepository;
import com.hius.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, String> {
}
