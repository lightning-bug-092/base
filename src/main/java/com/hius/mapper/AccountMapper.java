package com.hius.mapper;

import com.hius.dao.request.AccountRequest;
import com.hius.entity.Account;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<Account, AccountRequest>{
}
