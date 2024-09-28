package com.reachabl.events.service.meeting.local;

import com.reachabl.models.meetings.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MeetingRepository extends MongoRepository<Meeting, String> {

    @Query("{ $or: [ { 'creator.$id': ObjectId(?0) }, { 'participants.$id': ObjectId(?0) } ] }")
    List<Meeting> findByParticipantOrCreatorId(String userId);
}
