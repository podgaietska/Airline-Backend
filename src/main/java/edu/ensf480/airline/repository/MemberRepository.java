package edu.ensf480.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensf480.airline.model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByEmail(String email);
    boolean existsByMembershipNum(String membershipNum);

}
