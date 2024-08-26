package com.reachabl.events.service.meeting.local;

import com.reachabl.events.service.meeting.MeetingService;
import com.reachabl.models.meetings.Meeting;

import java.util.List;

public class DefaultMeetingService implements MeetingService {
    private final MeetingRepository meetingRepository;

    public DefaultMeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public void saveMeeting(Meeting meeting) {
        if (meeting.getReminder() != null){
            ///TODO SETUP SCHEDULER
        }

       meetingRepository.save(m)
    }

    @Override
    public List<Meeting> getUserMeetings() {
        return List.of();
    }
}
