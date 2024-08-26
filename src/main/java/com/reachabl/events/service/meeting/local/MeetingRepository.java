package com.reachabl.events.service.meeting.local;

import com.reachabl.models.meetings.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeetingRepository extends MongoRepository<Meeting, String> {
}
