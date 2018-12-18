package com.shkrub.onlineConverter.repositories;

import com.shkrub.onlineConverter.entities.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {
}
