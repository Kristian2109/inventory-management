package com.fmi.inventory.repository.inmemory;

import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.repository.ClubMemberRepository;
import com.fmi.inventory.repository.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryClubMemberRepository extends InMemoryCrudRepository<ClubMember>
    implements ClubMemberRepository { }