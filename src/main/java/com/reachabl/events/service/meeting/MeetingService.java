package com.reachabl.events.service.meeting;

import com.reachabl.models.meetings.Meeting;

import java.util.List;

public interface MeetingService {

   Meeting saveMeeting(Meeting meeting);

   List<Meeting> getUserMeetings();

   boolean deleteMeeting(String meetingId);
}
