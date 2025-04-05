package com.fmi.inventory.repository.inmemory;

import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.repository.ClubMemberRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryClubMemberRepository extends InMemoryCrudRepository<ClubMember>
    implements ClubMemberRepository { }