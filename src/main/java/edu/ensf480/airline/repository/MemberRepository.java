package edu.ensf480.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensf480.airline.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    List<Member> findAll();
    Optional<Member> findByEmail(String email);
    boolean existsByMembershipNum(String membershipNum);
}
