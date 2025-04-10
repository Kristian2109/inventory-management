package com.fmi.inventory.controller;

import com.fmi.inventory.dto.CreateClubMemberDto;
import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.repository.ClubMemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class ClubMemberController {

    private final ClubMemberRepository repository;

    public ClubMemberController(ClubMemberRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<ClubMember> createMember(@RequestBody CreateClubMemberDto dto) {
        ClubMember member = new ClubMember(dto.firstName(), dto.lastName(), dto.email());
        ClubMember saved = repository.save(member);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ClubMember>> getAllMembers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMember> getMemberById(@PathVariable String id) {
        return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable String id, @RequestBody CreateClubMemberDto dto) {
        if (repository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClubMember updated = new ClubMember(id, dto.firstName(), dto.lastName(), dto.email());
        repository.update(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}