package com.brainridge.avbank.repository;

import com.brainridge.avbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//    private final Map<UUID, Account> idVsAccountMap = new ConcurrentHashMap<>();
//
//    public Account save(Account account) {
//        idVsAccountMap.put(account.getAccountId(), account);
//        return account;
//    }
//
//    public Optional<Account> findById(UUID accountId) {
//        return Optional.ofNullable(idVsAccountMap.get(accountId));
//    }

    Optional<Account> findByEmail(String email);
    Optional<Account> findById(Long id);
}
