package com.reachabl.events.service.meeting;

import com.reachabl.models.meetings.Meeting;

import java.util.List;

public interface MeetingService {

   void saveMeeting(Meeting meeting);

   List<Meeting> getUserMeetings();
}
