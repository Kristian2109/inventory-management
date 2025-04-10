package com.fmi.inventory;

import com.fmi.inventory.model.ClubMember;
import com.fmi.inventory.repository.ClubMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner  {
	private ClubMemberRepository memberRepository;
	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
