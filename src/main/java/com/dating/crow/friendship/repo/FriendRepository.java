package com.dating.crow.friendship.repo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dating.crow.friendship.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, UUID>{

}
